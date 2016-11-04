/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xxy.struts2.bean.Data;

public class LiebiaoDB {

	public static List<String> queryzl() {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT distinct zhulei FROM chanpin"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("zhulei")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	}  
	
	public static List<String> queryfl(String zhulei) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT distinct fenlei FROM chanpin where zhulei='"+zhulei+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("fenlei")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	}  
	
	public static List<String> queryxl(String zhulei, String fenlei) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT distinct xiaolei FROM chanpin where zhulei='"+zhulei+"' and fenlei='"+fenlei+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("xiaolei")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	} 
	
	public static List<String> queryfx(String zhulei, String fenlei, String xiaolei) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT distinct fenxiang FROM chanpin where zhulei='"+zhulei+"' and fenlei='"+fenlei+"' and xiaolei='"+xiaolei+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("fenxiang")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	} 
	
	public static List<String> querycp(String zhulei, String fenlei, String xiaolei, String fenxiang) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT distinct chanpin FROM chanpin where zhulei='"+zhulei+"' and fenlei='"+fenlei+"' and xiaolei='"+xiaolei+"' and fenxiang='"+fenxiang+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("chanpin")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	} 
	public static List<String> queryjl(String chanpin) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT jiliang FROM chanpin where chanpin='"+chanpin+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("jiliang")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	}
	public static List<String> queryzgs(String chanpin) {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<String> qlist=new ArrayList<String>();
	    conn = Database.getConnection();
	    try {        
	        String sql="SELECT zgs FROM chanpin where chanpin='"+chanpin+"'"; 
	        st = (Statement) conn.createStatement();   
	        rs = st.executeQuery(sql);   
	        while (rs.next()) {  
	         qlist.add(rs.getString("zgs")); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	}
}
