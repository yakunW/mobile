package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.OperateData;

public class SearchOpAction extends ActionSupport{
private static final long serialVersionUID = 1L; 
	
	
	public String searchOp()throws Exception{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		//HttpSession session=(HttpSession)req.getSession();
		
		String id=req.getParameter("id");
		
		System.out.println(id);
		Data sdata=OperateData.queryOp(Integer.parseInt(id));
		List<Data> xlist=OperateData.querysXf(Integer.parseInt(id));

		req.setAttribute("sdata", sdata);
		req.setAttribute("sxlist", xlist);
		
		return "SUCCESS";
	}
}
