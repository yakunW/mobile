/*
 * author:sunzhenhua,xuxiaoyin 
*/
package com.xxy.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import com.xxy.struts2.dao.Database;
import com.xxy.struts2.dao.LiebiaoDB;

public class DianyuanAction extends ActionSupport{
     

	
	public String queryZl() throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		
		
		
		List<String> xlist=LiebiaoDB.queryzl();
		
		session.setAttribute("zhulei", xlist);
		
		return "SUCCESS";
	}
}
