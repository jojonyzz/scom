package cn.imust.ys.scom.student.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.CollengTest;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.ICollengTestService;

/**
 * 等级考试成绩
 * */
@Controller
@Scope("prototype")
public class CollengTestAction extends BaseAction<CollengTest>{
	@Resource private ICollengTestService collengTestService;
	private static final long serialVersionUID = 1L;
	private File upload; // 上传的Excel文件
	private String uploadFileName; // 上传文件名称
	private String uploadContentType; // 上传文件类型
	
	private Integer termid;
	
	public void setTermid(Integer termid) {
		this.termid = termid;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public String save(){
		try {
			collengTestService.save(model);
			write(ajaxReturn(true, "录入成绩成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "发生异常"));
		}
		return NONE;
	}
	
	public void upload(){
		try {
			collengTestService.doAddExcel(upload, uploadFileName, uploadContentType,termid);
			write(ajaxReturn(true, "导入成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "信息填写错误"));
		}
		
	}
	/**
	 * 显示个人成绩列表
	 * @throws IOException 
	 * */
	public String getImportListBySid() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user.getStudent() == null){
			return NONE;
		}
		List<CollengTest> collengTests = collengTestService.getImportListBySid(user.getStudent().getId(),user.getStudent().getName());
		String[] excludes = new String[]{"student","term","rankList"};
		writeList2Json(collengTests, excludes );
		return NONE;
	}
	/**
	 * 删除导入的成绩
	 * */
	public String delete(){
		collengTestService.delete(model);
		return "list";
	}

}
