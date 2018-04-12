package cn.imust.ys.scom.student.domain;



/**
 * Scholarship entity. @author MyEclipse Persistence Tools
 */

public class Scholarship  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
     private Major major;
     private Term term;
     private String total;
     private Integer winnum;
     private Integer joinnum;


    // Constructors

    /** default constructor */
    public Scholarship() {
    }

	/** minimal constructor */
    public Scholarship(Integer id, Term term) {
        this.id = id;
        this.term = term;
    }
    
    /** full constructor */
    public Scholarship(Integer id, Major major, Term term, String total, Integer winnum, Integer joinnum) {
        this.id = id;
        this.major = major;
        this.term = term;
        this.total = total;
        this.winnum = winnum;
        this.joinnum = joinnum;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Major getMajor() {
        return this.major;
    }
    
    public void setMajor(Major major) {
        this.major = major;
    }

    public Term getTerm() {
        return this.term;
    }
    
    public void setTerm(Term term) {
        this.term = term;
    }

    public String getTotal() {
        return this.total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getWinnum() {
        return this.winnum;
    }
    
    public void setWinnum(Integer winnum) {
        this.winnum = winnum;
    }

    public Integer getJoinnum() {
        return this.joinnum;
    }
    
    public void setJoinnum(Integer joinnum) {
        this.joinnum = joinnum;
    }
   








}