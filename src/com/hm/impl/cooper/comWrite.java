package com.hm.impl.cooper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.hm.entity.company;
import com.hm.org.Database;


public class comWrite {
	
	private List<company> exist = new ArrayList<company>();
	private List<company> noexist = new ArrayList<company>();
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public void write(List<company> com){
		isExist(com);
		if(noexist.size() != 0)
			insert(noexist);
		if(exist.size() != 0)
			update(exist);
		try {
			if (!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("0"+e.getMessage());
		}
	}
	
	
//	判断数据是否已经存在，不存在进行插入，存在进行更新
	public void isExist(List<company> com){
		List<company> sqlList = new ArrayList<company>();
		
		String sql ="select year,quarter,name,province from company";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//			将数据库中的记录存到list中，方便比较
			while(rs.next()){
				company sqlcom = new company();
				sqlcom.setYear(rs.getInt(1));
				sqlcom.setQuarter(rs.getInt(2));
				sqlcom.setName(rs.getString(3));
				sqlcom.setProvince(rs.getString(4));
				sqlList.add(sqlcom);
			}
		} catch (SQLException e) {
			System.out.println("1"+e.getMessage());
		}
//		
//		for(int i = 0; i < com.size(); i++){
//			for(int j = 0; j < sqlList.size(); j++){
//				System.out.print(com.get(i).getYear() == sqlList.get(j).getYear());
//				System.out.print("  ");
//				System.out.print(com.get(i).getQuarter() == sqlList.get(j).getQuarter());
//				System.out.print("  ");
//				System.out.print(com.get(i).getName().equals(sqlList.get(j).getName()));
//				System.out.print("  ");
//				System.out.print(com.get(i).getProvince().equals(sqlList.get(j).getProvince()));
//				System.out.print("  ");
//				System.out.println(com.get(i).getYear()+"   "
//						+ com.get(i).getQuarter() +"  " + com.get(i).getName() + " " +com.get(i).getProvince()+"  "
//						+ sqlList.get(j).getYear()+"  "+
//						sqlList.get(j).getQuarter()+"  "+
//						sqlList.get(j).getName()+"  "+
//						sqlList.get(j).getProvince());
//				if(com.get(i).getYear() == sqlList.get(j).getYear()
//						&& com.get(i).getQuarter() == sqlList.get(j).getQuarter()
//						&& com.get(i).getName().equals(sqlList.get(j).getName())
//						&& com.get(i).getProvince().equals(sqlList.get(j).getProvince())){
//					break;//
//			}
//			
//		}
//		}
		int j ;
		for(int i = 0; i < com.size(); i++){
			j = 0;
			for(j = 0; j < sqlList.size(); j++){
				if(com.get(i).getYear() == sqlList.get(j).getYear()
						&& com.get(i).getQuarter() == sqlList.get(j).getQuarter()
						&& com.get(i).getName().equals(sqlList.get(j).getName())
						&& com.get(i).getProvince().equals(sqlList.get(j).getProvince())){
					exist.add(com.get(i));
					break;//存在，跳出j循环；
				}
			}//for  j
			if(j == sqlList.size())
				noexist.add(com.get(i));//不存在
		}
		
		
//		System.out.println("存在----------------------------------------------------");
//		for(int i = 0; i < exist.size(); i++){
//			System.out.println(exist.get(i).getName());
//		}
//		System.out.println("不存在---------------------------------------------------");
//		for(int i = 0; i < noexist.size(); i++){
//			System.out.println(noexist.get(i).getName());
//		}
	}
	
//	传入的数据不存在的时候进行插入
	public void insert(List<company> noexist){
		String sql = "INSERT INTO company(year,quarter,name,province,sum,trans,wireless,switchx,data,power,civil,network)" +
				" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			for(i = 0; i< noexist.size(); i++){

				ps.setInt(1, noexist.get(i).getYear());
				ps.setInt(2, noexist.get(i).getQuarter());
				ps.setString(3, noexist.get(i).getName());
				ps.setString(4, noexist.get(i).getProvince());
				ps.setDouble(5, noexist.get(i).getSum());
				ps.setDouble(6, noexist.get(i).getTrans());
				ps.setDouble(7, noexist.get(i).getWireless());
				ps.setDouble(8, noexist.get(i).getSwitchx());
				ps.setDouble(9, noexist.get(i).getData());
				ps.setDouble(10, noexist.get(i).getPower());
				ps.setDouble(11, noexist.get(i).getCivil());
				ps.setDouble(12, noexist.get(i).getNetwork());
				ps.executeUpdate();
				
			}
//			if (!ps.isClosed())
//				ps.close();
		}catch(SQLException e){
			System.out.println("2"+e.getMessage());
		}
	}
	
//	传入已存在的数据时，进行Update
	public void update(List<company> exist){
		String sql = "UPDATE company SET sum=?,trans=?,wireless=?,switchx=?,data=?,power=?,civil=?,network=? " +
				"where year=? and quarter=? and name=? and province=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i = 0; i< exist.size(); i++){

				ps.setDouble(1, exist.get(i).getSum());
				ps.setDouble(2, exist.get(i).getTrans());
				ps.setDouble(3, exist.get(i).getWireless());
				ps.setDouble(4, exist.get(i).getSwitchx());
				ps.setDouble(5, exist.get(i).getData());
				ps.setDouble(6, exist.get(i).getPower());
				ps.setDouble(7, exist.get(i).getCivil());
				ps.setDouble(8, exist.get(i).getNetwork());
				ps.setInt(9, exist.get(i).getYear());
				ps.setInt(10, exist.get(i).getQuarter());
				ps.setString(11, exist.get(i).getName());
				ps.setString(12, exist.get(i).getProvince());
				ps.executeUpdate();
				
			}
//			if (!ps.isClosed())
//				ps.close();
		}catch(SQLException e){
			System.out.println("3"+e.getMessage());
		}
	}
	
}
