package com.hackathon.bean;


import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
public class Company {
    private String name;

    private Map<String, String> alias = new HashMap<String, String>();
    private Map<String, String> description = new HashMap<String, String>();

    private String web;

    public Company(String name){
        this.name = name;
    }

    public void putAlias(String lan, String value){
        alias.put(lan, value);
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAlias() {
        return alias;
    }

    public void putDescription(String lan, String value){
        description.put(lan, value);
    }

    public Map<String, String> getDescription() {
        return description;
    }
}
