package com.wyk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class RecoveryUploadAction extends ActionSupport{
	
	private File sql;
	private String sqlFileName;
	private String sqlContentType;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(null==sqlFileName){
			return INPUT;
		}
		if(-1==sqlFileName.toString().indexOf("sql")){
			return INPUT;
		}
		File save=new File(ServletActionContext.getServletContext().getRealPath("upload"), sqlFileName);
		
		InputStream in=null;
		OutputStream out=null;
		
		try{
			in=new FileInputStream(sql);
			out=new FileOutputStream(save);
			
			byte[] b=new byte[1024];
			int len=0;
			while((len=in.read(b))!=-1){
				out.write(b,0,len);
			}
			
			new BackUpAction().recovery(sqlFileName);
		}
		catch(Exception e){
			e.printStackTrace();
			return INPUT;
		}
		finally{
			if(in!=null) in.close();
			if(out!=null) out.close();
		}
		return SUCCESS;
		
	}

	public File getSql() {
		return sql;
	}

	public void setSql(File sql) {
		this.sql = sql;
	}

	public String getSqlFileName() {
		return sqlFileName;
	}

	public void setSqlFileName(String sqlFileName) {
		this.sqlFileName = sqlFileName;
	}

	public String getSqlContentType() {
		return sqlContentType;
	}

	public void setSqlContentType(String sqlContentType) {
		this.sqlContentType = sqlContentType;
	}
	
	

}
