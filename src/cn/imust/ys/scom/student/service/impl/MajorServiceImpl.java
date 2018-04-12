package cn.imust.ys.scom.student.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.IMajorDao;
import cn.imust.ys.scom.student.domain.Major;
import cn.imust.ys.scom.student.service.IMajorService;

@Service
@Transactional
public class MajorServiceImpl implements IMajorService{
	@Resource private IMajorDao majorDao;
	@Override
	public void save(Major model) {
		majorDao.save(model);
	}
	@Override
	public List<Major> findAll() {
		return majorDao.findAll();
	}
	@Override
	public List<Major> findAllByAid(Integer academyId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Major.class);
		criteria.add(Restrictions.eq("academy.id", academyId));
		return majorDao.findByCriteria(criteria );
	}
}
