package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Function;


public interface IFunctionService {

	void queryPage(PageBean pageBean);

	List<Function> findAll();

	void save(Function model);

	List<Function> findMenu();

	List<Function> findBySno(String sno);


}
