package cn.imust.ys.scom.student.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.service.IGyearService;

@Controller
@Scope("prototype")
public class GyearAction extends BaseAction<Gyear>{

	private static final long serialVersionUID = 1L;
	@Resource private IGyearService gyearService;
	private Integer mid;
	
	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String save(){
		try {
			if(StringUtils.isBlank(model.getName()) || mid == null){
				throw new ScomException("添加数据不能为空");
			}
			gyearService.save(model,mid);
			write(ajaxReturn(true, "添加成功"));
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
		List<Gyear> gyears = gyearService.findAll();
		String[] excludes = new String[]{"major","stuClasses","courses"};
		writeList2Json(gyears, excludes );
		return NONE;
	}
	
	public String listByMajor() throws IOException{
		List<Gyear> gyears = gyearService.listByMajor(mid);
		String[] excludes = new String[]{"major","stuClasses","courses"};
		writeList2Json(gyears, excludes );
		return NONE;
	}
	public String queryPage() throws IOException{
		gyearService.findAll(pageBean);
		String[] excludes = new String[]{"major","stuClasses","courses"};
		writePageBean2Json(pageBean, excludes );
		return NONE;
	}

}
