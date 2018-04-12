package cn.imust.ys.scom.student.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.GradeRecord;
import cn.imust.ys.scom.student.service.IGradeRecordService;

/**
 * 成绩导入记录显示
 * */
@Controller
@Scope("prototype")
public class GradeRecordAction extends BaseAction<GradeRecord>{
	private static final long serialVersionUID = 1L;
	@Resource IGradeRecordService gradeRecordService;
	public String queryPage() throws IOException{
		gradeRecordService.findAll(pageBean);
		String[] excludes = new String[]{"term","stuClass"};
		writePageBean2Json(pageBean, excludes );
		return NONE;
	}

}
