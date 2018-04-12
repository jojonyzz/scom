package cn.imust.ys.scom.student.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.dao.IGradeRecordDao;
import cn.imust.ys.scom.student.service.IGradeRecordService;

@Service
@Transactional
public class GradeRecordServiceImpl implements IGradeRecordService{
	@Resource IGradeRecordDao gradeRecordDao;

	@Override
	public void findAll(PageBean pageBean) {
		gradeRecordDao.queryPage(pageBean);
	}
}
