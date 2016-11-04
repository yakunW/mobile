package com.szh.struts2.action;

import java.sql.Timestamp;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//import java.io.OutputStream;

import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;

//import java.sql.Timestamp;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.Database;
import com.szh.struts2.dao.OperateData;


import com.szh.excelimport.util.ImportExcel;

//import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class UpAction extends ActionSupport{

	
	private static final long serialVersionUID = 1L;
	//private Data data;
	private File upload;//����Ϊupload���������ܺ�uploadContentType��uploadFileName��Ӧ�������������ϴ�
	private String uploadContentType;
    private String uploadFileName;
    private String savePath;
    private String picpath;//ͼƬ�ϴ���ľ���·�������磺D:/tmp/upload/large_cFLo_382a132019.jpg
	
	
    
    
    
    
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}



	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return "D:\\tmp\\"+savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}






	public String addData() throws Exception
	{
		HttpServletRequest req =(HttpServletRequest)ServletActionContext.getRequest();
       // HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		
		
		FileOutputStream fos=new FileOutputStream(getSavePath()+"\\"+getUploadFileName());
		FileInputStream fis=new FileInputStream(getUpload());
		//System.out.println(System.getProperty("user.dir"));
		
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=fis.read(buffer))>0)
		{
			fos.write(buffer,0,len);
		}		
		fis.close();
		fos.close();
		System.out.println(getUploadFileName());
		int count=0;
		try{ 
					
			count=ImportExcel.ExcelImp(getUploadFileName());//��ȡExcel�ļ�������Excel���ݲ��뵽���ݿ��У����ݿ����1
			
			//count=excelData.getRepeatCount();//��ȡExcel����ļ�¼����		
						
			
		}//�������ݿ�
		    
		   catch(Exception e)
		     {
			  e.printStackTrace();
		     }
		  
		  HttpSession session=req.getSession();
		  List<Data> listq=Database.query(count);//��ѯ���ݿ⣬���ݿ����2
		 
		  int updatecount=Database.update(listq);//�������ݿ������£����ݿ����3
		  List<Data> list=Database.query(count);//��ѯ���¹�������ݿ⣬���ݿ����4
		  if((list.size()>0)&&(list!=null))
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", count);
		  session.setAttribute("count", (count-updatecount));
		return "SUCCESS";
	 }
	public String addDatatest() throws Exception//�ڲ�ѯҳ���в���   �µĶ�������
	{
		HttpServletRequest req =(HttpServletRequest)ServletActionContext.getRequest();
       // HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		
		
		  HttpSession session=req.getSession();
		  
		 
			String id=req.getParameter("id");
			
			System.out.println(id);
			
			Data sdata=OperateData.queryOp(Integer.parseInt(id));//��ʾ������¼������
			
			
			//��������
			Database.insert2(sdata.getXid(),sdata.getName(),sdata.getProvince(),sdata.getDanwei(),sdata.getYewu(),sdata.getJmanager(),sdata.getKmanager(),sdata.getFzr(),sdata.getZzy(),sdata.getSjjd(),sdata.getZhulei(),sdata.getFenlei(),sdata.getXiaolei(),sdata.getFenxiang(),
					sdata.getChanpin(),sdata.getZhuanye(),sdata.getJiliang(),sdata.getJdxs(),sdata.getJishu(),sdata.getXs1(),sdata.getXs2(),sdata.getXs3(),sdata.getGs(),new Timestamp(System.currentTimeMillis()),0);
			int nid=OperateData.queryID();//��ȡ�ղ������ݵ�id
			Data ndata=OperateData.queryOp(nid);
			List<Data> list=new ArrayList<Data>();
			list.add(ndata);
			

		  
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", 1);//��count��ֵΪ1
		  session.setAttribute("count", 0);
		return "SUCCESS";
	 }
	public String updateDatatest() throws Exception//�ڲ�ѯҳ���������޸��Ѿ������������
	{
		HttpServletRequest req =(HttpServletRequest)ServletActionContext.getRequest();
       // HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		
		
		  HttpSession session=req.getSession();
		  
		 
			String id=req.getParameter("id");
			
			System.out.println(id);
			
			Data sdata=OperateData.queryOp(Integer.parseInt(id));//��ʾ������¼������
			List<Data> list=new ArrayList<Data>();
			list.add(sdata);
			

		  
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", 1);//��count��ֵΪ1
		  session.setAttribute("count", 0);
		return "SUCCESS";
	 }
}
