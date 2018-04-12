package cn.imust.ys.scom.admin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.admin.domain.BackBug;
import cn.imust.ys.scom.admin.service.IBackBugService;
import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;

@Controller
@Scope("prototype")
public class BackBugAction extends BaseAction<BackBug>{
	private static final long serialVersionUID = 1L;
	@Resource IBackBugService backBugService;
	
	public String save(){
		try {
			backBugService.save(model);
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
	public String list() throws IOException{
		List<BackBug> backBugs = backBugService.findAll();
		String[] excludes = new String[]{};
		writeList2Json(backBugs, excludes);
		return NONE;
	}
}
