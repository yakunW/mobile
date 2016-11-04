package com.wyk.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class BackUpAction {
	
	String user="root" ; // ���ݿ��ʺ�    
    String password="123" ; // ��½����    
	String database="dinge" ; // ��Ҫ���ݵ����ݿ���    
	String filename;
	public String  backUp() {
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		File backup=new File("d:\\database");
		filename=myformat.format(new Date())+"-dinge.sql";
		String filepath = "d:\\database\\"+filename; // ���ݵ�·����ַ    	
		System.out.println("����·����"+filepath);
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String root = sc.getRealPath("");//��Ŀ��Ŀ¼�ľ���·��
        System.out.println(root);
          
		String str="D:\\database\\mysqldump " + "-hlocalhost" + " -u" + user + " -p"+ password + " "+database+" >" + filepath;
		System.out.println(str);
		String oldPathB=root+"\\mysqldump\\mysqldump.exe";
		String oldPathR=root+"\\mysqldump\\mysql.exe";
		String newPath="D:\\database";
		String copyB="copy "+oldPathB+" "+newPath;//dos�������
		String copyR="copy "+oldPathR+" "+newPath;
		
		  
		try {   
			 backup.mkdir();//���d:\\database�ļ��в����ڣ������ļ���
			 System.out.println(copyB);
			 System.out.println(copyR);
			 Runtime.getRuntime().exec("cmd /c" +copyB);
			 Runtime.getRuntime().exec("cmd /c" +copyR);
			 Process p=Runtime.getRuntime().exec("cmd /c" +str); // "/c",ִ���ַ���ָ��������Ȼ���ն� 
			 try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //����ִ��ʱ���ȴ�״̬ 
		    System.out.println("�����ѵ������ļ�" + filepath + "��");    
		   } catch (Exception e) { 
		    e.printStackTrace();   
		    ActionContext.getContext().put("result","����ʧ��");
		    return "success";
		  } 
		   ActionContext.getContext().put("result","���ݳɹ�");
		   ActionContext.getContext().put("filename", "filename");
//		   HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
//		   req.setAttribute("result", "���ݳɹ�2");
		   return "success";
	}

	/*
	 * ���ݿ�ָ�
	 */
	public String recovery(String fileName) { 
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String root = sc.getRealPath("");//��Ŀ��Ŀ¼�ľ���·��
        
		   String filepath = root+"\\upload\\"+fileName; // ���ݵ�·����ַ 
		   String stmt="D:\\database\\mysql"+ " -hlocalhost" +" -u" + user + " -p"+ password + " "+database+" <" + filepath;
		   System.out.println(stmt);
		   try {  
		    Runtime.getRuntime().exec("cmd /c"+stmt); 
		    System.out.println("�����Ѵ� " + filepath + " ���뵽���ݿ���"); 
		   } catch (Exception e) { 
			   ActionContext.getContext().put("result","�ָ�ʧ��");
		    e.printStackTrace(); 
		   } 
		   ActionContext.getContext().put("result","�ָ��ɹ�");
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
