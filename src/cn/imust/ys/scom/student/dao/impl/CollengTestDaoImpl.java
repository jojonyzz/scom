package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.ICollengTestDao;
import cn.imust.ys.scom.student.domain.CollengTest;

@Repository
public class CollengTestDaoImpl extends BaseDaoImpl<CollengTest> implements ICollengTestDao{

	@Override
	public List<CollengTest> getImportListBySid(Integer id, String name) {
		String hql = "select c from CollengTest c left outer join c.student s where s.id=? or c.importName = ?";
		@SuppressWarnings("unchecked")
		List<CollengTest> list = this.getHibernateTemplate().find(hql,id,name);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
