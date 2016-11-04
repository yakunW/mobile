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
	String fileName;//����ҳ�洫�ݵĲ���
	int type;

	
	@Override
	public String execute() throws Exception {
		System.out.println("Ҫ���ص��ļ���"+this.fileName);
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	public InputStream getDownloadFile() throws IOException
	{
		FileInputStream in = null;
		if(type==2){//type=2����Ҫ���ص������ݿⱸ���ļ���������ģ���ļ����������ļ��ڷ����� �˵Ĵ�ŵ�ַ��ͬ
			try{
				in=new FileInputStream("D:\\database\\"+fileName);//����InputStream����		
			}
			catch(Exception e){
				e.printStackTrace();
			}						
			return in;
		}
//		hm�������
		else if(type == 1){
			return ServletActionContext.getServletContext().getResourceAsStream("newName\\"+fileName);	
		}
		else{
			return ServletActionContext.getServletContext().getResourceAsStream("ģ��\\"+fileName);	
		}	 	 
	}

	public String getFileName() {
		try{
//			fileName= new String(this.fileName.getBytes(),"utf-8");//����
//			fileName= new String(this.fileName.getBytes("gbk"),"utf-8");
//			fileName= new String(fileName.getBytes(),"ISO8859-1");
			fileName=java.net.URLEncoder.encode(fileName, "utf-8");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("get�����õ����ļ�����"+this.fileName);
		return fileName;
	}

	public void setFileName(String filename) {
		System.out.println("ȡ�����ļ�����"+filename);
		try{
//			this.fileName=new String(filename.getBytes("utf-8"),"ISO8859-1");
//			this.fileName=new String(filename.getBytes(),"utf-8");
			this.fileName=new String(filename.getBytes("ISO8859-1"),"utf-8");
//			this.fileName=java.net.URLEncoder.encode(this.fileName, "GBK");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("��������     "+this.fileName);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
