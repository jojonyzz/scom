package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * StuClass entity. @author MyEclipse Persistence Tools
 */

public class StuClass implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Gyear gyear;
	private String name;
	private Set<Teacher> teachers = new HashSet<Teacher>(0);
	private Set<Student> students = new HashSet<Student>(0);
	private Set<Course> courses = new HashSet<Course>();

	// Constructors

	public Set<Course> getCourses() {
		return courses;
	}
	public String getAcademyName(){
		if(gyear!=null){
			return gyear.getMajor().getAcademy().getAcadname();
		}
		return "";
	}
	public String getMajorname(){
		if(gyear!=null){
			return gyear.getMajor().getMajorName();
		}
		return "";
	}
	public String getGyearname(){
		if(gyear!=null ){
			return gyear.getName();
		}
		return "";
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	/** default constructor */
	public StuClass() {
	}

	/** minimal constructor */
	public StuClass(Integer id, Gyear gyear) {
		this.id = id;
		this.gyear = gyear;
	}
	public String getClassName(){
		return this.name;
	}

	/** full constructor */
	public StuClass(Integer id, Gyear gyear, String name, Set<Teacher> teachers,
			Set<Student> students) {
		this.id = id;
		this.gyear = gyear;
		this.name = name;
		this.teachers = teachers;
		this.students = students;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Gyear getGyear() {
		return this.gyear;
	}

	public void setGyear(Gyear gyear) {
		this.gyear = gyear;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Teacher> getTeachers() {
		return this.teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}