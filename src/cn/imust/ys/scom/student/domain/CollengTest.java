package cn.imust.ys.scom.student.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.imust.ys.scom.base.exception.ScomException;

/**
 * CollengTest entity. @author MyEclipse Persistence Tools
 */

public class CollengTest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Student student;
	private Term term;
	private RankList rankList;
	private String score;
	private Date date;//录入时间
	private String importName;//录入人姓名
	
	public String getCourse(){
		if(rankList!=null){
			return rankList.getName();
		}else{
			return "";
		}
	}
	public String getName(){
		if(student!=null){
			return student.getName();
		}else{
			return "";
		}
	}
	public String getSno(){
		if(student!=null){
			return student.getSno();
		}else{
			return "";
		}
	}
	public String getTime(){
		return term.getTime();
	}
	public String getImportName() {
		return importName;
	}

	public void setImportName(String importName) {
		this.importName = importName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 得到学分
	 * */
	public Double getCredit() {
		// 首先判断是英语还是计算机
		String name = rankList.getName();
		if(score.equals("")){
			return 0.0;
		}
		double parseScore = Double.parseDouble(score);
		if (name.startsWith("计算机")) {

			Map<String, Double> maps = new HashMap<String, Double>();
			maps.put("计算机-二级", 3.0);
			maps.put("计算机-三级", 4.0);
			maps.put("计算机-四级", 5.0);
			Double grade = maps.get(name);
			if(grade==null){
				throw new ScomException("无效课程名");
			}
			return grade;
		}else if(name.startsWith("英语")){
			//判断是四级否
			if(name.equals("英语-四级")){
				if(parseScore >= 639){
					return 6.0;
				}
				if(parseScore >=568){
					return 5.0;
				}
				if(parseScore >=497){
					return 4.0;
				}
				if(parseScore >=425){
					return 3.0;
				}
				if(parseScore < 425){
					return 0.0;
				}
			}else if(name.equals("英语-六级")){
				if(parseScore >= 639){
					return 8.0;
				}
				if(parseScore >=568){
					return 7.0;
				}
				if(parseScore >=497){
					return 6.0;
				}
				if(parseScore >=425){
					return 5.0;
				}
				if(parseScore < 425){
					return 0.0;
				}
			}else{
				throw new ScomException("无效课程");
			}
		}else{
			throw new ScomException("无效课程");
		}
		return 0.0;
	}

	// Constructors

	/** default constructor */
	public CollengTest() {
	}
	

	/** minimal constructor */
	public CollengTest(Integer id, Student student) {
		this.id = id;
		this.student = student;
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

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public RankList getRankList() {
		return rankList;
	}

	public void setRankList(RankList rankList) {
		this.rankList = rankList;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}