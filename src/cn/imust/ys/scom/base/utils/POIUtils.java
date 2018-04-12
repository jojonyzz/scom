package cn.imust.ys.scom.base.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.imust.ys.scom.student.domain.ScholarshipDistribution;
import cn.imust.ys.scom.student.domain.StudyScom;

public class POIUtils {

	static String fontName = "宋体";
	final static short VERTICAALIGNMENT = HSSFCellStyle.VERTICAL_CENTER;
	final static short NOBOLD = HSSFFont.BOLDWEIGHT_NORMAL;
	final static short BOLD = HSSFFont.BOLDWEIGHT_BOLD;

	/**
	 * 班级样表
	 * */
	public static HSSFWorkbook getClassExcel(String academyName,
			String className, String term, List<StudyScom> list)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFSheet sheet = workbook.createSheet("班级样表");
		// 设置默认行宽
		sheet.setDefaultColumnWidth(12);
		// 创建第一行标题行
		HSSFRow row = sheet.createRow(0);
		// 创建第一个单元格
		HSSFCell cell = row.createCell(0);
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("内蒙古科技大学学生素质测评表(班级样表)");
		// 创建第二行
		row = sheet.createRow(1);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("学院： " + academyName);
		cell = row.createCell(3);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
		// 得到普通样式
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("班级： " + className);
		// 学期
		cell = row.createCell(6);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 9));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue(term);
		// 制表时间
		cell = row.createCell(10);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 16));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("制表时间： "
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));

		// 创建第三行
		row = sheet.createRow(2);
		String[] title = new String[] { "序号", "班级", "学号", "姓名" };
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			sheet.addMergedRegion(new CellRangeAddress(2, 3, i, i));
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		// 学习成绩测评
		cell = row.createCell(4);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 9));
		cell.setCellStyle(setCellStyle(font, style, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("学习成绩测评");
		// 社会活动测评
		cell = row.createCell(10);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 10, 19));
		cell.setCellStyle(setCellStyle(font, style, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("社会活动测评");
		// 签字
		cell = row.createCell(20);
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 20, 20));
		cell.setCellStyle(setCellStyle(font, style, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("签字(学生本人)");
		// 第四行
		row = sheet.createRow(3);
		String[] studyTitle = new String[] { "学习成绩", "计算机成绩", "英语成绩", "合计",
				"班级排名", "不及格课程门次" };
		for (int i = 0; i < studyTitle.length; i++) {
			cell = row.createCell(i + 4);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyTitle[i]);
		}

		String[] socieTitle = new String[] { "学生干部工作加分", "课外科技活动加分",
				"文艺艺术活动加分", "体育活动加分", "思想品德加分", "其他加分", "扣分", "合计", "班级排名",
				"备注" };
		for (int i = 0; i < socieTitle.length; i++) {
			cell = row.createCell(i + 10);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(socieTitle[i]);
		}

		// 表数据填充，遍历 list 数据
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 4);
			StudyScom scom = list.get(i);
			// 第一列 序号
			cell = row.createCell(0);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(i + 1);
			// 第二列 班级
			cell = row.createCell(1);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getClassname());
			// 第三列 学号
			cell = row.createCell(2);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getSno());
			// 第四列 姓名
			cell = row.createCell(3);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getName());
			// 第五列 学习成绩
			cell = row.createCell(4);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getGrade());
			// 第六列 计算机成绩
			cell = row.createCell(5);
			cell.setCellStyle(getContextStyle(font, style));
			String computer = scom.getComputer();
			cell.setCellValue((computer.startsWith("0.0") ? "" : computer + ""));
			// 第七列 英语成绩
			cell = row.createCell(6);
			cell.setCellStyle(getContextStyle(font, style));
			Double english = scom.getEnglish();
			cell.setCellValue((english == 0.0 ? "" : english + ""));
			// 第八列 合计
			cell = row.createCell(7);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getAmount());
			// 第九列 班级排名
			cell = row.createCell(8);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getRank());
			// 第十列 不及格门次
			cell = row.createCell(9);
			cell.setCellStyle(getContextStyle(font, style));
			int nopass = scom.getNopass();
			cell.setCellValue((nopass == 0.0 ? "" : nopass + ""));
		}
		// 表尾说明制作
		int num = list.size() + 5;
		List<String> foots = new ArrayList<String>();
		foots.add("说明: 1. 本表一个评比单位制定一份,用十号字体.");
		foots.add("      2. 本表要求统一用EXCEL软件设计");
		foots.add("      3. 本表按学号升序排列");
		foots.add("      4. 本表需学生本人亲自确认，不得代签。");
		foots.add("      5. 有些表格已经提前设置，打印前请先预览。");
		for (int i = 0; i < foots.size(); i++) {
			row = sheet.createRow(num + i);
			sheet.addMergedRegion(new CellRangeAddress(num + i, num + i, 0, 10));
			cell = row.createCell(0);
			cell.setCellStyle(getHead2Style(font, style));
			cell.setCellValue(foots.get(i).toString());
		}
		return workbook;
	}

	/**
	 * 综合测评样表
	 * 
	 * @param term
	 * @param yearMajor
	 * @throws Exception
	 * */
	public static HSSFWorkbook getComExcel(String term, String yearMajor,
			List<StudyScom> list) throws Exception {
		//TODO 综合测评
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFSheet sheet = workbook.createSheet("学习测评--分年级按专业排名样表（报送学工）");
		// 创建第一行-标题行
		HSSFRow row = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
		sheet.setDefaultColumnWidth(14);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue(term + "  学生综合测评表");
		// 创建第二行
		row = sheet.createRow(1);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 10));
		cell.setCellStyle(setCellStyle(font, style, (short) 16, NOBOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("（学习活动测评分年级按专业排名表）");
		// 创建第三行
		// 学院
		row = sheet.createRow(2);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("学院（公章）：");
		// 制表日期
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 10));
		cell = row.createCell(6);
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("制表时间： "
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		// 创建第四行
		row = sheet.createRow(3);
		// 年级专业
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 4));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("年级专业：" + yearMajor);
		// 人数
		cell = row.createCell(6);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 10));
		cell.setCellStyle(getHead2Style(font, style));
		cell.setCellValue("人数： " + list.size());

		// 创建第五行
		row = sheet.createRow(4);
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 4, 10));
		cell = row.createCell(4);
		cell.setCellStyle(setCellStyle(font, style, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue("学习成绩测评");

		String[] title = new String[] { "序号", "班级", "学号", "姓名" };
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			sheet.addMergedRegion(new CellRangeAddress(4, 5, i, i));
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		// 创建第五行
		row = sheet.createRow(5);
		String[] study = new String[] { "学习成绩", "计算机成绩", "英语成绩", "合计",
				"不及格课程门次", "专业排名", "备注" };
		for (int i = 0; i < study.length; i++) {
			cell = row.createCell(i + 4);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(study[i]);
		}
		// 表数据填充，遍历 list 数据
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 6);
			StudyScom scom = list.get(i);
			// 第一列 序号
			cell = row.createCell(0);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(i + 1);
			// 第二列 班级
			cell = row.createCell(1);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getClassname());
			// 第三列 学号
			cell = row.createCell(2);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getSno());
			// 第四列 姓名
			cell = row.createCell(3);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getName());
			// 第五列 学习成绩
			cell = row.createCell(4);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getGrade());
			// 第六列 计算机成绩
			cell = row.createCell(5);
			cell.setCellStyle(getContextStyle(font, style));
			String computer = scom.getComputer();
			cell.setCellValue((computer.startsWith("0.0") ? "" : computer + ""));
			// 第七列 英语成绩
			cell = row.createCell(6);
			cell.setCellStyle(getContextStyle(font, style));
			Double english = scom.getEnglish();
			cell.setCellValue((english == 0.0 ? "" : english + ""));
			// 第八列 合计
			cell = row.createCell(7);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(scom.getAmount());
			// 第九列 不及格门次
			cell = row.createCell(8);
			cell.setCellStyle(getContextStyle(font, style));
			int nopass = scom.getNopass();
			cell.setCellValue((nopass == 0.0 ? "" : nopass + ""));
			// 第十列 专业排名
			cell = row.createCell(9);
			cell.setCellStyle(getContextStyle(font, style));
			cell.setCellValue(i + 1);

		}

		// 表尾说明制作
		int num = list.size() + 7;
		List<String> foots = new ArrayList<String>();
		foots.add("说明: 1. 本表一个评比单位制定一份,用十号字体.");
		foots.add("      2. 本表要求统一用EXCEL软件设计");
		foots.add("      3. 本表按学号升序排列");
		foots.add("      4. 本表需学生本人亲自确认，不得代签。");
		foots.add("      5. 有些表格已经提前设置，打印前请先预览。");
		for (int i = 0; i < foots.size(); i++) {
			row = sheet.createRow(num + i);
			sheet.addMergedRegion(new CellRangeAddress(num + i, num + i, 0, 10));
			cell = row.createCell(0);
			cell.setCellStyle(getHead2Style(font, style));
			cell.setCellValue(foots.get(i).toString());
		}
		return workbook;
	}

	/**
	 * 奖学金样表
	 * 
	 * @param term
	 * @param academyName
	 * */
	public static HSSFWorkbook getScholarshipExcel(String term,
			String academyName,
			ScholarshipDistribution scholarshipDistribution,
			List<StudyScom> studyScoms) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFFont font = workbook.createFont();
		font.setFontName(fontName);
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFSheet studySheet = workbook.createSheet("学习奖样表");
		HSSFSheet societySheet = workbook.createSheet("活动奖样表");
		HSSFSheet societyAmount = workbook.createSheet("活动汇总");
		HSSFSheet studyAmount = workbook.createSheet("学习奖汇总");
		//默认单元格宽
		studySheet.setDefaultColumnWidth(14);
		societySheet.setDefaultColumnWidth(14);
		studyAmount.setDefaultColumnWidth(14);
		societyAmount.setDefaultColumnWidth(14);
		//合并单元格
		studySheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		studySheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));
		societySheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		societySheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));
		studyAmount.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		studyAmount.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		societyAmount.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		societyAmount.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		//TODO 奖学金
		//学习
		getStudySheet(term, academyName, studyScoms, font, style, studySheet,scholarshipDistribution);
		//社会
		getSocietySheet(term, academyName, null, font, style, societySheet);
		List<StudyScom> studyAmountlist = null;
		//学习汇总
		getStudyAmount(term, academyName, studyAmountlist, font, style,
				studyAmount);
		//社会汇总
		getSocietyAmount(term, academyName, studyAmountlist, font, style,
				societyAmount);
		return workbook;
	}

	private static void getStudySheet(String term, String academyName,
			List<StudyScom> list, HSSFFont font, HSSFCellStyle style,
			HSSFSheet studySheet, ScholarshipDistribution scholarshipDistribution) {
		// 奖学金 excel
		// 学习奖样表,创建头
		HSSFRow row = studySheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue(term + "学习奖学金明细表");
		// 第二行,公章和制表时间
		row = studySheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT));
		cell.setCellValue("学院（公章）：" + academyName
				+ "                      制表日期："
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		// 第三行 内容标题行
		String[] title = new String[] { "序号", "姓名", "班级", "学号", "专业排名", "等级",
				"金额(元)", "备注" };
		row = studySheet.createRow(2);
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		if(list.size() < (scholarshipDistribution.getFnum()+scholarshipDistribution.getSnum()*2)){
			System.out.println("该年级通过人数不足!");
		}
		//一等奖 填充数据
		for (int i = 0; i < scholarshipDistribution.getFnum(); i++) {
			row = studySheet.createRow(i+3);
			//第一列 序号 
			cell = row.createCell(0);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(i+1);
			//第二列 姓名
			StudyScom studyScom = list.get(i);
			cell = row.createCell(1);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getName());
			//第三列 班级
			cell = row.createCell(2);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getClassname());
			//第四列 学号
			cell = row.createCell(3);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getSno());
			//第五列 专业排名
			cell = row.createCell(4);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getMajorrank());
			//第六列  等级
			cell = row.createCell(5);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("一等奖");
			//第七列 金额 
			cell = row.createCell(6);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("700");
		}
		//二等奖 填充数据
		for (int i = 0; i < scholarshipDistribution.getSnum(); i++) {
			row = studySheet.createRow(i+3+scholarshipDistribution.getFnum());
			//第一列 序号 
			cell = row.createCell(0);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(i+1+scholarshipDistribution.getFnum());
			//第二列 姓名
			StudyScom studyScom = list.get(i+scholarshipDistribution.getFnum());
			cell = row.createCell(1);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getName());
			//第三列 班级
			cell = row.createCell(2);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getClassname());
			//第四列 学号
			cell = row.createCell(3);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getSno());
			//第五列 专业排名
			cell = row.createCell(4);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getMajorrank());
			//第六列  等级
			cell = row.createCell(5);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("二等奖");
			//第七列 金额 
			cell = row.createCell(6);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("500");
		}
		
		//三等奖 填充数据
		for (int i = 0; i < scholarshipDistribution.getSnum(); i++) {
			row = studySheet.createRow(i+3+scholarshipDistribution.getFnum()+scholarshipDistribution.getSnum());
			//第一列 序号 
			cell = row.createCell(0);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(i+1+scholarshipDistribution.getFnum()+scholarshipDistribution.getSnum());
			//第二列 姓名
			StudyScom studyScom = list.get(i+scholarshipDistribution.getFnum()+scholarshipDistribution.getSnum());
			cell = row.createCell(1);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getName());
			//第三列 班级
			cell = row.createCell(2);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getClassname());
			//第四列 学号
			cell = row.createCell(3);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getSno());
			//第五列 专业排名
			cell = row.createCell(4);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(studyScom.getMajorrank());
			//第六列  等级
			cell = row.createCell(5);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("三等奖");
			//第七列 金额 
			cell = row.createCell(6);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue("300");
		}
		
		// 表尾注释
		int num = scholarshipDistribution.getFnum()+scholarshipDistribution.getSnum()*2;
		row = studySheet.createRow(num + 4);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		studySheet.addMergedRegion(new CellRangeAddress(num+4,num+4,0,7));
		Integer amount = scholarshipDistribution.getFnum()*700+scholarshipDistribution.getSnum()*500+scholarshipDistribution.getSnum()*300;
		cell.setCellValue("合计"+amount);
		row = studySheet.createRow(num + 5);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		studySheet.addMergedRegion(new CellRangeAddress(num+5,num+5,0,7));
		cell.setCellValue("批准（签字）：                  审核（签字）：                制表：（签字）");
	}

	/**
	 * 社会奖学金页
	 * */
	private static void getSocietySheet(String term, String academyName,
			List<StudyScom> list, HSSFFont font, HSSFCellStyle style,
			HSSFSheet studySheet) {
		// 奖学金 excel
		// 学习奖样表,创建头
		HSSFRow row = studySheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue(term + "社会活动奖学金明细表");
		// 第二行,公章和制表时间
		row = studySheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT));
		cell.setCellValue("学院（公章）：" + academyName
				+ "                      制表日期："
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		// 第三行 内容标题行
		String[] title = new String[] { "序号", "姓名", "班级", "学号", "专业排名", "等级",
				"金额(元)", "备注" };
		row = studySheet.createRow(2);
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		// 表尾注释
		int num = 20;
		row = studySheet.createRow(num + 4);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("合计");
		row = studySheet.createRow(num + 5);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("批准（签字）：                  审核（签字）：                制表：（签字）");
	}

	/**
	 * 学习奖学金汇总
	 * */
	private static void getSocietyAmount(String term, String academyName,
			List<StudyScom> list, HSSFFont font, HSSFCellStyle style,
			HSSFSheet studySheet) {
		// 奖学金 excel
		// 学习奖样表,创建头
		HSSFRow row = studySheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue(term + "社会活动奖学金汇总表");
		// 第二行,公章和制表时间
		row = studySheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT));
		cell.setCellValue("学院（公章）：" + academyName
				+ "                      制表日期："
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		// 第三行 内容标题行
		String[] title = new String[] { "序号", "专业年级", "参评人数", "获奖人数", "总金额(元)" };
		row = studySheet.createRow(2);
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		// 表尾注释
		int num = 20;
		row = studySheet.createRow(num + 4);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("合计");
		row = studySheet.createRow(num + 5);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("批准（签字）：                  审核（签字）：                制表：（签字）");
	}

	/**
	 * 社会奖学金汇总
	 * */
	private static void getStudyAmount(String term, String academyName,
			List<StudyScom> list, HSSFFont font, HSSFCellStyle style,
			HSSFSheet studySheet) {
		// 奖学金 excel
		// 学习奖样表,创建头
		HSSFRow row = studySheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER));
		cell.setCellValue(term + "学习奖学金汇总表");
		// 第二行,公章和制表时间
		row = studySheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(font, style, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT));
		cell.setCellValue("学院（公章）：" + academyName
				+ "                      制表日期："
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		// 第三行 内容标题行
		String[] title = new String[] { "序号", "专业年级", "参评人数", "获奖人数", "总金额(元)" };
		row = studySheet.createRow(2);
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(getHead3Style(font, style));
			cell.setCellValue(title[i]);
		}
		// 表尾注释
		int num = 20;
		row = studySheet.createRow(num + 4);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("合计");
		row = studySheet.createRow(num + 5);
		cell = row.createCell(0);
		cell.setCellStyle(getHead3Style(font, style));
		cell.setCellValue("批准（签字）：                  审核（签字）：                制表：（签字）");
	}

	private static HSSFCellStyle setCellStyle(HSSFFont font,
			HSSFCellStyle style, short size, short boldweight, short alignment) {
		// 设置单元格字体样式
		font.setBoldweight(boldweight);// 加粗字体
		font.setFontHeightInPoints((short) size);
		// 设置样式
		style.setWrapText(true);
		style.setAlignment(alignment);
		style.setVerticalAlignment(VERTICAALIGNMENT);
		style.setFont(font);
		return style;
	}

	private static HSSFCellStyle getHead3Style(HSSFFont font,
			HSSFCellStyle style) {
		return setCellStyle(font, style, (short) 11, NOBOLD,
				HSSFCellStyle.ALIGN_CENTER);
	}

	private static HSSFCellStyle getHead2Style(HSSFFont font,
			HSSFCellStyle style) {
		return setCellStyle(font, style, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT);
	}

	/**
	 * 设置正文单元格样式
	 * */
	private static HSSFCellStyle getContextStyle(HSSFFont font,
			HSSFCellStyle style) {
		return setCellStyle(font, style, (short) 11, NOBOLD,
				HSSFCellStyle.ALIGN_CENTER);
	}

}
