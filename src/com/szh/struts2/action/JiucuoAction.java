package com.szh.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.szh.struts2.bean.Data;
import com.szh.struts2.dao.Database;

public class JiucuoAction extends ActionSupport{

	
	
	public String fError() throws Exception
	{
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		//HttpServletResponse rep=(HttpServletResponse)ServletActionContext.getResponse();
		HttpSession session=req.getSession();
//		
//		List<Data> dlist = new ArrayList<Data>();
//        dlist = (List<Data>)session.getAttribute("data");/*��session�л�ȡ�մ�Excel�е��������*/
//        int count=0;
//		for(int i=0;i<dlist.size();i++)
//		{   
//			Database.update(dlist.get(i));
//			if(dlist.get(i).getXid().equals("")||dlist.get(i).getName().equals("")||dlist.get(i).getProvince().equals("")||
//					   dlist.get(i).getDanwei().equals("")||dlist.get(i).getYewu().equals("")||dlist.get(i).getJmanager().equals("")||
//					   dlist.get(i).getKmanager().equals("")||dlist.get(i).getFzr().equals("")||dlist.get(i).getZzy().equals("")||
//					   dlist.get(i).getZhulei().equals("")||dlist.get(i).getFenlei().equals("")||dlist.get(i).getXiaolei().equals("")||
//					   dlist.get(i).getFenxiang().equals("")||dlist.get(i).getChanpin().equals("")||dlist.get(i).getZhuanye().equals("")||
//					   dlist.get(i).getJiliang().equals(""))
//					{
//						dlist.get(i).setFlag(1);
//						count++;
//					}
//			
//			List<Data> qlist=Database.query(dlist.get(i).getChanpin());/*�ԡ���Ʒ��Ϊ��������ѯ�����*/
//			if(qlist.size()==0)
//			{
//				System.out.println("��"+i+"����¼�ڹ�����û�ж�Ӧ��");
//			}
//			else
//		    {
//			for(int j=0;j<qlist.size();i++)
//			 {
//				
//			 }
//		    }
//		}
        
        
        
        
//        for(Data data:dlist)
//        {
//        	System.out.println("������̣�"+data.getJmanager());
//        }
		
		
		
		
		String id=req.getParameter("cpid");
		int i=Integer.parseInt(req.getParameter("i"));//showdataҳ���ж�̬���ɵı������Ҫ�޸ĵ����е��к�
		String upcount1=req.getParameter("upcount");
		int upcount=Integer.parseInt(upcount1);	
		
		
		String[] name=req.getParameterValues("chanpin");//ȡ�����ı����޸ĺ�Ĳ�Ʒ����
		String[] fenlei=req.getParameterValues("fenlei");
		System.out.println("��ϸ��������"+name[i]);
		System.out.println(id);
		
		String[] jdxs=req.getParameterValues("jdxs");
		String[] jishu=req.getParameterValues("jishu");
		String[] xs1=req.getParameterValues("xs1");
		String[] xs2=req.getParameterValues("xs2");
		String[] xs3=req.getParameterValues("xs3");
	
		
		req.setAttribute("jdxs", jdxs[i]);
		req.setAttribute("jishu", jishu[i]);
		req.setAttribute("xs1", xs1[i]);
		req.setAttribute("xs2", xs2[i]);
		req.setAttribute("xs3", xs3[i]);
		req.setAttribute("xfchanpin", name[i]);
		
		int id1=Integer.parseInt(id);
		double jd=Double.parseDouble(jdxs[i]);
		double js=Double.parseDouble(jishu[i]);
		double x1=Double.parseDouble(xs1[i]);
		double x2=Double.parseDouble(xs2[i]);
		double x3=Double.parseDouble(xs3[i]);
		
		
		int updatecount=Database.update(id1,fenlei[i],name[i],jd,js,x1,x2,x3);
		
		
		
		
		List<Data> hlist=Database.query(upcount);
		if((hlist.size()>0)&&(hlist!=null))
		session.setAttribute("data", hlist);
		session.setAttribute("upcount", upcount);
		int count=(Integer)session.getAttribute("count");//�ϴμ�����д��������
	    session.setAttribute("count", (count-updatecount));//������������������
		
		
		
		

		return "SUCCESS";
	}
}
