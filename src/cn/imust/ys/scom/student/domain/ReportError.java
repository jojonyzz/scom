package cn.imust.ys.scom.student.domain;

/**
 * ReportError entity. @author MyEclipse Persistence Tools
 */

public class ReportError implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String resume;
	private Integer uid;
	private String detail;
	private String image;

	// Constructors

	/** default constructor */
	public ReportError() {
	}

	/** minimal constructor */
	public ReportError(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ReportError(Integer id, String resume, Integer uid, String detail,
			String image) {
		this.id = id;
		this.resume = resume;
		this.uid = uid;
		this.detail = detail;
		this.image = image;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}