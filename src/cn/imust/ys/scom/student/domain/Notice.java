package cn.imust.ys.scom.student.domain;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String time;
	private String title;
	private String author;
	private String context;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Notice(Integer id, String time, String title, String author,
			String context) {
		this.id = id;
		this.time = time;
		this.title = title;
		this.author = author;
		this.context = context;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}