package cn.imust.ys.scom.student.service;

import java.io.File;
import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.StudyScom;


public interface IStudentService {

	void findAll(PageBean pageBean);

	void save(Student model);

	void doAddExcel(File upload, String uploadFileName,
			String uploadContentType, Integer termid,Student student,Integer tid,Integer class_id);

	List<StudyScom> getStudentScoreList();

	List<Course> listByYeatATid(Integer yid, Integer termid);

	List<Student> getStudentListByDB(int yid);

	List<Student> getClassStudent(Integer class_id);

	List<Course> listByYearAndTerm(Integer class_id, Integer termid);

	/**
	 * 得到某年级某学期下的综合测评表
	 * */
	List<StudyScom> getScomYear(Integer yid, Integer termid);
	
	/**
	 * 判断学生是否存在
	 * */
	boolean isExistStudentBySno(String sno);
	/**
	 * 是否完成综合测评
	 * */
	boolean isScom(Integer termid, Integer yid);

}
