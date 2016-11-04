package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.OperateData;

public class SchagainAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	public String schagain()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		String date1=req.getParameter("date1");
		String date2=req.getParameter("date2");
		String zhulei=req.getParameter("zhulei");
		String fenlei=req.getParameter("fenlei");
		String fenxiang=req.getParameter("fenxiang");
		String chanpin=req.getParameter("chanpin");
		
		List<Data> againList=OperateData.query(date1, date2, zhulei, fenlei, fenxiang, chanpin);
		
		session.setAttribute("searchList", againList);
		return "SUCCESS";
	}
}
