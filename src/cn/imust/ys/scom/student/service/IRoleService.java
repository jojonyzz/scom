package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Role;


public interface IRoleService {

	void save(Role model, String ids);

	void queryPage(PageBean pageBean);

	List<Role> findAll();

	void update(String sno, int[] roleIds);
}
