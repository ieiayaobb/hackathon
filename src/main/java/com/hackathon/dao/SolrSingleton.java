package com.hackathon.dao;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

//import com.patsnap.ip.report.rest.bean.CitedTrendAcrossYearsBean;
//import com.patsnap.ip.report.rest.bean.CitedYearCountBean;

/**
 * @author Markus Haense
 */
public class SolrSingleton {

	public static final Logger LOG = Logger.getLogger(SolrSingleton.class);

	private static SolrSingleton m_instance;

	private static SolrServer m_solr;

//	private SolrParamsGenerator paramsGenerator;

	/**
	 * make sure you called getInstance(commandLine, databaseName) before!
	 */
	public static synchronized SolrSingleton getInstance() {
		if (m_instance == null) {
			throw new RuntimeException();
		}

		return SolrSingleton.m_instance;
	}

//	public static synchronized SolrSingleton getInstance(
//			Map<String, String> configMap) throws Exception {
//		if (m_instance == null) {
//			m_instance = new SolrSingleton(configMap);
//		}
//
//		return SolrSingleton.m_instance;
//	}
//
//	public static synchronized SolrSingleton getInstance(SolrServer server) {
//		if (m_instance == null) {
//			m_instance = new SolrSingleton(server);
//		}
//
//		return SolrSingleton.m_instance;
//	}

	private SolrSingleton() {
	}

//	private SolrSingleton(Map<String, String> configMap) throws Exception {
//		String host = configMap.get(Constant.Solr.PARAM_SOLR_HOST);
//		String port = configMap.get(Constant.Solr.PARAM_SOLR_PORT);
//		String path = configMap.get(Constant.Solr.PARAM_SOLR_PATH);
//
//		paramsGenerator = new SolrParamsGenerator();
//
//		if (!CommonUtil.isNumeric(port)) {
//			throw new Exception(
//					"solr port is not numeric! please choose a proper port e.g. "
//							+ Default.DEFAULT_SOLR_PORT);
//		}
//		String m_host = "http://" + host + ":" + port + path;
//		try {
//			m_solr = new ExtendedHttpSolrServer(m_host);
//			((ExtendedHttpSolrServer) m_solr)
//					.setConnectionTimeout(Constant.Solr.TIMEOUT_MILLI_SECONDS);
//			((ExtendedHttpSolrServer) m_solr)
//					.setSoTimeout(Constant.Solr.TIMEOUT_MILLI_SECONDS);
//			((ExtendedHttpSolrServer) m_solr)
//					.setMaxRetries(Constant.Solr.MAX_RETRY);
//			((ExtendedHttpSolrServer) m_solr)
//					.setDefaultMaxConnectionsPerHost(Constant.Solr.MAX_CONNECTIONS_PER_HOST);
//			((ExtendedHttpSolrServer) m_solr)
//					.setMaxTotalConnections(Constant.Solr.MAX_TOTAL_CONNECTIONS);
//			// allowCompression defaults to false.
//			// Server side must support gzip or deflate for this to have any
//			// effect.
//			((ExtendedHttpSolrServer) m_solr).setAllowCompression(true);
//		} catch (Exception e) {
//			throw new Exception(
//					"solr host is incorrect! please choose a proper host e.g. 192.168.1.114");
//		}
//	}
//
//	private SolrSingleton(SolrServer server) {
//		paramsGenerator = new SolrParamsGenerator();
//		m_solr = server;
//	}

	public static synchronized void shutdown() {
		if (m_solr != null) {
			m_solr.shutdown();
			m_instance = null;
		}
	}

//	public List<AssigneeBean> industry(String assignee, String search_mode)
//			throws Exception {
//
//		if (m_selectedDocumentTypeList.size() > 0) {
//			List<AssigneeBean> industry = StrategyMapSolrSingleton
//					.getInstance().getIndustryFromAssignee(assignee,
//							search_mode, 20, m_selectedDocumentTypeList,
//							"IPC_M_SUB_CLASS", "IPC_SUB_CLASS", 0.80f);
//
//			int toIndex = industry.size() > 10 ? 10 : industry.size();
//
//			return industry.subList(0, toIndex);
//		}
//
//		return new LinkedList<AssigneeBean>();
//	}
//
//	public List<AssigneeBean> getIndustryFromAssignee(String assignee,
//													  String search_mode, int limit, List<String> documentTypeList,
//													  String facetFieldStr, String searchFieldStr, float globalRate)
//			throws SolrServerException {
//
//		List<ClassificationBean> classificationList = new LinkedList<ClassificationBean>();
//
//		QueryResponse response = m_solr.query(paramsGenerator
//				.getClassificationGroupedBySearchModeFromAssigneeParams(
//						assignee, search_mode, limit, documentTypeList,
//						facetFieldStr));
//
//		long total = 0;
//		if (response != null) {
//			FacetField facetField = response.getFacetField(facetFieldStr);
//
//			if ((facetField != null) && (facetField.getValueCount() > 0)) {
//				List<Count> values = facetField.getValues();
//
//				for (Count count : values) {
//					String ipc_sub_class_code = count.getName();
//					long ipc_sub_class_count = count.getCount();
//
//					classificationList.add(new ClassificationBean(
//							ipc_sub_class_code, "", ipc_sub_class_count));
//
//					total += ipc_sub_class_count;
//				}
//			}
//		}
//
//		if (total > 0) {
//			List<String> selectedClassificationList = new LinkedList<String>();
//
//			float currentRate = 0.0f;
//
//			for (ClassificationBean currentClassificationBean : classificationList) {
//				currentRate += (float) currentClassificationBean.getCount()
//						/ (float) total;
//
//				if (currentRate >= globalRate) {
//					break;
//				} else {
//					selectedClassificationList.add(currentClassificationBean
//							.getCode());
//
//				}
//			}
//
//			if (selectedClassificationList.size() > 0) {
//				return getTopAssignee(search_mode, documentTypeList,
//						selectedClassificationList, searchFieldStr, assignee,
//						11);
//			}
//		}
//
//		return new LinkedList<AssigneeBean>();
//	}
}
