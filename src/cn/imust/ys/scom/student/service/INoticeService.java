package cn.imust.ys.scom.student.service;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Notice;


public interface INoticeService {

	void save(Notice model);

	void findAll(PageBean pageBean);


}
