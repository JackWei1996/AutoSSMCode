/*
 * All rights Reserved, Copyright (C) JACK LIMITED 2018
 * FileName: WriterConfigure.java
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
 * class name:WriterConfigure <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2018年10月16日
 * @author JACK)jackwei
 */
public class WriterConfigure {
	private static String filePath = "D:/AutoCode/config/";
	/**
	 * Method name: writerWebXml <BR>
	 * Description: 生成web.xml文件 <BR>
	 * Remark: <BR>
	 * @param proName
	 * @param pack  void<BR>
	 */
	public static void writerWebXml(String proName) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"web.xml";
		
		String text="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd\" id=\"WebApp_ID\" version=\"3.0\">\r\n" + 
				"  <display-name>"+proName+"</display-name>\r\n" + 
				"   <!-- 欢迎页面 -->\r\n" + 
				"  <welcome-file-list>\r\n" + 
				"    <welcome-file>/WEB-INF/jsp/index/index.jsp</welcome-file>\r\n" + 
				"  </welcome-file-list>\r\n" + 
				"  <!-- 字符编码过滤器 -->\r\n" + 
				"  <filter>\r\n" + 
				"    <filter-name>encodingFilter</filter-name>\r\n" + 
				"    <filter-class>org.springframework.web.filter.CharacterEncodingFilter\r\n" + 
				"	</filter-class>\r\n" + 
				"    <init-param>\r\n" + 
				"      <param-name>encoding</param-name>\r\n" + 
				"      <param-value>UTF-8</param-value>\r\n" + 
				"    </init-param>\r\n" + 
				"    <init-param>\r\n" + 
				"      <param-name>forceEncoding</param-name>\r\n" + 
				"      <param-value>true</param-value>\r\n" + 
				"    </init-param>\r\n" + 
				"  </filter>\r\n" + 
				"  <filter-mapping>\r\n" + 
				"    <filter-name>encodingFilter</filter-name>\r\n" + 
				"    <url-pattern>/*</url-pattern>\r\n" + 
				"  </filter-mapping>\r\n" + 
				"  <!-- 加载Spring容器 -->\r\n" + 
				"  <listener>\r\n" + 
				"    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\r\n" + 
				"  </listener>\r\n" + 
				"  <!-- 设置Spring容器的位置 -->\r\n" + 
				"  <context-param>\r\n" + 
				"    <param-name>contextConfigLocation</param-name>\r\n" + 
				"    <param-value>classpath:applicationContent.xml</param-value>\r\n" + 
				"  </context-param>\r\n" + 
				"  <!-- 加载mvc容器 -->\r\n" + 
				"  <servlet>\r\n" + 
				"  	<servlet-name>mvc</servlet-name>\r\n" + 
				"  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\r\n" + 
				"  	<!-- 设置mvc容器的位置 -->\r\n" + 
				"  	<init-param>\r\n" + 
				"  		<param-name>contextConfigLocation</param-name>\r\n" + 
				"  		<param-value>classpath:mvc.xml</param-value>\r\n" + 
				"  	</init-param>\r\n" + 
				"	<!-- 开始就加载  -->\r\n" + 
				"  	<load-on-startup>1</load-on-startup>\r\n" + 
				"  </servlet>\r\n" + 
				"  <!-- 页面访问过滤器 -->\r\n" + 
				"  <servlet-mapping>\r\n" + 
				"  	<servlet-name>mvc</servlet-name>\r\n" + 
				"  	<url-pattern>*.action</url-pattern>\r\n" + 
				"  </servlet-mapping>\r\n" + 
				"</web-app>";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerMybatisXml <BR>
	 * Description: 自动生成mybatis.xml <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerMybatisXml(String pack) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"mybatis.xml";
		
		String text="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				"<!DOCTYPE configuration\r\n" + 
				"  PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\r\n" + 
				"  \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\r\n" + 
				"<configuration>\r\n" + 
				"	<settings>\r\n" + 
				"	<!-- 打印sql语句 -->\r\n" + 
				"	  	<setting name=\"logImpl\" value=\"STDOUT_LOGGING\" />\r\n" + 
				"	</settings>\r\n" + 
				"	<!-- 拦截器 -->\r\n" + 
				"	<plugins>\r\n" + 
				"   		<plugin interceptor=\""+pack+".interceptor.PageInterceptor\">\r\n" + 
				"   		</plugin>\r\n" + 
				" 	</plugins>\r\n" + 
				"</configuration>";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerMvcXml <BR>
	 * Description: 自动生成mvc.xml <BR>
	 * Remark: <BR>
	 * @param pack  void<BR>
	 */
	public static void writerMvcXml(String pack) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"mvc.xml";
		
		String text="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n" + 
				"	xmlns:mvc=\"http://www.springframework.org/schema/mvc\" \r\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
				"    xmlns:context=\"http://www.springframework.org/schema/context\"\r\n" + 
				"    xmlns:tx=\"http://www.springframework.org/schema/tx\"\r\n" + 
				"    xsi:schemaLocation=\"\r\n" + 
				"     	http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd\r\n" + 
				"        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\r\n" + 
				"        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\r\n" + 
				"         http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd\">\r\n" + 
				"	\r\n" + 
				"	<!-- 先给action设置注解之后,再扫包 -->\r\n" + 
				"	<context:component-scan base-package=\""+pack+".action\"></context:component-scan>\r\n" + 
				"	\r\n" + 
				"	<!--视图解析器  -->\r\n" + 
				"	<bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">\r\n" + 
				"		<property name=\"viewClass\" value=\"org.springframework.web.servlet.view.JstlView\" /> \r\n" + 
				"        <property name=\"prefix\" value=\"/WEB-INF/jsp/\"/>\r\n" + 
				"        <property name=\"suffix\" value=\".jsp\"/>\r\n" + 
				"	</bean>\r\n" + 
				"	\r\n" + 
				"<!--注册驱动, 映射处理器，映射适配器  -->\r\n" + 
				"	<mvc:annotation-driven>\r\n" + 
				"        <mvc:message-converters register-defaults=\"true\">\r\n" + 
				"            <bean class=\"org.springframework.http.converter.StringHttpMessageConverter\">\r\n" + 
				"				<property name=\"supportedMediaTypes\">\r\n" + 
				"					<list>\r\n" + 
				"						<value>text/plain;charset=UTF-8</value>\r\n" + 
				"						<value>text/html;charset=UTF-8</value>\r\n" + 
				"					</list>\r\n" + 
				"				</property>\r\n" + 
				"			</bean>\r\n" + 
				"            <!-- 配置Fastjson支持 -->\r\n" + 
				"            <bean class=\"com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter\">\r\n" + 
				"                <property name=\"supportedMediaTypes\">\r\n" + 
				"                    <list>\r\n" + 
				"                        <value>application/json</value>\r\n" + 
				"                    </list>\r\n" + 
				"                </property>\r\n" + 
				"                <property name=\"features\">\r\n" + 
				"                    <!-- \r\n" + 
				"                        Fastjson的SerializerFeature序列化属性： \r\n" + 
				"                            QuoteFieldNames———-输出key时是否使用双引号,默认为true \r\n" + 
				"                            WriteMapNullValue——–是否输出值为null的字段,默认为false \r\n" + 
				"                            WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null \r\n" + 
				"                            WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null \r\n" + 
				"                            WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null \r\n" + 
				"                            WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null\r\n" + 
				"                            DisableCircularReferenceDetect: 关闭json循环引用\r\n" + 
				"                     -->\r\n" + 
				"                    <list>\r\n" + 
				"                    	<value>QuoteFieldNames</value>\r\n" + 
				"                        <value>WriteMapNullValue</value>\r\n" + 
				"                        <value>DisableCircularReferenceDetect</value>\r\n" + 
				"                    </list>\r\n" + 
				"                </property>\r\n" + 
				"            </bean>\r\n" + 
				"        </mvc:message-converters>\r\n" + 
				"    </mvc:annotation-driven>\r\n";
		text+="<!-- 对静态资源文件的访问 方案二 （二选一） -->\r\n" + 
				"	<mvc:default-servlet-handler />\r\n"+
				"		<!--配置文件上传的bean  -->\r\n" + 
				"	<bean id=\"multipartResolver\" class=\"org.springframework.web.multipart.commons.CommonsMultipartResolver\">\r\n" + 
				"			<property name=\"defaultEncoding\" value=\"UTF-8\"/>\r\n" + 
				"			<!-- 指定所上传文件的总大小不能超过2MB。\r\n" + 
				"			注意maxUploadSize属性的限制不是针对单个文件，而是所有文件的容量之和 -->\r\n" + 
				"			<property name=\"maxUploadSize\" value=\"2000000\"/>\r\n" + 
				"			<!-- 最大内存大小 (40960)-->\r\n" + 
				"			<property name=\"maxInMemorySize\" value=\"40960\" />\r\n" + 
				"	</bean>\r\n";
		text+=  "</beans>\r\n" + 
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
	 * Method name: writerLog4j <BR>
	 * Description: 自动生成log4j.properties <BR>
	 * Remark: <BR>  void<BR>
	 */
	public static void writerLog4j() {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"log4j.properties";
		
		String text="### direct log messages to stdout ###\r\n" + 
				"log4j.appender.stdout=org.apache.log4j.ConsoleAppender\r\n" + 
				"log4j.appender.stdout.Target=System.out\r\n" + 
				"log4j.appender.stdout.layout=org.apache.log4j.PatternLayout\r\n" + 
				"log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n\r\n" + 
				"\r\n" + 
				"### direct messages to file mylog.log ###\r\n" + 
				"log4j.appender.file=org.apache.log4j.FileAppender\r\n" + 
				"log4j.appender.file.File=/mylog.log\r\n" + 
				"log4j.appender.file.layout=org.apache.log4j.PatternLayout\r\n" + 
				"log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n\r\n" + 
				"\r\n" + 
				"### set log levels - for more verbose logging change 'info' to 'debug' ###\r\n" + 
				"\r\n" + 
				"log4j.rootLogger=info, stdout";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerDatabase <BR>
	 * Description: 自动生成database.properties <BR>
	 * Remark: <BR>  void<BR>
	 */
	public static void writerDatabase(String driver, String url, String user, String pass) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"database.properties";
		
		String text="jdbc.driverClass = "+driver+"\r\n" + 
				"jdbc.jdbcUrl = "+url+"\r\n" + 
				"jdbc.user = "+user+"\r\n" + 
				"jdbc.password = "+pass+"\r\n" + 
				"jdbc.miniPoolSize = 1\r\n" + 
				"jdbc.maxPoolSize = 100\r\n" + 
				"jdbc.initialPoolSize = 10";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method name: writerApplication <BR>
	 * Description: 自动生成applicationContent.xml文件 <BR>
	 * Remark: <BR>  void<BR>
	 */
	public static void writerApplication(String pack) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+"applicationContent.xml";
		
		String text="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
				"    xmlns:context=\"http://www.springframework.org/schema/context\"\r\n" + 
				"    xmlns:tx=\"http://www.springframework.org/schema/tx\"\r\n" + 
				"    xsi:schemaLocation=\"\r\n" + 
				"        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd\r\n" + 
				"        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\r\n" + 
				"         http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd\">\r\n" + 
				"	<context:component-scan base-package=\""+pack+".dao\"></context:component-scan>\r\n" + 
				"	<context:component-scan base-package=\""+pack+".service\"></context:component-scan>\r\n" + 
				"\r\n" + 
				"	<!-- 1.加载properties数据库配置文件 -->\r\n" + 
				"	<bean id=\"propertyConfigurer\" class=\"org.springframework.beans.factory.config.PropertyPlaceholderConfigurer\">\r\n" + 
				"		<property name=\"location\" value=\"classpath:database.properties\" />\r\n" + 
				"	</bean>\r\n" + 
				"	\r\n" + 
				"	<!-- 2.加载数据源 -->\r\n" + 
				"	<bean id=\"mysqlDataSource\" class=\"com.mchange.v2.c3p0.ComboPooledDataSource\">\r\n" + 
				"		<property name=\"driverClass\" value=\"${jdbc.driverClass}\"></property>\r\n" + 
				"         <property name=\"jdbcUrl\" value=\"${jdbc.jdbcUrl}\"></property>\r\n" + 
				"         <property name=\"user\" value=\"${jdbc.user}\"></property>\r\n" + 
				"         <property name=\"password\" value=\"${jdbc.password}\"></property>\r\n" + 
				"	</bean>\r\n" + 
				"	\r\n" + 
				"	<!--3.获取sqlSessionFactory  -->\r\n" + 
				"	<bean id=\"sqlSessionFactory\" 	class=\"org.mybatis.spring.SqlSessionFactoryBean\">\r\n" + 
				"		 <!-- dataSource:引用数据源 -->\r\n" + 
				"		<property name=\"dataSource\" ref=\"mysqlDataSource\"/>\r\n" + 
				"		<!-- 加载配置mybatis配置 -->\r\n" + 
				"		<property name=\"configLocation\"  value=\"classpath:mybatis.xml\" />\r\n" + 
				"	</bean>\r\n" + 
				"	\r\n" + 
				"	<!--4.获取dao层实现类放入容器中  -->\r\n" + 
				"	<bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">\r\n" + 
				"<!--指定sql映射文件/接口所在的包（自动扫描） -->\r\n" + 
				"		<property name=\"basePackage\" value=\""+pack+".dao\"/>\r\n" + 
				"		<!-- 依赖现有的sqlSessionFactory -->\r\n" + 
				"		<property name=\"sqlSessionFactoryBeanName\"  value=\"sqlSessionFactory\"/>\r\n" + 
				"	</bean>\r\n" + 
				"	\r\n" + 
				"	<!--5.设置事务  -->\r\n" + 
				"	<bean id=\"transactionManager\"  class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">\r\n" + 
				"		<!-- dataSource:引用上面定义的数据源 -->\r\n" + 
				"		<property name=\"dataSource\" ref=\"mysqlDataSource\"></property>\r\n" + 
				"	</bean>\r\n" + 
				"	<!--6.开启事务  -->\r\n" + 
				"	<tx:annotation-driven transaction-manager=\"transactionManager\"/>\r\n" + 
				"</beans>\r\n" + 
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
