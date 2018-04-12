package cn.imust.ys.scom.student.domain;

/**
 * 奖学金分配表
 * */
public class ScholarshipDistribution {
	private Integer id;
	private Term term;
	private Gyear gyear;
	private Integer fnum;//一等奖人数
	private Integer snum;//二等奖人数
	private Integer tnum;//三等奖人数
	
	
	public ScholarshipDistribution() {
		super();
	}
	
	public ScholarshipDistribution(Term term, Gyear gyear, Integer fnum,
			Integer snum, Integer tnum) {
		super();
		this.term = term;
		this.gyear = gyear;
		this.fnum = fnum;
		this.snum = snum;
		this.tnum = tnum;
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
	public Gyear getGyear() {
		return gyear;
	}
	public void setGyear(Gyear gyear) {
		this.gyear = gyear;
	}
	public Integer getFnum() {
		return fnum;
	}
	public void setFnum(Integer fnum) {
		this.fnum = fnum;
	}
	public Integer getSnum() {
		return snum;
	}
	public void setSnum(Integer snum) {
		this.snum = snum;
	}
	public Integer getTnum() {
		return tnum;
	}
	public void setTnum(Integer tnum) {
		this.tnum = tnum;
	}
}
