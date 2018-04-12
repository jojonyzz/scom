package cn.imust.ys.scom.student.service;

import java.io.File;
import java.util.List;

import cn.imust.ys.scom.student.domain.CollengTest;

public interface ICollengTestService {

	void save(CollengTest model);

	void doAddExcel(File upload, String uploadFileName, String uploadContentType, Integer termid);

	List<CollengTest> findGradeByTAS(Integer termid, Integer sid);

	List<CollengTest> getImportListBySid(Integer sid, String name);

	void delete(CollengTest model);

}
