package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.IGradeDao;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.Student;

@Repository
public class GradeDaoImpl extends BaseDaoImpl<Grade> implements IGradeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findScoreByTidASno(String sno, Integer tId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Grade.class);
		criteria.add(Restrictions.eq("term.id", tId));
		criteria.add(Restrictions.eq("student.sno", sno));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double findBeforeScore(Integer sid, Integer cid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Grade.class);
		criteria.add(Restrictions.eq("student.id", sid));
		criteria.add(Restrictions.eq("course.id", cid));
		criteria.setProjection(Projections.max("score"));
		List<Double> maxs = this.getHibernateTemplate().findByCriteria(criteria);
		if(maxs != null && maxs.size()>0){
			return maxs.get(0);
		}else{
			return 0.0;
		}
	}

	@Override
	public Grade findScoreBySidACid(Integer sid, int cid, int termid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Grade.class);
		criteria.add(Restrictions.eq("course.id", cid));
		criteria.add(Restrictions.eq("student.id", sid));
		criteria.add(Restrictions.eq("term.id", termid));
		@SuppressWarnings("unchecked")
		List<Grade> list = this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
