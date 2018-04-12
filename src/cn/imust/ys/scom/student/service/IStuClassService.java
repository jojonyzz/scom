package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.StuClass;

public interface IStuClassService {

	void save(StuClass model);

	List<StuClass> listByGyear(Integer gid);

	StuClass findById(Integer class_id);

	List<StuClass> findAll();

	void findAll(PageBean pageBean);

}
