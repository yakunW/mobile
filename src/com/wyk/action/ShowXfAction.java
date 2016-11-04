package com.wyk.action;

import java.util.ArrayList;
import java.util.List;

import com.wyk.bean.StandardXf;
import com.wyk.dao.Database;

public class ShowXfAction {
	private String id;
	private StandardXf standardXf;
	private List<StandardXf> standardXfList;
	
	public String showXf(){	
		System.out.println(id);
		String sql="select fenlei,chanpin,hj,xf,gsxf from xf where fid=? order by id";
		Database database=new Database();
		standardXfList=new ArrayList<StandardXf>();
		try{		
			database.con=database.connect();	
			database.ps=database.con.prepareStatement(sql);
			database.ps.setInt(1, Integer.parseInt(id));
			database.rs=database.ps.executeQuery();
			while(database.rs.next()){
				System.out.println("гаЪ§Он");
				standardXf=new StandardXf();
				standardXf.setFenlei(database.rs.getString("fenlei"));
				standardXf.setChanpin(database.rs.getString("chanpin"));
				standardXf.setHj(database.rs.getString("hj"));
				standardXf.setXf(database.rs.getString("xf"));
				standardXf.setGs(database.rs.getString("gsxf"));
				standardXfList.add(standardXf);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(standardXfList.size());
		return "success";
	}

	public StandardXf getStandardXf() {
		return standardXf;
	}

	public void setStandardXf(StandardXf standardXf) {
		this.standardXf = standardXf;
	}

	public List<StandardXf> getStandardXfList() {
		return standardXfList;
	}

	public void setStandardXfList(List<StandardXf> standardXfList) {
		this.standardXfList = standardXfList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
