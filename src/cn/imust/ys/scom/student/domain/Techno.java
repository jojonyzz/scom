package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Techno entity. @author MyEclipse Persistence Tools
 */

public class Techno implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Student student;
	private String tname;
	private Set<Student> students = new HashSet<Student>(0);

	// Constructors

	/** default constructor */
	public Techno() {
	}

	/** minimal constructor */
	public Techno(Integer id, Student student) {
		this.id = id;
		this.student = student;
	}

	/** full constructor */
	public Techno(Integer id, Student student, String tname, Set<Student> students) {
		this.id = id;
		this.student = student;
		this.tname = tname;
		this.students = students;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}