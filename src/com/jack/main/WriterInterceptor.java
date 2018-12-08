/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: WriterInterceptor.java
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
 * class name:WriterInterceptor <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月16日
 * @author JACK)jackwei
 */
public class WriterInterceptor {
	
	/**
	 * Method name: writerInterceptor <BR>
	 * Description: 自动生成Mybatis过滤器文件 <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerInterceptor(String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"interceptor/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"PageInterceptor.java";
		String text="package "+pack+".interceptor;\r\n" + 
				"\r\n" + 
				"import java.sql.Connection;\r\n" + 
				"import java.util.Properties;\r\n" + 
				"\r\n" + 
				"import org.apache.ibatis.executor.statement.StatementHandler;\r\n" + 
				"import org.apache.ibatis.mapping.BoundSql;\r\n" + 
				"import org.apache.ibatis.mapping.MappedStatement;\r\n" + 
				"import org.apache.ibatis.plugin.Interceptor;\r\n" + 
				"import org.apache.ibatis.plugin.Intercepts;\r\n" + 
				"import org.apache.ibatis.plugin.Invocation;\r\n" + 
				"import org.apache.ibatis.plugin.Plugin;\r\n" + 
				"import org.apache.ibatis.plugin.Signature;\r\n" + 
				"import org.apache.ibatis.reflection.DefaultReflectorFactory;\r\n" + 
				"import org.apache.ibatis.reflection.MetaObject;\r\n" + 
				"import org.apache.ibatis.reflection.SystemMetaObject;\r\n" + 
				"\r\n" + 
				"import "+pack+".bean.BaseBean;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * class name:PageInterceptor <BR>\r\n" + 
				" * class description: 分页拦截器 <BR>\r\n" + 
				" * Remark: <BR>\r\n" + 
				" * @version 1.00 2018年10月12日\r\n" + 
				" * @author JACK)jackwei\r\n" + 
				" */\r\n" + 
				"@Intercepts({\r\n" + 
				"@Signature(type = StatementHandler.class, method = \"prepare\", args = { Connection.class, Integer.class }) })\r\n" + 
				"public class PageInterceptor implements Interceptor {\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * @Override\r\n" + 
				"	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation) <BR>\r\n" + 
				"	 * Method name: intercept <BR>\r\n" + 
				"	 * Description: 拦截器主要实现方法 <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param arg0\r\n" + 
				"	 * @return\r\n" + 
				"	 * @throws Throwable  <BR>\r\n" + 
				"	*/\r\n" + 
				"	@Override\r\n" + 
				"	public Object intercept(Invocation invocation) throws Throwable {\r\n" + 
				"		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();\r\n" + 
				"	    MetaObject metaObject = MetaObject.forObject(statementHandler,\r\n" + 
				"	    		SystemMetaObject.DEFAULT_OBJECT_FACTORY,\r\n" + 
				"	        SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,\r\n" + 
				"	        new DefaultReflectorFactory());\r\n" + 
				"	    MappedStatement mappedStatement = (MappedStatement) metaObject.getValue(\"delegate.mappedStatement\");\r\n" + 
				"	    //通过MetaObject元数据取得方法名id：com.XXX.XXXMapper.方法\r\n" + 
				"	    String id = mappedStatement.getId();\r\n" + 
				"	    //匹配在mybatis中定义的与分页有关的查询id--分页方法\r\n" + 
				"	    if (id.endsWith(\"Condition\")) {\r\n" + 
				"	      //BoundSql中有原始的sql语句和对应的查询参数\r\n" + 
				"	      BoundSql boundSql = statementHandler.getBoundSql();\r\n" + 
				"	      //获取参数对象\r\n" + 
				"	      Object params = boundSql.getParameterObject();\r\n" + 
				"	      //设置参数对象赋值给父类,进行sql语句参数修改\r\n" + 
				"	      BaseBean page = (BaseBean) params;\r\n" + 
				"	      //设置开始/结束页--分页操作[根据你的需求实际操作]\r\n" + 
				"	      //首先要判空!!!!!!\r\n" + 
				"	      if (page!=null) {\r\n" + 
				"	    	  if(page.getRows()!=null) {\r\n" + 
				"		    	  page.setPageSize(page.getRows());\r\n" + 
				"		      }\r\n" + 
				"	    	  if (page.getPage()!=null && page.getPageSize()!=null) {				\r\n" + 
				"	    		  page.setPage((page.getPage()-1)*page.getPageSize());\r\n" + 
				"	    	  }\r\n" + 
				"	      }\r\n" + 
				"	    }\r\n" + 
				"	    return invocation.proceed();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * @Override\r\n" + 
				"	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object) <BR>\r\n" + 
				"	 * Method name: plugin <BR>\r\n" + 
				"	 * Description: please write your description <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param arg0\r\n" + 
				"	 * @return  <BR>\r\n" + 
				"	*/\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public Object plugin(Object target) {\r\n" + 
				"		// 如果将拦截器类比喻为代购票的公司，那this就是代购业务员\r\n" + 
				"		//（进入方法前是无代理购票能力业务员，进入后成为有代理能力的业务员）\r\n" + 
				"	    // 通过注解获取拦截目标的信息，如果不符合拦截要求就返回原目标，如果符合则使用动态代理生成代理对象\r\n" + 
				"	    return Plugin.wrap(target, this);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * @Override\r\n" + 
				"	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties) <BR>\r\n" + 
				"	 * Method name: setProperties <BR>\r\n" + 
				"	 * Description: please write your description <BR>\r\n" + 
				"	 * Remark: <BR>\r\n" + 
				"	 * @param arg0  <BR>\r\n" + 
				"	*/\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public void setProperties(Properties arg0) {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
