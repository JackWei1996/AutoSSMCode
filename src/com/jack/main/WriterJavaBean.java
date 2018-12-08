/**
 * 
 */
package com.jack.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class name:WriterJavaBean <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 Oct 15, 2018
 * @author JACK)JackWei
 */
public class WriterJavaBean {
	/**
	 * Method name: writerJavaBean <BR>
	 * Description: 按照模板把javaBean写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param text  void<BR>
	 */
	public static void writerJavaBean(String name, String text, String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"bean/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+name+".java";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerBaseBean <BR>
	 * Description: 生成baseBean文件 <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerBaseBean(String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"bean/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String text="";
		
		text += "package "+pack+".bean;\r\n" + 
				"\r\n" + 
				"import java.io.Serializable;\r\n" + 
				"import org.apache.commons.lang.builder.ReflectionToStringBuilder;\r\n" + 
				"import org.apache.commons.lang.builder.ToStringStyle;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * 分页/排序参数bean\r\n" + 
				" */\r\n" + 
				"public class BaseBean implements Serializable{\r\n" + 
				"	\r\n" + 
				"	private static final long serialVersionUID = -4714709574354070550L;\r\n" + 
				"	\r\n" + 
				"	private Integer page;\r\n" + 
				"	private Integer pageSize;\r\n" + 
				"	private String sort;\r\n" + 
				"	private String order;\r\n" + 
				"	private Integer rows;\r\n" + 
				"	\r\n" + 
				"	public Integer getRows() {\r\n" + 
				"		return rows;\r\n" + 
				"	}\r\n" + 
				"	public void setRows(Integer rows) {\r\n" + 
				"		this.rows = rows;\r\n" + 
				"	}\r\n" + 
				"	public String getSort() {\r\n" + 
				"		return sort;\r\n" + 
				"	}\r\n" + 
				"	public void setSort(String sort) {\r\n" + 
				"		this.sort = sort;\r\n" + 
				"	}\r\n" + 
				"	public String getOrder() {\r\n" + 
				"		return order;\r\n" + 
				"	}\r\n" + 
				"	public void setOrder(String order) {\r\n" + 
				"		this.order = order;\r\n" + 
				"	}\r\n" + 
				"	public Integer getPage() {\r\n" + 
				"		return page;\r\n" + 
				"	}\r\n" + 
				"	public void setPage(Integer page) {\r\n" + 
				"		this.page = page;\r\n" + 
				"	}\r\n" + 
				"	public Integer getPageSize() {\r\n" + 
				"		return pageSize;\r\n" + 
				"	}\r\n" + 
				"	public void setPageSize(Integer pageSize) {\r\n" + 
				"		this.pageSize = pageSize;\r\n" + 
				"	}\r\n" + 
				"	@Override\r\n" + 
				"	public String toString() {\r\n" + 
				"		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);\r\n" + 
				"	}\r\n" + 
				"}";

		String path = filePath+"BaseBean.java";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
