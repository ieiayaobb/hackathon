package com.hackathon.util;

import com.hackathon.base.Constant;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StringUtil {
	public static final String NEWLINE = System.getProperty("line.separator");

	public static <T> String listToString(List<T> list, String separator) {
		StringBuffer result = new StringBuffer();

		if ((list != null) && (list.size() > 0) && (separator != null)) {

			result.append(list.get(0));

			for (int i = 1; i < list.size(); i++) {
				if (list.get(i).toString().length() > 0) {
					result.append(separator);
					result.append(list.get(i));
				}
			}
		}

		return result.toString();
	}

	public static <T> String setToString(Set<T> set, String separator) {
		List<T> list = new ArrayList<T>(set);

		return listToString(list, separator);
	}

	public static String buildDocumentTypeRestriction(
			List<String> document_types) {
		return "DOCUMENT_TYPE:(" + listToString(document_types, " OR ") + ")";
		// return "(DOCUMENT_TYPE:"
		// + StringUtils.join(document_types, " OR DOCUMENT_TYPE:") + ")";
	}

	private static String assignQueryUsingAnAndAnsAndAnsFacet(String assignee) {
		String query = String
				.format("(AN:\"%s\" OR AN_CN:\"%s\" OR AN_DE:\"%s\" OR AN_FR:\"%s\" OR AN_JP:\"%s\" OR ANS:\"%s\" OR ANS_FACET:\"%s\")",
						assignee, assignee, assignee, assignee, assignee,
						assignee, assignee);
		return query;
	}

	private static String assignQueryUsingAnAndAnsFacet(String assignee) {
		String query = String
				.format("(AN:\"%s\" OR AN_CN:\"%s\" OR AN_DE:\"%s\" OR AN_FR:\"%s\" OR AN_JP:\"%s\" OR ANS_FACET:\"%s\")",
						assignee, assignee, assignee, assignee, assignee,
						assignee);
		return query;
	}

	private static String assignQueryUsingANSFacet(String assignee) {
		String query = String.format("(ANS_FACET:\"%s\")", assignee);
		return query;
	}

	public static String assignQueryForAssignee(String assignee) {
		return assignQueryForAssignee(assignee,
				Constant.Indicator.CURRENT_AN_FLAG);
	}

	public static String assignQueryForAssignee(String assignee, String flag) {
		if (StringUtils.equals(Constant.Fields.AN_AND_ANS_AND_ANS_FACET, flag)) {
			return assignQueryUsingAnAndAnsAndAnsFacet(assignee);
		} else if (StringUtils.equals(Constant.Fields.AN_AND_ANS_FACET, flag)) {
			return assignQueryUsingAnAndAnsFacet(assignee);
		} else if (StringUtils.equals(Constant.Fields.ANS_FACET, flag)) {
			return assignQueryUsingANSFacet(assignee);
		}
		return "";
	}


	private static String assignQueryForInventorUsingInAndIns(String inventor) {
		return String
				.format("(IN:\"%s\" OR IN_CN:\"%s\" OR IN_DE:\"%s\" OR IN_FR:\"%s\" OR IN_JP:\"%s\" OR INS:\"%s\")",
						inventor, inventor, inventor, inventor, inventor,
						inventor);
	}

	private static String assignQueryForInventorUsingINSFacet(String inventor) {
		return String.format("(INS_FACET:\"%s\")", inventor);
	}

	public static String assignQueryForInventor(String inventor) {
		return assignQueryForInventor(inventor,
				Constant.Indicator.CURRENT_IN_FLAG);
	}

	public static String assignQueryForInventor(String inventor, String flag) {
		if (StringUtils.equals(Constant.Fields.IN_AND_INS, flag)) {
			return assignQueryForInventorUsingInAndIns(inventor);
		} else if (StringUtils.equals(Constant.Fields.INS_FACET, flag)) {
			return assignQueryForInventorUsingINSFacet(inventor);
		}
		return "";
	}

	public static String clean(String val){
		return val.replaceAll("\'", "").replaceAll("\"", "").replaceAll("\\\\", "");
	}
}