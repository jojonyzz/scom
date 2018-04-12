package cn.imust.ys.scom.student.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.student.dao.ICourseDao;
import cn.imust.ys.scom.student.dao.IGradeDao;
import cn.imust.ys.scom.student.dao.IGradeRecordDao;
import cn.imust.ys.scom.student.dao.IStuClassDao;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.GradeRecord;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.service.IGradeService;

@Service
@Transactional
public class GradeServiceImpl implements IGradeService {
	@Resource
	private IGradeDao gradeDao;
	@Resource
	private IGradeRecordDao gradeRecordDao;
	@Resource
	private ICourseDao courseDao;
	@Resource
	private IStuClassDao stuClassDao;
	@Resource
	private IStudentDao studentDao;

	@Override
	public Grade findScoreBySidACid(Integer id, int cid, int termid) {
		return gradeDao.findScoreBySidACid(id, cid, termid);
	}

	@Override
	public Double findBeforeScore(String sno, Integer cid) {
		// 根据学号找多个学生 id
		List<Integer> sids = studentDao.findBySno(sno);
		double[] scores = null;
		if (sids != null && sids.size() > 0) {
			// 如果找到将成绩放到数组中找最大的值
			scores = new double[sids.size()];
			for (int i = 0; i < sids.size(); i++) {
				Double score = gradeDao.findBeforeScore(sids.get(i), cid);
				if (score != null) {
					scores[i] = score;
				}
			}
			// 找最大值
		}
		return getMaxByArray(scores);
	}

	private double getMaxByArray(double[] score) {
		if (score != null) {

			double max = 0;
			for (int i = 0; i < score.length; i++) {
				if (score[i] > max) {
					max = score[i];
				}
			}
			return max;
		}else{
			return 0.0;
		}
	}

	/**
	 * 撤销成绩录入
	 * */
	@Override
	public void cancelGradeImport(Integer id) {
		GradeRecord gradeRecord = gradeRecordDao.findById(id);
		Integer clazid = gradeRecord.getStuClass().getId();
		Integer tid = gradeRecord.getTerm().getId();
		// 1. 删除录入的脏成绩
		// 1.1 根据班级找到学生
		Set<Student> students = stuClassDao.findById(clazid).getStudents();
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student stu = iterator.next();
			DetachedCriteria gc = DetachedCriteria.forClass(Grade.class);
			gc.add(Restrictions.eq("student.id", stu.getId()));
			gc.add(Restrictions.eq("term.id", tid));
			List<Grade> grades = gradeDao.findByCriteria(gc);
			for (Grade grade : grades) {
				Grade g = gradeDao.findScoreBySidACid(stu.getId(), grade
						.getCourse().getId(), tid);
				if (g != null) {
					gradeDao.delete(g);
				}
			}
		}
		// 2. 根据班级和学期删除课程
		courseDao.DeleteByCidAndTid(clazid, tid);
		// 3. 删除录入记录
		gradeRecordDao.delete(gradeRecord);
	}

	@Override
	public List<Grade> getGradeBySnoAndTid(String sno, Integer tid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("sno", sno));
		List<Student> list = studentDao.findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			DetachedCriteria c = DetachedCriteria.forClass(Grade.class);
			c.add(Restrictions.eq("term.id", tid));
			c.add(Restrictions.eq("student.id", list.get(0).getId()));
			List<Grade> gs = gradeDao.findByCriteria(c);
			if (list != null && list.size() > 0) {
				return gs;
			}
		}
		return null;
	}

	@Override
	public void update(Grade model) {
		Grade g = gradeDao.findById(model.getId());
		if (g != null) {
			g.setScore(model.getScore());
			gradeDao.update(g);
		}
	}
}
