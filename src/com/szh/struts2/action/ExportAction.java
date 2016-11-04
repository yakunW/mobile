package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;

public class ExportAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	public String exportExcel()throws Exception
	{
		
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		HttpSession session=(HttpSession)req.getSession();
		
			System.out.println("zhixingle");
			List<Data> searchlist=(List<Data>)session.getAttribute("searchList");
			System.out.println("dlist sizes:"+searchlist.size());
			try{
				//Excelexport.exportExcel(searchlist);

				System.out.println("ExportAction: Export Excel success.");}
			catch(Exception e){
				return "Failure";
			}
		
		return "SUCCESS";
	}
	
	public String export()throws Exception
	{
		
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		HttpSession session=(HttpSession)req.getSession();
		
		
		List<Data> dlist=(List<Data>)session.getAttribute("data");	
		System.out.println("dlist sizes:"+dlist.size());
//	//	try{Excelexport.exportExcel(dlist);
//		
//		System.out.println("ExportAction: Export Excel success.");
//		}catch(Exception e){
//			return "Failure";
//		}
//		
		
		
		return "SUCCESS";
	}
}
