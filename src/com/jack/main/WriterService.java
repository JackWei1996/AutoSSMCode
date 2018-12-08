/**
 * 
 */
package com.jack.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class name:WriterService <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 Oct 16, 2018
 * @author JACK)JackWei
 */
public class WriterService {
	/**
	 * Method name: writerIService <BR>
	 * Description: 按照模板把Service接口写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param pack  void<BR>
	 */
	public static void writerIService(String name, String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"service/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String path = filePath+"I"+name+"Service.java";
		String text = "package "+pack+".service;\r\n\r\n" + 
				"import java.util.List;\r\n" + 
				"import org.apache.ibatis.annotations.Param;\r\n" + 
				"import "+pack+".bean."+name+"Bean;\n\n";
		
		text += "public interface "+"I"+name+"Service{\r\n" + 
				"	public void insert("+name+"Bean bean);\r\n" + 
				"	public void delete(Integer id);\r\n" + 
				"	public void update("+name+"Bean bean);\r\n" + 
				"	public "+name+"Bean findById(Integer id);\r\n" + 
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
	 * Method name: writerServiceImpl <BR>
	 * Description: 按照模板把Service实现类写到文件中 <BR>
	 * Remark: <BR>
	 * @param name
	 * @param pack  void<BR>
	 */
	public static void writerServiceImpl(String name, String pack) {
		//新建文件夹
		String[] ff = pack.split("\\.");
		String pf = "";
		for (String str : ff) {
			pf+=str+"/";
		}
		String  filePath = "D:/AutoCode/"+pf+"service/impl/";
		
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String path = filePath+name+"ServiceImpl.java";
		String text = "package "+pack+".service.impl;\r\n" + 
				"\r\n" + 
				"import java.util.List;\r\n" + 
				"import javax.annotation.Resource;\r\n" + 
				"import org.springframework.stereotype.Service;\r\n" + 
				"import org.springframework.transaction.annotation.Transactional;\r\n" + 
				"import "+pack+".bean."+name+"Bean;\r\n" + 
				"import "+pack+".dao."+name+"DaoMapper;\r\n" + 
				"import "+pack+".service.I"+name+"Service;\r\n\r\n";
		
		text +="@Service(\""+toLowCase(name)+"Service\")\r\n" + 
				"public class "+name+"ServiceImpl implements I"+name+"Service {\r\n" + 
				"	@Resource\r\n" + 
				"	private "+name+"DaoMapper "+toLowCase(name)+"Mapper;\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	@Transactional\r\n" + 
				"	public void insert("+name+"Bean bean) {\r\n" + 
				"		"+toLowCase(name)+"Mapper.insert(bean);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	@Transactional\r\n" + 
				"	public void delete(Integer id) {\r\n" + 
				"		"+toLowCase(name)+"Mapper.delete(id);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	@Transactional\r\n" + 
				"	public void update("+name+"Bean bean) {\r\n" + 
				"		"+toLowCase(name)+"Mapper.update(bean);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public "+name+"Bean findById(Integer id) {\r\n" + 
				"		return "+toLowCase(name)+"Mapper.findById(id);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	@Transactional\r\n" + 
				"	public List<"+name+"Bean> findByCondition("+name+"Bean bean) {\r\n" + 
				"		return "+toLowCase(name)+"Mapper.findByCondition(bean);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	@Transactional\r\n" + 
				"	public long findByConditionCount("+name+"Bean bean) {\r\n" + 
				"		return "+toLowCase(name)+"Mapper.findByConditionCount(bean);\r\n" + 
				"	}\r\n" + 
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
	 * Method name: toLowCase <BR>
	 * Description: 第一个字母小写 <BR>
	 * Remark: <BR>
	 * @param s
	 * @return  String<BR>
	 */
	public static String toLowCase(String s) {
		return s.substring(0, 1).toLowerCase()+s.substring(1,s.length());
	}
}
