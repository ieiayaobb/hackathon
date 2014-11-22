package com.hackathon.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CommonUtil {

	public static Map<String, String> loadConfig() {
		Map<String, String> configMap = new HashMap<>();
		Properties config = new Properties();
		try (InputStream is = CommonUtil.class
				.getResourceAsStream("/config.properties")) {
			config.load(is);
			for (Object obj : config.keySet()) {
				String key = obj.toString();
				String value = config.getProperty(key);
				configMap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configMap;
	}

}
