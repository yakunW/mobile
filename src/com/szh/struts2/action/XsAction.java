package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
//import com.szh.struts2.bean.Data;
import com.szh.struts2.bean.Xishu;
import com.szh.struts2.dao.Database;

public class XsAction extends ActionSupport{
     private static final long serialVersionUID = 1L;
	
	public String addXs() throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		String cp=req.getParameter("chanpin");
		String fl=req.getParameter("fenlei");
		
		String chanpin = new String(cp.getBytes("iso-8859-1"),"GBK");
		String fenlei =  new String(fl.getBytes("iso-8859-1"),"GBK");
	//	String chanpin=(String)session.getAttribute("chanpin");
		System.out.println("XsActionÖÐµÄ"+chanpin);	
		List<Xishu> slist=Database.queryXs(chanpin,fenlei);
		session.setAttribute("sdata", slist);
		return "SUCCESS";
	}
	
}
