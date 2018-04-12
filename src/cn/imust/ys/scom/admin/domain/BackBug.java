package cn.imust.ys.scom.admin.domain;

import java.util.Date;

/**
 * bug 反馈 
 * */
public class BackBug {
	private Integer id;
	private Date date;//发布时间
	private String name;//发布人姓名
	private String context;//bug 描述
	private Boolean deal = false;//是否处理
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Boolean getDeal() {
		return deal;
	}
	public void setDeal(Boolean deal) {
		this.deal = deal;
	}
}
