package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.OperateData;

public class DeleteDataAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String deletedata()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		HttpSession session=(HttpSession)req.getSession();
		String[] check=req.getParameterValues("selectdelete"); //获取checkbox的值，存在数组中
		for(String a:check)
			System.out.println(a);
		OperateData.delete(check);
		List<Data> searchList=OperateData.query();
		session.setAttribute("searchList", searchList);
		
		return "SUCCESS";
	}
}
