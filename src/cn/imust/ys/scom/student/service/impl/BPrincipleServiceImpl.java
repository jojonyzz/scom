package cn.imust.ys.scom.student.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.student.dao.IBPrincipleDao;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.domain.BPrinciple;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.service.IBPrincipleService;

@Service
@Transactional
public class BPrincipleServiceImpl implements IBPrincipleService {
	@Resource
	private IBPrincipleDao principleDao;
	@Resource
	private IStudentDao studentDao;

	@Override
	public List<BPrinciple> findBySnoAndTerm(Integer id, Integer termid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BPrinciple.class);
		criteria.add(Restrictions.eq("student.id", id));
		criteria.add(Restrictions.eq("term.id", termid));
		List<BPrinciple> list = principleDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public void save(BPrinciple model) {
		List<Student> students = null;
		if (model.getStudent() != null) {
			students = studentDao.findStudentBySnoAndName(model
					.getStudent());
		}
		if (students != null ) {
			for (Student student : students) {
				model.getStudent().setId(student.getId());
				principleDao.save(model);
			}
		}
	}

	@Override
	public void doAddExcel(File upload, String uploadFileName,
			String uploadContentType) {

	}

	@Override
	public void findAll(PageBean pageBean) {
		principleDao.queryPage(pageBean);
	}

	@Override
	public void delete(BPrinciple model) {
		principleDao.delete(model);
	}
}
