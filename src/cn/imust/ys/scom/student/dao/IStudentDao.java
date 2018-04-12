package cn.imust.ys.scom.student.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Student;

public interface IStudentDao extends IBaseDao<Student>{

	List<Student> getStudentScoreListByDB(Integer yid , Integer tId);

	List<Course> listByYeatATid(Integer yid, Integer termid);

	List<Student> getStudentListByDB(int yid);

	Student saveNoExist(Student student,StuClass stuClass , Role role);

	List<Student> findStudentByClass(Integer class_id);

	List<Course> listByYearAndTerm(Integer class_id, Integer termid);

	List<Integer> findBySno(String sno);
	
	/**
	 * 根据学号和姓名找到 '所有的学生'
	 * */
	List<Student> findStudentBySnoAndName(Student student);
	
	List<Student> findStudentBySno(String sno);
	
}
