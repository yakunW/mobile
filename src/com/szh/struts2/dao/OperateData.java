package com.szh.struts2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.szh.struts2.bean.Data;

public class OperateData {

	
	
	/* 查询数据库，查看数据库中data表所有的数据*/  
	public static List<Data> query() {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<Data> qlist=new ArrayList<Data>();
	    conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    try {        
	        String sql="SELECT * FROM data ORDER BY id desc  "; 
	        st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
	          
	        rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
	        //System.out.println("最后的查询结果为：");  
	        
	     while (rs.next()) { // 判断是否还有下一个数据  
	              
	            // 根据字段名获取相应的值  
	    	 Data qdata = new Data();//声明必须放在此循环内
	    	 //System.out.println(rs.getString("jmanager"));
	    	    qdata.setId(rs.getInt("id"));
	            qdata.setXid(rs.getString("xid"));  
	            qdata.setName(rs.getString("name"));
	            qdata.setProvince(rs.getString("province"));
	            qdata.setDanwei(rs.getString("danwei"));
	            qdata.setYewu(rs.getString("yewu"));
	            qdata.setJmanager(rs.getString("jmanager"));
	            qdata.setKmanager(rs.getString("kmanager"));
	            qdata.setFzr(rs.getString("fzr"));
	            qdata.setZzy(rs.getString("zzy"));
	            qdata.setSjjd(rs.getString("sjjd"));
	            qdata.setZhulei(rs.getString("zhulei"));
	            qdata.setFenlei(rs.getString("fenlei"));
	            qdata.setXiaolei(rs.getString("xiaolei"));
	            qdata.setFenxiang(rs.getString("fenxiang"));
	            qdata.setChanpin(rs.getString("chanpin"));
	            qdata.setZhuanye(rs.getString("zhuanye"));
	            qdata.setJiliang(rs.getString("jiliang"));
	            qdata.setJdxs(rs.getDouble("jdxs"));
	            qdata.setJishu(rs.getDouble("jishu"));
	            qdata.setXs1(rs.getDouble("xs1"));
	            qdata.setXs2(rs.getDouble("xs2"));
	            qdata.setXs3(rs.getDouble("xs3"));
	            qdata.setGs(rs.getDouble("gs"));
	            qdata.setXzgs(rs.getDouble("xzgs"));
	            qdata.setBjgs(rs.getDouble("bjgs"));
	            qdata.setWygs(rs.getDouble("wygs"));
	            qdata.setHzgs(rs.getDouble("hzgs"));
	            qdata.setDate(rs.getTimestamp("date"));
	            qdata.setDone(rs.getInt("done"));
	         qlist.add(qdata); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("查询数据失败");  
	    }  		  
	    return qlist;
	}  
	/* 查询数据库，查看数据库中newdata表所有的数据*/  
	public static List<Data> querypiliang() {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<Data> qlist=new ArrayList<Data>();
	    conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    try {        
	        String sql="SELECT * FROM newdata ORDER BY id desc  "; 
	        st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
	          
	        rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
	        //System.out.println("最后的查询结果为：");  
	        
	     while (rs.next()) { // 判断是否还有下一个数据  
	              
	            // 根据字段名获取相应的值  
	    	 Data qdata = new Data();//声明必须放在此循环内
	    	 //System.out.println(rs.getString("jmanager"));
	    	    qdata.setId(rs.getInt("id"));
	            qdata.setXid(rs.getString("xid"));  
	            qdata.setName(rs.getString("name"));
	            qdata.setProvince(rs.getString("province"));
	            qdata.setDanwei(rs.getString("danwei"));
	            qdata.setYewu(rs.getString("yewu"));
	            qdata.setJmanager(rs.getString("jmanager"));
	            qdata.setKmanager(rs.getString("kmanager"));
	            qdata.setFzr(rs.getString("fzr"));
	            qdata.setZzy(rs.getString("zzy"));
	            qdata.setSjjd(rs.getString("jieduan"));
	            qdata.setZhulei(rs.getString("zhulei"));
	            qdata.setFenlei(rs.getString("fenlei"));
	            qdata.setXiaolei(rs.getString("xiaolei"));
	            qdata.setFenxiang(rs.getString("fenxiang"));
	            qdata.setChanpin(rs.getString("chanpin"));
	            qdata.setZhuanye(rs.getString("zhuanye"));
	            qdata.setJiliang(rs.getString("jiliang"));
	            qdata.setJdxs(rs.getDouble("jdxs"));
	            qdata.setJishu(rs.getDouble("jishu"));
	            qdata.setXs1(rs.getDouble("xs1"));
	            qdata.setXs2(rs.getDouble("xs2"));
	            qdata.setXs3(rs.getDouble("xs3"));
	            qdata.setGs(rs.getDouble("bzdinge"));
	            qdata.setXzgs(rs.getDouble("tzdinge"));
	            qdata.setBjgs(rs.getDouble("bjgs"));
	            qdata.setWygs(rs.getDouble("wygs"));
	            qdata.setHzgs(rs.getDouble("hzgs"));
	            qdata.setDate(rs.getTimestamp("date"));
	            qdata.setDone(rs.getInt("done"));
	         qlist.add(qdata); 		                
	       } 	       
	        conn.close();   //关闭数据库连接  	          
	    } catch (SQLException e) {  
	        System.out.println("newdata查询数据失败");  
	    }  		  
	    return qlist;
	}  
	
	 public static List<String> queryname()
	    {
		    Connection conn=null;
	        Statement st=null;
	        ResultSet rs=null;
	    	List<String> namelist=new ArrayList<String>();
	    	conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	        try {  
	            String sql = "select distinct chanpin from chanpin ";     // 查询数据的sql语句  
	            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  	              
	            rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
	            //System.out.println("最后的查询结果为：");  
	            
	         while (rs.next()) { // 判断是否还有下一个数据  
	                  
	                // 根据字段名获取相应的值  
	        	    //声明必须放在此循环内
	        	 String name="";
	        	 name=rs.getString("chanpin");
                 namelist.add(name);	               	             	   		                   
	           }           
	            conn.close();   //关闭数据库连接                
	        } catch (SQLException e) {  
	            System.out.println("规则表“chanpin”查询数据失败");  
	        }  
	    	return namelist;
	    }
	 
	 /* 查询数据库，根据条件查看数据库中data表所有的数据*/  
		public static List<Data> query(String date1,String date2,String zhulei,String fenlei,String fenxiang,String chanpin) 
		{  		
		      Connection conn=null;
		      Statement st=null;
		      ResultSet rs=null;

			List<Data> qlist=new ArrayList<Data>();
		    conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
		    try {   
		    	boolean a=false;
		        String sql="SELECT * FROM data  "; 
		        if((date1!=null)&&(date1!=""))
		        {sql=sql+"WHERE date>='"+date1+"'";a=true;}
		        if((date2!=null)&&(date2!=""))
		        {
		        	if(a==false)
		        	{sql+="WHERE date<='"+date2+"'";a=true;}
		        	else
		        	 sql+=" AND date<='"+date2+"'";
		        }
		        if((zhulei!=null)&&(zhulei!=""))
		        {
		        	if(a==false)
		        	{sql+="WHERE zhulei='"+zhulei+"'";a=true;}
		        	else
		        	 sql+=" AND zhulei='"+zhulei+"'";
		        }
		        if((fenlei!=null)&&(fenlei!=""))
		        {
		        	if(a==false)
		        	{sql+="WHERE fenlei='"+fenlei+"'";a=true;}
		        	else
		        	 sql+=" AND fenlei='"+fenlei+"'";
		        }
		        if((fenxiang!=null)&&(fenxiang!=""))
		        {
		        	if(a==false)
		        	{sql+="WHERE fenxiang='"+fenxiang+"'";a=true;}
		        	else
		        	 sql+=" AND fenxiang='"+fenxiang+"'";
		        }
		        if((chanpin!=null)&&(chanpin!=""))
		        {
		        	if(a==false)
		        	{sql+="WHERE chanpin='"+chanpin+"'";a=true;}
		        	else
		        	 sql+=" AND chanpin='"+chanpin+"'";
		        }
		        		
		        		        
		        sql+=" ORDER BY id desc";
		        st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  		          
		        rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
		        //System.out.println("最后的查询结果为：");  
		        
		     while (rs.next()) { // 判断是否还有下一个数据  
		              
		           // 根据字段名获取相应的值  
		    	   Data qdata = new Data();//声明必须放在此循环内
		    	    //System.out.println(rs.getString("jmanager"));
		    	    qdata.setId(rs.getInt("id"));
		            qdata.setXid(rs.getString("xid"));  
		            qdata.setName(rs.getString("name"));
		            qdata.setProvince(rs.getString("province"));
		            qdata.setDanwei(rs.getString("danwei"));
		            qdata.setYewu(rs.getString("yewu"));
		            qdata.setJmanager(rs.getString("jmanager"));
		            qdata.setKmanager(rs.getString("kmanager"));
		            qdata.setFzr(rs.getString("fzr"));
		            qdata.setZzy(rs.getString("zzy"));
		            qdata.setSjjd(rs.getString("sjjd"));
		            qdata.setZhulei(rs.getString("zhulei"));
		            qdata.setFenlei(rs.getString("fenlei"));
		            qdata.setXiaolei(rs.getString("xiaolei"));
		            qdata.setFenxiang(rs.getString("fenxiang"));
		            qdata.setChanpin(rs.getString("chanpin"));
		            qdata.setZhuanye(rs.getString("zhuanye"));
		            qdata.setJiliang(rs.getString("jiliang"));
	                qdata.setJdxs(rs.getDouble("jdxs"));
	                qdata.setJishu(rs.getDouble("jishu"));
		            qdata.setXs1(rs.getDouble("xs1"));
		            qdata.setXs2(rs.getDouble("xs2"));
		            qdata.setXs3(rs.getDouble("xs3"));
		            qdata.setGs(rs.getDouble("gs"));
		            qdata.setXzgs(rs.getDouble("xzgs"));
		            qdata.setBjgs(rs.getDouble("bjgs"));
		            qdata.setDate(rs.getTimestamp("date"));
		         qlist.add(qdata); 		                
		       } 	       
		        conn.close();   //关闭数据库连接  	          
		    } catch (SQLException e) {  
		        System.out.println("查询数据失败");  
		    }  		  
		    return qlist;
		}  
		
		
		/*被SearchXfAction调用--*/
	    public static List<Data> querysXf(int id)
	    {
	    	List<Data> qlist=new ArrayList<Data>();
	    	Connection conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from xfdone where wid=?";     // 查询数据的sql语句  
	            PreparedStatement pst = conn.prepareStatement(sql);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
	            pst.setInt(1, id);

	            
	             
	            rs = pst.executeQuery();    //执行sql查询语句，返回查询数据的结果集  
	            //System.out.println("最后的查询结果为：");  
	            
	         while (rs.next()) { // 判断是否还有下一个数据  
	                  
	                // 根据字段名获取相应的值  
	        	 Data qdata = new Data();//声明必须放在此循环内
	                qdata.setId(rs.getInt("id"));
	                qdata.setFenlei(rs.getString("fenlei"));
	                qdata.setChanpin(rs.getString("chanpin"));
	                qdata.setHj(rs.getString("hj"));
	                qdata.setXf(rs.getString("xf"));
	                qdata.setGsxf(rs.getInt("gsxf"));
	                qdata.setXzgsxf(rs.getInt("xzgsxf"));
	                qdata.setBlxsxf(rs.getFloat("blxsxf"));
	             qlist.add(qdata);               
	           }           
	             pst.close();
	            conn.close();   //关闭数据库连接  
	              
	        } catch (SQLException e) {  
	            System.out.println("查询数据失败");  
	        }  
	    	return qlist;
	    }
	    /*被SXfAction调用--*/
	    public static List<Data> queryXf(String fenlei,String chanpin)
	    {
	    	List<Data> qlist=new ArrayList<Data>();
	    	Connection conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from xf where fenlei=? AND chanpin=?";     // 查询数据的sql语句  
	            PreparedStatement pst = conn.prepareStatement(sql);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
	            pst.setString(1, fenlei.trim());
	            pst.setString(2, chanpin.trim());
	            System.out.println("sql zhixing qian");
	            rs = pst.executeQuery();    //执行sql查询语句，返回查询数据的结果集  
	            System.out.println("sql zhixing");  
	            
	         while (rs.next()) { // 判断是否还有下一个数据  
	                  
	                // 根据字段名获取相应的值  
	        	 Data qdata = new Data();//声明必须放在此循环内
	                qdata.setId(rs.getInt("id"));
	                qdata.setFenlei(rs.getString("fenlei"));
	                qdata.setChanpin(rs.getString("chanpin"));
	                qdata.setHj(rs.getString("hj"));
	                qdata.setXf(rs.getString("xf"));
	                qdata.setGsxf(rs.getInt("gsxf"));
	                //qdata.setXzgsxf(rs.getInt("xzgsxf"));//xf表中没有这两项数据
	               // qdata.setBlxsxf(rs.getFloat("blxsxf"));
	             qlist.add(qdata);               
	           }           
	             pst.close();
	            conn.close();   //关闭数据库连接  
	              
	        } catch (SQLException e) {  
	            System.out.println("queryXf(str,str)查询数据失败");  
	        }  
	    	return qlist;
	    }
		
	    /*被SearchOpAction调用--*/
	    public static Data queryOp(int id)
	    {
	    	Data qdata = new Data();
	    	Connection conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from data where id=?";     // 查询数据的sql语句  
	            PreparedStatement pst = conn.prepareStatement(sql);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
	            pst.setInt(1, id);

	            
	             
	            rs = pst.executeQuery();    //执行sql查询语句，返回查询数据的结果集  
	            //System.out.println("最后的查询结果为：");  
	            
	        if (rs.next()) { // 判断是否还有下一个数据  
	                  
	                // 根据字段名获取相应的值  
	        	 //声明必须放在此循环内
	        	qdata.setId(rs.getInt("id"));
                qdata.setXid(rs.getString("xid"));  
                qdata.setName(rs.getString("name"));
                qdata.setProvince(rs.getString("province"));
                qdata.setDanwei(rs.getString("danwei"));
                qdata.setYewu(rs.getString("yewu"));
                qdata.setJmanager(rs.getString("jmanager"));
                qdata.setKmanager(rs.getString("kmanager"));
                qdata.setFzr(rs.getString("fzr"));
                qdata.setZzy(rs.getString("zzy"));
                qdata.setSjjd(rs.getString("sjjd"));
                qdata.setZhulei(rs.getString("zhulei"));
                qdata.setFenlei(rs.getString("fenlei"));
                qdata.setXiaolei(rs.getString("xiaolei"));
                qdata.setFenxiang(rs.getString("fenxiang"));
                qdata.setChanpin(rs.getString("chanpin"));
                qdata.setZhuanye(rs.getString("zhuanye"));
                qdata.setJiliang(rs.getString("jiliang"));
                qdata.setJdxs(rs.getDouble("jdxs"));
                qdata.setJishu(rs.getDouble("jishu"));
                qdata.setXs1(rs.getDouble("xs1"));
                qdata.setXs2(rs.getDouble("xs2"));
                qdata.setXs3(rs.getDouble("xs3"));
                qdata.setGs(rs.getDouble("gs"));
                qdata.setXzgs(rs.getDouble("xzgs"));
                qdata.setBjgs(rs.getDouble("bjgs"));
                qdata.setWygs(rs.getDouble("wygs"));
                qdata.setHzgs(rs.getDouble("hzgs"));
                qdata.setDone(rs.getInt("done"));

	                	                           
	           }           
	             pst.close();
	            conn.close();   //关闭数据库连接  
	              
	        } catch (SQLException e) {  
	            System.out.println("查询数据失败");  
	        }  
	    	return qdata;
	    }
	    public static int queryID()
	    {
	    	int id=0;
	    	Connection conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select max(id) as id from data";     // 查询数据的sql语句  
	            PreparedStatement pst = conn.prepareStatement(sql);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
	            

	            
	             
	            rs = pst.executeQuery();    //执行sql查询语句，返回查询数据的结果集  
	            //System.out.println("最后的查询结果为：");  
	            
	        if (rs.next()) { // 判断是否还有下一个数据  
	                  
	                // 根据字段名获取相应的值  
	        	 //声明必须放在此循环内
	        	id=rs.getInt("id");                	                           
	           }           
	             pst.close();
	            conn.close();   //关闭数据库连接  
	              
	        } catch (SQLException e) {  
	            System.out.println("querID查询数据失败");  
	        }  
	    	return id;
	    }
		
	    /* 删除符合要求的记录，输出情况*/  
	    public static void delete(String[] check) {  
	         Connection conn=null;
	         PreparedStatement pst = null;   
		     ResultSet rs=null;
	         conn = Database.getConnection(); //同样先要获取连接，即连接到数据库  
	         String sql = "delete from data  where id = ?";// 删除数据的sql语句  
	        try {  
	            
	            pst = conn.prepareStatement(sql); 
	            conn.setAutoCommit(false);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
	            for(int i =0 ;i<check.length;i++){   
	            	 pst.setInt(1, Integer.parseInt(check[i])); 
	                 pst.addBatch();                 
	            } 	           
	            //int count = pst.executeUpdate();// 执行sql删除语句，返回删除数据的数量            
	            pst.executeBatch(); //批量执行   
	            conn.commit();//提交事务 
	           // System.out.println("staff表中删除 " + count + " 条数据\n");    //输出删除操作的处理结果  
	            pst.close();  
	            conn.close();   //关闭数据库连接  
	              
	        } catch (SQLException e) {  
	            System.out.println("删除数据失败");  
	        }  
	          
	    }  
}
