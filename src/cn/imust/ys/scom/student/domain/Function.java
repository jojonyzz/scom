package cn.imust.ys.scom.student.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Function entity. @author MyEclipse Persistence Tools
 */

public class Function implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Function function;
	private String name;
	private String page;
	private String generatemenu = "1";
	private Set<Function> functions = new HashSet<Function>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	
	public Function(Integer id) {
		super();
		this.id = id;
	}

	public Integer getpId() {
		if(function != null){
			return function.getId();
		}else{
			return 0;
		}
	}

	// Constructors

	/** default constructor */
	public Function() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGeneratemenu() {
		return this.generatemenu;
	}

	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}

	public Set<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}