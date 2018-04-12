package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Academy entity. @author MyEclipse Persistence Tools
 */

public class Academy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String acadname;
	private Set<Major> majors = new HashSet<Major>(0);
	private Set<Dept> depts = new HashSet<Dept>(0);

	// Constructors

	/** default constructor */
	public Academy() {
	}

	/** minimal constructor */
	public Academy(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Academy(Integer id, String acadname, Set<Major> majors, Set<Dept> depts) {
		this.id = id;
		this.acadname = acadname;
		this.majors = majors;
		this.depts = depts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcadname() {
		return this.acadname;
	}

	public void setAcadname(String acadname) {
		this.acadname = acadname;
	}

	public Set<Major> getMajors() {
		return this.majors;
	}

	public void setMajors(Set<Major> majors) {
		this.majors = majors;
	}

	public Set<Dept> getDepts() {
		return this.depts;
	}

	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}

}