package cn.imust.ys.scom.student.dao;

import java.util.List;

import cn.imust.ys.scom.base.dao.IBaseDao;
import cn.imust.ys.scom.student.domain.CollengTest;

public interface ICollengTestDao extends IBaseDao<CollengTest>{

	List<CollengTest> getImportListBySid(Integer id, String name);

}
