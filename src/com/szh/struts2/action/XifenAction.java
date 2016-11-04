package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.Database;

public class XifenAction extends ActionSupport{
	private static final long serialVersionUID = 1L; 
	
	
	
	
	public String addXf() throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		
		
		
		String id=req.getParameter("cpid");
		int i=Integer.parseInt(req.getParameter("i"));//showdata页面中动态生成的表格中需要修改的那行的行号
		String upcount1=req.getParameter("upcount");
		int upcount=Integer.parseInt(upcount1);	
		
		
		String[] name=req.getParameterValues("chanpin");//取的是文本框修改后的产品名称
		
		String[] fenlei=req.getParameterValues("fenlei");
		System.out.println("在细分中名称"+name[i]);
		System.out.println(id);
		List<Data> xlist=Database.queryXf(name[i],fenlei[i]);
		
		String[] jdxs=req.getParameterValues("jdxs");
		String[] jishu=req.getParameterValues("jishu");
		String[] xs1=req.getParameterValues("xs1");
		String[] xs2=req.getParameterValues("xs2");
		String[] xs3=req.getParameterValues("xs3");
	
		
		req.setAttribute("jdxs", jdxs[i]);
		req.setAttribute("fenlei", fenlei[i]);
		req.setAttribute("jishu", jishu[i]);
		req.setAttribute("xs1", xs1[i]);
		req.setAttribute("xs2", xs2[i]);
		req.setAttribute("xs3", xs3[i]);
		req.setAttribute("xfchanpin", name[i]);
		
		req.setAttribute("xdata", xlist);//该产品的环节细分列表
		session.setAttribute("id",id);//在数据库表data中的id值
		session.setAttribute("upcount", upcount);//上传的Excel中总的记录条数
		
		
		
		
		
		return "SUCCESS";
	}
}
