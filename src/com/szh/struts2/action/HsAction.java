package com.szh.struts2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.Database;

public class HsAction extends ActionSupport{
      
	private static final long serialVersionUID = 1L; 
	
	public String hesuan()throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=req.getSession();
		int id=Integer.parseInt((String)session.getAttribute("id"));
		int upcount=(Integer)session.getAttribute("upcount");
		//System.out.println("HsAction中的"+upcount);
		double sum=0,mul=0;
		//System.out.println("HsAction中的"+sum);
//		List<Data> xlist=(List<Data>)session.getAttribute("xdata");
//		
//		for(int i=0;i<xlist.size();i++)
//		{
//			//System.out.println("1输出获取的值"+req.getParameter("100801"));
//			String x=req.getParameter(String.valueOf(xlist.get(i).getId()));
////			System.out.println(""+xlist.get(i).getId()+"");
////			System.out.println("输出获取的值"+x);
////			System.out.println("2输出获取的值"+req.getParameter("100801"));
//			double y=Double.parseDouble(x);
//			sum+=y;
//			
//		}
		
		
		String[] hj=req.getParameterValues("hj");
		String[] xf=req.getParameterValues("xf");
		String[] gsxf=req.getParameterValues("gsxf");
		String[] xzgsxf=req.getParameterValues("xzgsxf");
		String[] bl=req.getParameterValues("bili");
		
		System.out.println(xzgsxf[0]);
		System.out.println(xf[1]);
		

           for(int i=0;i<xzgsxf.length;i++)
           {
        	    double x=Double.parseDouble(bl[i]);
        	    double y=Double.parseDouble(xzgsxf[i]);
        	    sum+=y;
        	    mul+=x*y;//我院占比的工时之和
           }
		
		
	
		String jdxs=req.getParameter("jdxs");
		String jishu=req.getParameter("jishu");
		String xs1=req.getParameter("xs1");
		String xs2=req.getParameter("xs2");
		String xs3=req.getParameter("xs3");
		
		double jd=0,js1=0,x1=0,x2=0,x3=0,bjgs=0,mulvalue=0;
		jd=Double.parseDouble(jdxs);
		js1=Double.parseDouble(jishu);
		x1=Double.parseDouble(xs1);
		x2=Double.parseDouble(xs2);
		x3=Double.parseDouble(xs3);
		
		System.out.println("sum:"+sum+"jdxs"+jd+"jishu:"+js1+"xs1:"+x1+"xs2:"+x2+"xs3:"+x3);
		
		
		bjgs=sum*js1*x1*x2*x3*jd;
		
		String xfchanpin=(String)req.getParameter("xfchanpin");
		String fenlei=(String)req.getParameter("fenlei");
		System.out.println("xfchanpinde zhi"+xfchanpin);
		
		mulvalue=mul*js1*x1*x2*x3*jd;
		System.out.println(sum+"        "+mulvalue);
		
		//先判定done的值为0还是1;
		boolean flag12=Database.queryXfdone(id);
		System.out.println("是否执行了？？？？"+flag12);
		
		if(flag12==true)
		{
			Database.insertxf(id,xfchanpin,fenlei,hj,xf,gsxf,xzgsxf,bl);	
		}
		else
		{
			String[] xfid=req.getParameterValues("xfid");
			Database.updatexf(id,xfchanpin,fenlei,hj,xf,gsxf,xzgsxf,bl,xfid);//xfid是xfdone表中自动增长的id，记录每一条细分数据的编号。
		}
		
		
		int updatecount=Database.update(id,fenlei,xfchanpin, sum, bjgs,x1,x2,x3,js1,jd,mulvalue);//最后的1标志着已经被修改过一次了
		//System.out.println("是否执行了？？？？");
		List<Data> hlist=Database.query(upcount);
		session.setAttribute("data", hlist);
		session.setAttribute("upcount", upcount);
		int count=(Integer)session.getAttribute("count");//上次检查是有错误的数量
	    session.setAttribute("count", (count-updatecount));//经过修正后错误的数量
		
		return "SUCCESS";
	}
}
