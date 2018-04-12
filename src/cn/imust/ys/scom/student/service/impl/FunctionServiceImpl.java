package cn.imust.ys.scom.student.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.admin.dao.IFunctionDao;
import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.domain.Function;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.IFunctionService;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{
	@Resource IFunctionDao functionDao;

	public void queryPage(PageBean pageBean) {
		functionDao.queryPage(pageBean);
	}

	public List<Function> findAll() {
		return functionDao.findAll();
	}

	public void save(Function model) {
		Function function = model.getFunction();
		if(function != null && null == function.getId()){
			model.setFunction(null);
		}
		functionDao.save(model);
	}
	public List<Function> findMenu() {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		List<Function> functions = null;
		if(user.getAccount().equals("admin")){
			functions = functionDao.findAllMenu();
		}else{
			functions = functionDao.findMenuByUserId(user.getId());
		}
		return functions;
	}

	@Override
	public List<Function> findBySno(String sno) {
		return functionDao.findBySno(sno);
	}
}
