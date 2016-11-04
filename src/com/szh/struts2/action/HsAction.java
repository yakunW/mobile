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
		//System.out.println("HsAction�е�"+upcount);
		double sum=0,mul=0;
		//System.out.println("HsAction�е�"+sum);
//		List<Data> xlist=(List<Data>)session.getAttribute("xdata");
//		
//		for(int i=0;i<xlist.size();i++)
//		{
//			//System.out.println("1�����ȡ��ֵ"+req.getParameter("100801"));
//			String x=req.getParameter(String.valueOf(xlist.get(i).getId()));
////			System.out.println(""+xlist.get(i).getId()+"");
////			System.out.println("�����ȡ��ֵ"+x);
////			System.out.println("2�����ȡ��ֵ"+req.getParameter("100801"));
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
        	    mul+=x*y;//��Ժռ�ȵĹ�ʱ֮��
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
		
		//���ж�done��ֵΪ0����1;
		boolean flag12=Database.queryXfdone(id);
		System.out.println("�Ƿ�ִ���ˣ�������"+flag12);
		
		if(flag12==true)
		{
			Database.insertxf(id,xfchanpin,fenlei,hj,xf,gsxf,xzgsxf,bl);	
		}
		else
		{
			String[] xfid=req.getParameterValues("xfid");
			Database.updatexf(id,xfchanpin,fenlei,hj,xf,gsxf,xzgsxf,bl,xfid);//xfid��xfdone�����Զ�������id����¼ÿһ��ϸ�����ݵı�š�
		}
		
		
		int updatecount=Database.update(id,fenlei,xfchanpin, sum, bjgs,x1,x2,x3,js1,jd,mulvalue);//����1��־���Ѿ����޸Ĺ�һ����
		//System.out.println("�Ƿ�ִ���ˣ�������");
		List<Data> hlist=Database.query(upcount);
		session.setAttribute("data", hlist);
		session.setAttribute("upcount", upcount);
		int count=(Integer)session.getAttribute("count");//�ϴμ�����д��������
	    session.setAttribute("count", (count-updatecount));//������������������
		
		return "SUCCESS";
	}
}
