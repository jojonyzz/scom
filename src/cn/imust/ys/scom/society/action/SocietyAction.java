package cn.imust.ys.scom.society.action;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.Society;
import cn.imust.ys.scom.student.service.ISocietyService;

@Controller
@Scope("prototype")
public class SocietyAction extends BaseAction<Society>{
	private static final long serialVersionUID = 1L;
	@Resource private ISocietyService societyService;
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
			societyService.save(model);
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
	
	public void upload(){
		try {
			societyService.doAddExcel(upload, uploadFileName, uploadContentType,termid);
			write(ajaxReturn(true, "导入成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "信息填写错误"));
		}
	}
}
