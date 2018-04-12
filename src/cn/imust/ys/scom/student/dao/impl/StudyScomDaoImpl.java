package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.IStudyScomDao;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.Term;

@Repository
public class StudyScomDaoImpl extends BaseDaoImpl<StudyScom> implements IStudyScomDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<StudyScom> findScortAll() {
		String hql = "SELECT s FROM StudyScom s where s.nopass<>0 ORDER BY s.amount desc";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteScomListByTY(Term term, Gyear gyear) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", term.getId()));
		criteria.add(Restrictions.eq("gyear.id", gyear.getId()));
		@SuppressWarnings("unchecked")
		List<StudyScom> list = this.getHibernateTemplate().findByCriteria(criteria);
		for (StudyScom studyScom : list) {
			this.getHibernateTemplate().delete(studyScom);
		}
	}

	@Override
	public List<StudyScom> sortScomList(Integer yid, Integer termid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("gyear.id", yid));
		criteria.addOrder(Order.desc("amount"));
		@SuppressWarnings("unchecked")
		List<StudyScom> list = this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size()>0){
			//追加专业排名
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setMajorrank(i+1);
			}
			return list;
		}
		return null;
	}

	@Override
	public Long getHandScoreNum(Integer yid, Integer termid) {
		String hql ="select count(*) from StudyScom sc right outer join sc.gyear y right outer join sc.term t where y.id=? and t.id=?";
		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql,yid,termid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
