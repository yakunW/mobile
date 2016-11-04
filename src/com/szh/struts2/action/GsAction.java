package com.szh.struts2.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class GsAction extends ActionSupport{
  
	
	public String addGs()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		
	//  List<Data> glist=Database
	  return "SUCCESS";	
	}
	
}
