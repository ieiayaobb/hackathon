package com.hackathon.dao;

import com.hackathon.base.Constant;
import com.mongodb.*;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Markus Haense
 */
public class MongoDBDatabaseSingleton {

	public static final Logger LOG = Logger
			.getLogger(MongoDBDatabaseSingleton.class);

	private static MongoDBDatabaseSingleton m_instance;

	private MongoClient m_mongo;

	private DB m_db;

	private DB freebase_db;

	/**
	 * make sure you called getInstance(commandLine, databaseName) before!
	 */
	public static synchronized MongoDBDatabaseSingleton getInstance() {
		if (m_instance == null) {
			throw new RuntimeException();
		}

		return MongoDBDatabaseSingleton.m_instance;
	}

	public static synchronized MongoDBDatabaseSingleton getInstance(
			Map<String, String> configMap) throws UnknownHostException {
		if (m_instance == null) {
			m_instance = new MongoDBDatabaseSingleton(configMap);
		}

		return MongoDBDatabaseSingleton.m_instance;
	}

	private MongoDBDatabaseSingleton() {
	}

	private MongoDBDatabaseSingleton(Map<String, String> configMap)
			throws UnknownHostException {

		String host = configMap.get(Constant.Mongo.PARAM_MONGO_HOST);
		String port = configMap.get(Constant.Mongo.PARAM_MONGO_PORT);
		String database = configMap.get(Constant.Mongo.PARAM_MONGO_DATABASE);
		String username = configMap.get(Constant.Mongo.PARAM_MONGO_USERNAME);
		String password = configMap.get(Constant.Mongo.PARAM_MONGO_PASSWORD);

		// MONGO
		ServerAddress address = new ServerAddress(host + ":" + port);
		m_mongo = new MongoClient(address, new MongoClientOptions.Builder()
				.autoConnectRetry(true).build());

		m_db = m_mongo.getDB(database);
		freebase_db = m_mongo.getDB("freebase");

		char[] passwordChars = password.toCharArray();

		try {
			if (m_db.authenticate(username, passwordChars) == false) {
				throw new Exception(
						"cannot authenficate against mongodb!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DB getDatabase() {
		return m_db;
	}

	public MongoClient getMongoClient() {
		return m_mongo;
	}

	public DBCollection getPatentCollection() {
		return m_db.getCollection("patent");
	}
	public DBCollection getFreebaseCollection(){
		return freebase_db.getCollection("data");
	}

	public void close() {
		m_mongo.close();
	}

}
