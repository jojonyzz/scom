package cn.imust.ys.scom.student.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.Term;

public interface IStudyScomDao extends IBaseDao<StudyScom>{

	List<StudyScom> findScortAll();

	void deleteScomListByTY(Term term, Gyear gyear);

	List<StudyScom> sortScomList(Integer yid, Integer termid);
	//查询带有测评成绩的总人数
	Long getHandScoreNum(Integer yid, Integer termid);
}
