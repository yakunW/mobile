package com.hm.org;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public Connection con=null;
	
	public void createConn(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dinge?useUnicode=true&characterEncoding=gbk","root","123");
//			System.out.println("���ӳɹ�");
		}
		catch(Exception e){
			System.out.println("����ʧ��");
		}
	}
	public Connection getConnection(){
		if(con == null){
			createConn();
		}
		return con;
	}	
}
