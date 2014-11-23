package com.hackathon.base;

import com.hackathon.dao.MongoDBDatabaseSingleton;
import com.hackathon.dao.SolrSingleton;
import com.hackathon.util.CommonUtil;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.net.UnknownHostException;

/**
 * Created by zhangxiaoxue on 14/11/22.
 */
public class LoadConfig extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        try {
            MongoDBDatabaseSingleton.getInstance(CommonUtil.loadConfig());
            SolrSingleton.getInstance(CommonUtil.loadConfig());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
