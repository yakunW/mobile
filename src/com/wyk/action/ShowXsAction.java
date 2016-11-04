package com.wyk.action;

import java.util.ArrayList;
import java.util.List;

import com.wyk.bean.StandardXf;
import com.wyk.bean.StandardXs;
import com.wyk.dao.Database;

public class ShowXsAction {
	private String id;
	private StandardXs standardXs;
	private List<StandardXs> standardXsList;
	
	public String showXs(){	
		System.out.println(id);
		String sql="select fid,xs1,xs2,xs3 from xs where fid=?";
		Database database=new Database();
		standardXsList=new ArrayList<StandardXs>();
		try{		
			database.con=database.connect();	
			database.ps=database.con.prepareStatement(sql);
			database.ps.setInt(1, Integer.parseInt(id));
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				System.out.println("xs表有数据");
				standardXs=new StandardXs();
				standardXs.setFid(database.rs.getString("fid"));
				standardXs.setXs1(database.rs.getString("xs1"));
				standardXs.setXs2(database.rs.getString("xs2"));
				standardXs.setXs3(database.rs.getString("xs3"));
				standardXsList.add(standardXs);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StandardXs getStandardXs() {
		return standardXs;
	}

	public void setStandardXs(StandardXs standardXs) {
		this.standardXs = standardXs;
	}

	public List<StandardXs> getStandardXsList() {
		return standardXsList;
	}

	public void setStandardXsList(List<StandardXs> standardXsList) {
		this.standardXsList = standardXsList;
	}

}
