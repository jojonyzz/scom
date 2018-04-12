package cn.imust.ys.scom.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.imust.ys.scom.base.utils.PageBean;


public interface IBaseDao<T> {
	
	public void save(T entity);
	
	public void saveOrUpdate(T entirty);
	
	public void delete(T entity);
	
	public void update(T entity);
	
	public T findById(Serializable id);

	public List<T> findAll();
	
	List<T> findByCriteria(DetachedCriteria criteria);
	
	//通用的修改方法
	public void executeUpdate(String queryName,Object ... objects);
	/**
	 * 通用的分页方法
	 * */
	public void queryPage(PageBean pageBean);
}
