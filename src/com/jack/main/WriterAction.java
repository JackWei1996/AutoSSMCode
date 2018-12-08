/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: WriterAction.java
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
 * class name:WriterAction <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月16日
 * @author JACK)jackwei
 */
public class WriterAction {
	/**
	 * Method name: writerAction <BR>
	 * Description: 按照模板把Action类写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param pack  void<BR>
	 */
	public static void writerAction(String name, String pack, String id) {
		//新建文件夹
		String pf = pack.replaceAll("\\.", "/");
		String[] tm = id.split(" ");
		id = tm[2];
		
		String filePath = "D:/AutoCode/"+pf+"/action/";
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+name+"Action.java";
		
		String text = "package "+pack+".action;\r\n\r\n" + 
				"import java.util.ArrayList;\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"import java.util.List;\r\n" + 
				"import java.util.Map;\r\n" + 
				"import javax.annotation.Resource;\r\n" + 
				"import javax.servlet.http.HttpServletRequest;\r\n" + 
				"import javax.servlet.http.HttpServletResponse;\r\n" + 
				"import org.springframework.stereotype.Controller;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestParam;\r\n"+
				"import org.springframework.web.bind.annotation.ResponseBody;" + 
				"\r\n" + 
				"import "+pack+".utils.JsonUtil;\r\n"+
				"import "+pack+".bean."+name+"Bean;\r\n" + 
				"import "+pack+".service.I"+name+"Service;\r\n\r\n";
		
		text += "@Controller\r\n" + 
				"@RequestMapping(\""+MyUtils.toLowCase(name)+"\")\r\n" + 
				"public class "+name+"Action {\r\n" + 
				"	@Resource(name=\""+MyUtils.toLowCase(name)+"Service\")\r\n" + 
				"	private I"+name+"Service "+MyUtils.toLowCase(name)+"Service;\r\n\r\n";
		text+=	"  /**\r\n" + 
				" 	* Method name: selectAll <BR>\r\n" + 
				" 	* Description: 查询所有记录 <BR>\r\n" + 
				" 	* Remark: <BR>\r\n" + 
				" 	* @param bean\r\n" + 
				" 	* @return  Object<BR>\r\n" + 
				" 	*/\r\n";
		text +="	@RequestMapping(\"selectAll\")\r\n" + 
				"	@ResponseBody\r\n" + 
				"	public Object selectAll("+name+"Bean bean) {\r\n" + 
				"		List<"+name+"Bean> list = new ArrayList<>();\r\n" + 
				"		list = "+MyUtils.toLowCase(name)+"Service.findByCondition(bean);\r\n" + 
				"		long count = "+MyUtils.toLowCase(name)+"Service.findByConditionCount(bean);\r\n" + 
				"		\r\n" + 
				"		Map<String, Object> mapBean = new HashMap<>();\r\n" + 
				"		mapBean.put(\"rows\", list);\r\n" + 
				"		mapBean.put(\"total\", count);\r\n" + 
				"		\r\n" + 
				"		return mapBean;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: index <BR>\r\n" + 
				"	 * Description: 转发到页面 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n"+
				"	@RequestMapping(\"index\")\r\n" + 
				"	public String index() {\r\n" + 
				"		return \""+MyUtils.toLowCase(name)+"/"+MyUtils.toLowCase(name)+"Index\";\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	/**\r\n" + 
				"	 * Method name: options <BR>\r\n" + 
				"	 * Description: 表的增删改查 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param request\r\n" + 
				"	 * @param response\r\n" + 
				"	 * @return  String<BR>\r\n" + 
				"	 */\r\n"+
				"	@RequestMapping(\"options\")\r\n" + 
				"	@ResponseBody\r\n" + 
				"	public String options(@RequestParam(\"op\")String op, @RequestParam(\"data\")String data) {\r\n" + 
				"		String f = \"\";\r\n" + 
				"		\r\n" + 
				"		switch (op) {\r\n" + 
				"			case \"add\":\r\n" + 
				"				if(data!=null&&!data.equals(\"\")) {\r\n" + 
				"					List<"+name+"Bean> list = JsonUtil.getList4Json(data, "+name+"Bean.class);\r\n" + 
				"					for ("+name+"Bean bean : list) {\r\n" + 
				"						"+MyUtils.toLowCase(name)+"Service.insert(bean);\r\n" + 
				"					}\r\n" + 
				"					f = \"增加成功\";\r\n" + 
				"				}\r\n" + 
				"				break;\r\n" + 
				"			case \"del\":\r\n" + 
				"				if(data!=null&&!data.equals(\"\")) {\r\n" + 
				"					List<"+name+"Bean> list = JsonUtil.getList4Json(data, "+name+"Bean.class);\r\n" + 
				"					for ("+name+"Bean bean : list) {\r\n" + 
				"						"+MyUtils.toLowCase(name)+"Service.delete(bean.get"+MyUtils.setStartUP(id)+"());\r\n" + 
				"					}\r\n" + 
				"					f = \"删除成功\";\r\n" + 
				"				}\r\n" + 
				"				break;\r\n" + 
				"			case \"upd\":\r\n" + 
				"				if(data!=null&&!data.equals(\"\")) {\r\n" + 
				"					List<"+name+"Bean> list = JsonUtil.getList4Json(data, "+name+"Bean.class);\r\n" + 
				"					for ("+name+"Bean bean : list) {\r\n" + 
				"						"+MyUtils.toLowCase(name)+"Service.update(bean);\r\n" + 
				"					}\r\n" + 
				"					f = \"更新成功\";\r\n" + 
				"				}\r\n" + 
				"				break;\r\n" + 
				"			default:\r\n" + 
				"				break;\r\n" + 
				"		}\r\n" + 
				"		return f;\r\n" + 
				"	}\r\n";
		text+="}";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
