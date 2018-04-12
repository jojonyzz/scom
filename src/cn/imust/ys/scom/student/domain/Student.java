package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Dept dept;
	private String name;
	private String sno;
	private StuClass stuClass;
	private Techno techno;
	private String qq;
	private String weixin;
	private String email;
	private String tel;
	private Set<Society> societies = new HashSet<Society>(0);
	private Set<CollengTest> collengTests = new HashSet<CollengTest>(0);
	private Set<User> users = new HashSet<User>(0);
	private Set<Techno> technos = new HashSet<Techno>(0);
	private Set<Grade> grades = new HashSet<Grade>();
	private Set<BPrinciple> principles = new HashSet<BPrinciple>();
	public Set<BPrinciple> getPrinciples() {
		return principles;
	}
	public void setPrinciples(Set<BPrinciple> principles) {
		this.principles = principles;
	}
	/**
	 * 学习成绩
	 * 平均学分绩＝Σ(课程成绩×课程学分)/Σ课程学分
	 * */
	public Double getComStudyGrade(){
		Iterator<Grade> iterator = grades.iterator();
		Double allGrade = new Double(0);
		Double allCredit = new Double(0);
		while(iterator.hasNext()){
			Grade next = iterator.next();
			Course course = next.getCourse();
			if(next.getScore() == -1){
				continue;
			}
			allGrade += next.getScore()*course.getCredit();
			allCredit += course.getCredit();
		}
		if(allCredit == 0.0){
			return -1.0;
		}
		return allGrade/allCredit;
	}
	/**
	 * 合计
	 * */
	public Double getAmount(){
		Iterator<Grade> iterator = grades.iterator();
		Double allGrade = new Double(0);//总成绩
		Double allCredit = new Double(0);//总学分
		while(iterator.hasNext()){
			Grade next = iterator.next();
			Course course = next.getCourse();
			if(next.getScore() == -1){
				continue;
			}
			allGrade += next.getScore()*course.getCredit();
			allCredit += course.getCredit();
		}
		Iterator<CollengTest> iterator2 = collengTests.iterator();
		while(iterator2.hasNext()){
			CollengTest next = iterator2.next();
			allCredit += next.getCredit();
			allGrade += 95*next.getCredit();
		}
		if(allCredit == 0.0){
			return -1.0;
		}
		return allGrade/allCredit;
	}
	/**
	 * 英语成绩
	 * */
	public Double getEnglish(){
		Iterator<CollengTest> iterator = collengTests.iterator();
		if(iterator != null){
			while(iterator.hasNext()){
				CollengTest next = iterator.next();
				if(next.getRankList().getName().startsWith("英语")){
					return Double.parseDouble(next.getScore());
				}
			}
		}
		return 0.0;
	}
	/**
	 * 计算机成绩
	 * */
	public String getComputer(){
		Iterator<CollengTest> iterator = collengTests.iterator();
		String g = "";
		if(iterator != null){
			while(iterator.hasNext()){
				CollengTest next = iterator.next();
				if(next.getRankList().getName().startsWith("计算机")){
					g = g + next.getScore()+" ";
				}
			}
		}
		return g;
	}
	/**
	 * 不及格门数
	 * */
	public int getNoPass(){
		Iterator<Grade> iterator = grades.iterator();
		int count = 0;
		while(iterator.hasNext()){
			if(iterator.next().getScore()<60){
				count ++ ;
			}
		}
		return count;
	}
	/**
	 * 是否违纪
	 * */
	public Integer getPrinciple(){
		Iterator<BPrinciple> iterator = principles.iterator();
		while(iterator.hasNext()){
			return 1;
		}
		return 0;
	}
	// Constructors

	/** default constructor */
	public Student() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSno() {
		return sno;
	}



	public void setSno(String sno) {
		this.sno = sno;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public StuClass getStuClass() {
		return stuClass;
	}

	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}

	public Techno getTechno() {
		return techno;
	}

	public void setTechno(Techno techno) {
		this.techno = techno;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<Society> getSocieties() {
		return societies;
	}

	public void setSocieties(Set<Society> societies) {
		this.societies = societies;
	}

	public Set<CollengTest> getCollengTests() {
		return collengTests;
	}

	public void setCollengTests(Set<CollengTest> collengTests) {
		this.collengTests = collengTests;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Techno> getTechnos() {
		return technos;
	}

	public void setTechnos(Set<Techno> technos) {
		this.technos = technos;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 根据所在的年级判断是否为降级生
	 * true 为降级生
	 * */
	public boolean isJJ(){
		if(stuClass!=null){
			if(sno.substring(0, 2).equals(stuClass.getGyear().getName().substring(0, 2))){
				return false;
			}
		}
		return true;
	}
	/**
	 * 判断学生所有的科目成绩是否全为 -1
	 * 返回 true 说明全为 -1  
	 * */
	public boolean isAllNo(){
		Iterator<Grade> iterator = grades.iterator();
		while(iterator.hasNext()){
			if(-1 == iterator.next().getScore()){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
}