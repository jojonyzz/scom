package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Society entity. @author MyEclipse Persistence Tools
 */

public class Society implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Dept dept;
	private String name;
	private String score;
	private String reason;
	private Term term;
	private Set<Student> students = new HashSet<Student>(0);
	
	// Constructors

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Society(Dept dept, String name, String score, String reason) {
		super();
		this.dept = dept;
		this.name = name;
		this.score = score;
		this.reason = reason;
	}

	/** default constructor */
	public Society() {
	}

	/** minimal constructor */
	public Society(Integer id, Dept dept) {
		this.id = id;
		this.dept = dept;
	}

	/** full constructor */
	public Society(Integer id, Dept dept, String name,
			String score, String reason, Set<Student> students) {
		this.id = id;
		this.dept = dept;
		this.name = name;
		this.score = score;
		this.reason = reason;
		this.students = students;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}