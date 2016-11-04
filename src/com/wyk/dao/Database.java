package com.wyk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class Database {
	public Connection con=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	
	
	public Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dinge?useUnicode=true&characterEncoding=gbk","root","123");
			return con;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet query(String sql){
		try{
			this.con=this.connect();
			this.ps=this.con.prepareStatement(sql);
			this.rs=this.ps.executeQuery();
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rs;	
	}
	
	public void close(){
		try{
			if(con!=null){
				con.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(rs!=null){
				rs.close();
			}
		 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public void createTable(List<String> list){
		
		String sql1="create table standard ("+list.get(0).toString().trim()+" varchar(255)) ENGINE=InnoDB DEFAULT CHARSET=gbk";
		String sql2="alter table standard add ? varchar(255)";
		if(list.get(0).equals(null)){
			
		}
		try{
			con=this.connect();
			System.out.println(list.get(0));
			ps=con.prepareStatement(sql1);
			 
//		    ps.setString(1, list.get(0).toString());
		    ps.execute();
		    
		    ps=con.prepareStatement(sql2);
		    
		for(int i=1;i<list.size();i++){
		
				ps.setString(1, list.get(i).toString());
				ps=con.prepareStatement(sql2);
				System.out.println(list.get(i));
			
			ps.executeUpdate();
		}
		}
		catch(Exception e){
			System.out.println("rr");
			e.printStackTrace();
		}
		
	}
	
	

}
