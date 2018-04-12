package cn.imust.ys.test;

import java.io.FileOutputStream;
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
import org.junit.Test;

public class POIUtils {

	String fontName = "宋体";
	final short VERTICAALIGNMENT = HSSFCellStyle.VERTICAL_CENTER;
	final short NOBOLD = HSSFFont.BOLDWEIGHT_NORMAL;
	final short BOLD = HSSFFont.BOLDWEIGHT_BOLD;

	@Test
	public void test1() throws Exception {
//		getComExcel("2015-2016学年第一学期", "2014级 计算机科学与技术", new ArrayList<Object>());
		getClassExcel("信息工程学院", "计外一班", "2015-2016学年第一学期",new ArrayList<Object>());
	}
	/**
	 * 班级样表
	 * */
	public void getClassExcel(String academyName, String className, String term,List<?> list)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet("班级样表");
		// 设置默认行宽
		sheet.setDefaultColumnWidth(12);
		// 创建第一行标题行
		HSSFRow row = sheet.createRow(0);
		// 创建第一个单元格
		HSSFCell cell = row.createCell(0);
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
		cell.setCellStyle(setCellStyle(workbook, fontName, (short) 16, BOLD,
				HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER));
		cell.setCellValue("内蒙古科技大学学生素质测评表(班级样表)");
		// 创建第二行
		row = sheet.createRow(1);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("学院： " + academyName);
		cell = row.createCell(3);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
		// 得到普通样式
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("班级： " + className);
		// 学期
		cell = row.createCell(6);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 9));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue(term);
		// 制表时间
		cell = row.createCell(10);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 16));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("制表时间： "
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));

		// 创建第三行
		row = sheet.createRow(2);
		String[] title = new String[] { "序号", "班级", "学号", "姓名" };
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			sheet.addMergedRegion(new CellRangeAddress(2, 3, i, i));
			cell.setCellStyle(getHead3Style(workbook));
			cell.setCellValue(title[i]);
		}
		// 学习成绩测评
		cell = row.createCell(4);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 9));
		cell.setCellStyle(setCellStyle(workbook, fontName, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue("学习成绩测评");
		// 社会活动测评
		cell = row.createCell(10);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 10, 19));
		cell.setCellStyle(setCellStyle(workbook, fontName, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue("社会活动测评");
		//签字
		cell = row.createCell(20);
		sheet.addMergedRegion(new CellRangeAddress(2,3,20,20));
		cell.setCellStyle(setCellStyle(workbook, fontName, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue("签字(学生本人)");
		// 第四行
		row = sheet.createRow(3);
		String[] studyTitle = new String[] { "学习成绩", "计算机成绩", "英语成绩", "合计",
				"班级排名", "不及格课程门次" };
		for (int i = 0; i < studyTitle.length; i++) {
			cell = row.createCell(i + 4);
			cell.setCellStyle(getHead3Style(workbook));
			cell.setCellValue(studyTitle[i]);
		}

		String[] socieTitle = new String[] { "学生干部工作加分", "课外科技活动加分",
				"文艺艺术活动加分", "体育活动加分", "思想品德加分", "其他加分", "扣分", "合计", "班级排名",
				"备注" };
		for (int i = 0; i < socieTitle.length; i++) {
			cell = row.createCell(i + 10);
			cell.setCellStyle(getHead3Style(workbook));
			cell.setCellValue(socieTitle[i]);
		}
		
		//表数据填充，遍历  list 数据
		
		
		//表尾说明制作
		int num = 40;
		
		
		List<String> foots = new ArrayList<String>();
		foots.add("说明: 1. 本表一个评比单位制定一份,用十号字体.");
		foots.add("      2. 本表要求统一用EXCEL软件设计");
		foots.add("      3. 本表按学号升序排列");
		foots.add("      4. 本表需学生本人亲自确认，不得代签。");
		foots.add("      5. 有些表格已经提前设置，打印前请先预览。");
		for (int i = 0; i < foots.size(); i++) {
			row = sheet.createRow(num+i);
			sheet.addMergedRegion(new CellRangeAddress(num + i, num +i, 0 , 10));
			cell = row.createCell(0);
			cell.setCellStyle(getHead2Style(workbook));
			cell.setCellValue(foots.get(i).toString());
		}
		

		FileOutputStream fos = new FileOutputStream("d:\\classExcel.xls");
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	/**
	 * 综合测评样表
	 * @param term 
	 * @param yearMajor 
	 * @throws Exception 
	 * */
	public HSSFWorkbook getComExcel(String term, String yearMajor,List<?> list) throws Exception{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("学习测评--分年级按专业排名样表（报送学工）");
		//创建第一行-标题行
		HSSFRow row = sheet.createRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));
		sheet.setDefaultColumnWidth(14);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(setCellStyle(workbook, fontName, (short)16, BOLD, HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue(term+"  学生综合测评表");
		//创建第二行
		row = sheet.createRow(1);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,10));
		cell.setCellStyle(setCellStyle(workbook, fontName, (short)16, NOBOLD, HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue("（社会活动测评分年级按专业排名表）");
		//创建第三行
		//学院
		row = sheet.createRow(2);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(2,2,0,3));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("学院（公章）：");
		//制表日期
		sheet.addMergedRegion(new CellRangeAddress(2,2,6,10));
		cell = row.createCell(6);
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("制表时间： "
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		//创建第四行
		row = sheet.createRow(3);
		//年级专业
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(3,3,0,4));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("年级专业：" + yearMajor);
		//人数
		cell = row.createCell(6);
		sheet.addMergedRegion(new CellRangeAddress(3,3,6,10));
		cell.setCellStyle(getHead2Style(workbook));
		cell.setCellValue("人数： " + list.size());
		
		//创建第五行
		row = sheet.createRow(4);
		sheet.addMergedRegion(new CellRangeAddress(4,4,4,10));
		cell = row.createCell(4);
		cell.setCellStyle(setCellStyle(workbook, fontName, (short) 11, BOLD,
				HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT));
		cell.setCellValue("学习成绩测评");
		
		String[] title = new String[] { "序号", "班级", "学号", "姓名" };
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			sheet.addMergedRegion(new CellRangeAddress(4, 5, i, i));
			cell.setCellStyle(getHead3Style(workbook));
			cell.setCellValue(title[i]);
		}
		//创建第五行
		row = sheet.createRow(5);
		String[] study = new String[]{"学习成绩","计算机成绩","英语成绩","合计","不及格课程门次","专业排名","备注"};
		for (int i = 0; i < study.length; i++) {
			cell = row.createCell(i+4);
			cell.setCellStyle(getHead3Style(workbook));
			cell.setCellValue(study[i]);
		}
		
		return workbook;
	}
	public HSSFCellStyle setCellStyle(HSSFWorkbook workbook, String fontName,
			short size, short boldweight, short alignment,
			short verticaAlignment) {
		// 设置单元格字体样式
		HSSFFont font = workbook.createFont();
		font.setBoldweight(boldweight);// 加粗字体
		font.setFontHeightInPoints((short) size);
		font.setFontName(fontName);
		// 设置样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(alignment);
		style.setVerticalAlignment(verticaAlignment);
		style.setFont(font);
		return style;
	}

	public HSSFCellStyle getHead3Style(HSSFWorkbook workbook) {
		return setCellStyle(workbook, fontName, (short) 11, NOBOLD,
				HSSFCellStyle.ALIGN_CENTER, VERTICAALIGNMENT);
	}

	public HSSFCellStyle getHead2Style(HSSFWorkbook workbook) {
		return setCellStyle(workbook, fontName, (short) 12, NOBOLD,
				HSSFCellStyle.ALIGN_LEFT, VERTICAALIGNMENT);
	}

}
