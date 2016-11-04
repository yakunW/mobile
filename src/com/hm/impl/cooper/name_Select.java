package com.hm.impl.cooper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.hm.entity.company;
import com.hm.org.Database;


public class name_Select {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public List<company> nameSel(String name){
//		System.out.println("Test00"+name);
		List<company> list = new ArrayList<company>();
//		直接查找
		setValue(list,name);
//		查找是否有曾用名
		setValue(list,findName(name));
//		曾用名是否有新名
		setValue(list,findNew(name));
		return list;
	}
	
//	查找旧名称
	public String findName(String name){
		String sql0 = "SELECT oldName FROM company WHERE name=? AND oldName is not null";
//		通过新名称查找旧名称
		PreparedStatement ps0;
		String name0 = null;
		try {
			ps0 = conn.prepareStatement(sql0);
			ps0.setString(1, name);
			ResultSet rs0 = ps0.executeQuery();
			if(rs0.next()){
				name0 = rs0.getString("oldName");
				rs0.close();
				ps0.close();
				return name0;
			}
			else
				return name0;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return name0;
		}
		
	}
	
//	//	查找新名称
	public String findNew(String name){
		String sql0 = "SELECT name FROM company WHERE oldName=? ";
//		通过新名称查找旧名称
		String name0 = null;
		PreparedStatement ps0;
		try {
			ps0 = conn.prepareStatement(sql0);
			ps0.setString(1, name);
			ResultSet rs0 = ps0.executeQuery();
			if(rs0.next()){
				name0 = rs0.getString("name");
				rs0.close();
				ps0.close();
				return name0;
			}
			else
				return name0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return name0;
		}
		
	}
	
//	设置值
	public void setValue(List<company> list,String name){
		String sql = "SELECT * FROM quar_cre WHERE name=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				company com = new company();
				com.setYear(rs.getInt("year"));
				com.setQuarter(rs.getInt("quarter"));
				com.setName(rs.getString("name"));
//				com.setNetworkProvinces(rs.getString("oldName"));
//				com.setNetwork(rs.getFloat("oldName"));
				com.setSumProvinces(rs.getString("sumProvince"));
				com.setSum(rs.getDouble("sumCredit"));
				com.setTransProvinces(rs.getString("transProvince"));
				com.setTrans(rs.getDouble("transCredit"));
				com.setWirelessProvinces(rs.getString("wirelessProvince"));
				com.setWireless(rs.getDouble("wirelessCredit"));
				com.setSwitchxProvinces(rs.getString("SwitchxProvince"));
				com.setSwitchx(rs.getDouble("SwitchxCredit"));
				com.setDataProvinces(rs.getString("dataProvince"));
				com.setData(rs.getDouble("dataCredit"));
				com.setPowerProvinces(rs.getString("powerProvince"));
				com.setPower(rs.getDouble("powerCredit"));
				com.setCivilProvinces(rs.getString("civilProvince"));
				com.setCivil(rs.getDouble("civilCredit"));
				com.setNetworkProvinces(rs.getString("networkProvince"));
				com.setNetwork(rs.getDouble("networkCredit"));
				list.add(com);
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
