package cn.imust.ys.scom.admin.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.Function;

public interface IFunctionDao extends IBaseDao<Function>{

	List<Function> findAllMenu();

	List<Function> findMenuByUserId(Integer id);

	List<Function> findBySno(String sno);

}
