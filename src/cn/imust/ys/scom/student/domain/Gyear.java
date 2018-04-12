package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 年级
 */

public class Gyear implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Major major;
	private String name;
	private Set<StuClass> stuClasses = new HashSet<StuClass>(0);
	// Constructors

	/** default constructor */
	public Gyear() {
	}
	public String getAcademyName(){
		if(major!=null){
			return major.getAcademy().getAcadname();
		}
		return "";
	}
	public String getMajorname(){
		if(major!=null){
			return major.getMajorName();
		}
		return "";
	}

	/** minimal constructor */
	public Gyear(Integer id, Major major) {
		this.id = id;
		this.major = major;
	}

	/** full constructor */
	public Gyear(Integer id, Major major, String name, Set<StuClass> stuClasses) {
		this.id = id;
		this.major = major;
		this.name = name;
		this.stuClasses = stuClasses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Major getMajor() {
		return this.major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<StuClass> getStuClasses() {
		return this.stuClasses;
	}

	public void setStuClasses(Set<StuClass> stuClasses) {
		this.stuClasses = stuClasses;
	}

}