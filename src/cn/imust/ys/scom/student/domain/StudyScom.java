package cn.imust.ys.scom.student.domain;

public class StudyScom {
	private Integer id; 
	private String classname;//班级名称
	private String sno;//学号
	private String name;//姓名
	private Double grade;//学习成绩
	private String computer;//计算机成绩
	private Double english; //英语成绩
	private Double amount;//合计成绩
	private int nopass;//不及格门数 
	private Term term ;//学期
	private StuClass stuClass;
	private Gyear gyear;
	private Integer principle ;//是否违纪 ,违纪为 1, 未违纪为 0 
	private Integer rank ;//班级排名
	private Integer majorrank;//专业排名
	
	public Integer getMajorrank() {
		return majorrank;
	}
	public void setMajorrank(Integer majorrank) {
		this.majorrank = majorrank;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Gyear getGyear() {
		return gyear;
	}
	public void setGyear(Gyear gyear) {
		this.gyear = gyear;
	}
	
	public Integer getPrinciple() {
		return principle;
	}
	public void setPrinciple(Integer principle) {
		this.principle = principle;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public StuClass getStuClass() {
		return stuClass;
	}
	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	
	public String getComputer() {
		return computer;
	}
	public void setComputer(String computer) {
		this.computer = computer;
	}
	public Double getEnglish() {
		return english;
	}
	public void setEnglish(Double english) {
		this.english = english;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getNopass() {
		return nopass;
	}
	public void setNopass(int nopass) {
		this.nopass = nopass;
	}
	
}
