package cn.imust.ys.scom.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.dao.IStuClassDao;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.service.IStuClassService;

@Service
@Transactional
public class StuClassServiceImpl implements IStuClassService{
	@Resource private IStuClassDao stuClassDao;

	@Override
	public void save(StuClass model) {
		stuClassDao.save(model);
	}

	@Override
	public List<StuClass> listByGyear(Integer gid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StuClass.class);
		criteria.add(Restrictions.eq("gyear.id", gid));
		return stuClassDao.findByCriteria(criteria);
	}

	@Override
	public StuClass findById(Integer class_id) {
		return stuClassDao.findById(class_id);
	}

	@Override
	public List<StuClass> findAll() {
		return stuClassDao.findAll();
	}

	@Override
	public void findAll(PageBean pageBean) {
		stuClassDao.queryPage(pageBean);
	}
}
