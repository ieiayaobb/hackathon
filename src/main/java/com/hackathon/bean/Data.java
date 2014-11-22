package com.hackathon.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Data {

	private String m_type;
	private String m_dim;

	public Data() {
	}

	public Data(String type) {
		m_type = type;
		m_dim = "20";
	}

	@JSONField(name = "$type")
	public String getType() {
		return m_type;
	}

	@JSONField(name = "$dim")
	public String getDim() {
		return m_dim;
	}

}
