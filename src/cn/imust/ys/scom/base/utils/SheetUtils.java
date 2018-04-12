package cn.imust.ys.scom.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import cn.imust.ys.scom.student.domain.Course;
import cn.imust.ys.scom.student.domain.Grade;
import cn.imust.ys.scom.student.domain.Student;

public class SheetUtils {
	
	public static Object[] getStudentScoreList(HSSFSheet sheet)
			throws IOException {
		HSSFRow row = null;
		// 先取表头课程信息
		List<Course> courses = new ArrayList<Course>();
		Course course = null;
		// 第一行取课程号
		row = sheet.getRow(1);
		short num = row.getLastCellNum();
		for (int j = 5; j < num; j++) {
			String str = getCellVal(sheet, 1, j);
			if (StringUtils.isBlank(str)) {
				continue;
			} else {
				course = new Course();
				course.setCno(str);
				courses.add(course);
			}
		}
		int length = courses.size();
		// 第二行取课程名
		for (int j = 5; j < length + 5 ; j++) {
			courses.get(j - 5).setCname(getCellVal(sheet, 2, j));
		}
		// 第三行取学号
		for (int j = 5; j < length + 5 ; j++) {
			courses.get(j - 5).setCredit(Double.parseDouble(getCellVal(sheet, 3, j)));
		}

		List<Student> students = new ArrayList<Student>();
		Student student = null;
		Grade grade = null;
		for (int i = 5; i <= sheet.getLastRowNum(); i++) {
			student = new Student();
			// 取学号
			student.setSno(getCellVal(sheet, i, 1));
			// 取姓名
			student.setName(getCellVal(sheet, i, 4));
			// 放入每个学生的成绩
			for (int j = 5; j < length + 5 ; j++) {
				// 每个成绩对应一个科目
				grade = new Grade();
				grade.setStudent(student);
				grade.setCourse(courses.get(j - 5));
				grade.setScore(getScore(getCellVal(sheet, i, j)));
				student.getGrades().add(grade);
			}
			students.add(student);
		}
		Object[] objects = new Object[2];
		objects[0] = courses;
		objects[1] = students;
		return objects;
	}

	/**
	 * 这个方法用来返回 指定行，指定列的元素值
	 * */
	public static String getCellVal(HSSFSheet sheet, int rowNum, int cellNum) {
		HSSFCell cell = sheet.getRow(rowNum).getCell(cellNum);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		return cell.getStringCellValue().replaceAll("\r", "")
				.replaceAll("\n", "").trim();
	}
	
	public static Double getScore(String string) {

		if ("-".equals(string)) {
			return (double) -1;
		} else {
			try {
				return Double.parseDouble(string);
			} catch (Exception e) {
				return getScoreByKey(string);
			}
		}
	}

	public static Double getScoreByKey(String string) {
		Map<String, Double> maps = new HashMap<String, Double>();
		maps.put("优", (double) 95);
		maps.put("良", (double) 85);
		maps.put("中", (double) 75);
		maps.put("及格", (double) 65);
		maps.put("不及格", (double) 55);
		maps.put("良好", (double) 85);
		maps.put("优秀", (double) 95);
		maps.put("中等", (double) 75);
		return maps.get(string);
	}
}
