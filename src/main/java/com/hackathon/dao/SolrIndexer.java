package com.hackathon.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.hackathon.base.Constant;
import com.hackathon.util.ConvertUtil;
import com.hackathon.util.IndexUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class SolrIndexer {
	private static final Logger LOG = Logger.getLogger(SolrIndexer.class);

	private DBCursor m_cursor;

	private int m_total;
	private int m_current = 0;
	private int m_convert = 0;
	private int m_succeed = 0;
	private int m_failed = 0;
	public Date START_INDEXING;
	public Date END_INDEXING = null;

	private BlockingQueue<SolrInputDocument> m_solr_queue;
	private BlockingQueue<DBObject> m_mongo_queue;
	private CountDownLatch m_producerSignal;
	private CountDownLatch m_convertSignal;
	private CountDownLatch m_consumerSignal;

	private void startIndex() {
		m_total = 0;

		m_cursor = IndexUtil.getPatentCollection().find()
				.batchSize(Constant.BATCH_SIZE);
		m_total = m_cursor.count();

		if (m_total > 0) {

			m_producerSignal = new CountDownLatch(Constant.MONGO_THREAD);
			m_convertSignal = new CountDownLatch(Constant.CONVERT_THREAD);
			m_consumerSignal = new CountDownLatch(Constant.SOLR_THREAD);

			Thread producer = new ProducerThread();
			producer.start();
			Thread tracer = new TraceThread();
			tracer.start();

			for (int i = 0; i < Constant.CONVERT_THREAD; i++) {
				Thread converter = new DocConvertThread();
				converter.start();
			}
			for (int i = 0; i < Constant.SOLR_THREAD; i++) {
				Thread consumer = new ConsumerThread();
				consumer.start();
			}

			try {
				m_producerSignal.await();
				m_convertSignal.await();
				m_consumerSignal.await();
			} catch (InterruptedException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	private class ProducerThread extends Thread {

		@Override
		public void run() {
			while (m_cursor.hasNext()) {
				try {
					DBObject obj = m_cursor.next();
					m_current++;
					m_mongo_queue.put(obj);
				} catch (Exception e) {
					LOG.error("producer put exception", e);
				}
			}
			m_cursor.close();
			m_producerSignal.countDown();
		}
	}

	private class DocConvertThread extends Thread {

		private ConvertUtil m_convertUtil;

		public DocConvertThread() {
			m_convertUtil = new ConvertUtil();
		}

		@Override
		public void run() {
			while (true) {
				if (m_mongo_queue.size() == 0
						&& m_producerSignal.getCount() == 0)
					break;

				if (m_mongo_queue.size() > 0) {
					try {
						DBObject obj = null;
						try {
							obj = m_mongo_queue.poll(10, TimeUnit.MILLISECONDS);
						} catch (InterruptedException e1) {
							LOG.error(e1.getMessage(), e1);
						}
						if (obj == null) {
							continue;
						}
						SolrInputDocument solrdoc = m_convertUtil
								.dbObjectToSolrInputDocument(obj);
						if (solrdoc != null) {
							m_solr_queue.put(solrdoc);
							m_convert++;
						}
					} catch (InterruptedException e) {
						LOG.error(e.getMessage(), e);
					}
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
			System.out.println(this.getName() + " Exit");
			m_convertSignal.countDown();
		}
	}

	private class ConsumerThread extends Thread {

		private ArrayList<SolrInputDocument> m_submongodoclist;
		private SolrServer m_allSolrServer;

		public ConsumerThread() {
			initSolrServer();
		}

		private void initSolrServer() {
			m_allSolrServer = new HttpSolrServer(Constant.SOLR_URL);
		}

		@Override
		public void run() {
			m_submongodoclist = new ArrayList<>(Constant.SOLR_BATCH);
			String pn = null;
			SolrInputDocument doc = null;
			StringBuilder sb = new StringBuilder();
			String pnString;

			while (m_solr_queue.size() > 0 || m_convertSignal.getCount() > 0) {
				try {
					doc = m_solr_queue.poll(10, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e1) {
					LOG.error(e1.getMessage(), e1);
				}
				if (doc == null) {
					continue;
				}

				pn = (String) doc.getFieldValue("PN");
				sb.append(pn).append(",");
				m_submongodoclist.add(doc);
				if (m_submongodoclist.size() == Constant.SOLR_BATCH) {
					sb.setLength(sb.length() - 1);
					pnString = sb.toString();
					try {
						m_allSolrServer.add(m_submongodoclist);
						ack(pnString);
					} catch (Exception e) {
						m_failed++;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} finally {
						m_submongodoclist = new ArrayList<>();
						sb.setLength(0);
					}
				}
			}

			if (m_submongodoclist.size() > 0) {
				sb.setLength(sb.length() - 1);
				pnString = sb.toString();
				try {
					m_allSolrServer.add(m_submongodoclist);
					ack(pnString);
				} catch (Exception e) {
					m_failed++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} finally {
					m_submongodoclist = new ArrayList<>();
					sb.setLength(0);
				}
			}

			commit();
			System.out.println(this.getName() + " Exit");
			m_consumerSignal.countDown();

		}

		private void commit() {
			long begin = System.currentTimeMillis();
			LOG.info("commit index at: " + new Date().toString());
			try {
				System.out.println("Index Commit " + this.getName());
				m_allSolrServer.commit();
			} catch (SolrServerException | IOException e) {
				LOG.error(e.getMessage(), e);
			} finally {
				m_allSolrServer.shutdown();
			}

			LOG.info("commit index finished: " + new Date().toString()
					+ " total in " + (System.currentTimeMillis() - begin)
					/ 1000 + " sec");

		}

		private void ack(String pnstring) {
			String[] pns = pnstring.split(",");
			m_succeed += pns.length;
		}
	}

	private class TraceThread extends Thread {
		private int m_last_succeed;

		public TraceThread() {
			m_last_succeed = 0;
		}

		@Override
		public void run() {

			while (true) {
				if (END_INDEXING != null)
					break;
				else {
					trace();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						LOG.error(e.getMessage(), e);
					}

				}
			}
		}

		private void trace() {
			long msec = System.currentTimeMillis() - START_INDEXING.getTime();
			long timeSecs = msec / 1000;
			float timeMins = timeSecs / 60;
			float timeHous = timeMins / 60;
			long eta = 0;
			if (m_succeed - m_last_succeed > 0) {
				eta = (m_total - m_succeed)
						/ ((m_succeed - m_last_succeed) / 5);

			}
			float etaMins = eta / 60;

			LOG.info("Cursor: mongo-" + m_current + ", convert-" + m_convert
					+ ", solr-" + m_succeed + ", total-" + m_total);
			LOG.info("Mongo Queue: " + m_mongo_queue.size());
			LOG.info("Solr Queue: " + m_solr_queue.size());
			LOG.info("Succeed: " + m_succeed + " Failed: " + m_failed + " at:"
					+ new Date().toString());
			LOG.info("TIME_COST " + timeSecs + " s, " + timeMins + " mins, "
					+ timeHous + " hours");
			LOG.info("AVG SPEED " + ((m_succeed - m_last_succeed) / 5)
					+ " patent/s");
			LOG.info("ETA " + eta + " s, " + etaMins + " mins");
			LOG.info("----------------------------------------------------------------------------------------");

			m_last_succeed = m_succeed;
		}

	}

	public void start() {
		IndexUtil.InitPatentDB(Constant.MONGO_URL);

		m_mongo_queue = new LinkedBlockingQueue<>(4000);
		m_solr_queue = new LinkedBlockingQueue<>(3000);

		START_INDEXING = new Date();

		startIndex();
	}

	public static void main(String[] args) {
		SolrIndexer solrIndexer = new SolrIndexer();
		solrIndexer.start();
	}

}
