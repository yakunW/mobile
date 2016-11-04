/*
 * author:sunzhenhua,xuxiaoyin
*/
package com.xxy.struts2.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xxy.struts2.bean.Data;
import com.xxy.struts2.dao.Database;

public class XifenAction extends ActionSupport{
private static final long serialVersionUID = 1L; 
	
	
	
	
	public String addXf() throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		
		
		String name=req.getParameter("chanpin");
		String id=req.getParameter("id");
		String upcount1=req.getParameter("upcount");
		int upcount=Integer.parseInt(upcount1);		
		System.out.println("chanpin:"+name);
		System.out.println(id);
		List<Data> xlist=Database.queryXf(name); 
		
		String xs1=req.getParameter("xs1");
		String xs2=req.getParameter("xs2");
		String xs3=req.getParameter("xs3");
	
		
		
		
		session.setAttribute("xs1", xs1);
		session.setAttribute("xs2", xs2);
		session.setAttribute("xs3", xs3);
		session.setAttribute("xfchanpin", name);
		
		session.setAttribute("xdata", xlist);
		session.setAttribute("id",id);
		session.setAttribute("upcount", upcount);
		

		return "SUCCESS";
	}
	
	public String add() throws Exception{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		String xid = req.getParameter("xid");
		String[] fenlei = req.getParameterValues("fenlei");
		String[] chanpin = req.getParameterValues("chanpin");
		String[] huanjie = req.getParameterValues("huanjie");
		String[] xifen = req.getParameterValues("xifen");
		String[] biaozhun = req.getParameterValues("biaozhun");
		String[] xiuzheng = req.getParameterValues("xiuzheng");
		String[] bili = req.getParameterValues("bili");
		
		List<Integer> xfid=Database.check(xid,fenlei[0], chanpin[0]);
		
		if(xfid.size()>0){
		
		for(int i=0;i<chanpin.length;i++){
			
			double biaozhun1 = Double.parseDouble(biaozhun[i]);
			double xiuzheng1 = Double.parseDouble(xiuzheng[i]);
			float bili1 = Float.parseFloat(bili[i]);
			Database.updateXfData(xfid.get(i), biaozhun1, xiuzheng1, bili1);
		}}
		else{
			for(int i=0;i<chanpin.length;i++){
				String fenlei1 = fenlei[i];
				String chanpin1 = chanpin[i];
				String huanjie1 = huanjie[i];
				String xifen1 = xifen[i];
				double biaozhun1 = Double.parseDouble(biaozhun[i]);
				double xiuzheng1 = Double.parseDouble(xiuzheng[i]);
				float bili1 = Float.parseFloat(bili[i]);
				Database.insertXfData(xid,fenlei1, chanpin1, huanjie1, xifen1, biaozhun1, xiuzheng1, bili1);
			}
		}
		
		return "success";
	}
	
}
