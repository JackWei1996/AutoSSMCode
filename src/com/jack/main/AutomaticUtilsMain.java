/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: AutomaticUtils.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年10月15日        | JACK)Administrator    | original version
 */
package com.jack.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * class name:AutomaticUtils <BR>
 * class description: 自动生成SSM框架 <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月15日
 * @author JACK)jackwei
 */
public class AutomaticUtilsMain {
	//设置的包名
	private static String packageName = "";
	//读取的类名(不带Bean)
	private static String className="";
	//获取所有的类名(不带后缀Bean)
	private static List<String> classNames = new ArrayList<>();
	//获取所有的表名(和数据库一致)
	private static List<String> tables = new ArrayList<>();
	//获取所有的属性名(和bean格式一样)
	private static List<String> names = new ArrayList<>();
	//获取表里面的所有字段名(和数据库一致)
	private static List<String> fields = new ArrayList<>();
	
	/**
	 * Method name: main <BR>
	 * Description: 程序入口,右击运行这个类即可 <BR>
	 * Remark: <BR>
	 * @param args  void<BR>
	 */
	public static void main(String[] args) {
		//获取当前类的路径
		/*File ft = new File(AutomaticUtilsMain.class.getResource("").getPath());
		String path = ft.getAbsolutePath();*/
		String path = "D:/config";
		//找到配置文件
		File f = new File(path+"/data.txt");
		//要写入的文件内容
		String text = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			//文件第一行为包名(去除首尾空格)
			packageName = br.readLine().trim();
System.out.println("包名: "+packageName);
			//循环每一行
			while ((line=br.readLine())!=null) {
				line=line.trim();//去除首尾空格
				if(!line.startsWith("#")) {//忽略注释
					if(line.startsWith("@")) {//类解析开始
						if(!text.equals("")) {	//当解析完类时,执行下面代码
							for (String name : names) {//获取get/set方法
								text+=AutoGetSet.getGet(name);
								text+=AutoGetSet.setSet(name);
							}
							//设置表字段方法------
							/*for (String field : fields) {
								System.out.println(field);
							}*/
							text += "\n}";
//System.out.println(text);
							//把bean写入文件
							WriterJavaBean.writerJavaBean(className+"Bean", text, packageName);
							//把dao接口写到文件
							WriterDao.writerIDao(className, packageName);
							//把dao映射xml写到文件
							WriterDao.writerDaoMapper(className, packageName,tables.get(tables.size()-1),fields);
							//把service接口写到文件
							WriterService.writerIService(className, packageName);
							//把service实现类写到文件
							WriterService.writerServiceImpl(className, packageName);
							//把action实现类写到文件
							WriterAction.writerAction(className, packageName, names.get(0));
							//生成对于jsp文件(对表的增删改查)
							WriterIndexJsp.writerIndexJsp(className, names);
							text = "";		//清空写入内容
							names.clear();	//清空属性(bean里面的属性)
							fields.clear(); //清空字段(和数据库一致)
						}
						//写入包名
						text = "package "+packageName+".bean;\n\n";
						//获取类名
						className = getUp_ClassName(line);
						classNames.add(className);
					
//System.out.println("类名: "+className);
						text+="public class "+className+"Bean"+" extends BaseBean{\n\n";
					}else {	//开始解析字段名
						//System.out.println(line);
						String field = getField(line);
						//写入属性和注释
						String pro = getProperty(line)+"; //"+getFieldNote(line)+"\n";
//System.out.print("属性: "+pro);
						text+="\t"+pro;
						names.add(getProperty(line));
						fields.add(field);
					}
				}
			}
			for (String name : names) {
				text+=AutoGetSet.getGet(name);
				text+=AutoGetSet.setSet(name);
			}
			//设置表字段方法------
			/*for (String field : fields) {
				System.out.println(field);
			}*/
			
			
			text+="\n}";//当解析完最后一个类时,执行下面代码
			//把bean写入文件
			WriterJavaBean.writerJavaBean(className+"Bean", text, packageName);
			//把dao接口写到文件
			WriterDao.writerIDao(className, packageName);
			//把dao映射xml写到文件
			WriterDao.writerDaoMapper(className, packageName,tables.get(tables.size()-1),fields);
			//把service接口写到文件
			WriterService.writerIService(className, packageName);
			//把service实现类写到文件
			WriterService.writerServiceImpl(className, packageName);
			//把action实现类写到文件
			WriterAction.writerAction(className, packageName, names.get(0));
			//生成对于jsp文件(对表的增删改查)
			WriterIndexJsp.writerIndexJsp(className, names);
//System.out.println(text);
			//获取到的所有表名
			int i = 0;
			for (String tab : tables) {
				i++;
				System.out.println("表"+i+": "+tab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//生成BaseBean
		WriterJavaBean.writerBaseBean(packageName);
		//生成JsonUtil文件
		WriterUtils.writerUtils(packageName);
		//生成拦截器文件
		WriterInterceptor.writerInterceptor(packageName);
		//生成所有配置文件
		writerConfig(packageName);
		//生成主页面index
		WriterIndexJsp.writerIndexList(classNames);
		//生成MyUtils工具类
		WriterUtils.writerMyUtils(packageName);
		System.out.println("感谢使用!文件生成完毕,请到D盘AutoCode文件夹查看");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	
	/**
	 * Method name: getUp_ClassName <BR>
	 * Description: 根据表名获取类名不带后缀Bean <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String getUp_ClassName(String s) {
		tables.add(s.substring(1,s.length()));//把所有的表添加到这里
		String cName = "";
		//首字母大写
		cName = s.substring(1,2).toUpperCase()+s.substring(2, s.length());
		
		String[] tem = cName.split("_");
		int len = tem.length;
		cName = tem[0];
		for (int i=1; i<len; i++) {		
			cName += setStartUP(tem[i]);
		}
		return cName;
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
	 * Method name: getProperty <BR>
	 * Description: 获取属性 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String getProperty(String s) {
		String pro = "";
		String pName = "";
		
		String[] temt = s.split("=");
		
		switch (temt[1]) {
			case "int":
				pro="private Integer ";
				break;
			case "String":
				pro="private String ";
				break;
			case "string":
				pro="private String ";
				break;
			case "double":
				pro="private Double ";
				break;
			case "Double":
				pro="private Double ";
				break;
			default:
				System.out.println("类型有问题!!!");
				break;
		}
		
		String[] tem = temt[0].split("_");
		
		int len = tem.length;
		pName = tem[0];
		for (int i=1; i<len; i++) {		
			pName += setStartUP(tem[i]);
		}
		
		//pName += ";\n";
		
		return pro+pName;
	}
	
	/**
	 * Method name: getFieldNote <BR>
	 * Description: 获取字段注释 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String getFieldNote(String s) {
		String[] temt = s.split("=");
		if (temt.length>2) {	
			try {
				temt[2] = new String(temt[2].getBytes("utf-8"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return temt[2];
		}
		return "";
	}
	
	/**
	 * Method name: getField <BR>
	 * Description: 获取字段名 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String getField(String s) {
		String[] temt = s.split("=");
		return temt[0];
	}
	
	/**
	 * Method name: writerConfig <BR>
	 * Description: 自动生成配置文件 <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerConfig(String pack) {
		String proName="项目名";		//项目名
		String driver="数据库驱动";		//数据库驱动
		String url="数据库url";		//数据库url
		String user="数据库用户"; 		//数据库用户
		String pass="数据库密码";		//数据库密码
		
		//获取当前类的路径
		/*File ft = new File(AutomaticUtilsMain.class.getResource("").getPath());
		String path = ft.getAbsolutePath();*/
		String path = "D:/config";
		//找到配置文件
		File f = new File(path+"/config.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			//文件第一行为项目名(去除首尾空格)
			proName = br.readLine().trim();
			//文件第二行为数据库驱动
			driver = br.readLine().trim();
			//文件第三行为数据库url
			url = br.readLine().trim();
			//文件第四行为数据库用户
			user = br.readLine().trim();
			//文件第五行为数据库密码
			pass = br.readLine().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WriterConfigure.writerWebXml(proName);
		WriterConfigure.writerApplication(pack);
		WriterConfigure.writerDatabase(driver, url, user, pass);
		WriterConfigure.writerLog4j();
		WriterConfigure.writerMvcXml(pack);
		WriterConfigure.writerMybatisXml(pack);
		
		System.out.println("项目名: "+proName);
		System.out.println("数据库驱动: "+driver);
		System.out.println("数据库url: "+url);
		System.out.println("数据库用户: "+user);
		System.out.println("数据库密码: "+pass);
	}
	
}
