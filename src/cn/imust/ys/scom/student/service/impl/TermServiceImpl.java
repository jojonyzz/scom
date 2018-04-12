package cn.imust.ys.scom.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.ITermDao;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.ITermService;

@Transactional
@Service
public class TermServiceImpl implements ITermService{
	@Resource ITermDao termDao;

	@Override
	public Term getNewTerm() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Term.class);
		criteria.addOrder(Order.desc("id"));
		List<Term> findByCriteria = termDao.findByCriteria(criteria );
		if(findByCriteria != null && findByCriteria.size()>0){
			return findByCriteria.get(0);
		}
		return null;
	}

	@Override
	public List<Term> findAll() {
		return termDao.findAll();
	}

	@Override
	public void save(Term model) {
		termDao.save(model);
	}

	@Override
	public Term findById(Integer termid) {
		return termDao.findById(termid);
	}
}
