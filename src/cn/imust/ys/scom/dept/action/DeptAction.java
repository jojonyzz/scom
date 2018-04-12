package cn.imust.ys.scom.dept.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Dept;
import cn.imust.ys.scom.student.service.IDeptService;
@Controller
@Scope("prototype")
public class DeptAction extends BaseAction<Dept>{
	private static final long serialVersionUID = 1L;
	@Resource private IDeptService deptService;
	public String save(){
		try {
			deptService.save(model);
			write(ajaxReturn(true, "录入成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "发生异常"));
		}
		return NONE;
	}

}
