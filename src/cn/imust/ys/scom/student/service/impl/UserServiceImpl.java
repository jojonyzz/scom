package cn.imust.ys.scom.student.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.admin.dao.IRoleDao;
import cn.imust.ys.scom.base.utils.MD5Utils;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.dao.IUserDao;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource
	IUserDao userDao;
	@Resource
	IStudentDao studentDao;
	@Resource IRoleDao roleDao;

	@Override
	public User login(User model) {
		return userDao.findByAccountAndPwd(model.getAccount(),
				MD5Utils.md5(model.getPwd()));
	}

	@Override
	public void updatePwd(String password, Integer id) {
		userDao.executeUpdate("editpwd", password, id);
	}

	public void save(User model, String[] roleIds) {
		if(model.getStudent() != null){
			studentDao.save(model.getStudent());
		}
//		org.activiti.engine.identity.User user = new UserEntity(model.getId());
//		identityService.saveUser(user);
		userDao.save(model);
		if(roleIds!=null){
			for (String string : roleIds) {
				Role role = roleDao.findById(Integer.parseInt(string));
				model.getRoles().add(role);
//				identityService.createMembership(user.getId(), role.getName());
			}
		}
	}
}
