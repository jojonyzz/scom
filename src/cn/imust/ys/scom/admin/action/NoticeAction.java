package cn.imust.ys.scom.admin.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Notice;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.INoticeService;

@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction<Notice>{
	private static final long serialVersionUID = 1L;
	@Resource private INoticeService noticeService;
	
	public String save(){
		model.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		model.setAuthor(user.getName());
		noticeService.save(model);
		return "list";
	}
	public String queryPage() throws IOException{
		noticeService.findAll(pageBean);
		String[] excludes = new String[]{"major","stuClasses","courses"};
		writePageBean2Json(pageBean, excludes );
		return NONE;
	}

}
