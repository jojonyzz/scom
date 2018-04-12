package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Major entity. @author MyEclipse Persistence Tools
 */

public class Major implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Academy academy;
	private String majorName;
	private Set<Gyear> gyears = new HashSet<Gyear>(0);
	private Set<Scholarship> scholarships = new HashSet<Scholarship>(0);

	// Constructors
	public String getAcademyname(){
		if(academy!=null){
			return academy.getAcadname();
		}
		return "";
	}

	/** default constructor */
	public Major() {
	}

	/** minimal constructor */
	public Major(Integer id, Academy academy) {
		this.id = id;
		this.academy = academy;
	}

	/** full constructor */
	public Major(Integer id, Academy academy, String majorName, Set<Gyear> gyears,
			Set<Scholarship> scholarships) {
		this.id = id;
		this.academy = academy;
		this.majorName = majorName;
		this.gyears = gyears;
		this.scholarships = scholarships;
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

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Set<Gyear> getGyears() {
		return this.gyears;
	}

	public void setGyears(Set<Gyear> gyears) {
		this.gyears = gyears;
	}

	public Set<Scholarship> getScholarships() {
		return this.scholarships;
	}

	public void setScholarships(Set<Scholarship> scholarships) {
		this.scholarships = scholarships;
	}

}