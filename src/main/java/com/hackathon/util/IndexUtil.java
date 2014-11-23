package com.hackathon.util;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class IndexUtil {

	private static final Logger LOG = Logger.getLogger(IndexUtil.class);

	private static MongoClient m_patClient;
	private static DB m_patentdb;
	private static DBCollection m_patentCollection;
	private static DBCollection m_normalNameCollection;

	public static void InitPatentDB(String address) {
		ArrayList<ServerAddress> addr = new ArrayList<>();
		try {
			addr.add(new ServerAddress(address.trim()));
			System.out.println("mongodb url: " + address.trim());

		} catch (UnknownHostException e) {
			LOG.error("InitPatentTxtDB", e);
		}
		m_patClient = new MongoClient(addr, GetReadOnlyOptions());
		m_patClient.setReadPreference(ReadPreference.secondaryPreferred());

		m_patentdb = m_patClient.getDB("patent");

		m_patentCollection = m_patentdb.getCollection("patent");
		m_normalNameCollection = m_patentdb.getCollection("assignee_names");

	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	private static MongoClientOptions GetReadOnlyOptions() {
		Builder builder = new MongoClientOptions.Builder();
		builder.autoConnectRetry(true);
		builder.readPreference(ReadPreference.secondaryPreferred());
		builder.socketTimeout(180000);
		builder.maxAutoConnectRetryTime(10);
		builder.maxWaitTime(180000);

		return builder.build();
	}

	public static DBCollection getNormalNameCollection() {
		return m_normalNameCollection;
	}

	public static DBCollection getPatentCollection() {
		return m_patentCollection;
	}
}
