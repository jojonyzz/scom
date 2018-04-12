package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.IGradeRecordDao;
import cn.imust.ys.scom.student.domain.GradeRecord;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Term;

@Repository
public class GradeRecordDaoImpl extends BaseDaoImpl<GradeRecord> implements IGradeRecordDao{

	@Override
	public boolean isImport(Term term, StuClass stuClass) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GradeRecord.class);
		criteria.add(Restrictions.eq("term.id", term.getId()));
		criteria.add(Restrictions.eq("stuClass.id", stuClass.getId()));
		@SuppressWarnings("unchecked")
		List<GradeRecord> findByCriteria = this.getHibernateTemplate().findByCriteria(criteria);
		if(findByCriteria!=null && findByCriteria.size()>0){
			return true;
		}else{
			//如果没有找到，将这条记录保存
			this.getHibernateTemplate().save(new GradeRecord(term,stuClass));
			return false;
		}
	}

}
