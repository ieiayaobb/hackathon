package com.hackathon.service;

import com.alibaba.fastjson.JSON;
import com.hackathon.dao.MongoDBDatabaseSingleton;
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

    public String getCompanyInfo(String name){
        return JSON.toJSONString(MongoDBDatabaseSingleton.getInstance().getCompanyInfo(name));
    }
}
