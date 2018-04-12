package cn.imust.ys.scom.student.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.utils.PoiExcel2k3Helper;
import cn.imust.ys.scom.base.utils.PoiExcel2k7Helper;
import cn.imust.ys.scom.base.utils.PoiExcelHelper;
import cn.imust.ys.scom.dept.dao.IDeptDao;
import cn.imust.ys.scom.society.dao.ISocietyDao;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.dao.ITermDao;
import cn.imust.ys.scom.student.domain.Dept;
import cn.imust.ys.scom.student.domain.Society;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.ISocietyService;

@Service
@Transactional
public class SocietyServiceImpl implements ISocietyService{
	@Resource private IStudentDao studentDao;
	@Resource private ISocietyDao societyDao;
	@Resource private IDeptDao deptDao;
	@Resource private ITermDao termDao;
	
	// 获取Excel处理类
	private static PoiExcelHelper getPoiExcelHelper(String filePath) {
		PoiExcelHelper helper;
		if (filePath.indexOf(".xlsx") != -1) {
			helper = new PoiExcel2k7Helper();
		} else {
			helper = new PoiExcel2k3Helper();
		}
		return helper;
	}

	@Override
	public void save(Society model) {
		
	}

	@Override
	public void doAddExcel(File upload, String uploadFileName,
			String uploadContentType, Integer termid) {
		
		if (upload == null) {
			throw new ScomException("请选择Excel文件！");
		}
		if (!uploadContentType.equals("application/vnd.ms-excel")) {
			if (!uploadContentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				throw new ScomException("上传文件类型错误！");
			}
		}
		PoiExcelHelper helper = getPoiExcelHelper(upload.getAbsolutePath());
		// 读取表头之后的数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(upload.getAbsolutePath(), 0, "2-");
		Society society = null;
		for (ArrayList<String> cellValuelist : dataList) {
			//根据录入的数据中学生
			String sno = cellValuelist.get(0);
			String stu_name = cellValuelist.get(1);
//			String className = cellValuelist.get(2);
			DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
			criteria.add(Restrictions.eq("sno", sno));
			criteria.add(Restrictions.eq("name",stu_name));
			List<Student> stus = studentDao.findByCriteria(criteria);
			if(stus!=null && stus.size()>0){
				//查找部门
				String deptName = cellValuelist.get(3);
				DetachedCriteria criteria2 = DetachedCriteria.forClass(Dept.class);
				criteria2.add(Restrictions.eq("deptname", deptName));
				List<Dept> depts = deptDao.findByCriteria(criteria2);
				if(depts!=null && depts.size()>0){
					String societyName = cellValuelist.get(4);
					String reason = cellValuelist.get(5);
					String score = cellValuelist.get(6);
					Term term = termDao.findById(termid);
					society = new Society(depts.get(0), societyName, score, reason);
					society.setTerm(term);
					societyDao.save(society);
				}else{
					throw new ScomException("未知部门 ！"+deptName);
				}
				
			}else{
				throw new ScomException("未找到学生！" + sno + " / " + stu_name);
			}
		}
		// 删除上传的文件
		upload.delete();
	}
}
