/**
 * 
 */
package com.jack.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * class name:WriterDao <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 Oct 16, 2018
 * @author JACK)JackWei
 */
public class WriterDao {
	/**
	 * Method name: writerIDao <BR>
	 * Description: 按照模板把Dao接口写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param pack  void<BR>
	 */
	public static void writerIDao(String name, String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"dao/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = filePath+name+"DaoMapper.java";
		String text = "package "+pack+".dao;\r\n\r\n" + 
				"import java.util.List;\r\n" + 
				"import org.apache.ibatis.annotations.Param;\r\n" + 
				"import "+pack+".bean."+name+"Bean;\n\n";
		
		text += "public interface "+name+"DaoMapper{\r\n" + 
				"	public void insert("+name+"Bean bean);\r\n" + 
				"	public void delete(@Param(\"id\")Integer id);\r\n" + 
				"	public void update("+name+"Bean bean);\r\n" + 
				"	public "+name+"Bean findById(@Param(\"id\")Integer id);\r\n" + 
				"	public List<"+name+"Bean> findByCondition("+name+"Bean bean);\r\n" + 
				"	public long findByConditionCount("+name+"Bean bean);\r\n" + 
				"}";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerDaoMapper <BR>
	 * Description: 按照模板把Dao映射xml写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param pack  void<BR>
	 */
	public static void writerDaoMapper(String name, String pack, String tableName, List<String> fields) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"dao/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		//表类里面所有的字段,用逗号隔开.换行
		String fsln = getFiledsLn(fields);
		//表类里面所有的字段,用逗号隔开.空格
		String fs = getFileds(fields);
		//保存
		String path = filePath+name+"DaoMapper.xml";
		String text = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				"<!DOCTYPE mapper\r\n" + 
				"  PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\r\n" + 
				"  \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n\r\n";
		
		
		text +="<mapper namespace=\""+pack+".dao."+name+"DaoMapper\">\r\n" + 
				"	<!-- 表字段映射 -->\r\n" + 
				"	<resultMap id=\"idmap\" type=\""+pack+".bean."+name+"Bean\">\r\n";
		for (String field : fields) {
			text+="		<result property=\""+MyUtils.getFiled2Pro(field)+"\" column=\""+field+"\"/>\r\n";
		}
				
		text+=  "	</resultMap>\r\n" + 
				"	\r\n" + 
				"	<!-- 要查询的所有项 -->\r\n" + 
				"	<sql id=\"item\">\r\n" + 
				fsln+ 
				"\r\n	</sql>\r\n" + 
				"	\r\n" + 
				"	<!-- 要查询的表-->\r\n" + 
				"	<sql id=\"table\">\r\n" + 
				"\t\t`"+tableName+"`"+ 
				"\r\n	</sql>\r\n" + 
				"	\r\n" + 
				"  	<!-- 增加记录 -->\r\n" + 
				"  	<insert id=\"insert\" parameterType=\""+pack+".bean."+name+"Bean\" useGeneratedKeys=\"true\" keyProperty=\""+MyUtils.getFiled2Pro(fields.get(0))+"\">\r\n" + 
				"		INSERT INTO <include refid=\"table\"/>("+removeDou(fs)+") \r\n" + 
				"		VALUES ("+removeDou(getParameters(fields))+");\r\n" + 
				"  	</insert>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 删除记录 -->\r\n" + 
				"  	<delete id=\"delete\" parameterType=\"int\">\r\n" + 
				"  		DELETE FROM <include refid=\"table\"/> \r\n" + 
				"  		WHERE `"+fields.get(0)+"` = #{id}\r\n" + 
				"  	</delete>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 修改记录 -->\r\n" + 
				"  	<update id=\"update\" parameterType=\""+pack+".bean."+name+"Bean\">\r\n" + 
				"  		UPDATE <include refid=\"table\"/>\r\n" + 
				"  		<set>\r\n";
		for (String field : fields) {
			if(!field.equals("id"))
			text+=  "  			<if test=\""+MyUtils.getFiled2Pro(field)+"!=null and "+MyUtils.getFiled2Pro(field)+"!=''\">\r\n" + 
					"  				`"+field+"` ="+getParameter(field)+", \r\n" + 
			 		"  			</if>\r\n";
		}
				
		text+=  "  		</set>\r\n" + 
				"  		 WHERE `"+fields.get(0)+"` = #{"+MyUtils.getFiled2Pro(fields.get(0))+", jdbcType=NUMERIC}\r\n" + 
				"  	</update>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 查询的条件 -->\r\n" + 
				"  	<sql id=\"condition\">\r\n" + 
				"  		<where>\r\n";
		for (String field : fields) {
		  text+="			<if test=\""+MyUtils.getFiled2Pro(field)+"!=null and "+MyUtils.getFiled2Pro(field)+"!=''\">\r\n" + 
				"  				and `"+field+"` like concat('%',"+getParameter(field)+",'%') \r\n" + 
				"  			</if>\r\n"; 
		}	
		text+=  "		</where>\r\n" + 
				"  	</sql>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 排序 -->\r\n" + 
				"  	<sql id=\"sort\">\r\n" + 
				"		<if test=\"sort!=null and sort!='' \">\r\n";
		
		for (String field : fields) {
		  text+="	    	<if test=\"sort == '"+MyUtils.getFiled2Pro(field)+"' \">\r\n" + 
				"	    		order by `"+field+"` ${order}\r\n" + 
				"	    	</if> \r\n";
		}		
		text+=  "		</if>\r\n" + 
				"  	</sql>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 根据id查找记录 -->\r\n" + 
				"  	<select id=\"findById\" parameterType=\"int\" resultMap=\"idmap\">\r\n" + 
				"		SELECT <include refid=\"item\"></include> \r\n" + 
				"		FROM <include refid=\"table\"/> \r\n" + 
				"		WHERE "+fields.get(0)+" = #{id}\r\n" + 
				"  	</select>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 根据条件查询记录 -->\r\n" + 
				"  	<select id=\"findByCondition\" parameterType=\""+pack+".bean."+name+"Bean\" resultMap=\"idmap\">\r\n" + 
				"		SELECT <include refid=\"item\"></include> \r\n" + 
				"		FROM <include refid=\"table\"/>\r\n" + 
				"		<include refid=\"condition\"></include>\r\n" + 
				"		<include refid=\"sort\"></include>\r\n" + 
				"		<if test=\"rows!=null\">\r\n" + 
				"			limit #{page}, #{pageSize}\r\n" + 
				"		</if>\r\n" + 
				"  	</select>\r\n" + 
				"  	\r\n" + 
				"  	<!-- 分页个数 -->\r\n" + 
				"	<select id=\"findByConditionCount\" parameterType=\""+pack+".bean."+name+"Bean\" resultType=\"long\">\r\n" + 
				"		SELECT count(0) \r\n" + 
				"		FROM <include refid=\"table\"/>\r\n" + 
				"		<include refid=\"condition\"></include>\r\n" + 
				"  	</select>\r\n" + 
				"  	\r\n" + 
				"</mapper>";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: getFileds <BR>
	 * Description: 根据字段List获取逗号隔开的字段名并换行 <BR>
	 * Remark: <BR>
	 * @param fields
	 * @return  String<BR>
	 */
	public static String getFiledsLn(List<String> fields) {
		String fs = "";
		for (String f : fields) {
			fs+="		`"+f+"`,\r\n";
		}
		fs = fs.substring(0, fs.length()-3);
		return fs;
	}
	
	/**
	 * Method name: getFiled <BR>
	 * Description: 根据字段List获取逗号隔开的字段名,不换行 <BR>
	 * Remark: <BR>
	 * @param fields
	 * @return  String<BR>
	 */
	public static String getFileds(List<String> fields) {
		String fs = "";
		for (String f : fields) {
			fs+="`"+f+"`, ";
		}
		fs = fs.substring(0, fs.length()-2);
		return fs;
	}
	
	/**
	 * Method name: getParameters <BR>
	 * Description: 根据字段名获取 所有参数#{},除了id<BR>
	 * Remark: <BR>
	 * @param fields
	 * @return  String<BR>
	 */
	public static String getParameters(List<String> fields) {
		String params = "";
		
		for (String field : fields) {
			String f = MyUtils.getFiled2Pro(field);
			params += "#{"+f+"}, ";
		}
		return params.substring(0, params.length()-2);
	}
	
	/**
	 * Method name: getParameter <BR>
	 * Description: 根据字段名获取单个参数#{},除了id<BR>
	 * Remark: <BR>
	 * @param fields
	 * @return  String<BR>
	 */
	public static String getParameter(String field) {
		String f = MyUtils.getFiled2Pro(field);
		return "#{"+f+"}";
	}
	
	/**
	 * Method name: removeDou <BR>
	 * Description: 去掉第一个逗号之前的数据 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String removeDou(String s) {
		int i = s.indexOf(',');
		return s.substring(i+1, s.length());
	}
}
