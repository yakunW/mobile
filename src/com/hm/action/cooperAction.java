package com.hm.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;




import com.hm.entity.company;
import com.hm.impl.cooper.cal_StarLevel;
import com.hm.impl.cooper.cal_YearCre;
import com.hm.impl.cooper.comWrite;
import com.hm.impl.cooper.exportCre;
import com.hm.impl.cooper.exportOrder;
import com.hm.impl.cooper.export_AnyCre;
import com.hm.impl.cooper.name_Select;
import com.hm.impl.cooper.readTwo;
import com.hm.impl.cooper.importBasic;
import com.hm.impl.cooper.renameImpl;
import com.hm.org.Database;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class cooperAction extends ActionSupport{
	
	private File file;// File����Ŀ���ǻ�ȡҳ���ϴ����ļ�
	private String fileContextType;
	private String fileFileName;
	private String message = "";
	private int year;
	private int quarter;
	private int class0;
	private int order;
	private String gsname;//����˾���Ʋ�ѯ
//	private String oldname;//�ֶ��޸Ĺ�˾����ֵ������
//	private String newname;//�ֶ��޸Ĺ�˾����ֵ������
	
	public static List<company> comList = new ArrayList<company>();
	
//	������
	public String importTwo()throws Exception{
		if (file != null) {
			String path = file.getAbsolutePath();// ��ȡ�ļ���·��
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
				
				readTwo readFile =new readTwo();
				readFile.read(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
//	�����������ݿ�
	public String importCoroper(){
		for(int i =0;i<comList.size();i++){
			comList.get(i).setYear(getYear());
			comList.get(i).setQuarter(getQuarter());
		}
		comWrite cow = new comWrite();
		cow.write(comList);
		comList.clear();
		setMessage("����ɹ���");
		return "success";
	}
	
//	������Ȼ���
	public String calCredit(){
		cal_YearCre cal = new cal_YearCre();
//		System.out.println("fff:"+cal.calCre(year, quarter));
		if(cal.calCre(year, quarter).equals(""))
			setMessage(getYear()+"��"+getQuarter()+"����"+"�Ļ��ּ���ɹ���");
		else
			setMessage(cal.calCre(year, quarter));
		return "success";
	}
	
//	�Ǽ���̬����
	public String starLevel(){
		cal_StarLevel cal = new cal_StarLevel();
		cal.cal("recent",0,0,null);
		setMessage("����˸����ȵĶ�̬�Ǽ������ɹ���");
		return "success";
	}
	
//	��Ȼ��ֵ���
	public String yearCredit(){
		String name = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		switch(getClass0()){
		case 1:
			name = formatter.format(currTime)+" - �ۺϻ��ܱ�.xlsx";
			break;
		case 2:
			name = formatter.format(currTime)+" - ������ܱ�.xlsx";
			break;
		case 3:
			name = formatter.format(currTime)+" - ���߻��ܱ�.xlsx";
			break;
		case 4:
			name = formatter.format(currTime)+" - �������ܱ�.xlsx";
			break;
		case 5:
			name = formatter.format(currTime)+" - ���ݻ��ܱ�.xlsx";
			break;
		case 6:
			name = formatter.format(currTime)+" - ��Դ���ܱ�.xlsx";
			break;
		case 7:
			name = formatter.format(currTime)+" - �������ܱ�.xlsx";
			break;
		case 8:
			name = formatter.format(currTime)+" - ���Ż��ܱ�.xlsx";
			break;
		}
		exportCre exp = new exportCre();
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename�����ص�xlsx���������������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			exp.export_year(getClass0()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("�����ɹ���");
		return null;
	}
	
	public String orderCredit(){
		String name = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		switch(getOrder()){
		case 1:
			name = formatter.format(currTime)+" - �ۺ������.xlsx";
			break;
		case 2:
			name = formatter.format(currTime)+" - ��˾�����.xlsx";
			break;
		}
		exportOrder exp = new exportOrder();
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename�����ص�xlsx���������������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			exp.export(getOrder()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("�����ɹ���");
		return null;
	}
	
//	���������8������
	public String exportAny(){
		export_AnyCre exAny = new export_AnyCre();
		String name = getYear()+"year"+getQuarter()+".xlsx";
//		exportOrder exp = new exportOrder();
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ name);// filename�����ص�xlsx���������������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			exAny.exAny(getYear(),getQuarter()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("�����ɹ���");
		return null;
	}
	
	 
//	����˾���ֲ�ѯ
	public String nameSelect(){
//		System.out.println("Test"+getGsname());
		name_Select naSel = new name_Select();
		List<company> list = new ArrayList<company>();
//		list = naSel.nameSel(getGsname());
//		for(int i = 0; i < list.size(); i++)
//			System.out.println(list.get(i).getYear()+"  "+list.get(i).getQuarter()+"  "+list.get(i).getName());
		ActionContext.getContext().put("nameSel", naSel.nameSel(getGsname()));
		return "list";
	}
	
	
//	�޸Ĺ�˾����,ͨ��Excel����޸�
	public String rename(){
		if (file != null) {
			String path = file.getAbsolutePath();// ��ȡ�ļ���·��
			InputStream inputStream = null;
//			System.out.println(path);
			try {
				inputStream = new FileInputStream(path);
				
				renameImpl rena = new renameImpl();
				rena.rename(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setMessage("�޸ĳɹ����뵽��һ��Tabҳ����ת����ʽ��Ĺ�˾����");
		return "success";
	}
	
//	������ݿ�����
	public String delete(){
		Database db = new Database();
		Connection conn = db.getConnection();
		PreparedStatement ps0 = null;
		try {
			ps0 = conn.prepareStatement("TRUNCATE TABLE company");
			ps0.executeUpdate();
			
			ps0 = conn.prepareStatement("TRUNCATE TABLE quar_cre");
			ps0.executeUpdate();

			ps0 = conn.prepareStatement("TRUNCATE TABLE gather");
			ps0.executeUpdate();
			ps0.close();
			setMessage("�����ɣ�");
		} catch (SQLException e) {
			System.out.println("�������");
		}
		return "success";
	}
	
	//�����������
	public String importBasic() throws Exception{
//		System.out.println("----------------------------");
		if (file != null) {
			String path = file.getAbsolutePath();// ��ȡ�ļ���·��
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
				importBasic readFile =new importBasic();
				readFile.read(inputStream,getClass0());
				setMessage("����ɹ���");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return "success";
	}
	
	
	/*
	 * 
//	�޸Ĺ�˾���ƣ��ֶ��޸�
	public String rename2(){
		if(getOldname().equals("")){
			setMessage("����������Ϊ�գ�");
			return "input";
		}
		else if(getNewname().equals("")){
			setMessage("�����Ʋ���Ϊ�գ�");
			return "input";
		}
		else{
			renameImpl rena = new renameImpl();
			if(rena.isExist(getOldname())){
				rena.modify(getOldname(), getNewname());
				setMessage("�޸ĳɹ���");
			}
			else
				setMessage("����������������ڣ�");
			return "success";
		}
		
	}
	*/
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getFileContextType() {
		return fileContextType;
	}
	
	public void setFileContextType(String fileContextType) {
		this.fileContextType = fileContextType;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}
	
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getQuarter() {
		return quarter;
	}
	
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getClass0() {
		return class0;
	}

	public void setClass0(int class0) {
		this.class0 = class0;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getGsname() {
		return gsname;
	}

	public void setGsname(String gsname) {
		this.gsname = gsname;
	}
	
    /*
	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getNewname() {
		return newname;
	}

	public void setNewname(String newname) {
		this.newname = newname;
	}
	*/
	
}
