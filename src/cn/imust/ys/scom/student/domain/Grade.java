package cn.imust.ys.scom.student.domain;

/**
 * 学生成绩表
 * */
public class Grade {
	
	private Integer id ;
	private Term term;
	private Student student;
	private Course course;
	private Double score;
	
	public String getCno(){
		if(course!=null){
			return course.getCno();
		}
		return "";
	}
	public String getCname(){
		if(course!=null){
			return course.getCname();
		}
		return "";
	}
	public String getCredit(){
		if(course!=null){
			return course.getCredit().toString();
		}
		return "";
	}
	
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
