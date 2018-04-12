package cn.imust.ys.test;

import java.io.UnsupportedEncodingException;

public class Encode {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String name = "社会成绩加分模板";
		// URL编码
		String nameStr = new String(java.net.URLEncoder.encode(name, "utf-8")
				.getBytes());
		System.out.println(nameStr);

		String cnStr = "中国";
		String cnStr1 = "";

		cnStr1 = new String(java.net.URLEncoder.encode(cnStr, "UTF-8")
				.getBytes(), "ISO-8859-1");
		System.out.println(cnStr1);
		//URL解码
		System.out.println(java.net.URLDecoder.decode(
				new String(cnStr1.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8"));

	}

	public static String getFileName(String fileName) {
		try {
			fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
