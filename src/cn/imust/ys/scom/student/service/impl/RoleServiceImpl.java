package cn.imust.ys.scom.student.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.admin.dao.IRoleDao;
import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.dao.IUserDao;
import cn.imust.ys.scom.student.domain.Function;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
	@Resource private IRoleDao roleDao;
	@Resource private IStudentDao studentDao;
	@Resource private IUserDao userDao;
	//工作流对象
//	@Resource IdentityService identityService;
	public void save(Role model, String ids) {
		roleDao.save(model);
		//工作流组同步
//		Group group = new GroupEntity(model.getName());
//		identityService.saveGroup(group);
		
		String[] split = ids.split(",");
		for (String string : split) {
			model.getFunctions().add(new Function(Integer.parseInt(string)));
		}
	}

	public void queryPage(PageBean pageBean) {
		roleDao.queryPage(pageBean);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void update(String sno, int[] roleIds) {
		User user = userDao.findUserByAccount(sno);
		user.getRoles().clear();
		for (int id : roleIds) {
			Role role = roleDao.findById(id);
			if(role!=null){
				user.getRoles().add(role);
			}
		}
	}

}
