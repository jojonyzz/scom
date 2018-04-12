package cn.imust.ys.scom.student.domain;

/**
 * 违纪处理
 * */
public class BPrinciple {
	private Integer id ;
	private Student student;
	private String gyearName;//年级名称
	private String reason ;//处分原因
	private String level ;// 处分级别
	private String mark;//个人签字
	private Term term;
	
	public String getSno(){
		if(student!=null){
			return student.getSno();
		}
		return "";
	}
	public String getName(){
		if(student!=null){
			return student.getName();
		}
		return "";
	}
	
	public String getTime(){
		if(term!=null){
			return term.getTime();
		}
		return "";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getGyearName() {
		return gyearName;
	}
	public void setGyearName(String gyearName) {
		this.gyearName = gyearName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public BPrinciple() {
		super();
	}
	
}
