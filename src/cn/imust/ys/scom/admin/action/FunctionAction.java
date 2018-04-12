package cn.imust.ys.scom.admin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Function;
import cn.imust.ys.scom.student.service.IFunctionService;

@Controller 
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	private static final long serialVersionUID = 1L;
	
	@Resource private IFunctionService functionService;
	
	public String queryPage() throws IOException{
		pageBean.setCurrentPage(Integer.parseInt(model.getPage()));
		String[] excludes = new String[]{"function","functions","roles"};
		functionService.queryPage(pageBean);
		writePageBean2Json(pageBean, excludes);
		return NONE;
	}
	public String findAll() throws IOException{
		List<Function> functions = functionService.findAll();
		String[] excludes = new String[]{"function","functions","roles"};
		writeList2Json(functions, excludes );
		return NONE;
	}
	public String save(){
		functionService.save(model);
		return "list";
	}
	public String findMenu() throws IOException{
		List<Function> functions = functionService.findMenu();
		String[] excludes = new String[]{"functions","function","roles"};
		writeList2Json(functions, excludes );
		return NONE;
	}
	private String sno;

	public void setSno(String sno) {
		this.sno = sno;
	}
	public String findBySno() throws IOException{
		List<Function> functions = functionService.findBySno(sno);
		String[] excludes = new String[]{"function","functions","roles"};
		writeList2Json(functions, excludes );
		return NONE;
	}
}
