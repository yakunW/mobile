package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.Database;
import com.szh.struts2.dao.OperateData;

public class SearchXfAction extends ActionSupport{
	private static final long serialVersionUID = 1L; 
	
	
	public String addXf()throws Exception{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		
		String id=req.getParameter("id");
//         System.out.println(id);
		List<Data> xlist=OperateData.querysXf(Integer.parseInt(id));
//		for(Data d:xlist)
//			System.out.println(d.getGsxf());
		
		req.setAttribute("sxlist", xlist);
		
		return "SUCCESS";
	}
}
