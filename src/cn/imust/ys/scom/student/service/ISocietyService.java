package cn.imust.ys.scom.student.service;

import java.io.File;

import cn.imust.ys.scom.student.domain.Society;


public interface ISocietyService {

	void save(Society model);

	void doAddExcel(File upload, String uploadFileName,
			String uploadContentType, Integer termid);


}
