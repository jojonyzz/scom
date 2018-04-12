package cn.imust.ys.scom.student.service;

import java.io.File;
import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.BPrinciple;

public interface IBPrincipleService {

	List<BPrinciple> findBySnoAndTerm(Integer id, Integer termid);

	void save(BPrinciple model);

	void doAddExcel(File upload, String uploadFileName, String uploadContentType);

	void findAll(PageBean pageBean);

	void delete(BPrinciple model);

}
