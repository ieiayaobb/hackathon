package com.hackathon.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

public class ClassificationBean implements Comparable<ClassificationBean> {

	private String m_code;

	private String m_title;

	private long m_count;

	private List<ClassificationBean> m_nodeList = new LinkedList<ClassificationBean>();

	public ClassificationBean(String code, String title) {
		m_code = code;
		m_title = title;
	}

	public ClassificationBean(String code, long count) {
		m_code = code;
		m_count = count;
	}

	public ClassificationBean(String code, String title, long count) {
		m_code = code;
		m_title = title;
		m_count = count;
	}

	public String getCode() {
		return m_code;
	}

	public String getTitle() {
		return m_title;
	}

	public long getCount() {
		return m_count;
	}

	public void setCode(String code) {
		m_code = code;
	}

	public void setTitle(String title) {
		m_title = title;
	}

	public void setCount(long count) {
		m_count = count;
	}

	public List<ClassificationBean> getNode() {
		return m_nodeList;
	}

	public void addNode(ClassificationBean bean) {
		m_nodeList.add(bean);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClassificationBean other = (ClassificationBean) obj;

		if (!getCode().equals(other.getCode())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return (41 * getCode().hashCode());
	}

	@Override
	public int compareTo(ClassificationBean o) {
		return getCode().compareTo(o.getCode());
	}
}