package cn.imust.ys.scom.student.dao;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.User;

public interface IUserDao extends IBaseDao<User>{

	User findByAccountAndPwd(String account, String md5);

	User findUserByAccount(String username);

}
