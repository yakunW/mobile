/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.xxy.struts2.dao.Database;
import com.xxy.struts2.dao.LiebiaoDB;

import com.xxy.struts2.bean.Liebiao;
import com.xxy.struts2.bean.TChanpin;

public class ChanpinAction extends ActionSupport{
	
	private List<TChanpin> tclist;

	public List<TChanpin> getTclist() {
		return tclist;
	}

	public void setTclist(List<TChanpin> tclist) {
		this.tclist = tclist;
	}

	public String Tchanpin() throws Exception{
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		req.setCharacterEncoding("utf-8");
		
		List<String> jString = new ArrayList<String>();
		List<String> zString = new ArrayList<String>();
		
		String chanpin_1 = req.getParameter("chanpin");
		String chanpin = URLDecoder.decode(chanpin_1,"utf-8");
		System.out.println("chanpin:"+chanpin);
		
		jString = (List<String>)LiebiaoDB.queryjl(chanpin);
		System.out.println("jString:"+jString.size());
		
		zString = (List<String>)LiebiaoDB.queryzgs(chanpin);
		System.out.println("zString:"+zString.size());
		
		tclist = new ArrayList<TChanpin>();
		
	    TChanpin tc = new TChanpin(jString.get(0),zString.get(0));
			
	    tclist.add(tc);

		System.out.println(tclist);
		return Action.SUCCESS;
	}
}
