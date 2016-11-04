package com.hm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.org.Database;

public class Judge {
	private List<employee> exist = new ArrayList<employee>();
	private List<employee> noexist = new ArrayList<employee>();
	private List<project> existp = new ArrayList<project>();
	private List<project> noexistp = new ArrayList<project>();
	private String message = "";
	Database db = new Database();
	Connection conn = db.getConnection();
	
	public boolean check(String table, List<employee> emp, List<project> pro,String pc)
			throws Exception {
		String sql1 = "", sql2 = "";
		PreparedStatement ps1 = null, ps2 = null;
		if (table.equals("score")) {
			sql1 = "select proid,name from employee where pc=?";
			sql2 = "select proid from project where pc=?";
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, pc);
			ResultSet rs1 = ps1.executeQuery();
			split("emp", rs1, emp, null);
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, pc);
			ResultSet rs2 = ps2.executeQuery();
			split("pro", rs2, pro, null);
			
			info(table);
		}
		if (table.equals("hour")) {
			sql1 = "select proid,name from employee where pc=?";
			sql2 = "select proid from project where pc=?";
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, pc);
			ResultSet rs1 = ps1.executeQuery();
			split("emp", rs1, emp, null);
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, pc);
			ResultSet rs2 = ps2.executeQuery();
			split("pro", rs2, pro, null);
			
			info(table);
		}
		if (table.equals("pro")) {
			sql1 = "select proid,name from employee where pc=?";
			sql2 = "select proid from project where pc=?";
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, pc);
			ResultSet rs1 = ps1.executeQuery();
			split("emp", rs1, emp, "pro");
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, pc);
			ResultSet rs2 = ps2.executeQuery();
			split("pro", rs2, pro, "pro");		
			
			info(table);
		}
//		System.out.println("check"+this.getMessage());
		
//		if (!ps1.isClosed())
			ps1.close();
//		if (!ps2.isClosed())
			ps2.close();
//		if(!conn.isClosed())
			conn.close();
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void split(String emp, ResultSet rs, List list, String pro)
			throws Exception {
		int i = 0, j = 0;
		String id = "";
		project p = null;
		String name = "";
		employee e = null;
		IdName in = null;
		List<IdName> lin = new ArrayList<IdName>();
		while (rs.next()) {
			in = new IdName();
			id = rs.getString("proid");
			if (emp.equals("emp"))
				name = rs.getString("name");
			// if (rs.getString("name") != null)
			// name = rs.getString("name");
			in.setID(id);
			in.setName(name);
//			lin中为已经存在的
			lin.add(in);
		}
//		lin.size() <= 0数据库中不存在，各自向链表中添加
		if (lin.size() <= 0) {
			for (i = 0; i < list.size(); i++) {
				if (list.get(i) instanceof employee) {
					employee em =(employee)list.get(i);
					if(!noexist.contains(em))
						noexist.add(em);
				}
				if (list.get(i) instanceof project) {
					project py = (project) list.get(i);
					if (!noexistp.contains(py))
						noexistp.add(py);
				}
			}
		} 
//		数据库中存在
		else if (lin.size() > 0) {

			for (i = 0; i < list.size(); i++) {
				for (j = 0; j < lin.size(); j++) {
					if (list.get(i) instanceof employee) {
						e = (employee) list.get(i);
						if (pro == null
								&& e.getProID().equals(lin.get(j).getID())
								&& (e.getName().equals(lin.get(j).getName()) || e
										.getIsLeader() == 1)) {
							exist.add(e);
							break;
						} else if (e.getProID().equals(lin.get(j).getID())
								&& pro != null) {
							exist.add(e);
							break;
						}
					}
					if (list.get(i) instanceof project) {
						p = (project) list.get(i);
						if (p.getProID() != null && lin.get(j).getID() != null
								&& p.getProID().equals(lin.get(j).getID())) {
							existp.add(p);
							break;
						}
					}
				}
				if (j == lin.size()) {
					if (list.get(i) instanceof employee) {
						noexist.add((employee) list.get(i));
					} else if (list.get(i) instanceof project) {
						project px = (project) list.get(i);
						if (px.getProID() != null)
							noexistp.add(px);
					}
				}
			}
		}
		rs.close();
	}

	public void info(String table) {
		int i = 0;
		String sql = "";
		String sql1 = "";
		PreparedStatement ps = null;
		if (table.equals("score")) {
			if (exist.size() > 0) {
				sql = "update employee set scores=? where proID=? and name=? and isleader=? and pc=?";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < exist.size(); i++) {
						ps.setFloat(1, exist.get(i).getScores());
						ps.setString(2, exist.get(i).getProID());
						ps.setString(3, exist.get(i).getName());
						ps.setInt(4, 0);
						ps.setString(5, exist.get(i).getPc());
						ps.executeUpdate();
					}				
				}catch(Exception e){
					System.out.println(e.getMessage());
					message = message + exist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
		}
		if (table.equals("hour")) {
			if (exist.size() > 0) {
				String name = "";
				sql = "update employee set hour=?,prohours=?,name=? where proID=? and name=? and pc=?";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < exist.size(); i++) {
						ps.setFloat(1, exist.get(i).getHour());
						ps.setFloat(2, exist.get(i).getProhours());
						ps.setString(3, exist.get(i).getName());
						ps.setString(4, exist.get(i).getProID());
						if (exist.get(i).getIsLeader() == 1) {
							// ps.setString(5, "jl");
							name = findName(exist.get(i).getProID(),exist.get(i).getPc());
							ps.setString(5, name);
						}
						else
							ps.setString(5, exist.get(i).getName());
						ps.setString(6, exist.get(i).getPc());
//						System.out.println("getName:"+exist.get(i).getName());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + exist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
			if (existp.size() > 0) {
				sql1 = "update project set proName=?,region=?,leaderN=?,hours=?,memberNs=?,memCount=? where proID=? and pc=?";
				try{
					ps = conn.prepareStatement(sql1);
					for (i = 0; i < existp.size(); i++) {
						ps.setString(1, existp.get(i).getProName());
						ps.setString(2, existp.get(i).getRegion());
						ps.setString(3, existp.get(i).getLeaderN());
						ps.setFloat(4, existp.get(i).getHours());
						ps.setString(5, existp.get(i).getMemberNs());
						ps.setFloat(6, existp.get(i).getMemCount());
						ps.setString(7, existp.get(i).getProID());
						ps.setString(8, existp.get(i).getPc());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + existp.get(i).getProID() + "的错误信息为：" +e.getMessage()+"\r\n";
				}
			}
		}
		if (table.equals("score")) {
			if (noexist.size() > 0) {
				sql = "INSERT INTO employee(proID, name,isLeader,scores,pc) VALUES(?,?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < noexist.size(); i++) {
						ps.setString(1, noexist.get(i).getProID());
						ps.setString(2, noexist.get(i).getName());
						ps.setInt(3, noexist.get(i).getIsLeader());
						ps.setFloat(4, noexist.get(i).getScores());
						ps.setString(5, noexist.get(i).getPc());
						ps.executeUpdate();
					}	
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
			if (noexistp.size() > 0) {
				sql1 = "insert into project(proid,proname,leaderN,pc) values(?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql1);
					for (i = 0; i < noexistp.size(); i++) {
						ps.setString(1, noexistp.get(i).getProID());
						ps.setString(2, noexistp.get(i).getProName());
						ps.setString(3, noexistp.get(i).getLeaderN());
						ps.setString(4, noexistp.get(i).getPc());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexistp.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
		}
		if (table.equals("hour")) {
			if (noexist.size() > 0) {
				sql = "INSERT INTO employee(proID, name,isLeader,hour,prohours,pc) VALUES(?,?,?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < noexist.size(); i++) {
						ps.setString(1, noexist.get(i).getProID());
						ps.setString(2, noexist.get(i).getName());
						ps.setInt(3, noexist.get(i).getIsLeader());
						//ps.setFloat(4, noexist.get(i).getScores());
						ps.setFloat(4, noexist.get(i).getHour());
						ps.setFloat(5, noexist.get(i).getProhours());
						ps.setString(6, noexist.get(i).getPc());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
			if (noexistp.size() > 0) {
				sql1 = "INSERT INTO project(proID, proName,region,leaderN,hours,memberNs,memCount,pc) VALUES(?,?,?,?,?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql1);
					for (i = 0; i < noexistp.size(); i++) {
						ps.setString(1, noexistp.get(i).getProID());
						ps.setString(2, noexistp.get(i).getProName());
						ps.setString(3, noexistp.get(i).getRegion());
						ps.setString(4, noexistp.get(i).getLeaderN());
						ps.setFloat(5, noexistp.get(i).getHours());
						ps.setString(6, noexistp.get(i).getMemberNs());
						ps.setInt(7, noexistp.get(i).getMemCount());
						ps.setString(8, noexistp.get(i).getPc());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexistp.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
		}
		if (table.equals("pro")) {
			if (exist.size() > 0) {
				
				sql = "update employee set scores=? where proID=? and isleader=? and pc=?";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < exist.size(); i++) {
						ps.setFloat(1, exist.get(i).getScores());
						ps.setString(2, exist.get(i).getProID());
						ps.setInt(3, 1);
						ps.setString(4, exist.get(i).getPc() );
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + exist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
			if (existp.size() > 0) {
				
				sql1 = "update project set score=? where proID=? and pc=?";
				try{
					ps = conn.prepareStatement(sql1);
					for (i = 0; i < existp.size(); i++) {
						ps.setFloat(1, existp.get(i).getScore());
						ps.setString(2, existp.get(i).getProID());
						ps.setString(3, existp.get(i).getPc());
						ps.executeUpdate();
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + existp.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
		}

		if (table.equals("pro")) {
			if (noexist.size() > 0) {
				sql = "insert into employee(proid,name,scores,isLeader,pc) values(?,?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql);
					for (i = 0; i < noexist.size(); i++) {
						ps.setString(1, noexist.get(i).getProID());
						ps.setString(2, "jl");
						ps.setFloat(3, noexist.get(i).getScores());
						ps.setInt(4, 1);
						ps.setString(5, noexist.get(i).getPc());
						ps.executeUpdate();
					}	
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexist.get(i).getProID() + "的错误信息为：" + e.getMessage()+"\r\n";
				}
			}
			if (noexistp.size() > 0) {
				
				sql1 = "insert into project(proid,proname,region,score,pc) values(?,?,?,?,?)";
				try{
					ps = conn.prepareStatement(sql1);
					for (i = 0; i < noexistp.size(); i++) {
						if (noexistp.get(i).getProID() != null) {
							ps.setString(1, noexistp.get(i).getProID());
							ps.setString(2, noexistp.get(i).getProName());
							ps.setString(3, noexistp.get(i).getRegion());
							ps.setFloat(4, noexistp.get(i).getScore());
							ps.setString(5, noexistp.get(i).getPc());
							ps.executeUpdate();
						}
					}
				}catch(Exception e){
//					System.out.println(e.getMessage());
					message = message + noexistp.get(i).getProID() + "错误信息为："+ e.getMessage()+"\r\n";
				}
			}
		}
		try {
			ps.close();
		} catch (SQLException e) {
			message = message + noexistp.get(i).getProID() + "错误信息为："+"\r\n";
		}
		this.setMessage(message);
//		System.out.println(this.getMessage());
	}

	public String findName(String ID,String pc) throws Exception {
		String sql = "select name from employee where proid=? and isleader=? and pc=?";
		
		PreparedStatement ps1 = conn.prepareStatement(sql);
		String name = "";
		ps1.setString(1, ID);
		ps1.setInt(2, 1);
		ps1.setString(3, pc);
		ResultSet rs = ps1.executeQuery();
		while (rs.next())
			name = rs.getString("name");
//		System.out.println("**********查询结果：***********" + name);
//		if (!ps1.isClosed())
			ps1.close();
		return name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
