package cn.imust.ys.scom.student.dao;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Course;

public interface ICourseDao extends IBaseDao<Course>{

	Course saveOrlink(Course course,Integer class_id,Integer termid);

	Course findByAll(Course course, Integer class_id, Integer termid);

	void DeleteByCidAndTid(Integer clazid, Integer tid);

}
