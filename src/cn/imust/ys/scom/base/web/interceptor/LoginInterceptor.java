package cn.imust.ys.scom.base.web.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.imust.ys.scom.student.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			return "login";
		}
		return arg0.invoke();
	}
}
