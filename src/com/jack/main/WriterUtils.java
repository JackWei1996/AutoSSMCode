/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: WriterUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年10月16日        | JACK)Administrator    | original version
 */
package com.jack.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class name:WriterUtils <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月16日
 * @author JACK)jackwei
 */
public class WriterUtils {
	/**
	 * Method name: writerUtils <BR>
	 * Description: 自动生成JsonUtil文件 <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerUtils(String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"utils/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"JsonUtil.java";
		
		String text="package "+pack+".utils;\r\n" + 
				"\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"import java.util.Iterator;\r\n" + 
				"import java.util.List;\r\n" + 
				"import java.util.Map;\r\n" + 
				"\r\n" + 
				"import net.sf.ezmorph.object.DateMorpher;\r\n" + 
				"import net.sf.json.JSONArray;\r\n" + 
				"import net.sf.json.JSONObject;\r\n" + 
				"import net.sf.json.JsonConfig;\r\n" + 
				"import net.sf.json.util.JSONUtils;\r\n" + 
				"import net.sf.json.util.PropertyFilter;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * class name:JsonUtil <BR>\r\n" + 
				" * class description: 处理json的工具类 <BR>\r\n" + 
				" * Remark: <BR>\r\n" + 
				" * @version 1.00 2012-6-19\r\n" + 
				" * @author JFTT)FuXiaofeng\r\n" + 
				" */\r\n" + 
				"@SuppressWarnings(\"rawtypes\")\r\n" + 
				"public class JsonUtil {\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getJsonString4JavaPOJO <BR>\r\n" + 
				"	 * Description: 将java对象转换成json字符串 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param javaObj\r\n" + 
				"	 * @return JSON字符串\r\n" + 
				"	 */\r\n" + 
				"	public static String getJsonString4JavaPOJO(Object javaObj) {\r\n" + 
				"		JSONUtils.getMorpherRegistry().registerMorpher(   \r\n" + 
				"                new DateMorpher(new String[] { \"yyyy-MM-dd\" })); \r\n" + 
				"		JSONObject json;\r\n" + 
				"		JsonConfig cfg = new JsonConfig();\r\n" + 
				"		cfg.setJsonPropertyFilter(new PropertyFilter() {\r\n" + 
				"			\r\n" + 
				"			@Override\r\n" + 
				"			public boolean apply(Object arg0, String arg1, Object arg2) {\r\n" + 
				"				if (\"callbacks\".equalsIgnoreCase(arg1) || \"callback\".equalsIgnoreCase(arg1)) {\r\n" + 
				"					return true;\r\n" + 
				"				}\r\n" + 
				"				return false;\r\n" + 
				"			}\r\n" + 
				"		});\r\n" + 
				"		json = JSONObject.fromObject(javaObj, cfg);\r\n" + 
				"		return json.toString();\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getObject4JsonString <BR>\r\n" + 
				"	 * Description: 从一个JSON 对象字符格式中得到一个java对象 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @param pojoCalss\r\n" + 
				"	 * @return Java对象\r\n" + 
				"	 */\r\n" + 
				"	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {\r\n" + 
				"		JSONUtils.getMorpherRegistry().registerMorpher(   \r\n" + 
				"                new DateMorpher(new String[] { \"yyyy-MM-dd\" }));  \r\n" + 
				"		Object pojo;\r\n" + 
				"		JSONObject jsonObject = JSONObject.fromObject(jsonString);\r\n" + 
				"		pojo = JSONObject.toBean(jsonObject, pojoCalss);\r\n" + 
				"		return pojo;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"    /**\r\n" + 
				"     * Method name: getJsonString4JavaList <BR>\r\n" + 
				"     * Description: 将一个JavaList转换成String <BR>\r\n" + 
				"     * Remark: <BR>\r\n" + 
				"     * @param objList\r\n" + 
				"     * @return JSON字符串\r\n" + 
				"     */\r\n" + 
				"    public static String getJsonString4JavaList(List objList) {\r\n" + 
				"    	JSONArray jsonArr;\r\n" + 
				"    	JsonConfig cfg = new JsonConfig();\r\n" + 
				"		cfg.setJsonPropertyFilter(new PropertyFilter() {\r\n" + 
				"			\r\n" + 
				"			@Override\r\n" + 
				"			public boolean apply(Object arg0, String arg1, Object arg2) {\r\n" + 
				"				if (\"callbacks\".equalsIgnoreCase(arg1) || \"callback\".equalsIgnoreCase(arg1)) {\r\n" + 
				"					return true;\r\n" + 
				"				}\r\n" + 
				"				return false;\r\n" + 
				"			}\r\n" + 
				"		});\r\n" + 
				"    	jsonArr = JSONArray.fromObject(objList, cfg);\r\n" + 
				"    	return jsonArr.toString();\r\n" + 
				"    }\r\n" + 
				"    /**\r\n" + 
				"     * Method name: getJson4JavaList <BR>\r\n" + 
				"     * Description: 将一个JavaList转换成jsON <BR>\r\n" + 
				"     * Remark: <BR>\r\n" + 
				"     * @param objList\r\n" + 
				"     * @return JSON字符串\r\n" + 
				"     */\r\n" + 
				"    public static JSONArray getJson4JavaList(List objList) {\r\n" + 
				"    	JSONArray jsonArr;\r\n" + 
				"    	JsonConfig cfg = new JsonConfig();\r\n" + 
				"		cfg.setJsonPropertyFilter(new PropertyFilter() {\r\n" + 
				"			\r\n" + 
				"			@Override\r\n" + 
				"			public boolean apply(Object arg0, String arg1, Object arg2) {\r\n" + 
				"				if (\"callbacks\".equalsIgnoreCase(arg1) || \"callback\".equalsIgnoreCase(arg1)) {\r\n" + 
				"					return true;\r\n" + 
				"				}\r\n" + 
				"				return false;\r\n" + 
				"			}\r\n" + 
				"		});\r\n" + 
				"    	jsonArr = JSONArray.fromObject(objList, cfg);\r\n" + 
				"    	return jsonArr;\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    /**\r\n" + 
				"     * Method name: getJson4JavaObject <BR>\r\n" + 
				"     * Description: 将一个javabean转换成jsON <BR>\r\n" + 
				"     * Remark: <BR>\r\n" + 
				"     * @param objList\r\n" + 
				"     * @return JSON字符串\r\n" + 
				"     */\r\n" + 
				"    public static JSONObject getJson4JavaObject(Object objJava) {\r\n" + 
				"    	JSONObject jsonObj;\r\n" + 
				"    	JsonConfig cfg = new JsonConfig();\r\n" + 
				"		cfg.setJsonPropertyFilter(new PropertyFilter() {\r\n" + 
				"			\r\n" + 
				"			@Override\r\n" + 
				"			public boolean apply(Object arg0, String arg1, Object arg2) {\r\n" + 
				"				if (\"callbacks\".equalsIgnoreCase(arg1) || \"callback\".equalsIgnoreCase(arg1)) {\r\n" + 
				"					return true;\r\n" + 
				"				}\r\n" + 
				"				return false;\r\n" + 
				"			}\r\n" + 
				"		});\r\n" + 
				"		jsonObj = JSONObject.fromObject(objJava, cfg);\r\n" + 
				"    	return jsonObj;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getList4Json <BR>\r\n" + 
				"	 * Description: 从json对象集合表达式中得到一个java对象列表 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @param pojoClass\r\n" + 
				"	 * @return java.util.List\r\n" + 
				"	 */\r\n" + 
				"	@SuppressWarnings(\"unchecked\")\r\n" + 
				"	public static List getList4Json(String jsonString, Class pojoClass) {\r\n" + 
				"		JSONUtils.getMorpherRegistry().registerMorpher(   \r\n" + 
				"                new DateMorpher(new String[] { \"yyyy-MM-dd\" })); \r\n" + 
				"		\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		JSONObject jsonObject;\r\n" + 
				"		Object pojoValue;\r\n" + 
				"\r\n" + 
				"		List list = new ArrayList();\r\n" + 
				"		for (int i = 0; i < jsonArray.size(); i++) {\r\n" + 
				"			jsonObject = jsonArray.getJSONObject(i);\r\n" + 
				"			pojoValue = JSONObject.toBean(jsonObject, pojoClass);\r\n" + 
				"			list.add(pojoValue);\r\n" + 
				"		}\r\n" + 
				"		return list;\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getMap4Json <BR>\r\n" + 
				"	 * Description: 从json HASH表达式中获取一个map <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	@SuppressWarnings(\"unchecked\")\r\n" + 
				"	public static Map getMap4Json(String jsonString) {\r\n" + 
				"		JSONUtils.getMorpherRegistry().registerMorpher(   \r\n" + 
				"                new DateMorpher(new String[] { \"yyyy-MM-dd\" })); \r\n" + 
				"		\r\n" + 
				"		JSONObject jsonObject = JSONObject.fromObject(jsonString);\r\n" + 
				"		Iterator keyIter = jsonObject.keys();\r\n" + 
				"		String key;\r\n" + 
				"		Object value;\r\n" + 
				"		Map valueMap = new HashMap();\r\n" + 
				"\r\n" + 
				"		while (keyIter.hasNext()) {\r\n" + 
				"			key = (String) keyIter.next();\r\n" + 
				"			value = jsonObject.get(key);\r\n" + 
				"			valueMap.put(key, value);\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		return valueMap;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getObjectArray4Json <BR>\r\n" + 
				"	 * Description: 从json数组中得到相应Object数组 <BR>\r\n" + 
				"	 * Remark:实用性不佳,建议使用getList4Json <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	@Deprecated\r\n" + 
				"	public static Object[] getObjectArray4Json(String jsonString) {\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		return jsonArray.toArray();\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getStringArray4Json <BR>\r\n" + 
				"	 * Description: 从json数组中解析出java字符串数组 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	public static String[] getStringArray4Json(String jsonString) {\r\n" + 
				"\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		String[] stringArray = new String[jsonArray.size()];\r\n" + 
				"		for (int i = 0; i < jsonArray.size(); i++) {\r\n" + 
				"			stringArray[i] = jsonArray.getString(i);\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		return stringArray;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getLongArray4Json <BR>\r\n" + 
				"	 * Description: 从json数组中解析出javaLong型对象数组 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	public static Long[] getLongArray4Json(String jsonString) {\r\n" + 
				"\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		Long[] longArray = new Long[jsonArray.size()];\r\n" + 
				"		for (int i = 0; i < jsonArray.size(); i++) {\r\n" + 
				"			longArray[i] = jsonArray.getLong(i);\r\n" + 
				"		}\r\n" + 
				"		return longArray;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getIntegerArray4Json <BR>\r\n" + 
				"	 * Description: 从json数组中解析出java Integer型对象数组 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	public static Integer[] getIntegerArray4Json(String jsonString) {\r\n" + 
				"\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		Integer[] integerArray = new Integer[jsonArray.size()];\r\n" + 
				"		for (int i = 0; i < jsonArray.size(); i++) {\r\n" + 
				"			integerArray[i] = jsonArray.getInt(i);\r\n" + 
				"		}\r\n" + 
				"		return integerArray;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getDoubleArray4Json <BR>\r\n" + 
				"	 * Description: 从json数组中解析出java Double型对象数组 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param jsonString\r\n" + 
				"	 * @return \r\n" + 
				"	 */\r\n" + 
				"	public static Double[] getDoubleArray4Json(String jsonString) {\r\n" + 
				"\r\n" + 
				"		JSONArray jsonArray = JSONArray.fromObject(jsonString);\r\n" + 
				"		Double[] doubleArray = new Double[jsonArray.size()];\r\n" + 
				"		for (int i = 0; i < jsonArray.size(); i++) {\r\n" + 
				"			doubleArray[i] = jsonArray.getDouble(i);\r\n" + 
				"		}\r\n" + 
				"		return doubleArray;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerMyUtils <BR>
	 * Description: 自动生成MyUtils <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerMyUtils(String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"utils/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"MyUtils.java";
		
		String text = "/*\r\n" + 
				" * All rights Reserved, Copyright (C) JACK LIMITED 2018\r\n" + 
				" * FileName: MyUtils.java\r\n" + 
				" * Version:  $Revision$\r\n" + 
				" * Modify record:\r\n" + 
				" * NO. |     Date       |    Name         |      Content\r\n" + 
				" * 1   | 2018年9月7日        | JACK)Jack    | original version\r\n" + 
				" */\r\n" + 
				"package "+pack+".utils;\r\n" + 
				"\r\n" + 
				"import java.io.BufferedReader;\r\n" + 
				"import java.io.BufferedWriter;\r\n" + 
				"import java.io.File;\r\n" + 
				"import java.io.FileNotFoundException;\r\n" + 
				"import java.io.FileReader;\r\n" + 
				"import java.io.FileWriter;\r\n" + 
				"import java.io.IOException;\r\n" + 
				"import java.text.ParseException;\r\n" + 
				"import java.text.SimpleDateFormat;\r\n" + 
				"import java.util.Date;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * class name:MyUtils <BR>\r\n" + 
				" * class description:工具类 <BR>\r\n" + 
				" * Remark: <BR>\r\n" + 
				" * @version 1.00 2018年9月7日\r\n" + 
				" * @author JACK)jackwei\r\n" + 
				" */\r\n" + 
				"public class MyUtils {\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: isEmail <BR>\r\n" + 
				"	 * Description: 判断是不是邮箱,是就返回true <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param email\r\n" + 
				"	 * @return  boolean<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static boolean isEmail(String email) {\r\n" + 
				"		String regex = \"^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\\\.[a-zA-Z0-9_-]+)+$\";\r\n" + 
				"		if(email.matches(regex)) {\r\n" + 
				"			return true;\r\n" + 
				"		}else {\r\n" + 
				"			return false;\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: isPhoneNum <BR>\r\n" + 
				"	 * Description: 判断手机号是不是正确,是就返回true <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param phoneNume\r\n" + 
				"	 * @return  boolean<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static boolean isPhoneNum(String phoneNume) {\r\n" + 
				"		String pattern = \"^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\\\d{8}$\";\r\n" + 
				"		if(phoneNume.matches(pattern)) {\r\n" + 
				"			return true;\r\n" + 
				"		}else {\r\n" + 
				"			return false;\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: nowDate <BR>\r\n" + 
				"	 * Description: 返回当前日期和时间 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static String getNowDateTime() {\r\n" + 
				"		String dateTime = \"\";\r\n" + 
				"		String pattern = \"yyyy-MM-dd HH:mm:ss\";\r\n" + 
				"		Date date = new Date();\r\n" + 
				"		SimpleDateFormat sdf = new SimpleDateFormat(pattern);\r\n" + 
				"		dateTime = sdf.format(date);\r\n" + 
				"		return dateTime;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getAutoNumber <BR>\r\n" + 
				"	 * Description: 根据时间获取编号:年月日+4位数字 <BR>\r\n" + 
				"	 * Remark: 格式:201809200001 <BR>  \r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static synchronized String getAutoNumber() {\r\n" + 
				"		String autoNumber = \"\";\r\n" + 
				"		int number = 0;\r\n" + 
				"		String oldDate=\"\";\r\n" + 
				"		SimpleDateFormat sdf = new SimpleDateFormat(\"yyyyMMdd\");\r\n" + 
				"		String nowDate = sdf.format(new Date());\r\n" + 
				"		File f2 = new File(MyUtils.class.getResource(\"\").getPath());\r\n" + 
				"		String path = f2.getAbsolutePath();\r\n" + 
				"\r\n" + 
				"		File f = new File(path+\"/date.txt\");\r\n" + 
				"		\r\n" + 
				"		try {\r\n" + 
				"			BufferedReader br = new BufferedReader(new FileReader(f));\r\n" + 
				"			String line = \"\";\r\n" + 
				"			try {\r\n" + 
				"				line=br.readLine();\r\n" + 
				"				String[] sb = line.split(\",\");\r\n" + 
				"				oldDate = sb[0];\r\n" + 
				"				if(oldDate.equals(nowDate)) {\r\n" + 
				"					number = Integer.parseInt(sb[1]);\r\n" + 
				"				}else {\r\n" + 
				"					number = 0;\r\n" + 
				"				}\r\n" + 
				"				br.close();\r\n" + 
				"			} catch (IOException e) {\r\n" + 
				"				e.printStackTrace();\r\n" + 
				"			}\r\n" + 
				"			\r\n" + 
				"			autoNumber += nowDate;\r\n" + 
				"			number++;\r\n" + 
				"			int i=1;\r\n" + 
				"			int n = number;\r\n" + 
				"			while((n=n/10)!=0) {\r\n" + 
				"				i++;\r\n" + 
				"			}\r\n" + 
				"			for(int j=0; j<4-i; j++) {\r\n" + 
				"				autoNumber+=\"0\";\r\n" + 
				"			}\r\n" + 
				"			autoNumber+=number;\r\n" + 
				"			\r\n" + 
				"			try {\r\n" + 
				"				BufferedWriter bw = new BufferedWriter(new FileWriter(f));\r\n" + 
				"				bw.write(nowDate+\",\"+number);\r\n" + 
				"				bw.close();\r\n" + 
				"			} catch (IOException e) {\r\n" + 
				"				e.printStackTrace();\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"		} catch (FileNotFoundException e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"		return autoNumber;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: get2DateDay <BR>\r\n" + 
				"	 * Description: 获取两个日期之间的天数 <BR>\r\n" + 
				"	 * Remark: 如2018-09-01 和 2018-09-017  返回就是17天<BR>\r\n" + 
				"	 * @param startDate\r\n" + 
				"	 * @param endDate\r\n" + 
				"	 * @return  int<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static int get2DateDay(String startDate, String endDate) {\r\n" + 
				"		SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd\");\r\n" + 
				"		Date date1 = null;\r\n" + 
				"		Date date2 = null;\r\n" + 
				"		long days = 0;\r\n" + 
				"		long yushu = 0;\r\n" + 
				"		\r\n" + 
				"		try {\r\n" + 
				"			date1 = sdf.parse(startDate);\r\n" + 
				"			date2 = sdf.parse(endDate);\r\n" + 
				"			\r\n" + 
				"			days = (date2.getTime() - date1.getTime()) / (24*3600*1000);\r\n" + 
				"			yushu = (date2.getTime() - date1.getTime()) % (24*3600*1000);\r\n" + 
				"\r\n" + 
				"		} catch (ParseException e) {\r\n" + 
				"			e.printStackTrace();\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"		return (int)days+1;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: toLowCase <BR>\r\n" + 
				"	 * Description: 第一个字母小写 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param s\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static String toLowCase(String s) {\r\n" + 
				"		return s.substring(0, 1).toLowerCase()+s.substring(1,s.length());\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: setStartUP <BR>\r\n" + 
				"	 * Description: 第一个字母大写 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param s\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static String setStartUP(String s) {\r\n" + 
				"		return s.substring(0,1).toUpperCase()+s.substring(1,s.length());\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getUp_ClassName <BR>\r\n" + 
				"	 * Description: 根据表名获取类名不带后缀Bean <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param s\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static String getUp_ClassName(String s) {\r\n" + 
				"		String cName = \"\";\r\n" + 
				"		//首字母大写\r\n" + 
				"		cName = s.substring(1,2).toUpperCase()+s.substring(2, s.length());\r\n" + 
				"		\r\n" + 
				"		String[] tem = cName.split(\"_\");\r\n" + 
				"		int len = tem.length;\r\n" + 
				"		cName = tem[0];\r\n" + 
				"		for (int i=1; i<len; i++) {		\r\n" + 
				"			cName += setStartUP(tem[i]);\r\n" + 
				"		}\r\n" + 
				"		//tables.add(cName);//把所有的表添加到这里\r\n" + 
				"		return cName;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: getFiled2Pro <BR>\r\n" + 
				"	 * Description: 根据字段名获取属性 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n" + 
				"	public static String getFiled2Pro(String s) {\r\n" + 
				"		String pName = \"\";\r\n" + 
				"		String[] tem = s.split(\"_\");\r\n" + 
				"		\r\n" + 
				"		int len = tem.length;\r\n" + 
				"		pName = tem[0];\r\n" + 
				"		for (int i=1; i<len; i++) {		\r\n" + 
				"			pName += setStartUP(tem[i]);\r\n" + 
				"		}\r\n" + 
				"		return pName;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writerDate(pack);
	}
	
	public static void writerDate(String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"utils/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"date.txt";
		
		String text="20181015,520";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
