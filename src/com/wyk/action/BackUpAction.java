package com.wyk.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class BackUpAction {
	
	String user="root" ; // 数据库帐号    
    String password="123" ; // 登陆密码    
	String database="dinge" ; // 需要备份的数据库名    
	String filename;
	public String  backUp() {
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		File backup=new File("d:\\database");
		filename=myformat.format(new Date())+"-dinge.sql";
		String filepath = "d:\\database\\"+filename; // 备份的路径地址    	
		System.out.println("备份路径是"+filepath);
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String root = sc.getRealPath("");//项目根目录的绝对路径
        System.out.println(root);
          
		String str="D:\\database\\mysqldump " + "-hlocalhost" + " -u" + user + " -p"+ password + " "+database+" >" + filepath;
		System.out.println(str);
		String oldPathB=root+"\\mysqldump\\mysqldump.exe";
		String oldPathR=root+"\\mysqldump\\mysql.exe";
		String newPath="D:\\database";
		String copyB="copy "+oldPathB+" "+newPath;//dos命令，复制
		String copyR="copy "+oldPathR+" "+newPath;
		
		  
		try {   
			 backup.mkdir();//如果d:\\database文件夹不存在，创建文件夹
			 System.out.println(copyB);
			 System.out.println(copyR);
			 Runtime.getRuntime().exec("cmd /c" +copyB);
			 Runtime.getRuntime().exec("cmd /c" +copyR);
			 Process p=Runtime.getRuntime().exec("cmd /c" +str); // "/c",执行字符串指定的命令然后终断 
			 try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //命令执行时，等待状态 
		    System.out.println("数据已导出到文件" + filepath + "中");    
		   } catch (Exception e) { 
		    e.printStackTrace();   
		    ActionContext.getContext().put("result","备份失败");
		    return "success";
		  } 
		   ActionContext.getContext().put("result","备份成功");
		   ActionContext.getContext().put("filename", "filename");
//		   HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
//		   req.setAttribute("result", "备份成功2");
		   return "success";
	}

	/*
	 * 数据库恢复
	 */
	public String recovery(String fileName) { 
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String root = sc.getRealPath("");//项目根目录的绝对路径
        
		   String filepath = root+"\\upload\\"+fileName; // 备份的路径地址 
		   String stmt="D:\\database\\mysql"+ " -hlocalhost" +" -u" + user + " -p"+ password + " "+database+" <" + filepath;
		   System.out.println(stmt);
		   try {  
		    Runtime.getRuntime().exec("cmd /c"+stmt); 
		    System.out.println("数据已从 " + filepath + " 导入到数据库中"); 
		   } catch (Exception e) { 
			   ActionContext.getContext().put("result","恢复失败");
		    e.printStackTrace(); 
		   } 
		   ActionContext.getContext().put("result","恢复成功");
		   return "success";
	}
	
	
	public static void main(String[] args){
//		BackUpAction b=new BackUpAction();
//		b.backUp();
//		b.recovery();
	}
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}


}
