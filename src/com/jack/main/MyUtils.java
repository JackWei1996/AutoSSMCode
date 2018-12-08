/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: MyUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年9月7日        | JACK)Jack    | original version
 */
package com.jack.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class name:MyUtils <BR>
 * class description:工具类 <BR>
 * Remark: <BR>
 * @version 1.00 2018年9月7日
 * @author JACK)jackwei
 */
public class MyUtils {
	/**
	 * Method name: isEmail <BR>
	 * Description: 判断是不是邮箱,是就返回true <BR>
	 * Remark: <BR>
	 * @param email
	 * @return  boolean<BR>
	 */
	public static boolean isEmail(String email) {
		String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		if(email.matches(regex)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Method name: isPhoneNum <BR>
	 * Description: 判断手机号是不是正确,是就返回true <BR>
	 * Remark: <BR>
	 * @param phoneNume
	 * @return  boolean<BR>
	 */
	public static boolean isPhoneNum(String phoneNume) {
		String pattern = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
		if(phoneNume.matches(pattern)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Method name: nowDate <BR>
	 * Description: 返回当前日期和时间 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	public static String getNowDateTime() {
		String dateTime = "";
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		dateTime = sdf.format(date);
		return dateTime;
	}
	
	/**
	 * Method name: getAutoNumber <BR>
	 * Description: 根据时间获取编号:年月日+4位数字 <BR>
	 * Remark: 格式:201809200001 <BR>  
	 * @return  String<BR>
	 */
	public static synchronized String getAutoNumber() {
		String autoNumber = "";
		int number = 0;
		String oldDate="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf.format(new Date());
		File f2 = new File(MyUtils.class.getResource("").getPath());
		String path = f2.getAbsolutePath();

		File f = new File(path+"/date.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			try {
				line=br.readLine();
				String[] sb = line.split(",");
				oldDate = sb[0];
				if(oldDate.equals(nowDate)) {
					number = Integer.parseInt(sb[1]);
				}else {
					number = 0;
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			autoNumber += nowDate;
			number++;
			int i=1;
			int n = number;
			while((n=n/10)!=0) {
				i++;
			}
			for(int j=0; j<4-i; j++) {
				autoNumber+="0";
			}
			autoNumber+=number;
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(nowDate+","+number);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return autoNumber;
	}
	
	/**
	 * Method name: get2DateDay <BR>
	 * Description: 获取两个日期之间的天数 <BR>
	 * Remark: 如2018-09-01 和 2018-09-017  返回就是17天<BR>
	 * @param startDate
	 * @param endDate
	 * @return  int<BR>
	 */
	public static int get2DateDay(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		long days = 0;
		long yushu = 0;
		
		try {
			date1 = sdf.parse(startDate);
			date2 = sdf.parse(endDate);
			
			days = (date2.getTime() - date1.getTime()) / (24*3600*1000);
			yushu = (date2.getTime() - date1.getTime()) % (24*3600*1000);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (int)days+1;
	}
	
	/**
	 * Method name: toLowCase <BR>
	 * Description: 第一个字母小写 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String toLowCase(String s) {
		return s.substring(0, 1).toLowerCase()+s.substring(1,s.length());
	}
	
	/**
	 * Method name: setStartUP <BR>
	 * Description: 第一个字母大写 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String setStartUP(String s) {
		return s.substring(0,1).toUpperCase()+s.substring(1,s.length());
	}
	
	/**
	 * Method name: getUp_ClassName <BR>
	 * Description: 根据表名获取类名不带后缀Bean <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String getUp_ClassName(String s) {
		String cName = "";
		//首字母大写
		cName = s.substring(1,2).toUpperCase()+s.substring(2, s.length());
		
		String[] tem = cName.split("_");
		int len = tem.length;
		cName = tem[0];
		for (int i=1; i<len; i++) {		
			cName += setStartUP(tem[i]);
		}
		//tables.add(cName);//把所有的表添加到这里
		return cName;
	}
	
	/**
	 * Method name: getFiled2Pro <BR>
	 * Description: 根据字段名获取属性 <BR>
	 * Remark: <BR>
	 * @return  String<BR>
	 */
	public static String getFiled2Pro(String s) {
		String pName = "";
		String[] tem = s.split("_");
		
		int len = tem.length;
		pName = tem[0];
		for (int i=1; i<len; i++) {		
			pName += setStartUP(tem[i]);
		}
		return pName;
	}
}
