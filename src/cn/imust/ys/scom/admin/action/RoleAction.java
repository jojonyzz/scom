package cn.imust.ys.scom.admin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.service.IRoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 1L;
	@Resource private IRoleService roleService;
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String save(){
		roleService.save(model,ids);
		return "list";
	}
	private String sno;
	public void setSno(String sno) {
		this.sno = sno;
	}
	private int[] roleIds;
	public void setRoleIds(int[] roleIds) {
		this.roleIds = roleIds;
	}
	public String update(){
		roleService.update(sno,roleIds);
		return "init";
	}
	public String queryPage() throws IOException{
		roleService.queryPage(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"functions","users","currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	
	public String findAll() throws IOException{
		List<Role> functions = roleService.findAll();
		String[] excludes = new String[]{"function","functions","users"};
		writeList2Json(functions, excludes);
		return NONE;
	}

}
