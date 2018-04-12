package cn.imust.ys.scom.student.dao.impl;

import java.util.HashSet;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.base.utils.MD5Utils;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.User;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao {
	/**
	 * 按年级进行测评
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentScoreListByDB(Integer yid , Integer tId) {
		String hql = "SELECT DISTINCT s FROM Gyear y RIGHT OUTER JOIN y.stuClasses claz RIGHT OUTER JOIN claz.students s RIGHT OUTER JOIN s.grades g RIGHT OUTER JOIN g.term t RIGHT OUTER JOIN g.course c WHERE y.id = ? AND t.id = ?";
		return this.getHibernateTemplate().find(hql,yid,tId);
	}
	/**
	 * 通过学生号和课程号找到学生的所有成绩
	 * */

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listByYeatATid(Integer yid, Integer termid) {
		String hql = "select c from Course c RIGHT OUTER JOIN c.gyear y LEFT OUTER JOIN c.term t where y.id = ? and t.id = ?";
		return this.getHibernateTemplate().find(hql,yid,termid);
	}
	/**
	 * 根据年级查询该年级下的学生
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentListByDB(int yid) {
		String hql = "SELECT DISTINCT s FROM Gyear y RIGHT OUTER JOIN y.stuClasses claz RIGHT OUTER JOIN claz.students s  WHERE y.id = ?";
		return this.getHibernateTemplate().find(hql,yid);
	}
	/**
	 * 查询学生是否存在，存在将查到的学生返回，不存在则保存学生
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public Student saveNoExist(Student student,StuClass stuClass ,Role role) {
		//按照学号和姓名找学生，找到则不保存学生
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("sno", student.getSno()));
		criteria.add(Restrictions.eq("name", student.getName()));
		criteria.add(Restrictions.eq("stuClass.id", stuClass.getId()));
		List<Student> list = this.getHibernateTemplate().findByCriteria(criteria);
		if(list !=null && list.size()>0){
			//找到学生
			return list.get(0);
		}else{
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(role);
			User user = new User(student, student.getSno(), MD5Utils.md5("123456"),roles);
			this.getHibernateTemplate().save(user);
			this.getHibernateTemplate().save(student);
			return student;
		}
	}
	/**
	 * 根据班级 ID 找到所有的学生
	 * */
	@Override
	public List<Student> findStudentByClass(Integer class_id) {
		String hql = "select s from StuClass c right outer join c.students s where c.id=?";
		@SuppressWarnings("unchecked")
		List<Student> students = this.getHibernateTemplate().find(hql,class_id);
		if(students!=null && students.size()>0){
			return students;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> listByYearAndTerm(Integer class_id, Integer termid) {
		String hql = "select c from Course c RIGHT OUTER JOIN c.stuClass sc LEFT OUTER JOIN c.term t where sc.id = ? and t.id = ?";
		return this.getHibernateTemplate().find(hql,class_id,termid);
	}
	/**
	 * 根据学号找学生 id
	 * */
	@Override
	public List<Integer> findBySno(String sno) {
		String hql ="select s.id from Student s where s.sno=?";
		@SuppressWarnings("unchecked")
		List<Integer> sids = this.getHibernateTemplate().find(hql,sno);
		if(sids != null && sids.size()>0){
			return sids;
		}
		return null;
	}
	@Override
	public List<Student> findStudentBySnoAndName(Student student) {
		String hql = "select s from Student s where s.sno=? and name=?";
		@SuppressWarnings("unchecked")
		List<Student> students = this.getHibernateTemplate().find(hql,student.getSno(),student.getName());
		if(students!=null && students.size()>0){
			return students;
		}
		return null;
	}
	public List<Student> findStudentBySno(String sno) {
		String hql = "select s from Student s where s.sno=?";
		@SuppressWarnings("unchecked")
		List<Student> students = this.getHibernateTemplate().find(hql,sno);
		if(students!=null && students.size()>0){
			return students;
		}
		return null;
	}
}
