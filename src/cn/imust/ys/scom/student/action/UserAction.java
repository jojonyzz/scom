package cn.imust.ys.scom.student.action;


import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.utils.MD5Utils;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.IUserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	@Resource IUserService userService;
	private static final long serialVersionUID = 1L;
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	public String login(){
		HttpSession ac = ServletActionContext.getRequest().getSession();
		String key = (String) ac.getAttribute("key");
		// 如果验证码不为空，并且验证码正确
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(key)) {
			//获得当前用户对象
			Subject subject = SecurityUtils.getSubject();//状态为“未认证”
			//构造一个用户名密码令牌
			AuthenticationToken token = new UsernamePasswordToken(model.getAccount(), MD5Utils.md5(model.getPwd()));
			try{
				subject.login(token);
			}catch (UnknownAccountException e) {
				e.printStackTrace();
				//设置错误信息
				this.addActionError(this.getText("用户未找到 !"));
				return "login";
			}catch (Exception e) {
				e.printStackTrace();
				//设置错误信息
				this.addActionError(this.getText("用户名或密码错误"));
				return "login";
			}
			//获取认证信息对象中存储的User对象
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			Map<String, Object> application = ServletActionContext.getContext().getApplication();
			Integer num = (Integer) application.get("num");
			application.remove("num");
			if(num != null){
				application.put("num", ++num);
			}else{
				application.put("num", 1);
			}
			return "index";
		} else {
			//验证码错误,设置错误提示信息，跳转到登录页面
			this.addActionError(this.getText("codeError"));
			return "login";
		}
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "login";
	}
	
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String save(){
		//TODO 初始化密码
		model.setPwd(MD5Utils.md5("123456"));
		userService.save(model, roleIds);
		return "list";
	}
	
	public String editpwd() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String falg = "0";
		String pwd = model.getPwd();
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Integer id = user.getId();
		try {
			userService.updatePwd(MD5Utils.md5(pwd),id);
			response.getWriter().print(falg);
		} catch (Exception e) {
			falg = "1";
			response.getWriter().print(falg);
		}
		return NONE;
	}
	
}
