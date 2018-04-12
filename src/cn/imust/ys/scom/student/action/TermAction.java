package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.ITermService;

@Controller
@Scope("prototype")
public class TermAction extends BaseAction<Term>{
	private static final long serialVersionUID = 1L;
	@Resource ITermService termService;
	
	public String list() throws IOException{
		List<Term> terms = termService.findAll();
		String[] excludes = new String[]{"collengTests","scholarships","grades","courses"};
		writeList2Json(terms, excludes );
		return NONE;
	}
	public String save(){
		try {
			if(StringUtils.isBlank(model.getTermname()) || StringUtils.isBlank(model.getYear())){
				throw new ScomException("数据无效,请重新输入!");
			}
			termService.save(model);
			write(ajaxReturn(true, "学期添加成功"));
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
