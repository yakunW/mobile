package com.wyk.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wyk.bean.Standard;
import com.wyk.dao.Database;

public class ShowStandardAction {
	private Standard standard;
	public List<Standard> standardList;
	public List<String> zhuleiList;
	public List<String> fenleiList;
	public List<String> xiaoleiList;
	public List<String> fenxiangList;
	public List<String> chanpinList;
	
	public String execute(){
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		    session.setAttribute("zhuleiList", this.getZhulei());
		    session.setAttribute("fenleiList", this.getFenlei());
			session.setAttribute("standardList", this.getStandard());
			session.setAttribute("xiaoleiList", this.getXiaolei());
			session.setAttribute("fenxiangList", this.getFenxiang());
			session.setAttribute("chanpinList", this.getChanpin());
		return "success";
	}
	
	public List getStandard(){
		standardList=new ArrayList<Standard>();
		String sql="select * from chanpin";
		Database database=new Database();
		try{			
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				standard=new Standard();
				standard.setId(database.rs.getInt("id"));
				System.err.println(standard.getId());
				standard.setZhulei(database.rs.getString("zhulei"));
				standard.setFenlei(database.rs.getString("fenlei"));
				standard.setXiaolei(database.rs.getString("xiaolei"));
				standard.setFenxiang(database.rs.getString("fenxiang"));
				standard.setChanpin(database.rs.getString("chanpin"));
				standard.setZhuanye(database.rs.getString("zhuanye"));
				standard.setJiliang(database.rs.getString("jiliang"));
				standard.setZgs(database.rs.getDouble("zgs"));
				standard.setGuimo(database.rs.getDouble("jishu"));
				standardList.add(standard);				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("取所有标准数据出错！");		
		}
		database.close();
		System.out.println("标准数据共有"+standardList.size()+"项");
		return standardList;
	
	}
	
	public String showAllStandard(){
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		session.setAttribute("standardList", this.getStandard());
		return "success";
	}
	
	
	public List getZhulei(){
		zhuleiList=new ArrayList<String>();
		String sql="select distinct zhulei from chanpin";
		Database database=new Database();
		try{		
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				zhuleiList.add(database.rs.getString("zhulei"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
		return zhuleiList;
	}
	
	public List getFenlei(){
		fenleiList=new ArrayList<String>();
		String sql="select distinct fenlei from chanpin";
		Database database=new Database();
		try{		
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				fenleiList.add(database.rs.getString("fenlei"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
		return fenleiList;
	}
	
	public List getXiaolei(){
		xiaoleiList=new ArrayList<String>();
		String sql="select distinct xiaolei from chanpin";
		Database database=new Database();
		try{		
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				xiaoleiList.add(database.rs.getString("xiaolei"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
		return xiaoleiList;
	}
	
	public List getFenxiang(){
		fenxiangList=new ArrayList<String>();
		String sql="select distinct fenxiang from chanpin";
		Database database=new Database();
		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				
				fenxiangList.add(database.rs.getString("fenxiang"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
		return fenxiangList;
	}
	
	public List getChanpin(){
		chanpinList=new ArrayList<String>();
		String sql="select distinct chanpin from chanpin";
		Database database=new Database();
		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				chanpinList.add(database.rs.getString("chanpin"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
		return chanpinList;
	}
	public List<String> getXiaoleiList() {
		return xiaoleiList;
	}

	public void setXiaoleiList(List<String> xiaoleiList) {
		this.xiaoleiList = xiaoleiList;
	}

	public List<String> getFenxiangList() {
		return fenxiangList;
	}

	public void setFenxiangList(List<String> fenxiangList) {
		this.fenxiangList = fenxiangList;
	}

	public List<String> getChanpinList() {
		return chanpinList;
	}

	public void setChanpinList(List<String> chanpinList) {
		this.chanpinList = chanpinList;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public static void main(String[] args){
		ShowStandardAction s=new ShowStandardAction();
//		s.getStandard();
		s.getXiaolei();
	}

	public List<Standard> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}


}

