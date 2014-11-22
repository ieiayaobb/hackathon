package com.hackathon.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Adjacency {

	private String m_nodeFrom;
	private String m_nodeTo;

	public Adjacency() {
	}

	public Adjacency(String nodeFrom, String nodeTo) {
		m_nodeFrom = nodeFrom;
		m_nodeTo = nodeTo;
	}

	@JSONField(name = "nodeFrom")
	public String getNodeFrom() {
		return m_nodeFrom;
	}

	@JSONField(name = "nodeTo")
	public String getNodeTo() {
		return m_nodeTo;
	}

}
