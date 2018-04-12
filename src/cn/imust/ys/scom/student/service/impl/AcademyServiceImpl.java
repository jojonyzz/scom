package cn.imust.ys.scom.student.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.IAcademyDao;
import cn.imust.ys.scom.student.domain.Academy;
import cn.imust.ys.scom.student.service.IAcademyService;

@Service
@Transactional
public class AcademyServiceImpl implements IAcademyService{
	@Resource private IAcademyDao academyDao;

	@Override
	public void save(Academy model) {
		academyDao.save(model);
	}

	@Override
	public List<Academy> findAll() {
		return academyDao.findAll();
	}
	
}
