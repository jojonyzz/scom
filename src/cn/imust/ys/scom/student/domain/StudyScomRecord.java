package cn.imust.ys.scom.student.domain;

/**
 * 学习测评记录
 * */
public class StudyScomRecord {
	
	private Integer id;
	private Term term;
	private StuClass stuClass;//年级
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
	public StudyScomRecord() {
		super();
	}
	public StuClass getStuClass() {
		return stuClass;
	}
	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}
	public StudyScomRecord(Term term, StuClass stuClass) {
		super();
		this.term = term;
		this.stuClass = stuClass;
	}
}
