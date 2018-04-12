package cn.imust.ys.scom.student.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.utils.PoiExcel2k3Helper;
import cn.imust.ys.scom.base.utils.PoiExcel2k7Helper;
import cn.imust.ys.scom.base.utils.PoiExcelHelper;
import cn.imust.ys.scom.student.dao.ICollengTestDao;
import cn.imust.ys.scom.student.dao.IRankListDao;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.dao.ITermDao;
import cn.imust.ys.scom.student.domain.CollengTest;
import cn.imust.ys.scom.student.domain.RankList;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.domain.User;
import cn.imust.ys.scom.student.service.ICollengTestService;

@Service
@Transactional
public class CollengTestServiceImpl implements ICollengTestService{
	@Resource private ICollengTestDao collengTestDao;
	@Resource private IStudentDao studentDao;
	@Resource private IRankListDao rankListDao;
	@Resource private ITermDao termDao;

	@Override
	public void save(CollengTest model) {
		String sno = model.getStudent().getSno();
		String name = model.getStudent().getName();
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("sno", sno));
		criteria.add(Restrictions.eq("name",name));
		List<Student> stus = studentDao.findByCriteria(criteria);
		if(stus!=null && stus.size()>0){
			//3-17 以学号为标识,为这个学号的每一个学生添加
			for (Student student : stus) {
				Integer tid = model.getTerm().getId();
				Integer rid = model.getRankList().getId();
				Term term = termDao.findById(tid);
				RankList rank = rankListDao.findById(rid);
				model.setTerm(term);
				model.setRankList(rank);
				model.setStudent(student);
				model.setDate(new Date());
				collengTestDao.save(model);
				User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				model.setImportName(user.getName());
			}
		}else{
			throw new ScomException("未找到学生！");
		}
	}

	@Override
	public void doAddExcel(File upload, String uploadFileName,
			String uploadContentType,Integer termid) {
		
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
		CollengTest collengTest = null;
		for (ArrayList<String> cellValuelist : dataList) {
			
			String coursename = cellValuelist.get(2);
			String score = cellValuelist.get(3);
			DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
			criteria.add(Restrictions.eq("sno", cellValuelist.get(0)));
			criteria.add(Restrictions.eq("name",cellValuelist.get(1)));
			List<Student> stus = studentDao.findByCriteria(criteria);
			if(stus!=null && stus.size()>0){
				collengTest = new CollengTest();
				DetachedCriteria criteria2 = DetachedCriteria.forClass(RankList.class);
				criteria2.add(Restrictions.eq("name", coursename));
				List<RankList> rankLists = rankListDao.findByCriteria(criteria2);
				Term term = termDao.findById(termid);
				collengTest.setStudent(stus.get(0));
				collengTest.setTerm(term);
				collengTest.setScore(score);
				collengTest.setRankList(rankLists.get(0));
				collengTest.setDate(new Date());
				User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				collengTest.setImportName(user.getName());
			}else{
				throw new ScomException("未找到学生！");
			}
			collengTestDao.save(collengTest);
		}
		// 删除上传的文件
		upload.delete();
	}
	
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
	public List<CollengTest> findGradeByTAS(Integer termid, Integer sid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CollengTest.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("student.id", sid));
		List<CollengTest> list = collengTestDao.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<CollengTest> getImportListBySid(Integer id,String name) {
		return collengTestDao.getImportListBySid(id,name);
	}

	@Override
	public void delete(CollengTest model) {
		collengTestDao.delete(model);
	}
	
}
