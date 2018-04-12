package cn.imust.ys.scom.student.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.dao.IGyearDao;
import cn.imust.ys.scom.student.dao.IMajorDao;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.service.IGyearService;

@Service
@Transactional
public class GyearServiceImpl implements IGyearService{
	@Resource private IGyearDao gyearDao;
	@Resource private IMajorDao majorDao;
	@Override
	public void save(Gyear model,Integer id) {
		model.setMajor(majorDao.findById(id));
		gyearDao.save(model);
	}

	@Override
	public List<Gyear> findAll() {
		return gyearDao.findAll();
	}

	@Override
	public List<Gyear> listByMajor(Integer mid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Gyear.class);
		criteria.add(Restrictions.eq("major.id", mid));
		return gyearDao.findByCriteria(criteria);
	}

	@Override
	public Gyear findById(Integer yid) {
		return gyearDao.findById(yid);
	}

	@Override
	public void findAll(PageBean pageBean) {
		gyearDao.queryPage(pageBean);
	}

}
