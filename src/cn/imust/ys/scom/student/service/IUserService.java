package cn.imust.ys.scom.student.service;

import cn.imust.ys.scom.student.domain.User;

public interface IUserService {

	User login(User user);

	void updatePwd(String md5, Integer id);

	void save(User model, String[] roleIds);
	
}
