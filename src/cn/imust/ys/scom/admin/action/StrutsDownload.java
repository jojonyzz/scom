package cn.imust.ys.scom.admin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件下载的action
 * */
@Controller
@Scope("prototype")
public class StrutsDownload extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private InputStream fileInputStream;
	private String fileName;
	private Integer fid;

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String excel() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/excel");
		String filename = "";
		switch (fid) {
		case 1:
			filename = "等级考试模板.xls";
			break;
		case 2:
			filename = "社会活动加分模板.xls";
			break;
		}
		fileInputStream = new FileInputStream(new File(path + "\\" + filename));
		fileName = new String(filename.getBytes("utf-8"), "ISO-8859-1");
		return SUCCESS;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
