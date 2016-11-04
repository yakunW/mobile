package com.wyk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wyk.Save;

public class UploadAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private File[] upload;//保存的是临时文件，包括路径和临时文件名
	private String[] uploadContextType;//文件的MIME类型
	private String[] uploadFileName;//真实的文件名
	private String savePath;//文件的保存路径，在struts.xml文件中配置，路径是工程根目录下的upload文件
	private String operation;//上传文件要实现的操作，添加或修改
	private String news;//执行结果
	
	
	public String execute() throws Exception{
		System.out.println("开始文件上传");
		File[] files=getUpload();
		Save save=new Save();
		String path=null;
		FileInputStream fis=null;
		FileOutputStream fos=null;
		byte[] buffer=new byte[1024];
		if(null==files){
			System.out.println("没有上传文件");
			return INPUT;
		}
		else{
			try{
				for(int i=0;i<files.length;i++){
					System.out.println("文件个数"+files.length+"##");
					path=this.getSavePath()+"\\"+this.getUploadFileName()[i];//存储路径加文件名
					fis=new FileInputStream(files[i]);
					fos=new FileOutputStream(path);
					int len=0;
					while((len=fis.read(buffer))>0){
						fos.write(buffer,0,len);
//						System.out.println("上传的文件名~~"+this.getUploadFileName()[i]);
//						System.out.println("文件存储路径是"+path);				
					}	
					HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
					HttpSession session=(HttpSession)req.getSession();
					System.out.println(operation);
					if(operation.equals("添加")){
						System.out.println("添加文件是"+this.uploadFileName[i]);
						if(this.uploadFileName[i].equals("产品系数模板.xlsx")){
							session.setAttribute("news", save.addChanpinXsStandard(path));
						}
						else if(this.uploadFileName[i].equals("细分模板.xlsx")){
							session.setAttribute("news", save.addXfStandard(path));
						}
						else{
							return ERROR;//文件名错误时，返回错误页面
						}
					}
					else if(operation.equals("修改")){
						System.out.println("修改");
						if(this.uploadFileName[i].equals("产品系数模板.xlsx")){
							session.setAttribute("news", save.upadateChanpinXs(path));
							
						}
						else if(this.uploadFileName[i].equals("细分模板.xlsx")){
							session.setAttribute("news", save.updateXf(path));
						
						}
						else{
							return ERROR;//文件名错误时，返回错误页面
						}
					}
					
				}
			}
			catch(Exception e){
				e.printStackTrace();
				return INPUT;
			}
			finally{
				if(fis!=null){
					fis.close();
				}
				if(fos!=null){
					fos.close();
				}		
			}
		}		
//		save.addGsStandard(path);
		return SUCCESS;
	}


	public String getSavePath() {
		return  ServletActionContext.getRequest().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public String[] getUploadContextType() {
		return uploadContextType;
	}
	public void setUploadContextType(String[] uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation=operation;
		System.out.println("传递过来的值是 "+operation);
//		try{
//			this.operation=new String(operation.getBytes("ISO8859-1"),"gbk");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}

	}


}
