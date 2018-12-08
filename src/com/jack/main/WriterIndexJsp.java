/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: WriterIndexJsp.java
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
import java.util.List;

/**
 * class name:WriterIndexJsp <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月16日
 * @author JACK)jackwei
 */
public class WriterIndexJsp {
	/**
	 * Method name: writerIndexJsp <BR>
	 * Description: 自动生成转发页面(可以增删改查) <BR>
	 * Remark: <BR>
	 * @param className
	 * @param names  void<BR>
	 */
	public static void writerIndexJsp(String className, List<String> names) {
		//新建文件夹
		String filePath = "D:/AutoCode/jsp/"+MyUtils.toLowCase(className)+"/";
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = filePath+MyUtils.toLowCase(className)+"Index.jsp";
		
		
		String text="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\r\n" + 
				"    pageEncoding=\"UTF-8\"%>\r\n" + 
				"<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\r\n" + 
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
				"<title>查询页面</title>\r\n" + 
				"<%\r\n" + 
				"	String bathPath = request.getContextPath();\r\n" + 
				"	pageContext.setAttribute(\"bathPath\", bathPath);\r\n" + 
				"%>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"${bathPath}/css/page.css\"/>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/jquery-1.9.1.min.js\" ></script>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/ajaxSessionOut.js\" ></script>\r\n" + 
				"<!-- 1    css资源 -->\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"${bathPath}/js/jquery-easyui-1.4.2/themes/default/easyui.css\">\r\n" + 
				"<!-- 2    图标资源 -->\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"${bathPath}/js/jquery-easyui-1.4.2/themes/icon.css\"> \r\n" + 
				"<!-- 3    EasyUI的js包 -->\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/jquery-easyui-1.4.2/jquery.easyui.min.js\"></script>\r\n" + 
				"<!-- 4    本地语言 -->\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js\"></script>\r\n" + 
				"<!-- layer -->\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"${bathPath}/js/layer/skin/layer.css\"/>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/layer/layer.js\" ></script>\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"var lastIndex;\r\n" + 
				"var allRegion=[];\r\n" + 
				"var logicPoint_no_value;\r\n" + 
				"var selectIndex;\r\n" + 
				"\r\n" + 
				"$(function(){\r\n" + 
				"	lodpage();\r\n" + 
				"	$(\".datagrid-header-rownumber\").html(\"序号\"); //给第一列设置标题\r\n" + 
				"});\r\n";
		//获取查询参数
		text += "function getParam(){\r\n" + 
				"	var param = new Object();\r\n";
		for (String name : names) {
			String[] tt = name.split(" ");
			name = tt[2];
			text+="	param."+name+" = $(\"#"+name+"\").val();\r\n";
		}
		text += "	return param;\r\n" + 
				"}\r\n";
		
		//加载表格
		text += "//加载表格\r\n" + 
				"function lodpage(){\r\n" + 
				"	$(\"#dg\").datagrid({\r\n" + 
				"        url:'${bathPath}/"+MyUtils.toLowCase(className)+"/selectAll.action',\r\n" + 
				"        idField:'pNumber',\r\n" + 
				"        pageNumber:1,//在设置分页属性的时候初始化页码\r\n" + 
				"		pageSize:10,//在设置分页属性的时候初始化页面大小。\r\n" + 
				"		pageList:[5,10,15,20],\r\n" + 
				"        queryParams: getParam(),//需要设置对象在上面设置\r\n" + 
				"        pagination:true,//分页\r\n" + 
				"        fitColumns:true,//列自适应宽度\r\n" + 
				"        rownumbers:true,//显示行号\r\n" + 
				"        striped:true,//斑马线\r\n" + 
				"        singleSelect:false,//如果为true，则只允许选择一行。\r\n" + 
				"        \r\n" + 
				"        toolbar:[{\r\n" + 
				"			id:'btnadd',\r\n" + 
				"			text:'增加',\r\n" + 
				"			iconCls:'icon-add',\r\n" + 
				"			handler:function(){\r\n" + 
				"				//alert('add');\r\n" + 
				"				$('#dg').datagrid('endEdit', lastIndex);\r\n" + 
				"				$('#dg').datagrid('appendRow',{\r\n" + 
				"				});\r\n" + 
				"				lastIndex = $('#dg').datagrid('getRows').length-1;\r\n" + 
				"				$('#dg').datagrid('selectRow', lastIndex);\r\n" + 
				"				$('#dg').datagrid('beginEdit', lastIndex);\r\n" + 
				"			}\r\n" + 
				"		},'-',{\r\n" + 
				"			id:'btndel',\r\n" + 
				"			text:'删除',\r\n" + 
				"			iconCls:'icon-remove',\r\n" + 
				"			handler:function(){\r\n" + 
				"				//alert('del');\r\n" + 
				"				var row = $('#dg').datagrid('getSelected');\r\n" + 
				"				if (row){\r\n" + 
				"					var index = $('#dg').datagrid('getRowIndex', row);\r\n" + 
				"					$('#dg').datagrid('deleteRow', index);\r\n" + 
				"				}else{\r\n" + 
				"					alert(\"请先选择一行!\");\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		},'-',{\r\n" + 
				"			id:'btnreject',\r\n" + 
				"			text:'撤销',\r\n" + 
				"			iconCls:'icon-undo',\r\n" + 
				"			handler:function(){\r\n" + 
				"				//alert('undo');\r\n" + 
				"				$('#dg').datagrid('rejectChanges');\r\n" + 
				"			}\r\n" + 
				"		},'-',{\r\n" + 
				"			id:'btnsave',\r\n" + 
				"			text:'保存',\r\n" + 
				"			iconCls:'icon-save',\r\n" + 
				"			handler:function(){\r\n" + 
				"				//alert('保存');\r\n" + 
				"				$('#dg').datagrid('endEdit', lastIndex);\r\n" + 
				"				//inserted，deleted，updated\r\n" + 
				"				\r\n" + 
				"				for(var i=0; i<$('#dg').datagrid('getRows').length; i++){\r\n" + 
				"					$('#dg').datagrid('endEdit', i);\r\n" + 
				"				}\r\n" + 
				"				\r\n" + 
				"				var saveStr = \"\";\r\n" + 
				"				var op = \"\";\r\n" + 
				"				\r\n" + 
				"				var addRows = $('#dg').datagrid('getChanges', 'inserted');				\r\n" + 
				"				if(addRows.length > 0){\r\n" + 
				"					//alert(\"去数据库保存  add\");\r\n" + 
				"					saveStr = toJSONStr(addRows);\r\n" + 
				"					op = \"add\";\r\n" + 
				"				}\r\n" + 
				"				var delRows = $('#dg').datagrid('getChanges', 'deleted');\r\n" + 
				"				if(delRows.length > 0){\r\n" + 
				"					//alert(\"去数据库保存  del\");\r\n" + 
				"					saveStr = toJSONStr(delRows);\r\n" + 
				"					op = \"del\";\r\n" + 
				"				}\r\n" + 
				"				var updateRows = $('#dg').datagrid('getChanges', 'updated');\r\n" + 
				"				if(updateRows.length > 0){\r\n" + 
				"					//alert(\"去数据库保存  update\");\r\n" + 
				"					saveStr = toJSONStr(updateRows);\r\n" + 
				"					op = \"upd\";\r\n" + 
				"				}\r\n" + 
				"				\r\n" + 
				"				if(isSubmit()){\r\n" + 
				"					if(saveStr){\r\n" + 
				"						//alert(saveStr);\r\n" + 
				"						console.log(saveStr);\r\n" + 
				"						  $.ajax({\r\n" + 
				"							type:\"POST\",\r\n" + 
				"							async:true,  //默认true,异步\r\n" + 
				"							url:\"${bathPath}/"+MyUtils.toLowCase(className)+"/options.action\",\r\n" + 
				"							data:\"op=\"+op+\"&data=\"+saveStr,\r\n" + 
				"							success:function(data){\r\n" + 
				"								$('#dg').datagrid(\"reload\");\r\n" + 
				"						    }\r\n" + 
				"						}); \r\n" + 
				"					}	\r\n" + 
				"				}else{\r\n" + 
				"					alert(\"验证错误!\");\r\n" + 
				"				}\r\n" + 
				"				$('#dg').datagrid('acceptChanges');\r\n" + 
				"			}\r\n" + 
				"		}],\r\n" + 
				"        columns :[[\r\n";
				for (String name : names) {
					String[] tt = name.split(" ");
					name = tt[2];
					text+="        		{field: '"+name+"', title: '"+name+"', width:100,align:'center',sortable:true,\r\n"
							+ "					editor:{\r\n" + 
							"					    type:'text',\r\n" + 
							"					    options:{\r\n" + 
							"							required:true\r\n" + 
							"						}\r\n" + 
							"        			}},\r\n";
				}
				text = text.substring(0, text.length()-3)+"\r\n";
				
		text+=  "        ]],\r\n" + 
				"        onDblClickRow:function(rowIndex, rowData){\r\n" + 
				"			//alert(lastIndex);\r\n" + 
				"			if (lastIndex != rowIndex){\r\n" + 
				"				$('#dg').datagrid('endEdit', lastIndex);\r\n" + 
				"				$('#dg').datagrid('beginEdit', rowIndex);\r\n" + 
				"			}\r\n" + 
				"			lastIndex = rowIndex;\r\n" + 
				"		}\r\n" + 
				"    }) ;\r\n" + 
				"}";
		
		text+="//条件查询\r\n" + 
				"function search(){\r\n" + 
				"	$('#dg').datagrid('load',getParam());\r\n" + 
				"}\r\n" + 
				"//JSON对象转化为字符串(防止中文乱码)\r\n" + 
				"function toJSONStr(obj){\r\n" + 
				"     //判断是否是IE8\r\n" + 
				"     if(navigator.appVersion.match(/8./i)==\"8.\"){\r\n" + 
				"          var answerStr = JSON.stringify(obj);   \r\n" + 
				"          var o = JSON.parse(answerStr); \r\n" + 
				"          eval(\"var answerStr = '\"+JSON.stringify(o)+\"';\");\r\n" + 
				"          return answerStr;\r\n" + 
				"     }\r\n" + 
				"     return JSON.stringify(obj);\r\n" + 
				"}\r\n" + 
				"function isSubmit(){\r\n" + 
				"	var sum = 0;\r\n" + 
				"	var box = $(\".validatebox-text\");\r\n" + 
				"	for(var i=0;i<box.length;i++){\r\n" + 
				"		if(!$(box[i]).validatebox(\"isValid\")){\r\n" + 
				"			sum++;\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	if(sum>0){\r\n" + 
				"		return false;\r\n" + 
				"	}else{\r\n" + 
				"		return true;	\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"</script>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"	img {\r\n" + 
				"		margin-left: 10px;\r\n" + 
				"		width: 20px;\r\n" + 
				"		height: 20px;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	body{\r\n" + 
				"		overflow：hidden;\r\n" + 
				"	}\r\n" + 
				"</style>";
		
		text+="</head>\r\n" + 
				"<body style=\"overflow: hidden\">\r\n" + 
				"	\r\n" + 
				"	<div id=\"tt\" class=\"easyui-panel\" style=\"position:relative;width:100%;height:800px;overflow:hidden;\">\r\n" + 
				"	\r\n" + 
				"	<div class=\"top_page_name\">"+className+"列表</div>\r\n" + 
				"<div class=\"first_div\" style=\"border-right:none;border-bottom:none;border-left:none;\">\r\n" + 
				"	\r\n" + 
				"	<table class=\"param_table\" style=\"margin-left:10px;\" >\r\n" + 
				"		<tr>\r\n";
		for (String name : names) {
			String[] tt = name.split(" ");
			name = tt[2];
			text+="			<td style=\"width:250px;\">\r\n" + 
					"                <span>"+name+"</span>\r\n" + 
					"				<input type=\"text\" id=\""+name+"\" name=\""+name+"\" class=\"input_0\" />\r\n" + 
					"			</td>\r\n";			
		}
				
		text+=  "			<td style=\"\">\r\n" + 
				"				<button class=\"btu_0\" onclick=\"search()\" style=\"margin-left:10px;\">查询</button>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"	\r\n" + 
				"	<div style=\"margin:3px 10px 10px 10px;\"></div>\r\n" + 
				"	\r\n" + 
				"	<table id=\"dg\"></table>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerIndexList <BR>
	 * Description: 生成主页面列表 <BR>
	 * Remark: <BR>
	 * @param classNames  void<BR>
	 */
	public static void writerIndexList(List<String> classNames) {
		
		String filePath = "D:/AutoCode/jsp/index/";
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = filePath+"index.jsp";
		
		String text="<%@ page language=\"java\" contentType=\"text/html;charset=UTF-8\" pageEncoding=\"UTF-8\" %>\r\n" + 
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<%\r\n" + 
				"	String bathPath = request.getContextPath();\r\n" + 
				"	pageContext.setAttribute(\"bathPath\", bathPath);\r\n" + 
				"%>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
				"<title>首页</title>\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"${bathPath}/css/page.css\"/>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/jquery-1.9.1.min.js\" ></script>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/ajaxSessionOut.js\" ></script>\r\n" + 
				"<!-- layer -->\r\n" + 
				"<link type=\"text/css\" rel=\"stylesheet\" href=\"${bathPath}/js/layer/skin/layer.css\"/>\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/layer/layer.js\" ></script>\r\n" + 
				"<!-- validation -->\r\n" + 
				"<script type=\"text/javascript\" src=\"${bathPath}/js/jquery-validation/jquery.validate.js\"></script>\r\n" + 
				"<style type=\"text/css\">\r\n" + 
				"html, body{\r\n" + 
				"	width:100%;\r\n" + 
				"	height:100%;\r\n" + 
				"	overflow:hidden;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"body{\r\n" + 
				"	background-color: #EFEFEF;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".index_top_div{\r\n" + 
				"	width:100%;\r\n" + 
				"	background-color: #EFEFEF;\r\n" + 
				"	background-image: url('${bathPath}/images/index/titlebg.png');\r\n" + 
				"}\r\n" + 
				".index_top_table{\r\n" + 
				"	width:100%;\r\n" + 
				"	height: 100%;\r\n" + 
				"	border-collapse: collapse;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".text_web{\r\n" + 
				"	font-size: 20px;\r\n" + 
				"	color: #FFFFFF;\r\n" + 
				"	letter-spacing: 1px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	font-family:Microsoft YaHei;\r\n" + 
				"}\r\n" + 
				".text_0{\r\n" + 
				"	color: #FFFFFF;\r\n" + 
				"	font-size: 13px;\r\n" + 
				"	font-family: Verdana, Arial, Helvetica, sans-serif;\r\n" + 
				"	margin-right: 5px;\r\n" + 
				"	cursor:pointer;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".l-link{ display:block; height:24px; line-height:24px; text-decoration:none; \r\n" + 
				"color:#333; padding-left:16px;border:1px solid white; margin:2px 4px;cursor:pointer;}\r\n" + 
				".l-link:HOVER, .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} \r\n" + 
				"\r\n" + 
				"#editPassword_form table td{\r\n" + 
				"	padding: 2px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".leftMenu_table{\r\n" + 
				"	width: 200px;\r\n" + 
				"	height: 40px;\r\n" + 
				"	border-collapse: collapse;\r\n" + 
				"	background-color: #D3EFFB;\r\n" + 
				"	cursor: pointer;\r\n" + 
				"	margin-bottom: 2px;\r\n" + 
				"	background-color: #4ABCF0;\r\n" + 
				"	text-align: center;\r\n" + 
				"	line-height: 40px;\r\n" + 
				"	color: #FFF;\r\n" + 
				"	font-size: 16px;\r\n" + 
				"}\r\n" + 
				".leftMenu_table .leftMenu_td_0{\r\n" + 
				"	width: 60px;\r\n" + 
				"}\r\n" + 
				".leftMenu_table .leftMenu_td_1{\r\n" + 
				"	color: #FFFFFF;\r\n" + 
				"	font-size: 13px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	letter-spacing: 0.5px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".index_menu_level_1{\r\n" + 
				"	color: #535556;\r\n" + 
				"	font-size: 13px;\r\n" + 
				"	font-weight: bold;\r\n" + 
				"	letter-spacing: 0.5px;\r\n" + 
				"	height: 32px;\r\n" + 
				"	line-height: 32px;\r\n" + 
				"	background-color: #D3EFFB;\r\n" + 
				"	cursor: pointer;\r\n" + 
				"	text-indent: 60px;\r\n" + 
				"	margin: 1px 0px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".index_menu_level_ul{\r\n" + 
				"	display:none;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".index_menu_level_1_hover, .index_menu_level_1_checked{\r\n" + 
				"	background-color: #25AE66;\r\n" + 
				"	color: #FFF;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"</style>\r\n" + 
				"\r\n" + 
				"<script type=\"text/javascript\">\r\n" + 
				"var topIframeHeight = 0;\r\n" + 
				"//调整iframe尺寸\r\n" + 
				"$(function(){\r\n" + 
				"	iframeResize();\r\n" + 
				"});\r\n" + 
				"\r\n" + 
				"function iframeResize(){\r\n" + 
				"	var hei = $(\"body\").height()-61;\r\n" + 
				"	$(\"#myIframe\").height(hei);\r\n" + 
				"	topIframeHeight = hei-3;\r\n" + 
				"}\r\n";
		for (String name : classNames) {
			String cname = name;
			name = MyUtils.toLowCase(name);
			text+="function "+name+"(){\r\n" + 
					"	$(\"#myIframe\").attr(\"src\",\"${bathPath}/"+name+"/index.action\");\r\n" + 
					"}\r\n";
		}
				
		text+= "</script>\r\n" + 
				"</head>\r\n" + 
				"<body >\r\n" + 
				"<table style=\"width:100%;height:100%;border-collapse: collapse;\">\r\n" + 
				"	<tr valign=\"top\" align=\"left\">\r\n" + 
				"		<td colspan=\"5\" style=\"height:51px;\">\r\n" + 
				"			<div class=\"index_top_div\" style=\"height:51px;\">\r\n" + 
				"				<table class=\"index_top_table\"  >\r\n" + 
				"					<tr>\r\n" + 
				"						<td align=\"center\" valign=\"middle\" style=\"width: 200px;\">\r\n" + 
				"						</td>\r\n" + 
				"						<td align=\"left\" valign=\"middle\" >\r\n" + 
				"							<div class=\"text_web\" style=\"text-align: center;\">XXX管理系统后台</div>\r\n" + 
				"						</td>\r\n" + 
				"						<td align=\"right\" valign=\"top\">\r\n" + 
				"							<div align=\"right\" style=\"margin-right:20px;margin-top:10px;\" >\r\n" + 
				"								<span class=\"text_0\" style=\"\">你好！</span>\r\n" + 
				"							</div>\r\n" + 
				"						</td>\r\n" + 
				"					</tr>\r\n" + 
				"				</table>\r\n" + 
				"			</div>\r\n" + 
				"			<div style=\"height:10px;\"></div>\r\n" + 
				"		</td>\r\n" + 
				"	</tr>\r\n" + 
				"	<tr valign=\"top\" align=\"left\">\r\n" + 
				"		<td style=\"width:6px;\"></td>\r\n" + 
				"		<td style=\"background-color:#FFFFFF;width:210px;\">\r\n" + 
				"			<div id=\"leftMenu\" style=\"padding:5px;\">\r\n";
		
		for (String name : classNames) {
			name = MyUtils.toLowCase(name);
			text+=  "				<div class=\"leftMenu_table\" onclick=\""+name+"()\">"+name+"</div>\r\n";
		}
				
		text+=  "			</div>\r\n" + 
				"		</td>\r\n" + 
				"		<td style=\"width:8px;\"></td>\r\n" + 
				"		<td style=\"height:100%;background-color:#FFFFFF;\">\r\n" + 
				"			<iframe id=\"myIframe\" name=\"mainIframe\" frameborder=\"0\" style=\"width:100%;min-width:800px;\" src=\"${bathPath}/"+MyUtils.toLowCase(classNames.get(0))+"/index.action\"></iframe>\r\n" + 
				"		</td>\r\n" + 
				"		<td style=\"width:6px;\"></td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
