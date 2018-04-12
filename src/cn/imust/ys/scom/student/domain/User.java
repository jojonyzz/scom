package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Student student;
	private Teacher teacher;
	private String account;
	private String pwd;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public User() {
		
	}
	
	public User(Student student, String account, String pwd, Set<Role> roles) {
		super();
		this.student = student;
		this.account = account;
		this.pwd = pwd;
		this.roles = roles;
	}


	public String getName() {
		if(student != null){
			return student.getName();
		}if(teacher != null){
			return teacher.getName();
		}else{
			return "管理员";
		}
	}


	/** minimal constructor */
	public User(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public User(Integer id, Student student, Teacher teacher, String account,
			String pwd, Set<Role> roles) {
		this.id = id;
		this.student = student;
		this.teacher = teacher;
		this.account = account;
		this.pwd = pwd;
		this.roles = roles;
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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}