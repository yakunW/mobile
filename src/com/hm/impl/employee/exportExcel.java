package com.hm.impl.employee;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.org.Database;

public class exportExcel {
	static employee sco = new employee();
	static Database db = new Database();
	static Connection conn = db.getConnection();
	static PreparedStatement ps = null;
	static PreparedStatement ps0 = null;
	static PreparedStatement ps1 = null;
//	static employee em = new employee();
	public static void writeExcel(){
		
	}
	public static List<employee> getWeight(String pc){
		String sql = "select distinct name from employee where pc=?";
    	List<employee> empList = new ArrayList<employee>();
    	List<String> emp = new ArrayList<String>();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, pc);
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()){
	    		emp.add(rs.getString(1));
	    	}
	 /*-------------------------------------------------------------------------------*/
	    	double weight = 0;double sum = 0;	    		
	    	double hours = 0;double base = 8;
	    	employee em1=null;
	    	project pro = new project();
	    	for(int i=0;i<emp.size();i++){
	    		String sql0 = "select * from employee where name=? and pc=?";
		    	ps0 = conn.prepareStatement(sql0);
		    	ResultSet rs0 = null;
	    		sum = 0;
	    		
	    		ps0.setString(1,emp.get(i));
	    		ps0.setString(2,pc);
	    		rs0 = ps0.executeQuery();
//	    		int n1 = 0,n2 = 0;
	    		while(rs0.next()){
//	    			n1++;
	    			em1 = new employee();
	    			em1.setProID(rs0.getString(1));
	    			em1.setName(rs0.getString(2));
	    			em1.setIsLeader(rs0.getInt(3));
	    			em1.setScores(rs0.getFloat(4));
	    			em1.setHour(rs0.getFloat(5));
	    			em1.setProhours(rs0.getFloat(6));
	    			
	    			String sql1 = "select score from project where proID=? and pc=?";
	    	    	try {
	    				ps1 = conn.prepareStatement(sql1);
	    				ps1.setString(1, em1.getProID());
	    				ps1.setString(2, pc);
	    				ResultSet rs1 = null;
	    		    	rs1 = ps1.executeQuery();
	    		    	while(rs1.next()){
	    		    		pro.setScore(rs1.getFloat(1));
	    		    	}
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			}
	    			
	    			weight = 0;
	    			if(em1.getScores()!=0 && pro.getScore()!=0 && em1.getHour()!=0 && em1.getProhours()!=0){
//	    				n2++;
	    				hours = em1.getProhours();
	    				if(em1.getIsLeader()==1){
	    					weight = (em1.getScores()*0.5+pro.getScore()*0.5)*(em1.getHour()/em1.getProhours());
	    		    	}
	    		    	else
	    		    		weight = (em1.getScores()*0.7+pro.getScore()*0.3)*(em1.getHour()/em1.getProhours());
	    			}
	    			sum += weight;
	    		}
	    		
//	    		if(sum != 0 && n1 == n2){
	    		if(sum != 0 ){
	    			BigDecimal b = new BigDecimal(Double.toString(sum));
	    			BigDecimal one = new BigDecimal("1");
	    			double a= b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue();
//	    			System.out.println(em1.getName()+"   "+a);
	    			em1.setSumweight(a);
	    			
	    			BigDecimal b0 = new BigDecimal(Double.toString(hours/base));
	    			BigDecimal one0 = new BigDecimal("1");
	    			float a0=(float) b0.divide(one0,1,BigDecimal.ROUND_HALF_UP).doubleValue();
	    			em1.setProhours(a0);
//	    			System.out.println(em1.getName()+"sum: "+sum+"  hours:"+hours+"  base:"
//	    					+base+"  hours/base:"+hours/base+"  a0:"+a0);
	    			empList.add(em1);
	    		}
	    	}
	    	conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return empList;
	}
}
