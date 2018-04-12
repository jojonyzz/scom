package cn.imust.ys.scom.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.utils.POIUtils;
import cn.imust.ys.scom.student.dao.IGyearDao;
import cn.imust.ys.scom.student.dao.IScholarshipDistributionDao;
import cn.imust.ys.scom.student.dao.IStuClassDao;
import cn.imust.ys.scom.student.dao.IStudyScomDao;
import cn.imust.ys.scom.student.dao.IStudyScomRecordDao;
import cn.imust.ys.scom.student.dao.ITermDao;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.domain.ScholarshipDistribution;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.StudyScomRecord;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.IStudyScomService;

@Service
@Transactional
public class StudyScomServiceImpl implements IStudyScomService {
	@Resource
	IStudyScomDao studyScomDao;
	@Resource
	IStudyScomRecordDao scomRecordDao;
	@Resource
	IGyearDao gyearDao;
	@Resource
	IStuClassDao stuClassDao;
	@Resource
	IScholarshipDistributionDao scholarshipDistributionDao;
	@Resource
	ITermDao termDao;

	@Override
	public void save(StudyScom scom) {
		studyScomDao.save(scom);
	}

	@Override
	public boolean isExistScom(Integer class_id, Integer termid) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(StudyScomRecord.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("stuClass.id", class_id));
		List<StudyScomRecord> list = scomRecordDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return true;
			// 存在测评数据
		}
		// 不存在测评数据
		return false;
	}

	@Override
	public List<StudyScom> getHistoryScom(Integer yid, Integer termid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("stuClass.id", yid));
		criteria.addOrder(Order.desc("amount"));
		criteria.setProjection(Projections.distinct(Projections.property("sno")));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void saveRecord(StudyScomRecord studyScomRecord) {
		scomRecordDao.save(studyScomRecord);
	}

	@Override
	public List<StudyScom> sortScomList(Integer termid, Integer yid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("gyear.id", yid));
		criteria.addOrder(Order.desc("amount"));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void deleteScomListByTY(Term term, StuClass stuClass) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", term.getId()));
		criteria.add(Restrictions.eq("stuClass.id", stuClass.getId()));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		for (StudyScom studyScom : list) {
			studyScomDao.delete(studyScom);
		}
	}

	/**
	 * 得到奖学金数据
	 * */
	@Override
	public HSSFWorkbook getScholarshipBy(Integer yid,Integer termid) {
		//得到该年级下未挂科,未违纪的学生
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("gyear.id", yid));
		criteria.addOrder(Order.desc("amount"));
		criteria.add(Restrictions.eq("nopass", 0));
		criteria.add(Restrictions.eq("principle", 0));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			
		}
		//查询带有测评成绩的总人数
		Long num = studyScomDao.getHandScoreNum(yid,termid);
		if(num !=null){
			//在录入 excel 中计算,一等奖人数 5% 700,二等奖人数 10% 500 ,三等奖人数  10% 300
			//TODO 使用进位取整
			Integer fnum = (int) Math.round(num*0.05);
			Integer snum = (int) Math.ceil(num*0.1); 
			//TODO 在此记录奖学金分配记录 , 年级,学期,一等奖人数,二等奖人数,三等奖人数
			Term term = termDao.findById(termid);
			Gyear gyear = gyearDao.findById(yid);
			ScholarshipDistribution scholarshipDistribution = new ScholarshipDistribution(term,gyear,fnum,snum,snum);
			scholarshipDistributionDao.save(scholarshipDistribution);
			HSSFWorkbook workbook = POIUtils.getScholarshipExcel(term.getTime(), gyear.getMajor().getAcademy().getAcadname(), scholarshipDistribution, list);
			return workbook;
		}
		return null;
	}

	@Override
	public List<StudyScom> getClassHistoryScom(Integer termid, Integer class_id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("stuClass.id", class_id));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 得到年级下的测评数据
	 * */
	@Override
	public void getScomYear(Integer yid, Integer termid) {
		// 首先得到该年级下的所有的班级
		List<Integer> claz = gyearDao.getAllClass(yid);
		// 找到各个班的测评数据
		for (Integer class_id : claz) {
			// 得到班级测评
			List<StudyScom> classHistoryScom = getClassHistoryScom(termid,
					class_id);
			if (classHistoryScom != null && classHistoryScom.size() > 0) {
				// TODO IO 流,压缩
			} else {
				StuClass stuClass = stuClassDao.findById(class_id);
				throw new ScomException(stuClass.getClassName()
						+ "未进行班级测评,不能计算综合测评!");
			}
		}
	}

	/**
	 * 批量保存测评数据并排序
	 * */
	@Override
	public void saveScom(List<StudyScom> students, Integer class_id,
			Integer termid) {
		for (StudyScom studyScom : students) {
			studyScomDao.save(studyScom);
		}
		// 按顺序去出来
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("stuClass.id", class_id));
		criteria.addOrder(Order.desc("amount"));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setRank(i + 1);
			}
		}
	}

	@Override
	public boolean isCompleteScom(Integer yid, Integer termid) {
		List<Integer> claz = gyearDao.getAllClass(yid);
		for (Integer integer : claz) {
			// 根据班级和学期判断是否完成测评
			DetachedCriteria criteria = DetachedCriteria
					.forClass(StudyScomRecord.class);
			criteria.add(Restrictions.eq("term.id", termid));
			criteria.add(Restrictions.eq("stuClass.id", integer));
			List<StudyScomRecord> list = scomRecordDao.findByCriteria(criteria);
			if (list != null && list.size() > 0) {
				// 找到
			} else {
				// 未找到,返回
				return false;
			}
		}
		return true;
	}
}
