package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class Term implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String year;
	private String termname;
	private Set<CollengTest> collengTests = new HashSet<CollengTest>(0);
	private Set<Scholarship> scholarships = new HashSet<Scholarship>(0);
	private Set<Grade> grades = new HashSet<Grade>(0);
	private Set<Course> courses = new HashSet<Course>(0);
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public String getTime(){
		return year+" " + termname;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTermname() {
		return termname;
	}
	public void setTermname(String termname) {
		this.termname = termname;
	}
	public Set<CollengTest> getCollengTests() {
		return collengTests;
	}
	public void setCollengTests(Set<CollengTest> collengTests) {
		this.collengTests = collengTests;
	}
	public Set<Scholarship> getScholarships() {
		return scholarships;
	}
	public void setScholarships(Set<Scholarship> scholarships) {
		this.scholarships = scholarships;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}