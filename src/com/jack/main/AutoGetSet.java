/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: AutoGetSet.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年10月15日        | JACK)Administrator    | original version
 */
package com.jack.main;

/**
 * class name:AutoGetSet <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月15日
 * @author JACK)jackwei
 */
public class AutoGetSet {
	/*public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}*/
	
	
	/**
	 * Method name: getGet <BR>
	 * Description: 获取get方法 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return  String<BR>
	 */
	public static String getGet(String name) {
		String get = "\n\tpublic ";
		
		String[] tem = name.split(" ");
		get += tem[1] + " get"+ setStartUP(tem[2]) + "() {";
		get += "return "+tem[2]+";"+"}";
		return get;
	}
	
	/**
	 * Method name: setSet <BR>
	 * Description: 获取set方法 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return  String<BR>
	 */
	public static String setSet(String name) {
		String set = "\n\tpublic ";
		String[] tem = name.split(" ");
		set += "void" + " set"+ setStartUP(tem[2]) + "("+tem[1]+" "+tem[2]+") {";
		set += "this."+tem[2]+"="+tem[2]+";"+"}";
		return set;
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
}
