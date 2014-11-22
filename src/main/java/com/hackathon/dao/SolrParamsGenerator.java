package com.hackathon.dao;

import com.hackathon.base.Constant;
import com.hackathon.bean.AssigneeBean;
import com.hackathon.util.SolrUtil;
import com.hackathon.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SolrParamsGenerator {
	public static final Logger LOG = Logger
			.getLogger(SolrParamsGenerator.class);

	public SolrParams getTopAssigneeFacetQueryParams(
			List<AssigneeBean> assigneeList, String search_mode) {
		List<String> queryList = new LinkedList<>();
		for (AssigneeBean assigneeBean : assigneeList) {
			queryList.add(StringUtil.assignQueryForAssignee(
					assigneeBean.getName(), Constant.Fields.ANS_FACET));
		}
		String query = StringUtil.listToString(queryList, " OR ");

		SolrQuery solrQuery = new SolrQuery(query);
		solrQuery.set(CommonParams.ROWS, 0);
		solrQuery.set(CommonParams.QT, "/select");
		solrQuery.set(FacetParams.FACET, true);
		for (String facetQuery : queryList) {
			solrQuery.addFacetQuery(facetQuery);
		}

		return solrQuery;
	}

	// limit to 20 to make sure that the query to solr is fast
	public ModifiableSolrParams getClassificationGroupedBySearchModeFromAssigneeParams(
			String assignee, String search_mode, int limit, String facetField) {

		ModifiableSolrParams params = new ModifiableSolrParams();
		params.set("q", StringUtil.assignQueryForAssignee(assignee));

		params.set("facet", true);
		params.set("facet.field", facetField);
		params.set("facet.mincount", 1);
		/*
		 * facet sort only support by index/count, make sure we set it to count
		 * because limit can be -1 and then it would be index!
		 */
		params.set("facet.sort", "count");
		params.set("facet.limit", limit);
		params.set("rows", 0);
		params.set("qt", "/select");

		return params;
	}
	public ModifiableSolrParams getTopAssigneeParams(
			List<String> classificationCodeList, String facetFieldStr, int limit) {
		ModifiableSolrParams params = new ModifiableSolrParams();

		if ((classificationCodeList != null)
				&& (classificationCodeList.size() > 0)) {

			if (classificationCodeList.size() < 20
					&& classificationCodeList.size() >= 3) {

				List<String> fqQuery = new LinkedList<String>();

				for (int x = 0; x < classificationCodeList.size();) {
					String firstCode = classificationCodeList.get(x);

					for (int y = x + 1; y < classificationCodeList.size(); y++) {
						String secondCode = classificationCodeList.get(y);

						for (int z = y + 1; z < classificationCodeList.size(); z++) {
							String thirdCode = classificationCodeList.get(z);

							StringBuilder sb = new StringBuilder();
							sb.append("(");
							sb.append(firstCode);
							sb.append(" AND ");
							sb.append(secondCode);
							sb.append(" AND ");
							sb.append(thirdCode);
							sb.append(")");

							fqQuery.add(sb.toString());
						}
					}

					break;
				}

				params.set(
						"q",
						String.format("%s:(%s)", facetFieldStr,
								StringUtil.listToString(fqQuery, " OR ")));

			} else if (classificationCodeList.size() == 2) {
				params.set("q", String.format("%s:(%s)", facetFieldStr,
						StringUtil
								.listToString(classificationCodeList, " AND ")));

			} else if (classificationCodeList.size() == 1) {
				params.set("q", String.format("%s:(%s)", facetFieldStr,
						StringUtil.listToString(classificationCodeList, "")));

			} else {
				params.set("q", String
						.format("%s:(%s)", facetFieldStr, StringUtil
								.listToString(classificationCodeList, " OR ")));
			}

			params.set("facet", true);
			params.set("facet.field", Constant.Fields.ANS_FACET);
			params.set("facet.mincount", 1);
			params.set("facet.limit", limit);
			/*
			 * facet sort only support by index/count, make sure we set it to
			 * count because limit can be -1 and then it would be index!
			 */
			params.set("facet.sort", "count");
			params.set("rows", 0);
			params.set("qt", "/select");
		}

		return params;
	}
}
