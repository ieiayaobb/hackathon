package com.hackathon.dao;

import com.hackathon.base.Constant;
import com.hackathon.bean.Company;
import com.hackathon.util.StringUtil;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

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

		// MONGO
		ServerAddress address = new ServerAddress(host + ":" + port);
		m_mongo = new MongoClient(address, new MongoClientOptions.Builder()
				.autoConnectRetry(true).build());

		m_db = m_mongo.getDB(database);
		freebase_db = m_mongo.getDB("freebase");
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

	public Company getCompanyInfo(String name){
		Company company = new Company(name);

		Pattern namePat = Pattern.compile(name);

		DBObject obj = MongoDBDatabaseSingleton.getInstance()
				.getFreebaseCollection().findOne(new BasicDBObject("search", namePat));

		if(obj != null) {
			if (obj.get("description") != null) {
				BasicDBList descriptionList = (BasicDBList) obj.get("description");
				for (Object description : descriptionList) {
					DBObject descriptionDBObject = (DBObject) description;
					company.putDescription(StringUtil.clean(descriptionDBObject.get("lang").toString()), StringUtil.clean(descriptionDBObject.get("value").toString()));
				}
			}
			if (obj.get("web") != null) {
				company.setWeb(StringUtil.clean(obj.get("web").toString()));
			}
			if ((obj != null) && obj.containsField("alias")) {
				BasicDBList aliasList = (BasicDBList) obj.get("alias");
				for (Object alias : aliasList) {
					DBObject aliasDBObject = (DBObject) alias;
					company.putAlias(StringUtil.clean(aliasDBObject.get("lang").toString()), StringUtil.clean(aliasDBObject.get("value").toString()));
				}
			}else if((obj != null) && obj.containsField("name")){
				BasicDBList aliasList = (BasicDBList) obj.get("name");
				for (Object alias : aliasList) {
					DBObject aliasDBObject = (DBObject) alias;
					company.putAlias(StringUtil.clean(aliasDBObject.get("lang").toString()), StringUtil.clean(aliasDBObject.get("value").toString()));
				}
			}
		}

		return company;
	}

	public void close() {
		m_mongo.close();
	}

}
