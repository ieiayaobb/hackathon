package com.hackathon.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Markus Haense
 */
public class AssigneeBean implements Comparable<AssigneeBean> {

	private String m_name;
	private long m_numberOfPatents;

	public AssigneeBean(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public long getNumberOfPatents() {
		return m_numberOfPatents;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AssigneeBean other = (AssigneeBean) obj;

		if ((m_name == null && other.m_name != null)
				|| (m_name != null && other.m_name == null)
				|| !m_name.equals(other.m_name)) {
			return false;
		}
		if (m_numberOfPatents != other.m_numberOfPatents) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return 41 * (m_name == null ? 0 : m_name.hashCode())
				+ (int) (m_numberOfPatents == 0 ? 1 : m_numberOfPatents);
	}

	@Override
	public int compareTo(AssigneeBean o) {
		if (getName() != null && o.getName() != null
				&& !getName().equals(o.getName())) {
			return getName().compareTo(o.getName());
		}
		return new Long(getNumberOfPatents()).compareTo(new Long(o
				.getNumberOfPatents()));
	}

	public void setNumberOfPatents(long numberOfPatents) {
		m_numberOfPatents = numberOfPatents;
	}


	private double score;

	@JSONField(serialize = false, deserialize = false)
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}