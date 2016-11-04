/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



public class NewdataDB {

	public static void insert(String xid, String name, String province, String danwei, String yewu, String jmanager, String kmanager,
			String fzr, String zzy, String jieduan, double xmzgs, double wyzgs, double hzzgs, String zhulei, String fenlei, String xiaolei, String fenxiang, String chanpin, String jiliang, double xs1, double xs2, double xs3, 
			double bzdinge, double tzdinge, double bjgs, double wygs, double hzgs, double jdxs) {  		
	      Connection conn=null;
	      PreparedStatement pst=null;
	      Statement st=null;
	      ResultSet rs=null;
	      conn = Database.getConnection();
	      try{
	    	  String sql ="INSERT INTO newdata(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,jieduan,xmzgs,wyzgs,hzzgs,zhulei,fenlei,xiaolei,fenxiang,chanpin,jiliang,xs1,xs2,xs3,bzdinge,tzdinge,bjgs,wygs,hzgs,date,jdxs,done) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	  pst = conn.prepareStatement(sql);
	    	  pst.setString(1, xid);
	    	  pst.setString(2, name);
	    	  pst.setString(3, province);
	    	  pst.setString(4, danwei);
	    	  pst.setString(5, yewu);
	    	  pst.setString(6, jmanager);
	    	  pst.setString(7, kmanager);
	    	  pst.setString(8, fzr);
	    	  pst.setString(9, zzy);
	    	  pst.setString(10, jieduan);
	    	  pst.setDouble(11, xmzgs);
	    	  pst.setDouble(12, wyzgs);
	    	  pst.setDouble(13, hzzgs);
	    	  pst.setString(14, zhulei);
	    	  pst.setString(15, fenlei);
	    	  pst.setString(16, xiaolei);
	    	  pst.setString(17, fenxiang);
	    	  pst.setString(18, chanpin);
	    	  pst.setString(19, jiliang);
	    	 
	    	  pst.setDouble(20, xs1);
	    	  pst.setDouble(21, xs2);
	    	  pst.setDouble(22, xs3);
	    	  pst.setDouble(23, bzdinge);
	    	  pst.setDouble(24, tzdinge);
	    	  pst.setDouble(25, bjgs);
	    	  pst.setDouble(26, wygs);
	    	  pst.setDouble(27, hzgs);
	    	  System.out.println("bjgs:"+bjgs);
	    	  pst.setTimestamp(28, new Timestamp(System.currentTimeMillis()));
	    	  pst.setDouble(29, jdxs);
	    	  pst.setInt(30, 0);
	    	  
	    	  
	    	  int count = pst.executeUpdate();
	    	  System.out.println("向data表中插入 " + count + " 条数据");
	    	  pst.close();
	    	  conn.close();
	      }catch(SQLException e){
	    	  System.out.println("数据插入错误"+e.getMessage());
	      }
	}
	
	//将数据统一插入到data表中
	public static void insertND(String xid, String name, String province, String danwei, String yewu, String jmanager, String kmanager,
			String fzr, String zzy, String jieduan, double xmzgs, double wyzgs, double hzzgs, String zhulei, String fenlei, String xiaolei, String fenxiang, String chanpin, String jiliang, double xs1, double xs2, double xs3, 
			double bzdinge, double tzdinge, double bjgs, double wygs, double hzgs, double jdxs) {  		
	      Connection conn=null;
	      PreparedStatement pst=null;
	      Statement st=null;
	      ResultSet rs=null;
	      conn = Database.getConnection();
	      try{
	    	  String sql ="INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,sjjd,xmzgs,wyzgs,hzzgs,zhulei,fenlei,xiaolei,fenxiang,chanpin,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,wygs,hzgs,date,jdxs,done) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    	  pst = conn.prepareStatement(sql);
	    	  pst.setString(1, xid);
	    	  pst.setString(2, name);
	    	  pst.setString(3, province);
	    	  pst.setString(4, danwei);
	    	  pst.setString(5, yewu);
	    	  pst.setString(6, jmanager);
	    	  pst.setString(7, kmanager);
	    	  pst.setString(8, fzr);
	    	  pst.setString(9, zzy);
	    	  pst.setString(10, jieduan);
	    	  pst.setDouble(11, xmzgs);
	    	  pst.setDouble(12, wyzgs);
	    	  pst.setDouble(13, hzzgs);
	    	  pst.setString(14, zhulei);
	    	  pst.setString(15, fenlei);
	    	  pst.setString(16, xiaolei);
	    	  pst.setString(17, fenxiang);
	    	  pst.setString(18, chanpin);
	    	  pst.setString(19, jiliang);
	    	 
	    	  pst.setDouble(20, xs1);
	    	  pst.setDouble(21, xs2);
	    	  pst.setDouble(22, xs3);
	    	  pst.setDouble(23, bzdinge);
	    	  pst.setDouble(24, tzdinge);
	    	  pst.setDouble(25, bjgs);
	    	  pst.setDouble(26, wygs);
	    	  pst.setDouble(27, hzgs);
	    	  System.out.println("bjgs:"+bjgs);
	    	  pst.setTimestamp(28, new Timestamp(System.currentTimeMillis()));
	    	  pst.setDouble(29, jdxs);
	    	  pst.setInt(30, 0);
	    	  
	    	  
	    	  int count = pst.executeUpdate();
	    	  System.out.println("向data表中插入 " + count + " 条数据");
	    	  pst.close();
	    	  conn.close();
	      }catch(SQLException e){
	    	  System.out.println("数据插入表data错误"+e.getMessage());
	      }
	}
}
