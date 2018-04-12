package cn.imust.ys.scom.student.service;

import java.util.List;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Gyear;


public interface IGyearService {

	void save(Gyear model,Integer id);

	List<Gyear> findAll();

	List<Gyear> listByMajor(Integer mid);

	Gyear findById(Integer yid);

	void findAll(PageBean pageBean);


}
