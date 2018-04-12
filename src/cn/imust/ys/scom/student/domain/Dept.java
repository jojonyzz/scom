package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Academy academy;
	private String deptname;
	private Set<Society> societies = new HashSet<Society>(0);
	private Set<Student> students = new HashSet<Student>(0);

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** minimal constructor */
	public Dept(Integer id, Academy academy) {
		this.id = id;
		this.academy = academy;
	}

	/** full constructor */
	public Dept(Integer id, Academy academy, String deptname, Set<Society> societies,
			Set<Student> students) {
		this.id = id;
		this.academy = academy;
		this.deptname = deptname;
		this.societies = societies;
		this.students = students;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Set<Society> getSocieties() {
		return this.societies;
	}

	public void setSocieties(Set<Society> societies) {
		this.societies = societies;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}