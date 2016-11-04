package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.OperateData;

public class SxfAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String searchXf(){
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
	//	HttpSession session=req.getSession(); 
		List<Data> xlist;
		
		
		 String id=req.getParameter("id");
		 String sdone=req.getParameter("done");
		 String fenlei=req.getParameter("data.fenlei");
		 String chanpin=req.getParameter("data.chanpin");
		 System.out.println(fenlei+"  "+chanpin+"  "+sdone);
		 int done=Integer.parseInt(sdone);
		 if(done==1){
			 xlist=OperateData.querysXf(Integer.parseInt(id));//查询出已有的细分项
		 }else{
			 xlist=OperateData.queryXf(fenlei,chanpin);//查询规则表中的细分项
		 }
			
		    req.setAttribute("cpid", id);
			req.setAttribute("sxlist", xlist);
		
		return "SUCCESS";
	}
	public String addXf(){
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		String id=req.getParameter("id");//产品的id
		String xfid=req.getParameter("xfid");
		
		
		return "SUCCESS";
	}
}
