package cn.imust.ys.scom.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import cn.imust.ys.scom.admin.dao.IBackBugDao;
import cn.imust.ys.scom.admin.domain.BackBug;
import cn.imust.ys.scom.admin.service.IBackBugService;

@Service
@Transactional
public class BackBugServiceImpl implements IBackBugService{
	@Resource IBackBugDao backBugDao;
	@Override
	public void save(BackBug model) {
		backBugDao.save(model);
	}
	@Override
	public List<BackBug> findAll() {
		backBugDao.findAll();
		return null;
	}

}
