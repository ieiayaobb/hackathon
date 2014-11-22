package com.hackathon.bean;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class GraphBean {

	private String m_id;
	private String m_name;
	private Data m_data;
	private List<Adjacency> m_adjacencies = new LinkedList<>();

	public GraphBean() {
	}

	public GraphBean(String id, String name, String type) {
		m_id = id;
		m_name = name;
		m_data = new Data(type);
	}

	public void addAdjacency(Adjacency adjacency) {
		m_adjacencies.add(adjacency);
	}

	public void addAdjacency(String nodeFrom, String nodeTo) {
		Adjacency adjacency = new Adjacency(nodeFrom, nodeTo);
		m_adjacencies.add(adjacency);
	}

	@JSONField(name = "id")
	public String getId() {
		return m_id;
	}

	@JSONField(name = "name")
	public String getName() {
		return m_name;
	}

	@JSONField(name = "data")
	public Data getData() {
		return m_data;
	}

	@JSONField(name = "adjacencies")
	public List<Adjacency> getAdjacencies() {
		return m_adjacencies;
	}

}
