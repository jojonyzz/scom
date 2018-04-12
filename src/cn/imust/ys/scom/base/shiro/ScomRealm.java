package cn.imust.ys.scom.base.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.imust.ys.scom.admin.dao.IFunctionDao;
import cn.imust.ys.scom.student.dao.IUserDao;
import cn.imust.ys.scom.student.domain.Function;
import cn.imust.ys.scom.student.domain.User;

public class ScomRealm extends AuthorizingRealm  {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource IUserDao userDao;
	@Resource IFunctionDao functionDao;
	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();// 从令牌中获得用户名
		logger.info("用户登录认证:[用户名:"+username+"]");
		User user = userDao.findUserByAccount(username);
		if (user == null) {
			// 用户名不存在
			return null;
		} else {
			// 用户名存在
			String password = user.getPwd();// 获得数据库中存储的密码
			// 创建简单认证信息对象
			/***
			 * 参数一：签名，程序可以在任意位置获取当前放入的对象
			 * 参数二：从数据库中查询出的密码
			 * 参数三：当前realm的名称
			 */
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
					password, this.getClass().getSimpleName());
			return info;//返回给安全管理器，由安全管理器负责比对数据库中查询出的密码和页面提交的密码
		}
	}
	/**
	 * 授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		logger.info("授权方法:[授权用户:"+user.getName()+"]" );
		List<Function> functions = null;
		if(user.getAccount().equals("admin")){
			functions = functionDao.findAll();
		}else{
			functions = functionDao.findMenuByUserId(user.getId());
		}
		for (Function function : functions) {
			info.addStringPermission(function.getName());
		}
		return info;
	}
}
