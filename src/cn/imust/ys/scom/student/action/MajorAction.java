package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Major;
import cn.imust.ys.scom.student.service.IMajorService;

@Controller
@Scope("prototype")
public class MajorAction extends BaseAction<Major>{
	private static final long serialVersionUID = 1L;
	@Resource private IMajorService majorService;
	public String save(){
		try {
			if(StringUtils.isBlank(model.getMajorName())){
				throw new ScomException("不可添加空专业!");
			}
			majorService.save(model);
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
		List<Major> academies = majorService.findAll();
		String[] excludes = new String[]{"scholarships","gyears","academy"};
		writeList2Json(academies, excludes );
		return NONE;
	}
	private Integer academyId;
	public void setAcademyId(Integer academyId) {
		this.academyId = academyId;
	}

	public String listByAcademy() throws IOException{
		List<Major> academies = majorService.findAllByAid(academyId);
		String[] excludes = new String[]{"scholarships","gyears","academy"};
		writeList2Json(academies, excludes );
		return NONE;
	}
}
