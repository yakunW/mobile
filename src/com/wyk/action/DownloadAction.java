package com.wyk.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName;//接受页面传递的参数
	int type;

	
	@Override
	public String execute() throws Exception {
		System.out.println("要下载的文件是"+this.fileName);
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	public InputStream getDownloadFile() throws IOException
	{
		FileInputStream in = null;
		if(type==2){//type=2表面要下载的是数据库备份文件，否则是模板文件，这两种文件在服务器 端的存放地址不同
			try{
				in=new FileInputStream("D:\\database\\"+fileName);//返回InputStream类型		
			}
			catch(Exception e){
				e.printStackTrace();
			}						
			return in;
		}
//		hm所需代码
		else if(type == 1){
			return ServletActionContext.getServletContext().getResourceAsStream("newName\\"+fileName);	
		}
		else{
			return ServletActionContext.getServletContext().getResourceAsStream("模板\\"+fileName);	
		}	 	 
	}

	public String getFileName() {
		try{
//			fileName= new String(this.fileName.getBytes(),"utf-8");//不行
//			fileName= new String(this.fileName.getBytes("gbk"),"utf-8");
//			fileName= new String(fileName.getBytes(),"ISO8859-1");
			fileName=java.net.URLEncoder.encode(fileName, "utf-8");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("get方法得到的文件名是"+this.fileName);
		return fileName;
	}

	public void setFileName(String filename) {
		System.out.println("取到的文件名是"+filename);
		try{
//			this.fileName=new String(filename.getBytes("utf-8"),"ISO8859-1");
//			this.fileName=new String(filename.getBytes(),"utf-8");
			this.fileName=new String(filename.getBytes("ISO8859-1"),"utf-8");
//			this.fileName=java.net.URLEncoder.encode(this.fileName, "GBK");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("设置名字     "+this.fileName);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
