package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.service.IGradeService;

/**
 * 处理成绩请求
 * */
@Controller
@Scope("prototype")
public class GradeAction extends BaseAction<Grade>{
	private static final long serialVersionUID = 1L;
	@Resource private IGradeService gradeService;
	private String sno;//学生 ID
	private Integer tid;//学期 ID
	public void setSno(String sno) {
		this.sno = sno;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	/**
	 * 查询该同学本学期的课程成绩
	 * @throws IOException 
	 * */
	public String getGradeBySnoAndTid() throws IOException{
		List<Grade> grades = gradeService.getGradeBySnoAndTid(sno,tid);
		String[] excludes = new String[]{"term","student","course"};
		writeList2Json(grades, excludes);
		return NONE;
	}
	/**
	 * 撤销成绩录入
	 * */
	public String cancelGradeImport(){
		gradeService.cancelGradeImport(model.getId());
		return "import";
	}
	public String update(){
		try {
			gradeService.update(model);
			write(ajaxReturn(true, "修改成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "修改失败"));
		}
		return NONE;
	}
	
}
