package com.hackathon.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class ConvertUtil {
	private static final Logger LOG = Logger.getLogger(ConvertUtil.class);

	// COMMON SOLR FIELDS
	private final static String SOLR_ID = "_id";
	private final static String SOLR_PN = "PN";
	private final static String SOLR_PURE_PN = "PN_STR";
	private final static String SOLR_FAMILY_ID = "FAMILY_ID";
	private final static String SOLR_INPADOC_FAMILY_ID = "INPADOC_FAMILY_ID";
	private final static String SOLR_unikey = "UniqueKey";
	private final static String SOLR_APNO = "APNO";
	private final static String SOLR_APNO_FACET = "APNO_FACET";
	private final static String SOLR_DOCUMENT_TYPE = "DOCUMENT_TYPE";

	// /
	// / DATE SOLR FIELDS
	// /
	private final static String SOLR_PBDT = "PBDT";
	private final static String SOLR_PBDT_YEAR = "PBDT_YEAR";
	private final static String SOLR_PBDT_YEARMONTH = "PBDT_YEARMONTH";
	private final static String SOLR_PBDT_YEARMONTHDAY = "PBDT_YEARMONTHDAY";

	private final static String SOLR_APD = "APD";
	private final static String SOLR_APD_YEAR = "APD_YEAR";
	private final static String SOLR_APD_YEARMONTH = "APD_YEARMONTH";
	private final static String SOLR_APD_YEARMONTHDAY = "APD_YEARMONTHDAY";

	// /
	// / CLASSIFICATION SOLR FIELDS
	// /
	private final static String SOLR_IPC = "IPC";
	private final static String SOLR_ICL = "ICL";
	private final static String SOLR_IPC_SECTION = "IPC_SECTION";
	private final static String SOLR_IPC_CLASS = "IPC_CLASS";
	private final static String SOLR_IPC_SUB_CLASS = "IPC_SUB_CLASS";
	private final static String SOLR_IPC_MAIN_GROUP = "IPC_MAIN_GROUP";
	private final static String SOLR_ICL_MAINCLASS = "ICL_MAINCLASS";

	private final static String SOLR_CPC = "CPC";
	private final static String SOLR_CPC_SECTION = "CPC_SECTION";
	private final static String SOLR_CPC_CLASS = "CPC_CLASS";
	private final static String SOLR_CPC_SUB_CLASS = "CPC_SUB_CLASS";
	private final static String SOLR_CPC_MAIN_GROUP = "CPC_MAIN_GROUP";

	private final static String SOLR_IPC_M_SECTION = "IPC_M_SECTION";
	private final static String SOLR_IPC_M_CLASS = "IPC_M_CLASS";
	private final static String SOLR_IPC_M_SUB_CLASS = "IPC_M_SUB_CLASS";
	private final static String SOLR_IPC_M_MAIN_GROUP = "IPC_M_MAIN_GROUP";
	private final static String SOLR_IPC_M = "IPC_M";
	private final static String SOLR_IPCR = "IPCR";

//	private final static String SOLR_IPC_SUB_CLASS_COUNT = "IPC_SUB_CLASS_COUNT";
//	private final static String SOLR_IPC_SECTION_COUNT = "IPC_SECTION_COUNT";
//	private final static String SOLR_IPC_CLASS_COUNT = "IPC_CLASS_COUNT";
//	private final static String SOLR_IPC_MAIN_GROUP_COUNT = "IPC_MAIN_GROUP_COUNT";
//	private final static String SOLR_IPC_COUNT = "IPC_COUNT";

	private final static String SOLR_CCL = "CCL";
	private final static String SOLR_UPC = "UPC";
	private final static String SOLR_UPC_CALSS = "UPC_CLASS";
	private final static String SOLR_LOC = "LOC";
	private final static String SOLR_LOC_CLASS = "LOC_CLASS";
	private final static String SOLR_FTERM = "FTERM";
	private final static String SOLR_FI = "FI";

	// /
	// / CITATION SOLR FIELDS
	// /
//	private final static String SOLR_CITE_FACET = "CITE_FACET";
//
//	private final static String SOLR_CITE_COUNT = "CITE_COUNT";
//	private final static String SOLR_CITED_COUNT = "CITED_COUNT";
//
//	// FAMILY MEMBER COUNT
//	private final static String SOLR_DOCDB_FAMILY_COUNT = "DOCDB_FAMILY_COUNT";
//	private final static String SOLR_INPADOC_FAMILY_COUNT = "INPADOC_FAMILY_COUNT";
//
//	// BCR
//	private final static String SOLR_BCR = "BCR";
	// isd
//	private final static String SOLR_ISD = "ISD";
//	// years pendency
//	private final static String SOLR_YEARS_PENDENCY = "YEARS_PENDENCY";
//	// renewal year
//	private final static String SOLR_RD_CLOSE = "RD_CLOSE";
//	// CLMS COUNT
//	private final static String SOLR_CLAIM_COUNT = "CLAIM_COUNT";

	// /
	// / PERSON SOLR FIELDS
	// /
	private final static String SOLR_AN_COUNTRY = "AN_COUNTRY";

	private final static String SOLR_AN = "AN";
	private final static String SOLR_ANS = "ANS";
	private final static String SOLR_ANS_FACET = "ANS_FACET";
	private final static String SOLR_AN_FACET = "ASSIGNEE_NORMALIZED_NAME_FACET";
//	private final static String SOLR_AN_COUNT = "AN_COUNT";

	private final static String SOLR_IN = "IN";
	private final static String SOLR_INS = "INS";
	private final static String SOLR_INS_FACET = "INS_FACET";
	private final static String SOLR_IN_FACET = "INVENTOR_NAME_FACET";

	private final static String SOLR_MONGO_TS = "MONGO_TS";
	private final static String SOLR_INDEX_TS = "INDEX_TS";

	// /
	// / LANGUAGE
	// /
	private static enum LANG {
		EN, CN, FR, DE, JP
	};

	// /
	// / MONGODB FIELDS
	// /
	private final static String MONGODB_id = "_id";
	private final static String MONGODB_apno = "apno";
	private final static String MONGODB_apno_original = "original";
	private final static String MONGODB_apno_docdb = "docdb";
	private final static String MONGODB_nname = "nname";

	private final static String MONGODB_family = "family";
	private final static String MONGODB_family_docdb = "original";
	private final static String MONGODB_family_inpadoc = "inpadoc";

	private final static String MONGODB_pbdt = "pbdt";
	private final static String MONGODB_apdt = "apdt";

	private final static String MONGODB_AN = "AN";
	private final static String MONGODB_IN = "IN";
	private final static String MONGODB_AT = "AT";
	private final static String MONGODB_ATC = "ATC";
	private final static String MONGODB_PE = "PE";
	private final static String MONGODB_AE = "AE";

	private final static String MONGODB_personIn = "personIn";
	private final static String MONGODB_personAn = "personAn";
	private final static String MONGODB_personAt = "personAt";
	private final static String MONGODB_personAtc = "personAtc";
	private final static String MONGODB_personAe = "personAe";
	private final static String MONGODB_personPe = "personPe";
	private final static String MONGODB_personOriginal = "original";
	private final static String MONGODB_personDocdb = "docdb";
	private final static String MONGODB_name = "name";
	private final static String MONGODB_type = "type";
	private final static String MONGODB_code = "code";
	private final static String MONGODB_lang = "lang";
	private final static String MONGODB_class = "class";
	private final static String MONGODB_class_ipc = "ipc";
	private final static String MONGODB_class_cpc = "cpc";
	private final static String MONGODB_class_ipcr = "ipcr";
//	private final static String MONGODB_citation = "citation";
	private final static String MONGODB_pn = "pn";

	private SimpleDateFormat m_solrDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	private SimpleDateFormat m_simpleDateformatY = new SimpleDateFormat("yyyy");
	private SimpleDateFormat m_simpleDateformatYM = new SimpleDateFormat(
			"yyyyMM");
	private SimpleDateFormat m_simpleDateformatYMD = new SimpleDateFormat(
			"yyyyMMdd");

//	private static final String MONGODB_analyzed_data = "analyzed_data";
//	private static final String MONGODB_bcr = "bcr";
//	private static final String MONGODB_claim_count = "claim_count";
//	private static final String MONGODB_rd_close = "rd_close";
//	private static final String MONGODB_rd_close4 = "4";
//	private static final String MONGODB_rd_close8 = "8";
//	private static final String MONGODB_rd_close12 = "12";
//	private static final String MONGODB_years_pendency = "years_pendency";
//
//	private static final String MONGODB_isd = "isd";

	public SolrInputDocument dbObjectToSolrInputDocument(DBObject mongoDocument) {

		ObjectId _id = (ObjectId) mongoDocument.get(MONGODB_id);
		
		if (mongoDocument.get(MONGODB_pn) == null) {
			return null;
		}
		String pn = mongoDocument.get(MONGODB_pn).toString();

		SolrInputDocument solrDocument = new SolrInputDocument();

		solrDocument.addField(SOLR_MONGO_TS,
				((Date) mongoDocument.get("ts")).getTime());
		solrDocument.addField(SOLR_INDEX_TS, new Date().getTime());

		// APNO
		String apno = null;
		if (mongoDocument.containsField(MONGODB_apno)
				&& ((BasicDBObject) mongoDocument.get(MONGODB_apno))
						.containsField(MONGODB_apno_original)) {
			apno = ((BasicDBObject) ((BasicDBObject) mongoDocument
					.get(MONGODB_apno)).get(MONGODB_apno_original))
					.getString("number");
		} else if (mongoDocument.containsField(MONGODB_apno)
				&& ((BasicDBObject) mongoDocument.get(MONGODB_apno))
						.containsField(MONGODB_apno_docdb)) {
			apno = ((BasicDBObject) ((BasicDBObject) mongoDocument
					.get(MONGODB_apno)).get(MONGODB_apno_docdb))
					.getString("number");
		}

		if (!isEmpty(apno)) {
			solrDocument.setField(SOLR_APNO, apno);
			solrDocument.setField(SOLR_APNO_FACET, apno);
		} else {
			solrDocument.setField(SOLR_APNO_FACET, pn);
		}

		solrDocument.setField(SOLR_ID, _id.toString());
		solrDocument.setField(SOLR_PN, pn);
		solrDocument.setField(SOLR_PURE_PN, pn);
		String family = null;
		String inpadocfamily = null;

		if (mongoDocument.containsField(MONGODB_family)) {
			family = ((BasicDBObject) mongoDocument.get(MONGODB_family))
					.getString(MONGODB_family_docdb);
			inpadocfamily = ((BasicDBObject) mongoDocument.get(MONGODB_family))
					.getString(MONGODB_family_inpadoc);
		}

		if (!isEmpty(family)) {
			solrDocument.setField(SOLR_FAMILY_ID, family);
		} else {
			if (isEmpty(apno)) {
				// family = pn;
				solrDocument.setField(SOLR_FAMILY_ID, pn);
			} else {
				solrDocument.setField(SOLR_FAMILY_ID, apno);
			}

		}

		// INPADOC_FAMILY_ID
		if (!isEmpty(inpadocfamily)) {
			solrDocument.setField(SOLR_INPADOC_FAMILY_ID, inpadocfamily);
		} else {
			if (!isEmpty(family)) {
				inpadocfamily = family;
			} else if (!isEmpty(apno)) {
				inpadocfamily = apno;
			} else {
				inpadocfamily = pn;
			}

			solrDocument.setField(SOLR_INPADOC_FAMILY_ID, inpadocfamily);
		}

		solrDocument.setField(SOLR_unikey, inpadocfamily + "!" + _id);

		BasicDBList typeList = (BasicDBList) mongoDocument.get(MONGODB_type);

		List<String> typeArray = new LinkedList<>();

		if (typeList != null && typeList.size() > 0) {
			for (Object currentType : typeList) {
				typeArray.add(currentType.toString());
			}
			solrDocument.setField(SOLR_DOCUMENT_TYPE, typeArray);

		} else {
			String country = pn.substring(0, 2);
			typeArray.add(country);
			solrDocument.setField(SOLR_DOCUMENT_TYPE, typeArray);
		}

		// PBDT
		handleEmbeddedDocumentDate(SOLR_PBDT, SOLR_PBDT_YEAR,
				SOLR_PBDT_YEARMONTH, SOLR_PBDT_YEARMONTHDAY, MONGODB_pbdt,
				solrDocument, mongoDocument);

		// APD
		handleEmbeddedDocumentDate(SOLR_APD, SOLR_APD_YEAR, SOLR_APD_YEARMONTH,
				SOLR_APD_YEARMONTHDAY, MONGODB_apdt, solrDocument,
				mongoDocument);

//		if (mongoDocument.containsField(MONGODB_analyzed_data)
//				&& ((DBObject) mongoDocument.get(MONGODB_analyzed_data))
//						.containsField(MONGODB_years_pendency)) {
//			solrDocument.setField(SOLR_YEARS_PENDENCY,
//					((DBObject) mongoDocument.get(MONGODB_analyzed_data))
//							.get(MONGODB_years_pendency));
//		}

//		if (mongoDocument.containsField(MONGODB_analyzed_data)
//				&& ((DBObject) mongoDocument.get(MONGODB_analyzed_data))
//						.containsField(MONGODB_rd_close)) {
//			DBObject tmpobj = (DBObject) ((DBObject) mongoDocument
//					.get(MONGODB_analyzed_data)).get(MONGODB_rd_close);
//			List<Integer> rdlist = new ArrayList<Integer>();
//			Calendar calendar = Calendar.getInstance();
//
//			if (tmpobj.containsField(MONGODB_rd_close4)) {
//				calendar.setTime((Date) tmpobj.get(MONGODB_rd_close4));
//				rdlist.add(calendar.get(Calendar.YEAR));
//			}
//			calendar.setTime((Date) tmpobj.get(MONGODB_rd_close8));
//			if (tmpobj.containsField(MONGODB_rd_close8)
//					&& rdlist.indexOf(calendar.get(Calendar.YEAR)) == -1) {
//				rdlist.add(calendar.get(Calendar.YEAR));
//			}
//			calendar.setTime((Date) tmpobj.get(MONGODB_rd_close12));
//			if (tmpobj.containsField(MONGODB_rd_close12)
//					&& rdlist.indexOf(calendar.get(Calendar.YEAR)) == -1) {
//				rdlist.add(calendar.get(Calendar.YEAR));
//			}
//
//			if (rdlist.size() > 0) {
//				solrDocument.addField(SOLR_RD_CLOSE, rdlist);
//			}
//		}

//		if (mongoDocument.containsField(MONGODB_analyzed_data)
//				&& ((DBObject) mongoDocument.get(MONGODB_analyzed_data))
//						.containsField(MONGODB_claim_count)) {
//			solrDocument.addField(SOLR_CLAIM_COUNT, ((DBObject) mongoDocument
//					.get(MONGODB_analyzed_data)).get(MONGODB_claim_count));
//		}

		// IPC & UPC
		handleEmbeddedDocumentClassification(solrDocument, mongoDocument);

//		// CITATION
//		handleEmbeddedCitation(solrDocument, mongoDocument);

//		// CITED
//		// BCR
//		handleCited(solrDocument, mongoDocument);

		// FAMILY COUNT
//		handleFamilyCount(solrDocument, mongoDocument);

		// PERSON
		handleEmbeddedPerson(solrDocument, mongoDocument, MONGODB_personAn);
		handleEmbeddedPerson(solrDocument, mongoDocument, MONGODB_personIn);

		return solrDocument;

	}

	private void handleEmbeddedDocumentDate(String solrFieldName,
			String solrFieldNameYear, String solrFieldNameYearMonth,
			String solrfieldNameYearMonthDay, String mongodbFieldName,
			SolrInputDocument solrDocument, DBObject mongoDocument) {
		Object dateObject = mongoDocument.get(mongodbFieldName);

		if (dateObject != null) {
			Date date = (Date) dateObject;

			solrDocument.setField(solrFieldName, m_solrDateFormat.format(date));
			solrDocument.setField(solrFieldNameYear, getYear(date));
			solrDocument.setField(solrFieldNameYearMonth, getYearMonth(date));
			solrDocument.setField(solrfieldNameYearMonthDay,
					getYearMonthDay(date));
		}
	}

	private int getYear(Date date) {
		int y = Integer.valueOf(m_simpleDateformatY.format(date));
		return y;
	}

	private int getYearMonth(Date date) {
		int ym = Integer.valueOf(m_simpleDateformatYM.format(date));
		return ym;
	}

	private int getYearMonthDay(Date date) {
		int ymd = Integer.valueOf(m_simpleDateformatYMD.format(date));
		return ymd;
	}

	private void handleEmbeddedDocumentClassification(
			SolrInputDocument solrDocument, DBObject mongoDocument) {
		BasicDBObject typeobj = (BasicDBObject) mongoDocument
				.get(MONGODB_class);

		if (typeobj == null)
			return;

		Iterator<String> it = typeobj.keySet().iterator();
		String key;

		while (it.hasNext()) {
			key = it.next();

			BasicDBList classlist = (BasicDBList) typeobj.get(key);
			BasicDBObject dbobj;
			if (key.equalsIgnoreCase(SOLR_IPC)
					|| key.equalsIgnoreCase(SOLR_IPCR)) {
				Set<String> fullset = new HashSet<String>();
				Set<String> sectionset = new HashSet<String>();
				Set<String> classset = new HashSet<String>();
				Set<String> subclassset = new HashSet<String>();
				Set<String> subgroupclassset = new HashSet<String>();
				Set<String> maingroupclassset = new HashSet<String>();
				for (Object list : classlist) {
					dbobj = (BasicDBObject) ((BasicDBObject) list)
							.get(MONGODB_code);
					if (dbobj == null)
						continue;
					if (dbobj.containsField("full")) {
						fullset.add(dbobj.getString("full"));
					}
					if (dbobj.containsField("section")) {
						sectionset.add(dbobj.getString("section"));
					}
					if (dbobj.containsField("class")) {
						classset.add(dbobj.getString("class"));
					}
					if (dbobj.containsField("subClass")) {
						subclassset.add(dbobj.getString("subClass"));
					}
					if (dbobj.containsField("mainGroup")) {
						maingroupclassset.add(dbobj.getString("mainGroup"));
					}
					if (dbobj.containsField("subGroup")) {
						subgroupclassset.add(dbobj.getString("subGroup"));
					}
				}

				if (fullset.size() > 0) {
					solrDocument.setField(SOLR_ICL,
							fullset.toArray(new String[] {}));
//					solrDocument.setField(SOLR_IPC_COUNT, fullset.size());
				}
				if (sectionset.size() > 0) {
					solrDocument.setField(SOLR_IPC_SECTION,
							sectionset.toArray(new String[] {}));
//					solrDocument.setField(SOLR_IPC_SECTION_COUNT,
//							sectionset.size());
				}
				if (classset.size() > 0) {
					solrDocument.setField(SOLR_IPC_CLASS,
							classset.toArray(new String[] {}));
//					solrDocument
//							.setField(SOLR_IPC_CLASS_COUNT, classset.size());
				}
				if (subclassset.size() > 0) {
					solrDocument.setField(SOLR_IPC_SUB_CLASS,
							subclassset.toArray(new String[] {}));
//					solrDocument.setField(SOLR_IPC_SUB_CLASS_COUNT,
//							subclassset.size());
				}
				// no need to index ipc sub group,it's equal to ipc and in solr
				// ,it's copied to icl_facet
				// if (subgroupclassset.size() > 0)
				// {
				// solrDocument.setField(SOLR_IPC_SUB_GROUP,
				// subgroupclassset.toArray(new String[] {}));
				// }
				if (maingroupclassset.size() > 0) {
					solrDocument.setField(SOLR_IPC_MAIN_GROUP,
							maingroupclassset.toArray(new String[] {}));
//					solrDocument.setField(SOLR_IPC_MAIN_GROUP_COUNT,
//							maingroupclassset.size());
				}
			} else if (key.equalsIgnoreCase(SOLR_UPC)) {
				Set<String> fullset = new HashSet<String>();
				Set<String> classset = new HashSet<String>();
				for (Object list : classlist) {
					dbobj = (BasicDBObject) ((BasicDBObject) list)
							.get(MONGODB_code);
					if (dbobj.containsField("full")) {
						fullset.add(dbobj.getString("full"));
					}

					if (dbobj.containsField("class")) {
						classset.add(dbobj.getString("class"));
					}

				}

				if (fullset.size() > 0) {
					solrDocument.setField(SOLR_CCL,
							fullset.toArray(new String[] {}));
				}
				if (classset.size() > 0) {
					solrDocument.setField(SOLR_UPC_CALSS,
							classset.toArray(new String[] {}));
				}

			} else if (key.equalsIgnoreCase(SOLR_LOC)) {
				Set<String> fullset = new HashSet<String>();
				Set<String> classset = new HashSet<String>();
				for (Object list : classlist) {
					dbobj = (BasicDBObject) ((BasicDBObject) list)
							.get(MONGODB_code);
					if (dbobj.containsField("full")) {
						fullset.add(dbobj.getString("full"));
					}

					if (dbobj.containsField("class")) {
						classset.add(dbobj.getString("class"));
					}

				}

				if (fullset.size() > 0) {
					solrDocument.setField(SOLR_LOC,
							fullset.toArray(new String[] {}));
				}
				if (classset.size() > 0) {
					solrDocument.setField(SOLR_LOC_CLASS,
							classset.toArray(new String[] {}));
				}

			} else if (key.equalsIgnoreCase(SOLR_FI)) {
				Set<String> fullset = new HashSet<String>();
				for (Object list : classlist) {
					dbobj = (BasicDBObject) ((BasicDBObject) list)
							.get(MONGODB_code);
					if (dbobj.containsField("full")) {
						fullset.add(dbobj.getString("full"));
					}

				}

				if (fullset.size() > 0) {
					solrDocument.setField(SOLR_FI,
							fullset.toArray(new String[] {}));
				}

			} else if (key.equalsIgnoreCase(SOLR_FTERM)) {
				Set<String> fullset = new HashSet<String>();
				for (Object list : classlist) {
					dbobj = (BasicDBObject) ((BasicDBObject) list)
							.get(MONGODB_code);
					if (dbobj.containsField("full")) {
						fullset.add(dbobj.getString("full"));
					}

				}

				if (fullset.size() > 0) {
					solrDocument.setField(SOLR_FTERM,
							fullset.toArray(new String[] {}));
				}

			}

		}

		// set main ipc,if there exits ipc,pick up the 1st of ipc,or
		// pick up the 1st of ipcr
		BasicDBObject mobj = null;
		if (typeobj.containsField(MONGODB_class_ipc)) {
			mobj = (BasicDBObject) ((BasicDBObject) ((BasicDBList) typeobj
					.get(MONGODB_class_ipc)).get(0)).get(MONGODB_code);
		} else if (typeobj.containsField(MONGODB_class_ipcr)) {
			mobj = (BasicDBObject) ((BasicDBObject) ((BasicDBList) typeobj
					.get(MONGODB_class_ipcr)).get(0)).get(MONGODB_code);
		}
		if (mobj != null) {

			if (mobj.containsField("full")) {
				solrDocument.setField(SOLR_IPC_M, mobj.getString("full"));
			}
			if (mobj.containsField("section")) {
				solrDocument.setField(SOLR_IPC_M_SECTION,
						mobj.getString("section"));
				solrDocument.setField(SOLR_ICL_MAINCLASS,
						mobj.getString("section"));
			}
			if (mobj.containsField("class")) {
				solrDocument
						.setField(SOLR_IPC_M_CLASS, mobj.getString("class"));
			}
			if (mobj.containsField("subClass")) {
				solrDocument.setField(SOLR_IPC_M_SUB_CLASS,
						mobj.getString("subClass"));
			}
			if (mobj.containsField("mainGroup")) {
				solrDocument.setField(SOLR_IPC_M_MAIN_GROUP,
						mobj.getString("mainGroup"));
			}

		}

		BasicDBObject cpcobj = null;
		BasicDBList cpclist = null;
		if (typeobj.containsField(MONGODB_class_cpc)) {
			cpclist = (BasicDBList) typeobj.get(MONGODB_class_cpc);
		}

		if (cpclist != null) {
			List<String> cpcfulllist = new ArrayList<String>();
			Set<String> cpcsectionset = new HashSet<String>();
			Set<String> cpcclassset = new HashSet<String>();
			Set<String> cpcsubclassset = new HashSet<String>();
			Set<String> cpcmaingroupset = new HashSet<String>();
			for (Object object : cpclist) {
				cpcobj = (BasicDBObject) ((BasicDBObject) object)
						.get(MONGODB_code);
				if (cpcobj == null)
					continue;
				if (cpcobj.containsField("full")) {
					cpcfulllist.add(cpcobj.getString("full"));
				}
				if (cpcobj.containsField("section")) {
					cpcsectionset.add(cpcobj.getString("section"));
				}
				if (cpcobj.containsField("class")) {
					cpcclassset.add(cpcobj.getString("class"));
				}
				if (cpcobj.containsField("subClass")) {
					cpcsubclassset.add(cpcobj.getString("subClass"));
				}
				if (cpcobj.containsField("mainGroup")) {
					cpcmaingroupset.add(cpcobj.getString("mainGroup"));
				}

			}
			solrDocument.setField(SOLR_CPC, cpcfulllist);
			solrDocument.setField(SOLR_CPC_SECTION, cpcsectionset);
			solrDocument.setField(SOLR_CPC_CLASS, cpcclassset);
			solrDocument.setField(SOLR_CPC_SUB_CLASS, cpcsubclassset);
			solrDocument.setField(SOLR_CPC_MAIN_GROUP, cpcmaingroupset);
		}
	}

//	private void handleEmbeddedCitation(SolrInputDocument solrDocument,
//			DBObject mongoDocument) {
//		Object obj = mongoDocument.get(MONGODB_citation);
//		if (obj != null) {
//			BasicDBList cites = (BasicDBList) obj;
//
//			if (cites != null) {
//				List<String> backwardCitation = new LinkedList<String>();
//
//				for (Object citationObject : cites) {
//					String pn = ((BasicDBObject) citationObject)
//							.getString(MONGODB_pn);
//					backwardCitation.add(pn);
//				}
//
//				if (mongoDocument.containsField(MONGODB_analyzed_data)) {
//
//					if (((BasicDBObject) mongoDocument
//							.get(MONGODB_analyzed_data)).get(MONGODB_bcr) != null)
//						solrDocument.setField(SOLR_BCR, Float
//								.valueOf(((BasicDBObject) mongoDocument
//										.get(MONGODB_analyzed_data)).get(
//										MONGODB_bcr).toString()));
//
//				}
//
//				if (backwardCitation.size() > 0) {
//					solrDocument.setField(SOLR_CITE_FACET, backwardCitation);
//					solrDocument.setField(SOLR_CITE_COUNT,
//							backwardCitation.size());
//
//				}
//			}
//		}
//	}

	private void handleEmbeddedPerson(SolrInputDocument solrDocument,
			DBObject mongoDocument, String field) {
		DBObject obj = (DBObject) mongoDocument.get(field);

		if (obj != null) {
			// / PERSON
			Map<String, List<String>> assigneeMap = new HashMap<String, List<String>>();
			Map<String, List<String>> inventorMap = new HashMap<String, List<String>>();

			List<String> anContryList = new ArrayList<String>();

			String language = "UNKNOWN";
			BasicDBList personList = (BasicDBList) obj
					.get(MONGODB_personOriginal);
			if (personList == null || personList.size() == 0) {
				personList = (BasicDBList) obj.get(MONGODB_personDocdb);
				language = "EN";
				if (personList == null || personList.size() == 0) {
					return;
				} else {
					for (Object p : personList) {
						((BasicDBObject) p).append(MONGODB_lang, language);
					}
				}
			}
			String type = maptoMongoType(field);
			if (type == null)
				return;

			for (Object personObject : personList) {
				BasicDBObject person = (BasicDBObject) personObject;

				String name = person.getString(MONGODB_name);
				language = person.getString(MONGODB_lang);

				if (name == null) {
					continue;
				}
				// FIX some processing error of mongodb
				if (name.startsWith(", ")) {
					try {
						name = name.substring(3, name.length()).trim();
						LOG.warn("fix name: "
								+ mongoDocument.get(MONGODB_pn).toString());
					} catch (StringIndexOutOfBoundsException e) {
						LOG.error("problem fixing name '" + name + "' \tpn: "
								+ mongoDocument.get(MONGODB_id).toString(), e);
					}
				}

				if (type.equalsIgnoreCase(MONGODB_AN)) {
					List<String> list = assigneeMap.get(language);
					if (list != null) {
						list.add(name);
					} else {
						list = new LinkedList<>();
						list.add(name);
						assigneeMap.put(language, list);
					}
				} else if (type.equalsIgnoreCase(MONGODB_IN)) {
					List<String> list = inventorMap.get(language);
					if (list != null) {
						list.add(name);
					} else {
						list = new LinkedList<>();
						list.add(name);
						inventorMap.put(language, list);
					}
				}
			}

			if (assigneeMap.size() > 0) {
				List<String> assigneeList = assigneeMap.get(LANG.EN.toString());
				Map<String, List<String>> nnamemap = getNormalizeList(assigneeList);
				List<String> normalizedNameList = nnamemap.get("name");
				List<String> normalizedNNameList = nnamemap.get("nname");

				if (assigneeList != null) {
					solrDocument.setField(SOLR_AN, assigneeList);
					solrDocument.setField(SOLR_AN_FACET, assigneeList);
//					solrDocument.setField(SOLR_AN_COUNT, assigneeList.size());

					if (normalizedNameList.size() > 0) {
						solrDocument.setField(SOLR_ANS, normalizedNameList);
					} else {
						solrDocument.setField(SOLR_ANS, assigneeList);
					}

					if (normalizedNNameList.size() > 0) {
						solrDocument.setField(SOLR_ANS_FACET,
								normalizedNNameList);
					} else {
						solrDocument.setField(SOLR_ANS_FACET, assigneeList);
					}

				} else {
					if (normalizedNNameList.size() > 0) {
						solrDocument.setField(SOLR_AN_FACET,
								normalizedNNameList);
					}
				}
				assigneeMap.clear();
			}

			if (inventorMap.size() > 0) {
				List<String> inventorList = inventorMap.get(LANG.EN.toString());

				Map<String, List<String>> nnamemap = getNormalizeList(inventorList);
				List<String> normalizedNameList = nnamemap.get("name");
				List<String> normalizedNNameList = nnamemap.get("nname");

				if (inventorList != null) {
					solrDocument.setField(SOLR_IN, inventorList);
					solrDocument.setField(SOLR_IN_FACET, inventorList);

					if (normalizedNameList.size() > 0) {
						solrDocument.setField(SOLR_INS, normalizedNameList);
					} else {
						solrDocument.setField(SOLR_INS, inventorList);
					}
					if (normalizedNNameList.size() > 0) {
						solrDocument.setField(SOLR_INS_FACET,
								normalizedNNameList);
					} else {
						solrDocument.setField(SOLR_INS_FACET, inventorList);
					}
				} else {
					if (normalizedNNameList.size() > 0) {

						solrDocument.setField(SOLR_IN_FACET,
								normalizedNNameList);
					}

				}
				inventorMap.clear();
			}

			if (anContryList.size() > 0) {
				solrDocument.setField(SOLR_AN_COUNTRY, anContryList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, List<String>> getNormalizeList(List<String> namesList) {
		List<String> normalizedNamesList = new LinkedList<String>();
		List<String> normalizedNNamesList = new LinkedList<String>();

		if (namesList != null && namesList.size() > 0) {
			DBCursor cursor = null;
			for (String name : namesList) {
				cursor = IndexUtil.getNormalNameCollection().find(
						new BasicDBObject(MONGODB_name, name));

				String nName = name;
				int nNameSize = 0;
				while (cursor.hasNext()) {
					BasicDBObject object = (BasicDBObject) cursor.next();
					int currentNameSize = ((List<String>) object
							.get(MONGODB_name)).size();
					String currentNName = object.getString(MONGODB_nname);
					if (currentNameSize > nNameSize) {
						nName = currentNName;
						nNameSize = currentNameSize;
					} else if (currentNameSize == nNameSize
							&& nName.length() > currentNName.length()) {
						nName = currentNName;
					}
				}
				if (cursor != null) {
					cursor.close();
				}
				normalizedNNamesList.add(nName);
			}

			QueryBuilder query = QueryBuilder.start(MONGODB_name);
			query.in(namesList);

			normalizedNamesList = IndexUtil.getNormalNameCollection().distinct(
					MONGODB_name, query.get());

		}

		HashMap<String, List<String>> map = new HashMap<>();
		map.put("name", normalizedNamesList);
		map.put("nname", normalizedNNamesList);
		return map;
	}

//	private void handleCited(SolrInputDocument solrDocument,
//			DBObject mongoDocument) {
//		String pn = (String) mongoDocument.get(MONGODB_pn);
//		long citedcount = 0;
//		if (pn != null) {
//			citedcount = IndexUtil.getPatentCollection().count(
//					new BasicDBObject("citation.pn", pn));
//			if (citedcount > 0) {
//				solrDocument.setField(SOLR_CITED_COUNT, citedcount);
//			}
//		}
//	}

//	private void handleFamilyCount(SolrInputDocument solrDocument,
//			DBObject mongoDocument) {
//		String docdbfamily = null;
//		String inpadocfamily = null;
//		if (mongoDocument.containsField(MONGODB_family)) {
//			docdbfamily = ((BasicDBObject) mongoDocument.get(MONGODB_family))
//					.getString(MONGODB_family_docdb);
//			inpadocfamily = ((BasicDBObject) mongoDocument.get(MONGODB_family))
//					.getString(MONGODB_family_inpadoc);
//		}
//
//		if (!isEmpty(docdbfamily)) {
//			long docdbcount = IndexUtil.getPatentCollection().count(
//					new BasicDBObject(MONGODB_family + "."
//							+ MONGODB_family_docdb, docdbfamily));
//			if (docdbcount > 0) {
//				solrDocument.setField(SOLR_DOCDB_FAMILY_COUNT, docdbcount);
//			}
//
//		}
//		if (!isEmpty(inpadocfamily)) {
//			long inpadoccount = IndexUtil.getPatentCollection().count(
//					new BasicDBObject(MONGODB_family + "."
//							+ MONGODB_family_inpadoc, inpadocfamily));
//			if (inpadoccount > 0) {
//				solrDocument.setField(SOLR_INPADOC_FAMILY_COUNT, inpadoccount);
//			}
//
//		}
//	}

	private String maptoMongoType(String field) {
		if (field.equals(MONGODB_personAe))
			return MONGODB_AE;
		else if (field.equals(MONGODB_personAn))
			return MONGODB_AN;
		else if (field.equals(MONGODB_personAt))
			return MONGODB_AT;
		else if (field.equals(MONGODB_personAtc))
			return MONGODB_ATC;
		else if (field.equals(MONGODB_personIn))
			return MONGODB_IN;
		else if (field.equals(MONGODB_personPe))
			return MONGODB_PE;

		return null;
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

}
