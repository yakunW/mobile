package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;

import com.szh.struts2.dao.OperateData;

public class SearchAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	public String search()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		
		List<Data> searchList=OperateData.query();
		List<String> nameList=OperateData.queryname();
		
		session.setAttribute("searchList", searchList);
		session.setAttribute("nameList", nameList);
		return "SUCCESS";
	}
	
	
	public String searchpiliang()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		
		List<Data> searchList=OperateData.querypiliang();
		List<String> nameList=OperateData.queryname();
		
		session.setAttribute("searchList", searchList);
		session.setAttribute("nameList", nameList);
		return "SUCCESS";
	}
}
