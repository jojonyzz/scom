package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Academy;
import cn.imust.ys.scom.student.service.IAcademyService;

/**
 * 学院
 * */
@Controller
@Scope("prototype")
public class AcademyAction extends BaseAction<Academy>{
	private static final long serialVersionUID = 1L;
	@Resource private IAcademyService academyService;
	public String save(){
		try {
			if(StringUtils.isBlank(model.getAcadname())){
				throw new ScomException("添加的学院不可为空");
			}
			academyService.save(model);
			write(ajaxReturn(true, "学院添加成功"));
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
		List<Academy> academies = academyService.findAll();
		String[] excludes = new String[]{"majors","depts"};
		writeList2Json(academies, excludes );
		return NONE;
	}

}
