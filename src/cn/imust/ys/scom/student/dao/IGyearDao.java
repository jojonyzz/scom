package cn.imust.ys.scom.student.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Gyear;

public interface IGyearDao extends IBaseDao<Gyear>{

	/**
	 * 得到年级下的所有班级
	 * */
	List<Integer> getAllClass(Integer yid);

}
