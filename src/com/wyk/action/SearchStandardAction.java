package com.wyk.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wyk.bean.Standard;
import com.wyk.dao.Database;

public class SearchStandardAction {
	private Standard standard;
	private List<Standard> standardList;
	private String zhulei;
	private String fenlei;
	private String xiaolei;
	private String fenxiang;
	private String chanpin;
	

	public String searchStandard(){
		List<String> key=new ArrayList<String>();
		List<String> value=new ArrayList<String>();
		standardList=new ArrayList<Standard>();
		String sql="select * from chanpin where";
		
		if(null!=zhulei&&zhulei!=""&&zhulei.length()!=0){
			key.add("zhulei");
			value.add(zhulei);
		}
		if(null!=fenlei&&fenlei!=""&&fenlei.length()!=0){
			key.add("fenlei");
			value.add(fenlei);
		}
		if(null!=xiaolei&&xiaolei!=""&&xiaolei.length()!=0){
			key.add("xiaolei");
			value.add(xiaolei);
		}
		if(null!=fenxiang&&fenxiang!=""&&fenxiang.length()!=0){
			key.add("fenxiang");
			value.add(fenxiang);
		}
		if(null!=chanpin&&chanpin!=""&&chanpin.length()!=0){
			key.add("chanpin");
			value.add(chanpin);
		}
		if(key.size()==0){
			return "input";
		}
		else if(key.size()==1){
			sql=sql+" "+key.get(0)+"='"+value.get(0)+"'";
		}
		else{
			sql=sql+" "+key.get(0)+"='"+value.get(0)+"'";
			for(int i=1;i<key.size();i++){
				sql=sql+" and "+key.get(i)+"='"+value.get(i)+"'";
			}
		}
		System.out.println(sql);
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
		}	
		database.close();
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		HttpSession session=(HttpSession)req.getSession();
		session.setAttribute("standardList", standardList);
		
		return "success";
		
	}
	
	public Standard getStandard() {
		return standard;
	}


	public void setStandard(Standard standard) {
		this.standard = standard;
	}


	public List<Standard> getstandardList() {
		return standardList;
	}


	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}


	public String getZhulei() {
		return zhulei;
	}


	public void setZhulei(String zhulei) {
		this.zhulei = zhulei;
	}


	public String getFenlei() {
		return fenlei;
	}


	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}


	public String getXiaolei() {
		return xiaolei;
	}


	public void setXiaolei(String xiaolei) {
		this.xiaolei = xiaolei;
	}


	public String getFenxiang() {
		return fenxiang;
	}


	public void setFenxiang(String fenxiang) {
		this.fenxiang = fenxiang;
	}


	public String getChanpin() {
		return chanpin;
	}


	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
	}


	
}
