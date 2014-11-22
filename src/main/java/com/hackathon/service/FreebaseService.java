package com.hackathon.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
@Service
public class FreebaseService {
    public static void main(String[] args) throws IOException {
        String query = "[{\"type\":\"/business/business_operation\",\"name\":\"Apple\"}]";
        GetMethod getMethod = new GetMethod("https://www.googleapis.com/freebase/v1/mqlread?query=" + URLEncoder.encode(query, "UTF-8"));
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(getMethod);
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Method failed: "
                    + getMethod.getStatusLine());
        }

        byte[] responseBody = getMethod.getResponseBody();
        System.out.println(new String(responseBody));
    }
}
