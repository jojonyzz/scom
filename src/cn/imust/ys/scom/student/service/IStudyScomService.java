package cn.imust.ys.scom.student.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.StudyScomRecord;
import cn.imust.ys.scom.student.domain.Term;

public interface IStudyScomService {

	void save(StudyScom scom);

	boolean isExistScom(Integer class_id, Integer termid);

	List<StudyScom> getHistoryScom(Integer yid, Integer termid);

	void saveRecord(StudyScomRecord studyScomRecord);

	List<StudyScom> sortScomList(Integer termid, Integer yid);

	void deleteScomListByTY(Term term, StuClass stuClass);

	HSSFWorkbook getScholarshipBy(Integer yid, Integer termid);
	
	/**
	 * 得到班级历史测评数据
	 * */
	List<StudyScom> getClassHistoryScom(Integer termid, Integer class_id);
	
	/**
	 * 得到年级下的测评数据
	 * */
	void getScomYear(Integer yid, Integer termid);
	
	/**
	 * 批量保存测评数据并排序
	 * @param termid 
	 * @param class_id 
	 * */
	void saveScom(List<StudyScom> students, Integer class_id, Integer termid);
	/**
	 * 判断该年级下的所有班级有没完成测评
	 * */
	boolean isCompleteScom(Integer yid, Integer termid);

}
