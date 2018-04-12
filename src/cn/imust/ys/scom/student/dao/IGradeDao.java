package cn.imust.ys.scom.student.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.Student;

public interface IGradeDao extends IBaseDao<Grade>{

	List<Student> findScoreByTidASno(String sno, Integer tId);

	Double findBeforeScore(Integer sid,Integer cid);

	Grade findScoreBySidACid(Integer id, int cid, int termid);

}
