package cn.imust.ys.scom.student.action;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.BPrinciple;
import cn.imust.ys.scom.student.service.IBPrincipleService;

/**
 * 违纪信息请求处理
 * */
@Controller
@Scope("prototype")
public class PrincipleAction extends BaseAction<BPrinciple>{
	private static final long serialVersionUID = 1L;
	@Resource private IBPrincipleService principleService;
	private File upload; // 上传的Excel文件
	private String uploadFileName; // 上传文件名称
	private String uploadContentType; // 上传文件类型

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
			principleService.save(model);
			write(ajaxReturn(true, "违纪信息录入成功!"));
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
			principleService.doAddExcel(upload, uploadFileName, uploadContentType);
			write(ajaxReturn(true, "导入成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "信息填写错误"));
		}
		
	}
	
	public String queryPage() throws IOException{
		principleService.findAll(pageBean);
		String[] excludes = new String[]{"student","term"};
		writePageBean2Json(pageBean, excludes );
		return NONE;
	}
	public String delete(){
		try {
			principleService.delete(model);
			write(ajaxReturn(true, "违纪信息删除成功!"));
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
