package com.hackathon.bean;

import java.util.LinkedList;
import java.util.List;

public class TreeBean {

	private String m_id;
	private String m_name;
	private List<TreeBean> m_children = new LinkedList<>();

	public TreeBean() {
	}

	public TreeBean(String id, String name) {
		m_id = id;
		m_name = name;
	}

	public void addChild(String id, String name) {
		TreeBean bean = new TreeBean(id, name);
		m_children.add(bean);
	}

	public void addChild(TreeBean bean) {
		m_children.add(bean);
	}

	public String getId() {
		return m_id;
	}

	public String getName() {
		return m_name;
	}

	public List<TreeBean> getChildren() {
		return m_children;
	}

}
