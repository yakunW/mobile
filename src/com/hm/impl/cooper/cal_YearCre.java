package com.hm.impl.cooper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.hm.entity.company;
import com.hm.org.Database;


public class cal_YearCre {

	private List<company> exist = new ArrayList<company>();
	private List<company> noexist = new ArrayList<company>();
	
	private List<company> comList = new ArrayList<company>();
	private List<company> comList0 = new ArrayList<company>();

	Database db = new Database();
	Connection conn = db.getConnection();
	
	public String calCre(int year,int quarter){
		boolean isExist = false;//判断输入的年份和季度是否存在
		String sql = "SELECT name FROM company WHERE year=? AND quarter=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, quarter);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				isExist = true;
			}
		}catch(SQLException e){
			System.out.println("判断输入的年份和季度是否存在有错误!");
		}
//		System.out.println(isExist);
		if(isExist){
			calSum(year,quarter);
			calTrans(year,quarter);
			calSwitchx(year,quarter);
			calWireless(year,quarter);
			calData(year,quarter);
			calPower(year,quarter);
			calCivil(year,quarter);
			calNetwork(year,quarter);
			return "";
		}
		else
			return "您输入年份季度不存在记录，请重新输入年份季度！";
	}
	
//	综合积分的计算
	public void calSum(int year,int quarter){
//		sql用于计算范围和均值,sql0用于计算省份
		String sql = "SELECT name,AVG(sum) FROM company" +
				" WHERE year=? AND quarter=? AND sum!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE sum!=-1 AND year=? AND quarter=? ORDER BY name";
		
		String insertsql = "INSERT INTO quar_cre(year,quarter,name,sumRange,sumCredit,sumProvince) VALUES(?,?,?,?,?,?)";
		
		String updatesql = " UPDATE quar_cre SET sumRange=?,sumCredit=?,sumProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		isExist(comList);
		
		insert(insertsql);
		update(exist,updatesql);
		
		comList.clear();comList0.clear();
	}
	

	
//	传输积分的计算
	public void calTrans(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(trans) FROM company" +
				" WHERE year=? AND quarter=? AND trans!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE trans!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET transRange=?,transCredit=?,transProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	无线积分的计算
	public void calWireless(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(wireless) FROM company" +
				" WHERE year=? AND quarter=? AND wireless!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE wireless!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET wirelessRange=?,wirelessCredit=?,wirelessProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	交换积分的计算
	public void calSwitchx(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(switchx) FROM company" +
				" WHERE year=? AND quarter=? AND switchx!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE switchx!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET switchxRange=?,switchxCredit=?,switchxProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	数据积分的计算
	public void calData(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(data) FROM company" +
				" WHERE year=? AND quarter=? AND data!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE data!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET dataRange=?,dataCredit=?,dataProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	电源积分的计算
	public void calPower(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(power) FROM company" +
				" WHERE year=? AND quarter=? AND power!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE power!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET powerRange=?,powerCredit=?,powerProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	土建积分的计算
	public void calCivil(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(civil) FROM company" +
				" WHERE year=? AND quarter=? AND civil!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE civil!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET civilRange=?,civilCredit=?,civilProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	网络积分的计算
	public void calNetwork(int year,int quarter){
//		sql用于计算范围和均值
		String sql = "SELECT name,AVG(network) FROM company" +
				" WHERE year=? AND quarter=? AND network!=-1 GROUP BY name ORDER BY name";
		
		String sql0 = "SELECT name,province FROM company WHERE network!=-1 AND year=? AND quarter=? ORDER BY name";
				
		String updatesql = " UPDATE quar_cre SET networkRange=?,networkCredit=?,networkProvince=? WHERE year=? AND quarter=? AND name=?";
		
		cal(year,quarter,sql,sql0);
		
		update(comList,updatesql);
		comList.clear();comList0.clear();
	}
	
//	计算的通用方法
	public void cal(int year,int quarter,String sql,String sql0){
			
			company com = null;
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, year);
				ps.setInt(2, quarter);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					com = new company();
					com.setYear(year);
					com.setQuarter(quarter);
					com.setName(rs.getString(1));
					com.setAvg(rs.getDouble(2));
					comList.add(com);
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("cal的第一个方法有错误！");
			}
			try {
//				省份的计算
				PreparedStatement ps0 = conn.prepareStatement(sql0);
				ps0.setInt(1, year);
				ps0.setInt(2, quarter);
				ResultSet rs0 = ps0.executeQuery();
				String name = null, provinces = null;
				int range = 0;
				while(rs0.next()){
					if(rs0.getString(2).equals("null"))
						provinces = "";
					else
						provinces = rs0.getString(2) + ",";
					if(rs0.getString(1).equals(name)){
						provinces = com.getProvince() + rs0.getString(2) + ",";
						com.setProvince(provinces);
						range++;
						com.setCount(range);
					}
					else{
						range = 0;
						com = new company();
						range++;
						if(provinces.equals(""))
							com.setCount(0);
						else
							com.setCount(range);
						com.setName(rs0.getString(1));
						name = rs0.getString(1);
						com.setProvince(provinces);
						comList0.add(com);
					}
				}
//				System.out.println(comList.size() == comList0.size());
//				for(int i = 0; i< comList0.size(); i++){
//					System.out.println(comList0.get(i).getName().equals(comList.get(i).getName()));
//				}
				rs0.close();
				ps0.close();
			} catch (SQLException e) {
				System.out.println("cal的第二个方法有错误！");
			}
			
		}
	
//  判断记录是否存在
	public void isExist(List<company> comList){
		String sql = "SELECT * FROM quar_cre WHERE year=? AND quarter=? AND name=?";

		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = null;
			for(int i = 0; i < comList.size(); i++){
				ps.setInt(1, comList.get(i).getYear());
				ps.setInt(2, comList.get(i).getQuarter());
				ps.setString(3, comList.get(i).getName());
				comList.get(i).setProvince(comList0.get(i).getProvince());
				comList.get(i).setCount(comList0.get(i).getCount());
				rs = ps.executeQuery();
				if(rs.next()){
					exist.add(comList.get(i));
				}
				else
					noexist.add(comList.get(i));
			}
//			System.out.println(exist.size() + "   noexist:" + noexist.size());
			
//			if (!ps.isClosed())
			rs.close();	
			ps.close();
		} catch (SQLException e) {
			System.out.println("判断记录是否存在有错误!");
		}
	}
	
//	插入数据
	public void insert(String insertsql){
//		System.out.println("insert");
		try {
			PreparedStatement ps = conn.prepareStatement(insertsql);
			for(int i = 0; i < noexist.size(); i++){
				ps.setInt(1, noexist.get(i).getYear());
				ps.setInt(2, noexist.get(i).getQuarter());
				ps.setString(3, noexist.get(i).getName());
				ps.setInt(4, noexist.get(i).getCount());
				if(noexist.get(i).getAvg() < 0)
					ps.setFloat(5, -1);
				else{
					BigDecimal b = new BigDecimal(Double.toString(noexist.get(i).getAvg()));
	    			BigDecimal one = new BigDecimal("1");
	    			double credit= b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue();
					ps.setFloat(5, (float) credit);
				}
				ps.setString(6, noexist.get(i).getProvince());
//				System.out.println(credit+"  "+noexist.get(i).getAvg());
				ps.executeUpdate();
			}
//			if (!ps.isClosed())
				ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
//	更新数据
	public void update(List<company> list,String updatesql){
//		System.out.println("update");
		try {
			PreparedStatement ps = conn.prepareStatement(updatesql);
			for(int i = 0; i < list.size(); i++){
				
				ps.setInt(1, comList0.get(i).getCount());
				BigDecimal b = new BigDecimal(Double.toString(list.get(i).getAvg()));
    			BigDecimal one = new BigDecimal("1");
    			double credit= b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ps.setFloat(2, (float) credit);
				ps.setString(3, comList0.get(i).getProvince());
				ps.setInt(4, list.get(i).getYear());
				ps.setInt(5, list.get(i).getQuarter());
				ps.setString(6, list.get(i).getName());
				ps.executeUpdate();
//				System.out.println("---------------:"+list.get(i).getAvg());
			}
//			if (!ps.isClosed())
				ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
