package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.student.domain.Grade;


public interface IGradeService {

	Grade findScoreBySidACid(Integer integer, int cid, int termid);

	Double findBeforeScore(String string,Integer cid);

	/**
	 * 查询该同学本学期的课程成绩
	 * */
	List<Grade> getGradeBySnoAndTid(String sno, Integer tid);

	/**
	 * 撤销成绩录入
	 * */
	void cancelGradeImport(Integer id);

	void update(Grade model);



}
