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
	private File[] upload;//���������ʱ�ļ�������·������ʱ�ļ���
	private String[] uploadContextType;//�ļ���MIME����
	private String[] uploadFileName;//��ʵ���ļ���
	private String savePath;//�ļ��ı���·������struts.xml�ļ������ã�·���ǹ��̸�Ŀ¼�µ�upload�ļ�
	private String operation;//�ϴ��ļ�Ҫʵ�ֵĲ�������ӻ��޸�
	private String news;//ִ�н��
	
	
	public String execute() throws Exception{
		System.out.println("��ʼ�ļ��ϴ�");
		File[] files=getUpload();
		Save save=new Save();
		String path=null;
		FileInputStream fis=null;
		FileOutputStream fos=null;
		byte[] buffer=new byte[1024];
		if(null==files){
			System.out.println("û���ϴ��ļ�");
			return INPUT;
		}
		else{
			try{
				for(int i=0;i<files.length;i++){
					System.out.println("�ļ�����"+files.length+"##");
					path=this.getSavePath()+"\\"+this.getUploadFileName()[i];//�洢·�����ļ���
					fis=new FileInputStream(files[i]);
					fos=new FileOutputStream(path);
					int len=0;
					while((len=fis.read(buffer))>0){
						fos.write(buffer,0,len);
//						System.out.println("�ϴ����ļ���~~"+this.getUploadFileName()[i]);
//						System.out.println("�ļ��洢·����"+path);				
					}	
					HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
					HttpSession session=(HttpSession)req.getSession();
					System.out.println(operation);
					if(operation.equals("���")){
						System.out.println("����ļ���"+this.uploadFileName[i]);
						if(this.uploadFileName[i].equals("��Ʒϵ��ģ��.xlsx")){
							session.setAttribute("news", save.addChanpinXsStandard(path));
						}
						else if(this.uploadFileName[i].equals("ϸ��ģ��.xlsx")){
							session.setAttribute("news", save.addXfStandard(path));
						}
						else{
							return ERROR;//�ļ�������ʱ�����ش���ҳ��
						}
					}
					else if(operation.equals("�޸�")){
						System.out.println("�޸�");
						if(this.uploadFileName[i].equals("��Ʒϵ��ģ��.xlsx")){
							session.setAttribute("news", save.upadateChanpinXs(path));
							
						}
						else if(this.uploadFileName[i].equals("ϸ��ģ��.xlsx")){
							session.setAttribute("news", save.updateXf(path));
						
						}
						else{
							return ERROR;//�ļ�������ʱ�����ش���ҳ��
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
		System.out.println("���ݹ�����ֵ�� "+operation);
//		try{
//			this.operation=new String(operation.getBytes("ISO8859-1"),"gbk");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}

	}


}
