package com.hackathon.service;

import com.alibaba.fastjson.JSON;
import com.hackathon.bean.AssigneeBean;
import com.hackathon.bean.Company;
import com.hackathon.bean.GraphBean;
import com.hackathon.bean.TreeBean;
import com.hackathon.dao.SolrSingleton;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
@Service
public class FetchDataService {

    @Autowired
    private SimulateService simulateService;

    private String TREE_URL = "http://192.168.100.78:8080/hackson/getTree";
    private String GRAPH_URL = "http://192.168.100.78:8080/hackson/getGraph";

    public String getAllCompany(int limit) throws Exception {
//        List<Company> companies = new LinkedList<Company>();
//        String url = GRAPH_URL;
//        GetMethod getMethod = new GetMethod(url);
//        HttpClient httpClient = new HttpClient();
//        System.out.println("execute : " + url);
//        int statusCode = 0;
//        try {
//            statusCode = httpClient.executeMethod(getMethod);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.out.println("Method failed: "
//                        + getMethod.getStatusLine());
//            }
//
//            byte[] responseBody = getMethod.getResponseBody();
//            return new String(responseBody);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";

        List<AssigneeBean> assigneeBeans = SolrSingleton.getInstance().getTopCompanies(limit);
        List<GraphBean> result = new LinkedList<>();
        for(AssigneeBean assigneeBean : assigneeBeans){
            String name = assigneeBean.getName();

            long num = Math.round(Math.random() * 7 + 2);

            GraphBean graph = new GraphBean(name, name, "building" + num);

            List<AssigneeBean> competes = SolrSingleton.getInstance().industry(name);
            for(AssigneeBean compete : competes){
                graph.addAdjacency(name, compete.getName());
            }
            result.add(graph);
        }
        return JSON.toJSONString(result);
    }

    public String getComptetesByCompanyId(String id) throws Exception {
//        List<Company> competes = new LinkedList<Company>();
//        String url = TREE_URL + "?ID=" + id;
//        GetMethod getMethod = new GetMethod(url);
//        HttpClient httpClient = new HttpClient();
//        System.out.println("execute : " + url);
//        int statusCode = 0;
//        try {
//            statusCode = httpClient.executeMethod(getMethod);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.out.println("Method failed: "
//                        + getMethod.getStatusLine());
//            }
//
//            byte[] responseBody = getMethod.getResponseBody();
//            return new String(responseBody);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";

        List<AssigneeBean> competes_1 = SolrSingleton.getInstance().industry(id);

        TreeBean tree = new TreeBean(id, id);

        for(AssigneeBean compete_1 : competes_1){
            String subTreeName_1 = compete_1.getName();
            TreeBean subTreeBean_1 = new TreeBean(subTreeName_1,subTreeName_1);
            tree.addChild(subTreeBean_1);
            List<AssigneeBean> competes_2 = SolrSingleton.getInstance().industry(subTreeName_1);

            for(AssigneeBean compete_2 : competes_2){
                String subTreeName_2 = compete_2.getName();
                TreeBean subTreeBean_2 = new TreeBean(subTreeName_2,subTreeName_2);
                subTreeBean_1.addChild(subTreeBean_2);
//                List<AssigneeBean> competes_3 = SolrSingleton.getInstance().industry(subTreeName_2);
//                for(AssigneeBean compete_3 : competes_3){
//                    String subTreeName_3 = compete_3.getName();
//                    tree.addChild(new TreeBean(subTreeName_3,subTreeName_3));
//                }
            }
        }

        return JSON.toJSONString(tree);
    }
}
