package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.IUserDao;
import cn.imust.ys.scom.student.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

	@Override
	public User findByAccountAndPwd(String account, String md5) {
		String hql = "FROM User u WHERE u.account = ? AND u.pwd = ?";
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().find(hql, account,md5);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByAccount(String username) {
		String hql = "FROM User u WHERE u.account = ?";
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
