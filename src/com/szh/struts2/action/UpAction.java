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
	private File upload;//必须为upload，这样才能和uploadContentType、uploadFileName对应起来，否则不能上传
	private String uploadContentType;
    private String uploadFileName;
    private String savePath;
    private String picpath;//图片上传后的绝对路径，例如：D:/tmp/upload/large_cFLo_382a132019.jpg
	
	
    
    
    
    
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
					
			count=ImportExcel.ExcelImp(getUploadFileName());//读取Excel文件，并将Excel内容插入到数据库中，数据库操作1
			
			//count=excelData.getRepeatCount();//获取Excel导入的记录条数		
						
			
		}//导入数据库
		    
		   catch(Exception e)
		     {
			  e.printStackTrace();
		     }
		  
		  HttpSession session=req.getSession();
		  List<Data> listq=Database.query(count);//查询数据库，数据库操作2
		 
		  int updatecount=Database.update(listq);//根据数据库规则更新，数据库操作3
		  List<Data> list=Database.query(count);//查询更新过后的数据库，数据库操作4
		  if((list.size()>0)&&(list!=null))
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", count);
		  session.setAttribute("count", (count-updatecount));
		return "SUCCESS";
	 }
	public String addDatatest() throws Exception//在查询页面中产生   新的定额数据
	{
		HttpServletRequest req =(HttpServletRequest)ServletActionContext.getRequest();
       // HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		
		
		  HttpSession session=req.getSession();
		  
		 
			String id=req.getParameter("id");
			
			System.out.println(id);
			
			Data sdata=OperateData.queryOp(Integer.parseInt(id));//显示这条记录的内容
			
			
			//插入数据
			Database.insert2(sdata.getXid(),sdata.getName(),sdata.getProvince(),sdata.getDanwei(),sdata.getYewu(),sdata.getJmanager(),sdata.getKmanager(),sdata.getFzr(),sdata.getZzy(),sdata.getSjjd(),sdata.getZhulei(),sdata.getFenlei(),sdata.getXiaolei(),sdata.getFenxiang(),
					sdata.getChanpin(),sdata.getZhuanye(),sdata.getJiliang(),sdata.getJdxs(),sdata.getJishu(),sdata.getXs1(),sdata.getXs2(),sdata.getXs3(),sdata.getGs(),new Timestamp(System.currentTimeMillis()),0);
			int nid=OperateData.queryID();//获取刚插入数据的id
			Data ndata=OperateData.queryOp(nid);
			List<Data> list=new ArrayList<Data>();
			list.add(ndata);
			

		  
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", 1);//给count赋值为1
		  session.setAttribute("count", 0);
		return "SUCCESS";
	 }
	public String updateDatatest() throws Exception//在查询页面中重新修改已经定额过的数据
	{
		HttpServletRequest req =(HttpServletRequest)ServletActionContext.getRequest();
       // HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		
		
		  HttpSession session=req.getSession();
		  
		 
			String id=req.getParameter("id");
			
			System.out.println(id);
			
			Data sdata=OperateData.queryOp(Integer.parseInt(id));//显示这条记录的内容
			List<Data> list=new ArrayList<Data>();
			list.add(sdata);
			

		  
		  session.setAttribute("data", list);
		  session.setAttribute("upcount", 1);//给count赋值为1
		  session.setAttribute("count", 0);
		return "SUCCESS";
	 }
}
