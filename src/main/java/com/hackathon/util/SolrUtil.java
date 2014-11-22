package com.hackathon.util;

import com.hackathon.base.Constant;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;

import java.util.List;

public class SolrUtil {

    public static long parseNumFound(QueryResponse response, String search_mode) {
        if (response == null) {
            return 0;
        } else if (search_mode.equals(Constant.Default.ALL)) {
            return response.getResults().getNumFound();
        } else {
            return response.getGroupResponse().getValues().get(0).getNGroups();
        }
    }

}
