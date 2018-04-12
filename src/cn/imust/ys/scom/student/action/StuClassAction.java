package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.service.IStuClassService;

@Controller
@Scope("prototype")
public class StuClassAction extends BaseAction<StuClass>{
	private static final long serialVersionUID = 1L;
	@Resource private IStuClassService stuClassService;
	
	public String save(){
		try {
			if(StringUtils.isBlank(model.getClassName())){
				throw new ScomException("请确实班级名正确");
			}
			stuClassService.save(model);
			write(ajaxReturn(true, "班级添加成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "发生异常"));
		}
		return NONE;
	}
	private Integer gid;
	
	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String listByGyear() throws IOException{
		List<StuClass> stuClasses = stuClassService.listByGyear(gid);
		String[] excludes = new String[]{"gyear","teachers","students","courses"};
		writeList2Json(stuClasses, excludes );
		return NONE;
	}
	public String list() throws IOException{
		List<StuClass> gyears = stuClassService.findAll();
		String[] excludes = new String[]{"gyear","teachers","students","courses"};
		writeList2Json(gyears, excludes );
		return NONE;
	}
	public String queryPage() throws IOException{
		stuClassService.findAll(pageBean);
		String[] excludes = new String[]{"gyear","teachers","students","courses"};
		writePageBean2Json(pageBean, excludes );
		return NONE;
	}
}
