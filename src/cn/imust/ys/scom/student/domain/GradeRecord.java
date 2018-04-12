package cn.imust.ys.scom.student.domain;

/**
 * 成绩导入的记录表
 * */
public class GradeRecord {

	private Integer id;
	private Term term;
	private StuClass stuClass;
	
	public String getTime(){
		if(term!=null){
			return term.getTime();
		}
		return "";
	}
	public String getClassName(){
		if(stuClass!=null){
			return stuClass.getAcademyName()+"->"+stuClass.getMajorname()+"->"+stuClass.getGyearname()+"->"+stuClass.getName();
		}
		return "";
	}
	
	public GradeRecord() {
		super();
	}
	public GradeRecord(Term term, StuClass stuClass) {
		super();
		this.term = term;
		this.stuClass = stuClass;
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
	public StuClass getStuClass() {
		return stuClass;
	}
	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}
}
