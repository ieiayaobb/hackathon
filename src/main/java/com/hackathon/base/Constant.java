package com.hackathon.base;

import java.util.Arrays;
import java.util.List;

public class Constant {

	public interface ErrorCode {
		public static final String UID_IS_EMPTY = "4001";
		public static final String KEY_IS_EMPTY = "4002";
		public static final String CALLBACK_IS_EMPTY = "4003";
		public static final String CALLBACK_MALFORMED_URL = "4004";
		public static final String ASSIGNEE_IS_EMPTY = "4005";
		public static final String COMPETES_IS_EMPTY = "4006";
		public static final String QUEUE_ERROR = "4007";
		public static final String FROM_IS_EMPTY = "4008";
		public static final String TO_IS_EMPTY = "4009";
		public static final String NBERS_IS_EMPTY = "4010";
		public static final String KEYWORD_IS_EMPTY = "4011";
		public static final String UPC_SET_IS_EMPTY = "4012";
		public static final String KEYWORD_IS_NOT_PARSABLE = "4013";
		public static final String PARAM_FORMAT_ERROR = "4014";
		public static final String INDUSTRIES_IS_EMPTY = "4015";
		public static final String PN_IS_EMPTY = "4016";

		public static final String NO_DATA = "4101";
		public static final String ERROR_GET_INDUSTRY_RECOMMENDATION = "4102";

		public static final String SERVICE_UNAVAILABLE = "5001";
	}

	public interface ErrorMsg {
		public static final String PARAM_FORMAT_ERROR = "Parameter <%s> format error!";
		public static final String NO_DATA = "No data found for the query!";
		public static final String NOT_EMPTY_FIELD = "%s cannot be empty!";
	}

	public interface Indicator {
		public static final String CURRENT_AN_FLAG = "AN_AND_ANS_AND_ANS_FACET";
		public static final String CURRENT_IN_FLAG = "INS_FACET";
	}

	public interface Mongo {
		public static final String DEFAULT_MONGODB_PORT = "27017";
		public static final String DEFAULT_MONGODB_USERNAME = "patsnap_r";
		public static final String DEFAULT_MONGODB_PASSWORD = "patsnap123";
		public static final String PARAM_MONGO_HOST = "param.mongodb.host";
		public static final String PARAM_MONGO_PORT = "param.mongodb.port";
		public static final String PARAM_MONGO_DATABASE = "param.mongodb.database";

		public static final String IPC_ID = "ipcid";
		public static final String LOC_ID = "locid";
		public static final String UPC_ID = "upcid";

	}

	public interface Solr {
		public static final String PARAM_SOLR_HOST = "param.solr.host";
		public static final String PARAM_SOLR_PORT = "param.solr.port";
		public static final String PARAM_SOLR_PATH = "param.solr.path";
		public static final String PARAM_STRATEGY_SOLR_HOST = "param.strategy.solr.host";
		public static final String PARAM_STRATEGY_SOLR_PORT = "param.strategy.solr.port";
		public static final String PARAM_STRATEGY_SOLR_PATH = "param.strategy.solr.path";
		public static final int TIMEOUT_MILLI_SECONDS = 30000;
		public static final int MAX_RETRY = 1;
		public static final int MAX_CONNECTIONS_PER_HOST = 100;
		public static final int MAX_TOTAL_CONNECTIONS = 100;
		public static final int MAX_CONNECTIONS_PER_HOST_STRATEGY_MAP = 10;
		public static final int MAX_TOTAL_CONNECTIONS_STRATEGY_MAP = 10;
	}

	public interface NNAME {
		public static final String PARAM_NNAME_SOLR_HOST = "param.nname.solr.host";
		public static final String PARAM_NNAME_SOLR_PORT = "param.nname.solr.port";
		public static final String PARAM_NNAME_SOLR_PATH = "param.nname.solr.path";
		public static final int TIMEOUT_MILLI_SECONDS = 15000;
	}

	public interface Pivot {
		public static final String PARAM_PIVOT_HOST = "param.pivot.host";
		public static final String PARAM_PIVOT_PORT = "param.pivot.port";
		public static final String PARAM_PIVOT_PATH = "param.pivot.path";
		public static final int TIMEOUT_MILLI_SECONDS = 600000;
	}

	public interface InitInfoMessage {
		public static final String CONNECT_TO_MONGO = "Connected to MongoDB, host: %s, port: %s ";
		public static final String CONNECT_TO_SOLR = "Connected to Solr, host: %s, port: %s ";
		public static final String CONNECT_TO_STRATEGY_MAP_SOLR = "Connected to Strategy Map Solr, host: %s, port: %s ";
		public static final String CONNECT_TO_NNAME = "Connected to NNAME, host: %s, port: %s ";
		public static final String CONNECT_TO_PIVOT = "Connected to Pivot, host: %s, port: %s ";

		public static final String NUM_OF_SYSTEM_PROCESSES = "\t=> Number of system cores (processors): %s";
		public static final String CURRENT_THREAD_FOR_STRATEGY_MAP = "\t=> Starting thread for strategy map %s";
		public static final String CURRENT_THREAD_FOR_STRATEGY_MAP_PATENT_INFLUENCE = "\t=> Starting thread for strategy map patent influence %s";
	}

	public interface InitErrorMessage {
		public static final String MONGO_INIT_ERROR = "Error initial MongoDB:";
		public static final String SOLR_INIT_ERROR = "Error initial Solr:";
		public static final String STRATEGY_MAP_SOLR_INIT_ERROR = "Error initial Strategy Map Solr:";
		public static final String NNAME_INIT_ERROR = "Error initial NNAME:";
		public static final String PIVOT_INIT_ERROR = "Error initial Pivot:";
	}

	public interface RESTErrorMessage {
		public static final String SOLR = "problem with SOLR [%s]";
		public static final String PIVOT = "problem with PIVOT [%s]";
		public static final String EMPTY = "problem with [%s]";
		public static final String CLASSIFICATION = "problem with classification [%s]";
	}

	public interface Default {
		public static final String DEFAULT = "Default";
		public static final String DEFAULT_HOST = "localhost";
		public static final String DEFAULT_PORT = "8000";
		public static final String DEFAULT_PIVOT_PORT = "9998";
		public static final String DEFAULT_SOLR_PORT = "2181";
		public static final String DEFAULT_GRIDFS_PORT = "27017";
		public static final String DEFAULT_FROM = "2009";
		public static final String DEFAULT_FROM_VERY_EARLY = "*";
		public static final String DEFAULT_TO = "2014";
		public static final String DEFAULT_UID = "IPREPORT_DEFAULT_UID";
		public static final String DEFAULT_KEY = "IPREPORT_DEFAULT_KEY";
		public static final String DEFAULT_INDUSTRY_RECCOMEND_CUT_RATE = "0.2";
		public static final String DEFAULT_INDUSTRY_RECCOMEND_TOP_N = "10";
		public static final float DEFAULT_INDUSTRY_RECCOMEND_CUT_RATE_F = 0.2f;
		public static final int DEFAULT_INDUSTRY_RECCOMEND_TOP_N_I = 10;
		public static final String DEFAULT_INDUSTRY_ASSIGNEES_LIMIT = "5";
		public static final String DEFAULT_INDUSTRY_INVENTORS_LIMIT = "10";
		public static final String DEFAULT_INDUSTRY_TECHNOLOGIES_LIMIT = "10";
		public static final String DEFAULT_TYPE = "IPC_MAIN_GROUP";
		public static final String DEFAULT_LIMIT = "10";
		public static final String DEFAULT_LIMIT_5 = "5";
		public static final String ALL = "ALL";
		public static final String IPC_CLASS = "IPC_CLASS";
		public static final int DEFAULT_DECIMAL_PLACES = 2;
		public static final int DEFAULT_THREADS_POOL_SIZE = 3;
		public static final int DEFAULT_THREADS_POOL_SIZE_STRATEGY_MAP = 4;
	}

	public interface InterfaceParams {
		public static final String LIMIT = "LIMIT";
		public static final String LIMIT_COMP = "LIMIT_COMP";
		public static final String LIMIT_TECH = "LIMIT_TECH";
		public static final String LIMIT_INVE = "LIMIT_INVE";
		public static final String ASSIGNEE = "ASSIGNEE";
		public static final String ASSIGNEES = "ASSIGNEES";
		public static final String DOCUMENT_TYPES = "DOCUMENT_TYPES";
		public static final String SEARCH_MODE = "SEARCH_MODE";
		public static final String SEARCH_FIELD = "SEARCH_FIELD";
		public static final String TYPE = "TYPE";
		public static final String LANG = "LANG";
		public static final String INVENTOR = "INVENTOR";
		public static final String INVENTORS = "INVENTORS";
		public static final String IPC_NUM = "IPC_NUM";
		public static final String COMPETES = "COMPETES";
		public static final String CALLBACK = "CALLBACK";
		public static final String TO = "TO";
		public static final String CLASSIFICATION = "CLASSIFICATION";
		public static final String FROM = "FROM";
		public static final String PN = "PN";
	}

	public interface Fields {

		public static final String NNAME = "NNAME";

		public static final String AN_AND_ANS_FACET = "AN_AND_ANS_FACET";
		public static final String AN_AND_ANS_AND_ANS_FACET = "AN_AND_ANS_AND_ANS_FACET";
		public static final String ANS_FACET = "ANS_FACET";

		public static final String IN_AND_INS_AND_INS_FACET = "IN_AND_INS_AND_INS_FACET";
		public static final String IN_AND_INS_FACET = "IN_AND_INS_FACET";
		public static final String IN_AND_INS = "IN_AND_INS";
		public static final String IN = "IN";
		public static final String INS_FACET = "INS_FACET";

		public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
		public static final String PN = "PN";
		public static final String PBDT_YEAR = "PBDT_YEAR";
		public static final String RD_CLOSE = "RD_CLOSE";
		public static final String INPADOC_FAMILY_COUNT = "INPADOC_FAMILY_COUNT";
	}

	public interface Switchers {
		public static final Boolean log_strategy_map_indicator_time = false;
	}

	public static final String EN = "EN";
	public static final String CN = "CN";
	public static final String LIMIT = "LIMIT";
	public static final String ASSIGNEES = "ASSIGNEES";
	public static final String INDUSTRIES = "INDUSTRIES";
	public static final String CODE = "CODE";
	public static final String IPC_SECTION = "IPC_SECTION";
	public static final String IPC_SUB_CLASS = "IPC_SUB_CLASS";
	public static final String IPC_MAIN_GROUP = "IPC_MAIN_GROUP";
	public static final String ICL = "ICL";
	public static final String UPC = "UPC";
	public static final String LOC = "LOC";
	public static final String COMPRESSION = "COMPRESSION";
	public static final String ERROR = "ERROR";
	public static final String DATA = "DATA";
	public static final String NBERS = "NBERS";
	public static final String KEYWORD = "KEYWORD";
	public static final String CUT_RATE = "CUT_RATE";
	public static final String TOP_N = "TOP_N";
	public static final String APNO = "APNO";
	public static final String DOCDB = "DOCDB";
	public static final String INPADOC = "INPADOC";
	public static final String APNO_FACET = "APNO_FACET";
	public static final String FAMILY_ID = "FAMILY_ID";
	public static final String INPADOC_FAMILY_ID = "INPADOC_FAMILY_ID";
	public static final String PARAM_STRATEGY_CURRENT_RELEVANCE = "param.strategy.currentRelevace";
	public static final String STRATEGY_TCT = "TCT";
	public static final String STRATEGY_CII = "CII";
	public static final String PARAM_STRATEGY_PATENT_INFLUENCE_IPC_LIMIT = "param.strategy.patentInfluence.ipcLimit";
	// Note: USB include USR & SIR, and USA & USB include USP.
	public static final List<String> ALL_DOCUMENT_TYPES = Arrays.asList("EP",
			"EPA", "EPB", "US", "USA", "USB", "USD", "USR", "USP", "CN", "CNA",
			"CNB", "CNU", "CND", "JP", "JPA", "JPB", "JPU", "JPD", "DE", "DEA",
			"DEB", "DEU", "WO");
	public static final String UID = "UID";
	public static final String KEY = "KEY";
	public static final String TTL_AND_ABST = "TTL_AND_ABST";

	public static final int MONGO_THREAD = 1;
	public static final int CONVERT_THREAD = 5;
	public static final int SOLR_THREAD = 3;
	public static final int BATCH_SIZE = 20;
	public static final int SOLR_BATCH = 50;
	
	public static final String MONGO_URL = "127.0.0.1:27017";
    public static final String SOLR_URL = "http://192.168.3.70:9080/solr/PATENT";
}
