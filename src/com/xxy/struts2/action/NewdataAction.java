/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.xxy.struts2.dao.NewdataDB;
import com.opensymphony.xwork2.ActionSupport;


public class NewdataAction extends ActionSupport{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
     
	
	public String add() throws Exception{
		
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();

		String xid = req.getParameter("xid");System.out.println("xid:"+xid);
		String name = req.getParameter("name");
		String province = req.getParameter("province");
		String danwei = req.getParameter("danwei");
		String yewu = req.getParameter("yewu");
		String jmanager = req.getParameter("jmanager");
		String kmanager = req.getParameter("kmanager");
		String fzr = req.getParameter("fzr");
		String zzy = req.getParameter("zzy");
		String jieduan = req.getParameter("jieduan");
		double jdxs=Double.parseDouble(req.getParameter("jdxs"));
	    double xmzgs = Double.parseDouble(req.getParameter("xmzgs"));
	    double wyzgs = Double.parseDouble(req.getParameter("wyzgs"));
	    double hzzgs = Double.parseDouble(req.getParameter("hzzgs"));
	    String achanpin[] = req.getParameterValues("cp");
	    String ajiliang[] = req.getParameterValues("jiliang");
	    String axs1[] = req.getParameterValues("guimo");
	    String axs2[] = req.getParameterValues("jishu");
	    String axs3[] = req.getParameterValues("diqu");
	    String abzdinge[] = req.getParameterValues("biaozhun");
	    String atzdinge[] = req.getParameterValues("tiaozheng");
	    String abjgs[] = req.getParameterValues("baojia");
	    String awygs[] = req.getParameterValues("woyuan");
	    String ahzgs[] = req.getParameterValues("hezuo");
	    String azhulei[] = req.getParameterValues("zhulei");
	    String afenlei[] = req.getParameterValues("fenlei");
	    String axiaolei[] = req.getParameterValues("xiaolei");
	    String afenxiang[] = req.getParameterValues("fenxiang");
	    System.out.println("chanpin length:"+achanpin.length);
	    for(int i=0;i<achanpin.length;i++){
	    	String chanpin = achanpin[i];
	    	String jiliang = ajiliang[i];
	    	String zhulei = azhulei[i];
	    	String fenlei = afenlei[i];
	    	String xiaolei = axiaolei[i];
	    	String fenxiang = afenxiang[i];
	    	double xs1 = Double.parseDouble(axs1[i]);
	    	double xs2 = Double.parseDouble(axs2[i]);
	    	double xs3 = Double.parseDouble(axs3[i]);
	    	double bzdinge = Double.parseDouble(abzdinge[i]);
	    	double tzdinge = Double.parseDouble(atzdinge[i]);
	    	double bjgs = Double.parseDouble(abjgs[i]);
	    	double wygs = Double.parseDouble(awygs[i]);
	    	double hzgs = Double.parseDouble(ahzgs[i]);
	    	
	    	NewdataDB.insertND(xid, name, province, danwei, yewu, jmanager, kmanager, fzr, zzy, jieduan, xmzgs, wyzgs, hzzgs, zhulei, fenlei, xiaolei, fenxiang, chanpin, jiliang, xs1, xs2, xs3, bzdinge, tzdinge, bjgs, wygs, hzgs,jdxs);
	    }
	    
	    return "success";
	}
}
