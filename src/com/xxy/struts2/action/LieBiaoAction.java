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

public class LieBiaoAction extends ActionSupport{
     
	private List<Liebiao> lblist;
	
	public List<Liebiao> getLblist() {
		return lblist;
	}

	public void setLblist(List<Liebiao> lblist) {
		this.lblist = lblist;
	}


	public String Fenlei() throws Exception{
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		req.setCharacterEncoding("utf-8");
		
		List<String> flString = new ArrayList<String>();
		
		String zhulei_1 = req.getParameter("zhulei");
		String zhulei = URLDecoder.decode(zhulei_1,"utf-8");
		System.out.println("第一次zhulei:"+zhulei);
		
		flString = (List<String>)LiebiaoDB.queryfl(zhulei);
		System.out.println("flString:"+flString.size());
		
		lblist = new ArrayList<Liebiao>();
		
		for(int i=0; i<flString.size(); i++){
			Liebiao lb = new Liebiao(i,flString.get(i));
			
			lblist.add(lb);
		}
		System.out.println(lblist);
		return Action.SUCCESS;
	}
	
    public String Xiaolei() throws Exception{
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		req.setCharacterEncoding("utf-8");
		
		List<String> xlString = new ArrayList<String>();
		
		String zhulei_1 = req.getParameter("zhulei");
		String zhulei = URLDecoder.decode(zhulei_1,"utf-8");
		System.out.println("第二次zhulei:"+zhulei);
		
		String fenlei_1 = req.getParameter("fenlei");
		String fenlei = URLDecoder.decode(fenlei_1,"utf-8");
		System.out.println("第二次fenlei:"+fenlei);
		
		xlString = (List<String>)LiebiaoDB.queryxl(zhulei, fenlei);
		
		lblist = new ArrayList<Liebiao>();
		
		for(int i=0; i<xlString.size(); i++){
			Liebiao lb = new Liebiao(i,xlString.get(i));
			
			lblist.add(lb);
		}
		System.out.println(lblist);
		return Action.SUCCESS;
	}
    
    
    public String Fenxiang() throws Exception{
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		req.setCharacterEncoding("utf-8");
		
		List<String> fxString = new ArrayList<String>();
		
		String zhulei_1 = req.getParameter("zhulei");
		String zhulei = URLDecoder.decode(zhulei_1,"utf-8");
		System.out.println("第三次zhulei:"+zhulei);
		
		String fenlei_1 = req.getParameter("fenlei");
		String fenlei = URLDecoder.decode(fenlei_1,"utf-8");
		System.out.println("第三次fenlei:"+fenlei);
		
		String xiaolei_1 = req.getParameter("xiaolei");
		String xiaolei = URLDecoder.decode(xiaolei_1,"utf-8");
		System.out.println("第三次xiaolei:"+xiaolei);
		
		fxString = (List<String>)LiebiaoDB.queryfx(zhulei, fenlei, xiaolei);
		
		lblist = new ArrayList<Liebiao>();
		
		for(int i=0; i<fxString.size(); i++){
			Liebiao lb = new Liebiao(i,fxString.get(i));
			
			lblist.add(lb);
		}
		System.out.println(lblist);
		return Action.SUCCESS;
	}
    
public String Chanpin() throws Exception{

		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		
		req.setCharacterEncoding("utf-8");
		
		List<String> cpString = new ArrayList<String>();
		
		String zhulei_1 = req.getParameter("zhulei");
		String zhulei = URLDecoder.decode(zhulei_1,"utf-8");
		System.out.println("第三次zhulei:"+zhulei);
		
		String fenlei_1 = req.getParameter("fenlei");
		String fenlei = URLDecoder.decode(fenlei_1,"utf-8");
		System.out.println("第三次fenlei:"+fenlei);
		
		String xiaolei_1 = req.getParameter("xiaolei");
		String xiaolei = URLDecoder.decode(xiaolei_1,"utf-8");
		System.out.println("第三次xiaolei:"+xiaolei);
		
		String fenxiang_1 = req.getParameter("fenxiang");
		String fenxiang = URLDecoder.decode(fenxiang_1,"utf-8");
		System.out.println("第四次fenxiang:"+fenxiang);
		
		cpString = (List<String>)LiebiaoDB.querycp(zhulei, fenlei, xiaolei, fenxiang);
		
		lblist = new ArrayList<Liebiao>();
		
		for(int i=0; i<cpString.size(); i++){
			Liebiao lb = new Liebiao(i,cpString.get(i));
			
			lblist.add(lb);
		}
		System.out.println(lblist);
		return Action.SUCCESS;
	}


	
}
