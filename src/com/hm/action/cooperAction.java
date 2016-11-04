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
	
	private File file;// File对象，目的是获取页面上传的文件
	private String fileContextType;
	private String fileFileName;
	private String message = "";
	private int year;
	private int quarter;
	private int class0;
	private int order;
	private String gsname;//按公司名称查询
//	private String oldname;//手动修改公司名称值旧名称
//	private String newname;//手动修改公司名称值新名称
	
	public static List<company> comList = new ArrayList<company>();
	
//	批量读
	public String importTwo()throws Exception{
		if (file != null) {
			String path = file.getAbsolutePath();// 获取文件的路径
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
	
//	批量导入数据库
	public String importCoroper(){
		for(int i =0;i<comList.size();i++){
			comList.get(i).setYear(getYear());
			comList.get(i).setQuarter(getQuarter());
		}
		comWrite cow = new comWrite();
		cow.write(comList);
		comList.clear();
		setMessage("导入成功！");
		return "success";
	}
	
//	计算年度积分
	public String calCredit(){
		cal_YearCre cal = new cal_YearCre();
//		System.out.println("fff:"+cal.calCre(year, quarter));
		if(cal.calCre(year, quarter).equals(""))
			setMessage(getYear()+"年"+getQuarter()+"季度"+"的积分计算成功！");
		else
			setMessage(cal.calCre(year, quarter));
		return "success";
	}
	
//	星级动态评定
	public String starLevel(){
		cal_StarLevel cal = new cal_StarLevel();
		cal.cal("recent",0,0,null);
		setMessage("最近八个季度的动态星级评定成功！");
		return "success";
	}
	
//	年度积分导出
	public String yearCredit(){
		String name = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		switch(getClass0()){
		case 1:
			name = formatter.format(currTime)+" - 综合汇总表.xlsx";
			break;
		case 2:
			name = formatter.format(currTime)+" - 传输汇总表.xlsx";
			break;
		case 3:
			name = formatter.format(currTime)+" - 无线汇总表.xlsx";
			break;
		case 4:
			name = formatter.format(currTime)+" - 交换汇总表.xlsx";
			break;
		case 5:
			name = formatter.format(currTime)+" - 数据汇总表.xlsx";
			break;
		case 6:
			name = formatter.format(currTime)+" - 电源汇总表.xlsx";
			break;
		case 7:
			name = formatter.format(currTime)+" - 土建汇总表.xlsx";
			break;
		case 8:
			name = formatter.format(currTime)+" - 网优汇总表.xlsx";
			break;
		}
		exportCre exp = new exportCre();
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename是下载的xlsx的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			exp.export_year(getClass0()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("导出成功！");
		return null;
	}
	
	public String orderCredit(){
		String name = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		switch(getOrder()){
		case 1:
			name = formatter.format(currTime)+" - 综合排序表.xlsx";
			break;
		case 2:
			name = formatter.format(currTime)+" - 公司排序表.xlsx";
			break;
		}
		exportOrder exp = new exportOrder();
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename是下载的xlsx的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			exp.export(getOrder()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("导出成功！");
		return null;
	}
	
//	导出任意的8个季度
	public String exportAny(){
		export_AnyCre exAny = new export_AnyCre();
		String name = getYear()+"year"+getQuarter()+".xlsx";
//		exportOrder exp = new exportOrder();
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ name);// filename是下载的xlsx的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			exAny.exAny(getYear(),getQuarter()).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMessage("导出成功！");
		return null;
	}
	
	 
//	按公司名字查询
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
	
	
//	修改公司名称,通过Excel表格修改
	public String rename(){
		if (file != null) {
			String path = file.getAbsolutePath();// 获取文件的路径
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
		setMessage("修改成功，请到另一个Tab页下载转换格式后的公司名单");
		return "success";
	}
	
//	清空数据库数据
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
			setMessage("清空完成！");
		} catch (SQLException e) {
			System.out.println("清空有误！");
		}
		return "success";
	}
	
	//导入基础数据
	public String importBasic() throws Exception{
//		System.out.println("----------------------------");
		if (file != null) {
			String path = file.getAbsolutePath();// 获取文件的路径
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
				importBasic readFile =new importBasic();
				readFile.read(inputStream,getClass0());
				setMessage("导入成功！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return "success";
	}
	
	
	/*
	 * 
//	修改公司名称，手动修改
	public String rename2(){
		if(getOldname().equals("")){
			setMessage("曾用名不能为空！");
			return "input";
		}
		else if(getNewname().equals("")){
			setMessage("新名称不能为空！");
			return "input";
		}
		else{
			renameImpl rena = new renameImpl();
			if(rena.isExist(getOldname())){
				rena.modify(getOldname(), getNewname());
				setMessage("修改成功！");
			}
			else
				setMessage("输入的曾用名不存在！");
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
