package cn.imust.ys.scom.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imust.ys.scom.admin.dao.IRoleDao;
import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.utils.PageBean;
import cn.imust.ys.scom.base.utils.SheetUtils;
import cn.imust.ys.scom.student.dao.ICourseDao;
import cn.imust.ys.scom.student.dao.IGradeDao;
import cn.imust.ys.scom.student.dao.IGradeRecordDao;
import cn.imust.ys.scom.student.dao.IGyearDao;
import cn.imust.ys.scom.student.dao.IStuClassDao;
import cn.imust.ys.scom.student.dao.IStudentDao;
import cn.imust.ys.scom.student.dao.IStudyScomDao;
import cn.imust.ys.scom.student.dao.IStudyScomRecordDao;
import cn.imust.ys.scom.student.dao.ITermDao;
import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.domain.Role;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.IStudentService;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IGradeDao gradeDao;
	@Resource
	private ICourseDao courseDao;
	@Resource
	private IStuClassDao stuClassDao;
	@Resource
	private ITermDao termDao;
	@Resource private IStudyScomDao studyScomDao;
	@Resource private IGyearDao gyearDao;
	@Resource private IGradeRecordDao gradeRecordDao;
	@Resource private IStudyScomRecordDao studyScomRecordDao;
	@Resource private IRoleDao roleDao;
	@Override
	public void findAll(PageBean pageBean) {
		studentDao.queryPage(pageBean);
	}

	@Override
	public void save(Student model) {
		studentDao.save(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAddExcel(File upload, String uploadFileName,
			String uploadContentType, Integer termid, Student model, Integer yid,Integer class_id) {
		if (upload == null) {
			throw new ScomException("请选择Excel文件！");
		}
		if (!uploadContentType.equals("application/vnd.ms-excel")) {
			if (!uploadContentType
					.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				throw new ScomException("上传文件类型错误！");
			}
		}
		Term term = termDao.findById(termid);
		StuClass stuClass = stuClassDao.findById(class_id);
		//首先判断成绩是否导入过
		boolean v = gradeRecordDao.isImport(term,stuClass);
		if(v){
			throw new ScomException("该成绩已导入,不可重复导入!");
		}
		// 读取表头之后的数据
		HSSFWorkbook workbook;
		Object[] test1 = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(upload));
			test1 = SheetUtils.getStudentScoreList(workbook.getSheetAt(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Course> courses = (List<Course>) test1[0];
		List<Student> students = (List<Student>) test1[1];
		for (Course course : courses) {
			course.setStuClass(stuClass);
			course.setTerm(term);
			//一个班级一个学期只有一张课程表,这里的判断取消
			courseDao.save(course);
		}
		for (Student student : students) {
			student.setStuClass(stuClass);
			//首先判断学生是否存在，存在不存，将找到的对象返回，不存在则将学生保存
			//并且为学生授予权限
			DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
			criteria.add(Restrictions.eq("name", "学生"));
			List<Role> roles = roleDao.findByCriteria(criteria);
			//新添加班级判断
			Student stu = studentDao.saveNoExist(student,stuClass,roles.get(0));
			student.setId(stu.getId());
			Set<Grade> grades = student.getGrades();
			Iterator<Grade> iterator = grades.iterator();
			while (iterator.hasNext()) {
				Grade grade = iterator.next();
				grade.setTerm(term);
				//成绩关联课程
				Course findByAll = courseDao.findByAll(grade.getCourse(),class_id,termid);
				if (findByAll != null) {
					grade.setCourse(findByAll);
				}else{
					throw new ScomException("课程录入出错!");
				}
				gradeDao.save(grade);
			}
		}
		// 删除上传的文件
		upload.delete();
	}
	@Override
	public List<StudyScom> getStudentScoreList() {
		return studyScomDao.findScortAll();
	}

	@Override
	public List<Course> listByYeatATid(Integer yid, Integer termid) {
		return studentDao.listByYeatATid(yid, termid);
	}

	@Override
	public List<Student> getStudentListByDB(int yid) {
		return studentDao.getStudentListByDB(yid);
	}

	public void getScomlist(Integer termid, Integer yid) {
		//直接进行测评的请求处理,第一步,进行保存测评记录
		Term term = termDao.findById(termid);

		Gyear gyear = gyearDao.findById(yid);
		//删除以前的测评数据
		studyScomDao.deleteScomListByTY(term,gyear);
//		studyScomRecordDao.save(new StudyScomRecord(term, gyear));
	}

	@Override
	public List<Student> getClassStudent(Integer class_id) {
		//首先根据班级 ID 找到这个班下的学生
		List<Student> students = studentDao.findStudentByClass(class_id);
		return students;
	}

	@Override
	public List<Course> listByYearAndTerm(Integer class_id, Integer termid) {
		return studentDao.listByYearAndTerm(class_id, termid);
	}
	/**
	 * 得到某年级某学期下的综合测评表
	 * */
	@Override
	public List<StudyScom> getScomYear(Integer yid, Integer termid) {
		//根据年级 ID 和学期 ID 找到两个班的测评数据
		List<StudyScom> studyScoms = studyScomDao.sortScomList(yid,termid);
		if(studyScoms!=null && studyScoms.size()>0){
			return studyScoms;
		}
		return null;
	}

	/**
	 * 判断学生是否存在
	 * */
	@Override
	public boolean isExistStudentBySno(String sno) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Student.class);
		criteria.add(Restrictions.eq("sno", sno));
		List<Student> list = studentDao.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean isScom(Integer termid, Integer yid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StudyScom.class);
		criteria.add(Restrictions.eq("term.id", termid));
		criteria.add(Restrictions.eq("gyear.id", yid));
		criteria.add(Restrictions.isNotNull("majorrank"));
		List<StudyScom> list = studyScomDao.findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
}
