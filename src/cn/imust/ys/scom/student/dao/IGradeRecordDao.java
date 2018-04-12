package cn.imust.ys.scom.student.dao;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.GradeRecord;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Term;

public interface IGradeRecordDao extends IBaseDao<GradeRecord>{

	boolean isImport(Term term, StuClass stuClass);

}
