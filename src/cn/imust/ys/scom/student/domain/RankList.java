package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * RankList entity. @author MyEclipse Persistence Tools
 */

public class RankList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Set<CollengTest> collengTests = new HashSet<CollengTest>(0);
	public Integer getId() {
		return id;
	}
	
	public RankList(String name) {
		super();
		this.name = name;
	}

	public RankList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<CollengTest> getCollengTests() {
		return collengTests;
	}
	public void setCollengTests(Set<CollengTest> collengTests) {
		this.collengTests = collengTests;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}