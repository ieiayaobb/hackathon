package com.hackathon.service;

import com.hackathon.bean.Company;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
@Service
public class FetchDataService {

    private String TREE_URL = "http://192.168.100.78:8080/hackson/getTree";
    private String GRAPH_URL = "http://192.168.100.78:8080/hackson/getGraph";

    public String getAllCompany() {
        List<Company> companies = new LinkedList<Company>();
        String url = GRAPH_URL;
        GetMethod getMethod = new GetMethod(url);
        HttpClient httpClient = new HttpClient();
        System.out.println("execute : " + url);
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Method failed: "
                        + getMethod.getStatusLine());
            }

            byte[] responseBody = getMethod.getResponseBody();
            return new String(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getComptetesByCompanyId(String id) {
        List<Company> competes = new LinkedList<Company>();
        String url = TREE_URL + "?ID=" + id;
        GetMethod getMethod = new GetMethod(url);
        HttpClient httpClient = new HttpClient();
        System.out.println("execute : " + url);
        int statusCode = 0;
        try {
            statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Method failed: "
                        + getMethod.getStatusLine());
            }

            byte[] responseBody = getMethod.getResponseBody();
            return new String(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
