package cn.imust.ys.scom.student.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.admin.dao.INoticeDao;
import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Notice;
import cn.imust.ys.scom.student.service.INoticeService;

@Service
@Transactional
public class NoticeServiceImpl implements INoticeService{
	@Resource private INoticeDao noticeDao;
	@Override
	public void save(Notice model) {
		noticeDao.save(model);
	}
	@Override
	public void findAll(PageBean pageBean) {
		noticeDao.queryPage(pageBean);
	}

}
