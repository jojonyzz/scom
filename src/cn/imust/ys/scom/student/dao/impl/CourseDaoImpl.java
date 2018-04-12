package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.ICourseDao;
import cn.imust.ys.scom.student.domain.Course;

@Repository
public class CourseDaoImpl extends BaseDaoImpl<Course> implements ICourseDao {

	@Override
	public Course saveOrlink(Course course, Integer class_id, Integer termid) {
		// 按照 年级，学期，查询课程是否存在或者保存
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
		criteria.add(Restrictions.eq("cno", course.getCno()));
		criteria.add(Restrictions.eq("cname", course.getCname()));
		criteria.add(Restrictions.eq("credit", course.getCredit()));
		criteria.add(Restrictions.eq("stuClass.id", class_id));
		criteria.add(Restrictions.eq("term.id", termid));
		@SuppressWarnings("unchecked")
		List<Course> findByCriteria = this.getHibernateTemplate()
				.findByCriteria(criteria);
		if (findByCriteria != null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		} else {
			this.getHibernateTemplate().save(course);
			return course;
		}
	}

	@Override
	public Course findByAll(Course course, Integer class_id, Integer termid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
		criteria.add(Restrictions.eq("cno", course.getCno()));
		criteria.add(Restrictions.eq("cname", course.getCname()));
		criteria.add(Restrictions.eq("credit", course.getCredit()));
		criteria.add(Restrictions.eq("stuClass.id", class_id));
		criteria.add(Restrictions.eq("term.id", termid));
		@SuppressWarnings("unchecked")
		List<Course> findByCriteria = this.getHibernateTemplate()
				.findByCriteria(criteria);
		if (findByCriteria != null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		}
		return null;
	}

	@Override
	public void DeleteByCidAndTid(Integer clazid, Integer tid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Course.class);
		criteria.add(Restrictions.eq("stuClass.id", clazid));
		criteria.add(Restrictions.eq("term.id", tid));
		@SuppressWarnings("unchecked")
		List<Course> findByCriteria = this.getHibernateTemplate()
				.findByCriteria(criteria);
		if (findByCriteria != null && findByCriteria.size() > 0) {
			this.getHibernateTemplate().deleteAll(findByCriteria);
		}
	}

}
