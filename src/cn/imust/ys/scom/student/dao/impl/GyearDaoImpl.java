package cn.imust.ys.scom.student.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.dao.IGyearDao;
import cn.imust.ys.scom.student.domain.Gyear;

@Repository
public class GyearDaoImpl extends BaseDaoImpl<Gyear> implements IGyearDao{
	
	/**
	 * 得到某年级下的所有班级
	 * */
	@Override
	public List<Integer> getAllClass(Integer yid) {
		String hql = "select sc.id from Gyear y right outer join y.stuClasses sc where y.id=?";
		@SuppressWarnings("unchecked")
		List<Integer> claz = this.getHibernateTemplate().find(hql,yid);
		if(claz != null && claz.size()>0){
			return claz;
		}
		return null;
	}

}
