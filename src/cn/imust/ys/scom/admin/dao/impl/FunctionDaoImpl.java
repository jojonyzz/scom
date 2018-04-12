package cn.imust.ys.scom.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.imust.ys.scom.admin.dao.IFunctionDao;
import cn.imust.ys.scom.base.dao.impl.BaseDaoImpl;
import cn.imust.ys.scom.student.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements
		IFunctionDao {

	@SuppressWarnings("unchecked")
	public List<Function> findAllMenu() {
		String hql = "from Function where generatemenu = '1' ORDER BY id DESC";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findMenuByUserId(Integer id) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles r"
				+ " LEFT OUTER JOIN r.users u WHERE u.id = ? and f.generatemenu = '1' ORDER BY f.id DESC";
		return this.getHibernateTemplate().find(hql, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findBySno(String sno) {
		String hql = "select DISTINCT f from Function f left outer join f.roles r left outer join r.users u left outer join u.student s where s.sno=?";
		return this.getHibernateTemplate().find(hql,sno);
	}
}
