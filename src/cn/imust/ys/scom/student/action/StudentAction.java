package cn.imust.ys.scom.student.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.imust.ys.scom.base.exception.ScomException;
import cn.imust.ys.scom.base.utils.POIUtils;
import cn.imust.ys.scom.base.web.action.BaseAction;
import cn.imust.ys.scom.student.domain.BPrinciple;
import cn.imust.ys.scom.student.domain.CollengTest;
import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.Gyear;
import cn.imust.ys.scom.student.domain.StuClass;
import cn.imust.ys.scom.student.domain.Student;
import cn.imust.ys.scom.student.domain.StudyScom;
import cn.imust.ys.scom.student.domain.StudyScomRecord;
import cn.imust.ys.scom.student.domain.Term;
import cn.imust.ys.scom.student.service.IBPrincipleService;
import cn.imust.ys.scom.student.service.ICollengTestService;
import cn.imust.ys.scom.student.service.ICourseService;
import cn.imust.ys.scom.student.service.IGradeService;
import cn.imust.ys.scom.student.service.IGyearService;
import cn.imust.ys.scom.student.service.IStuClassService;
import cn.imust.ys.scom.student.service.IStudentService;
import cn.imust.ys.scom.student.service.IStudyScomService;
import cn.imust.ys.scom.student.service.ITermService;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(StudentAction.class);    
	@Resource
	IStudentService studentService;
	@Resource
	ICourseService courseService;
	@Resource
	IGradeService gradeService;
	@Resource
	ITermService termService;
	@Resource
	IGyearService gyearService;
	@Resource
	IStudyScomService studyScomService;
	@Resource ICollengTestService testService;
	@Resource IStuClassService stuClassService;
	@Resource IBPrincipleService principleService;
	
	private File upload; // 上传的Excel文件
	private String uploadFileName; // 上传文件名称
	private String uploadContentType; // 上传文件类型

	private Integer termid;

	public void setTermid(Integer termid) {
		this.termid = termid;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String queryPage() throws IOException {
		studentService.findAll(pageBean);
		String[] excludes = new String[] { "dept", "stuClass", "techno",
				"societies", "collengTests", "users", "technos",
				"grades" };
		writePageBean2Json(pageBean, excludes);
		return NONE;
	}

	private Integer yid;

	public void setYid(Integer yid) {
		this.yid = yid;
	}

	/**
	 * 得到该年级该学期的所有课程
	 * 
	 * @throws IOException
	 * */
	public String coursesByYearATid() throws IOException {
		List<Course> courses = studentService.listByYeatATid(yid, termid);
		String[] excludes = new String[] { "grades", "gyear", "term" };
		writeList2Json(courses, excludes);
		return NONE;
	}
	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * ajax 异步判断是否存在测评数据
	 * */
	public String isExistScom() {
		boolean v = studyScomService.isExistScom(class_id, termid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if (v) {
				// 如果存在返回 1,询问是否使用以前的测评结果
				response.getWriter().print("1");
			} else {
				// 如果不存在返回 0 ,直接进行测评运算,并保存测评记录
				response.getWriter().print("0");
			}
		} catch (Exception e) {
		}
		return NONE;
	}

	/**
	 * 使用以前的测评数据
	 * @throws Exception 
	 * */
	public String getHistoryScom() throws Exception {
		// 返回学习测评的数据列表
		List<StudyScom> studyScoms = studyScomService.getHistoryScom(class_id,
				termid);
		// TODO 还有社会测评的数据列表
		Term term = termService.findById(termid);

		Gyear gyear = gyearService.findById(yid);
		HSSFWorkbook work = POIUtils.getComExcel(term.getTime(),
				gyear.getName(), studyScoms);
		//文件下载
		String fileName = gyear.getName() + "综合测评.xls";
		fileName = getDownloadFileName(fileName);
		// 一个流两个头
		ServletOutputStream out = ServletActionContext.getResponse()
				.getOutputStream();
		String contentType = ServletActionContext.getServletContext()
				.getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition",
				"attchment;filename=" + fileName);
		work.write(out);
		return NONE;
	}

	private void getStudentScomByStudentAndCourse(List<Student> stus) {
		String[] split = ids.split(",");
		// 学生所有的课程
		for (Student s : stus) {
			// 在每个学生中遍历课程
			s.setGrades(null);
			s.setGrades(new HashSet<Grade>(0));
			//查询等级考试成绩
			List<CollengTest> collengTests = testService.findGradeByTAS(termid,s.getId());
			if(collengTests !=null){
				for (CollengTest collengTest : collengTests) {
					s.getCollengTests().add(collengTest);
				}
			}
			//违纪处理,根据学号和学期找违纪信息
			List<BPrinciple> principles = principleService.findBySnoAndTerm(s.getId(),termid);
			if(principles!=null){
				for (BPrinciple bPrinciple : principles) {
					s.getPrinciples().add(bPrinciple);
				}
			}
			for (int i = 0; i < split.length; i++) {
				/**
				 * 成绩查询使用,学生 ID , 学期 ID 和 课程 ID
				 * */
				Grade g = gradeService.findScoreBySidACid(s.getId(),
						Integer.parseInt(split[i]), termid);
				// g 为查询到的成绩,直接填充到学生到,对成绩的处理在事务外进行,避免托管态持久化
				// 对成绩进行处理
				if (g != null) {
					// 成绩缺失问题
					if (-1 == g.getScore() || 0 == g.getScore()) {
						// 1. 如果是降级生
						if (s.isJJ()) {
							// 如果是降级生查询以前成绩，通过即不算该次测评
							// 未通过则算本次成绩
							//根据学号定位多个 学生 id ,依次查询
							Double score = gradeService.findBeforeScore(
									s.getSno(), g.getCourse().getId());
							logger.info(s.getName() + " 为降级生\t查询[ "
									+ g.getCourse().getCname()
									+ " ] 缺失成绩历史最高分为 ：" + score + "\n");    
							if (score >= 60) {
								// 如果以前的成绩通过,则该课程不加入该次测评
							} else {
								// 如果未通过,则该课程的成绩,为本学期的成绩
								g.setScore(0.0);
								s.getGrades().add(g);
							}
						} else {
							// 如果是非降级生，则将成绩计为 0 分
							g.setScore(0.0);
							s.getGrades().add(g);
						}
					} else {
						s.getGrades().add(g);
					}
				}
			}
		}
	}

	public String save() {
		try {
			studentService.save(model);
			write(ajaxReturn(true, "录入成绩成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "发生异常"));
		}
		return NONE;
	}

	private Integer class_id;

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	/**
	 * Excel 录入成绩
	 * */
	public void upload() {
		try {
			studentService.doAddExcel(upload, uploadFileName,
					uploadContentType, termid, model, yid, class_id);
			write(ajaxReturn(true, "导入成功"));
		} catch (ScomException e) {
			e.printStackTrace();
			write(ajaxReturn(false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "信息填写错误"));
		}
	}

	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 提供转换编码后的供下载用的文件名
	 * */
	public String getDownloadFileName(String name) throws Exception {
		String agent = ServletActionContext.getRequest()
				.getHeader("user-agent");
		if (agent.contains("Firefox")) {
			return new String(name.getBytes("GB2312"), "ISO-8859-1");
		} else {
			return URLEncoder.encode(name, "UTF-8");
		}
	}
	private Integer flag;
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * 班级测评结果
	 * @throws IOException 
	 * */
	public String getClassScom() throws IOException{
		Term term = termService.findById(termid);
		StuClass stuClass = stuClassService.findById(class_id);
		//得到班级所在的年级
		Gyear gyear = stuClass.getGyear();
		if(flag == 1){
			//存在重新计算
			//删除以前的测评数据
			studyScomService.deleteScomListByTY(term,stuClass);
		}else if(flag == 2){
			//不存在直接计算
			//进行处理后保存测评数据
			studyScomService.saveRecord(new StudyScomRecord(term, stuClass));
		}
		//根据班级得到学生
		List<Student> students = studentService.getClassStudent(class_id);
		getStudentScomByStudentAndCourse(students);
		List<StudyScom> studyScoms = new ArrayList<StudyScom>();
		// 得到学生成绩的数据列表
		StudyScom scom = null;
		for (Student student : students) {
			if(student.getAmount() == -1.0){
				logger.info(student.getName() + "该学生跳过!");    
				continue;
			}
			scom = new StudyScom();
			scom.setSno(student.getSno());
			scom.setName(student.getName());
			scom.setClassname(student.getStuClass().getClassName());
			scom.setNopass(student.getNoPass());
			scom.setComputer(student.getComputer());
			scom.setTerm(term);
			scom.setStuClass(stuClass);
			scom.setAmount(student.getAmount());
			scom.setGrade(student.getComStudyGrade());
			scom.setEnglish(student.getEnglish());
			scom.setPrinciple(student.getPrinciple());
			scom.setGyear(gyear);
			studyScoms.add(scom);
		}
		studyScomService.saveScom(studyScoms,class_id,termid);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", studyScoms);
		request.setAttribute("tid", termid);
		request.setAttribute("cid", class_id);
		return "scomlist";
	}
	/**
	 * 通过班级和学期得到课程表
	 * @throws IOException 
	 * */
	public String coursesByClass() throws IOException{
		List<Course> courses = studentService.listByYearAndTerm(class_id, termid);
		String[] excludes = new String[] { "grades", "gyear", "term","stuClass"};
		writeList2Json(courses, excludes);
		return NONE;
	}
	/**
	 * 年级综合测评
	 * @throws Exception 
	 * */
	public String getScomYear() throws Exception{
		studyScomService.getScomYear(yid,termid);
		Gyear gyear = gyearService.findById(yid);
		Term term = termService.findById(termid);
		//得到测评数据
		List<StudyScom> studyScoms = studentService.getScomYear(yid,termid);
		HSSFWorkbook workbook = POIUtils.getComExcel(term.getTime(), gyear.getMajor().getMajorName()+""+gyear.getName(), studyScoms);
		String fileName = gyear.getMajor().getMajorName()+""+gyear.getName()+ "-综合测评.xls";
		fileName = getDownloadFileName(fileName);
		// 一个流两个头
		ServletOutputStream out = ServletActionContext.getResponse()
				.getOutputStream();
		String contentType = ServletActionContext.getServletContext()
				.getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition",
				"attchment;filename=" + fileName);
		workbook.write(out);
		
		//得到奖学金数据
//		HSSFWorkbook workbook = studyScomService.getScholarshipBy(yid,termid);
		return NONE;
	}
	/**
	 * 判断该年级下的所有班级有没有完成测评
	 * */
	public String isCompleteScom(){
		boolean v = studyScomService.isCompleteScom(yid, termid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if (v) {
				//完成
				response.getWriter().print("1");
			} else {
				// 未完成
				response.getWriter().print("0");
			}
		} catch (Exception e) {
		}
		return NONE;
	}
	/**
	 * 得到某年级下的奖学金
	 * @throws Exception 
	 * */
	public String getScholarshiplist() throws Exception{
		//TODO 奖学金数据
		Gyear gyear = gyearService.findById(yid);
		HSSFWorkbook workbook = studyScomService.getScholarshipBy(yid,termid);
		if(workbook != null){
			String fileName = gyear.getMajor().getMajorName()+""+gyear.getName()+ "-奖学金样表.xls";
			fileName = getDownloadFileName(fileName);
			// 一个流两个头
			ServletOutputStream out = ServletActionContext.getResponse()
					.getOutputStream();
			String contentType = ServletActionContext.getServletContext()
					.getMimeType(fileName);
			ServletActionContext.getResponse().setContentType(contentType);
			ServletActionContext.getResponse().setHeader("content-disposition",
					"attchment;filename=" + fileName);
			workbook.write(out);
		}
		return NONE;
	}
	/**
	 * 得到班级历史测评数据
	 * */
	public String getClassHistoryScom(){
		List<StudyScom> studyScoms = studyScomService.getClassHistoryScom(termid,class_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", studyScoms);
		request.setAttribute("tid", termid);
		request.setAttribute("cid", class_id);
		return "scomlist";
	}
	/**
	 * 下载班级测评结果表
	 * @throws Exception 
	 * */
	public String downloadClass() throws Exception{
		List<StudyScom> studyScoms = studyScomService.getClassHistoryScom(termid,class_id);
		// 将测评数据放到 excel 中
		Term term = termService.findById(termid);
		StuClass stuClass = stuClassService.findById(class_id);
		HSSFWorkbook work = POIUtils.getClassExcel(stuClass.getGyear().getMajor().getAcademy().getAcadname(), stuClass.getClassName(), term.getTime(), studyScoms);
		// 文件下载
		String fileName = stuClass.getClassName()+ "-班级测评.xls";
		fileName = getDownloadFileName(fileName);
		// 一个流两个头
		ServletOutputStream out = ServletActionContext.getResponse()
				.getOutputStream();
		String contentType = ServletActionContext.getServletContext()
				.getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(contentType);
		ServletActionContext.getResponse().setHeader("content-disposition",
				"attchment;filename=" + fileName);
		work.write(out);
		return NONE;
	}
	/**
	 * 查询学生是否存在
	 * */
	public String isExistStudentBySno(){
		boolean v = studentService.isExistStudentBySno(model.getSno());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if (v) {
				//存在
				response.getWriter().print("1");
			} else {
				//不存在
				response.getWriter().print("0");
			}
		} catch (Exception e) {
		}
		return NONE; 
	}
	/**
	 * 判断是否进行过综合测评
	 **/
	public String isScom(){
		boolean v = studentService.isScom(termid,yid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if (v) {
				//进行过
				response.getWriter().print("1");
			} else {
				//为进行过
				response.getWriter().print("0");
			}
		} catch (Exception e) {
		}
		return NONE; 
	}
}
