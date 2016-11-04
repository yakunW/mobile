package com.szh.struts2.dao;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.PreparedStatement;  
import java.sql.Statement;
//import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import com.szh.struts2.bean.*;

public class Database {

	static Connection conn;  
	  
    static PreparedStatement pst; 
    static Statement st;
    static ResultSet rs;
    static List<Data> list=new ArrayList<Data>();
    //static Data data=new Data();//不能用static的，这样不会产生副本
  
    public static void main(String[] args) {
//    	insert("2012003","中国移动一干项目（北京－呼和浩特）","集团","中国移动","集团区",
//"王翔","尹阳","王怀宇","传输","传统业务","工程设计","通信设备电源"
//,"干线传输站电源","干线传输站电源","交流引入、直流电源、小型UPS"
//        		,"站",11,11,11,34,34,34,new Timestamp(System.currentTimeMillis())); 
    	System.out.println("中国移动一干项目（北京－呼和浩特）");
//插入添加记录  
////        update();   //更新记录数据  
//       delete(1);   //删除记录  

//    	ResultSet rst=query();    //查询记录并显示  
//      try{ while(rst.next())
//       {
//    	   String name=rst.getString("name");
//    	   System.out.println(name);
//    	   
//       }
//      }
//      catch (SQLException e) {  
//          System.out.println("查询数据失败");  
//      }  
    }  
      
    /* 插入数据记录，并输出插入的数据记录数*/  
    public static void insert(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double jishu,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,Timestamp date) {  
          
        conn = getConnection(); // 首先要获取连接，即连接到数据库  
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // 插入数据的sql语句  
              
            pst = conn.prepareStatement(sql);    // 创建用于执行静态sql语句的prepareStatement对象  
            pst.setString(1, xid);
            pst.setString(2, name);
            pst.setString(3, province);
            pst.setString(4, danwei);
            pst.setString(5, yewu);
            pst.setString(6, jmanager);
            pst.setString(7, kmanager);
            pst.setString(8, fzr);
            pst.setString(9, zzy);
            pst.setString(10, zhulei);
            pst.setString(11, fenlei);
            pst.setString(12, xiaolei);
            pst.setString(13, fenxiang);
            pst.setString(14, chanpin);
            pst.setString(15, zhuanye);
            pst.setString(16, jiliang);
            pst.setDouble(17, xs1);
            pst.setDouble(18, xs2);
            pst.setDouble(19, xs3);
            pst.setDouble(20, gs);
            pst.setDouble(21, xzgs);
            pst.setDouble(22, bjgs);
            pst.setTimestamp(23, date);
            
                
            int count =pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数  
              
            System.out.println("向data表中插入 " + count + " 条数据"); //输出插入操作的处理结果  
            
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("插入数据失败" + e.getMessage());  
        }  
    }  
    /* 插入数据记录，并输出插入的数据记录数*/
    public static void insert1(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang, double xs1,double xs2,double xs3,double gs,Timestamp date) {  
          
        conn = getConnection(); // 首先要获取连接，即连接到数据库  
  
        try {  
//            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
//            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,jishu,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
//                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // 插入数据的sql语句  
//            
            String sql1="INSERT into data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,"+
            "zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"+
            		"SELECT ?,?,?,?,?,?,?,?,?,c.zhulei,?,c.xiaolei,c.fenxiang,?,c.zhuanye,c.jiliang,"+
            "?,?,?,c.zgs,c.zgs,c.zgs*"+xs1+"*"+xs2+"*"+xs3+",? FROM chanpin c where fenlei='"+fenlei+"' AND chanpin='"+chanpin+"'";
            
            
            pst = conn.prepareStatement(sql1);    // 创建用于执行静态sql语句的prepareStatement对象  
            pst.setString(1, xid);
            pst.setString(2, name);
            pst.setString(3, province);
            pst.setString(4, danwei);
            pst.setString(5, yewu);
            pst.setString(6, jmanager);
            pst.setString(7, kmanager);
            pst.setString(8, fzr);
            pst.setString(9, zzy);
            pst.setString(10, fenlei);
            pst.setString(11, chanpin);
            pst.setDouble(12, xs1);
            pst.setDouble(13, xs2);
            pst.setDouble(14, xs3);
            pst.setTimestamp(15, date);                           
            int count =pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数                
            System.out.println("向data表中插入 " + count + " 条数据"); //输出插入操作的处理结果  
            
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("插入数据失败" + e.getMessage());  
        }  
    }  
    /* 插入数据记录，并输出插入的数据记录数--被UpAction---ExcelDataReader类调用*/  
    public static void insert2(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,String sjjd,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double jdxs,double jishu,double xs1,double xs2,double xs3,double gs,Timestamp date,int done) {  
          
        conn = getConnection(); // 首先要获取连接，即连接到数据库  
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,sjjd," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,jdxs,jishu,xs1,xs2,xs3,gs,xzgs,bjgs,wygs,hzgs,date,done)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // 插入数据的sql语句  
              
            pst = conn.prepareStatement(sql);    // 创建用于执行静态sql语句的prepareStatement对象  
            pst.setString(1, xid);
            pst.setString(2, name);
            pst.setString(3, province);
            pst.setString(4, danwei);
            pst.setString(5, yewu);
            pst.setString(6, jmanager);
            pst.setString(7, kmanager);
            pst.setString(8, fzr);
            pst.setString(9, zzy);
            pst.setString(10, sjjd);
            pst.setString(11, zhulei);
            pst.setString(12, fenlei);
            pst.setString(13, xiaolei);
            pst.setString(14, fenxiang);
            pst.setString(15, chanpin);
            pst.setString(16, zhuanye);
            pst.setString(17, jiliang);
            pst.setDouble(18, jdxs);
            pst.setDouble(19, jishu);
            pst.setDouble(20, xs1);
            pst.setDouble(21, xs2);
            pst.setDouble(22, xs3);
            pst.setDouble(23, gs);
            pst.setDouble(24, gs);
            pst.setDouble(25, gs);
            pst.setDouble(26, gs);
            pst.setDouble(27, gs);
            pst.setTimestamp(28, date);
            pst.setInt(29,done);
            
                
            int count =pst.executeUpdate();  // 执行插入操作的sql语句，并返回插入数据的个数  
              
            System.out.println("向data表中插入 " + count + " 条数据"); //输出插入操作的处理结果  
            
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("插入数据失败" + e.getMessage());  
        }  
    }  
    /* 插入数据记录，数据记录为工时的细分项--被HsAction类调用*/ 
    public static void insertxf(int wid,String chanpin,String fenlei,String[] hj,String[] xf,String[] gsxf,String[] xzgsxf,String[] bl){
    	System.out.println("数据库插入insertxf : "+fenlei+chanpin);
    	conn=getConnection();
    	PreparedStatement pst;
    	try{
    		String sql="insert into xfdone (wid,fenlei,chanpin,hj,xf,gsxf,xzgsxf,blxsxf) values (?,?,?,?,?,?,?,?)";
    		for(int i=0;i<xf.length;i++){
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, wid);
    		pst.setString(2, fenlei.trim());
    		pst.setString(3, chanpin.trim());
    		pst.setString(4, hj[i]);
    		pst.setString(5, xf[i]);
    		pst.setString(6, gsxf[i]);
    		pst.setString(7, xzgsxf[i]);
    		pst.setString(8, bl[i]);
    	
    		
    		pst.executeUpdate();
    		pst.close(); 
    		
    		}
    		 
            conn.close();
    	}catch (SQLException e) {  
            System.out.println("插入数据失败" + e.getMessage());  
        }  
    }
    
      
    /* 更新符合要求的记录，并返回更新的记录数目*/  
    public static void update(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,int id) {  
        
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  // 更新数据的sql语句  
              
            pst =conn.prepareStatement(sql);    //创建用于执行静态sql语句的Statement对象，pst属局部变量  
            pst.setString(1, xid);
            pst.setString(2, name);
            pst.setString(3, province);
            pst.setString(4, danwei);
            pst.setString(5, yewu);
            pst.setString(6, jmanager);
            pst.setString(7, kmanager);
            pst.setString(8, fzr);
            pst.setString(9, zzy);
            pst.setString(10, zhulei);
            pst.setString(11, fenlei);
            pst.setString(12, xiaolei);
            pst.setString(13, fenxiang);
            pst.setString(14, chanpin);
            pst.setString(15, zhuanye);
            pst.setString(16, jiliang);
            pst.setDouble(17, xs1);
            pst.setDouble(18, xs2);
            pst.setDouble(19, xs3);
            pst.setDouble(20, gs);
            pst.setDouble(21, xzgs);
            pst.setDouble(22, bjgs);
            pst.setInt(23, id);
              
            int count = pst.executeUpdate();// 执行更新操作的sql语句，返回更新数据的个数  
              
            System.out.println("staff表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("更新数据失败");  
        }  
    }  
    /* 更新符合要求的记录，将核算好的工时填入data表中*/  
    public static void update(Data data) {  
        
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  // 更新数据的sql语句  
              
            PreparedStatement pst =conn.prepareStatement(sql);
            //创建用于执行静态sql语句的Statement对象，pst属局部变量  
//            System.out.println(data.getJmanager());
//            System.out.println(data.getGs());
            pst.setString(1, data.getXid());
            pst.setString(2, data.getName());
            pst.setString(3, data.getProvince());
            pst.setString(4, data.getDanwei());
            pst.setString(5, data.getYewu());
            pst.setString(6, data.getJmanager());
            pst.setString(7, data.getKmanager());
            pst.setString(8, data.getFzr());
            pst.setString(9, data.getZzy());
            pst.setString(10, data.getZhulei());
            pst.setString(11, data.getFenlei());
            pst.setString(12, data.getXiaolei());
            pst.setString(13, data.getFenxiang());
            pst.setString(14, data.getChanpin());
            pst.setString(15, data.getZhuanye());
            pst.setString(16, data.getJiliang());
            pst.setDouble(17, data.getXs1());
            pst.setDouble(18, data.getXs2());
            pst.setDouble(19, data.getXs3());
            pst.setDouble(20, data.getGs());
            pst.setDouble(21, data.getXzgs());
            pst.setDouble(22, data.getBjgs());
            pst.setInt(23, data.getId());
//            System.out.println(data.getId());
              
            int count = pst.executeUpdate();// 执行更新操作的sql语句，返回更新数据的个数  
              
            System.out.println("staff表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("更新数据失败");  
        }  
    }  
    /*-----------被UpAction调用-----------*/
    public static int update(List<Data> dlist) {  
        
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        int count=0,sum=0;
        String sql = "update data d,chanpin c set d.zhulei=c.zhulei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
        		"d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs,d.xzgs=c.zgs,d.bjgs=(c.zgs*d.jdxs*d.jishu*d.xs1*d.xs2*d.xs3),d.wygs=d.bjgs,d.hzgs=? "+
        		" where d.id=? and c.chanpin=? and c.fenlei=?";  // 更新数据的sql语句   
          

        try { 
            for(Data data:dlist)
            {System.out.println(data.getId());
        	
        	PreparedStatement pst =conn.prepareStatement(sql);
            //创建用于执行静态sql语句的Statement对象，pst属局部变量  
//            System.out.println(data.getJmanager());
//            System.out.println(data.getGs());
        	   pst.setDouble(1, 0);
               pst.setInt(2, data.getId());
               pst.setString(3, data.getChanpin());
               pst.setString(4, data.getFenlei());
              

//            System.out.println(data.getId());
              
             count = pst.executeUpdate();// 执行更新操作的sql语句，返回更新数据的个数  
             sum+=count; 
            System.out.println("staff表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
            
            pst.close();  }
            
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("更新数据失败");  
        }  
            return sum;
    }  
    /*-----------被HsAction调用--------------------*/
public static int update(int id,String fenlei,String chanpin,double xzgs,double bjgs,double x1,double x2,double x3,double jishu,double jdxs, double mul) {  
        
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        int sum=0;
        String sql = "update data d,chanpin c set d.done=1,d.xzgs=? , d.bjgs=? , d.xs1=? , d.xs2=? ," +
        		" d.xs3=? ,d.jishu=?,d.jdxs=?, d.zhulei=c.zhulei,d.fenlei=c.fenlei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
        		"d.chanpin=c.chanpin,d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs,d.wygs=? ,d.hzgs=? " +
        		" where d.id=? and c.chanpin=? and c.fenlei=?"; 
        try {  
          // 更新数据的sql语句  
              
            PreparedStatement pst =conn.prepareStatement(sql);
            //创建用于执行静态sql语句的Statement对象，pst属局部变量  
//            System.out.println(data.getJmanager());
//            System.out.println(data.getGs());

            pst.setDouble(1,xzgs);
            pst.setDouble(2, bjgs);  
            pst.setDouble(3, x1); 
            pst.setDouble(4, x2);
            pst.setDouble(5, x3); 
            pst.setDouble(6, jishu); 
            pst.setDouble(7, jdxs);
            pst.setDouble(8, mul);
            pst.setDouble(9, (bjgs-mul));
            pst.setInt(10, id);
            pst.setString(11, chanpin.trim());
            pst.setString(12, fenlei.trim());
            

              
            int count = pst.executeUpdate();// 执行更新操作的sql语句，返回更新数据的个数  
            sum+=count; 
            System.out.println("data表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
            System.out.println("data表中更新的id为：  " + id ); 
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("更新数据失败");  
        }  
        return sum;
    }  
/*-----------被JiuCuoAction调用--------------------*/
public static int update(int id,String fenlei,String chanpin,double jdxs,double jishu,double x1,double x2,double x3) {  
    
    conn = getConnection(); //同样先要获取连接，即连接到数据库  
    int sum=0;
    String sql = "update data d,chanpin c set  d.xs1=? , d.xs2=? ," +
    		" d.xs3=? ,d.jishu=?,d.jdxs=?, d.zhulei=c.zhulei,d.fenlei=c.fenlei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
    		"d.chanpin=c.chanpin,d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs ,d.xzgs=c.zgs , d.bjgs=(c.zgs*d.jdxs*d.jishu*d.xs1*d.xs2*d.xs3),d.wygs=d.bjgs" +
    		" where d.id=? and c.chanpin=? and c.fenlei=?"; 
    try {  
      // 更新数据的sql语句  
          
        PreparedStatement pst =conn.prepareStatement(sql);
        //创建用于执行静态sql语句的Statement对象，pst属局部变量  
//        System.out.println(data.getJmanager());
//        System.out.println(data.getGs());

 
        pst.setDouble(1, x1); 
        pst.setDouble(2, x2);
        pst.setDouble(3, x3); 
        pst.setDouble(4, jishu); 
        pst.setDouble(5, jdxs); 
        pst.setInt(6, id);
        pst.setString(7, chanpin);
        pst.setString(8, fenlei);
//        System.out.println(data.getId());
          
        int count = pst.executeUpdate();// 执行更新操作的sql语句，返回更新数据的个数  
        sum+=count;   
        System.out.println("staff表中更新 " + count + " 条数据");      //输出更新操作的处理结果  
        System.out.println("staff表中更新的id为：  " + id ); 
        pst.close();  
        conn.close();   //关闭数据库连接  
          
    } catch (SQLException e) {  
        System.out.println("更新数据失败");  
    }  
    return sum;
}  

/* 更新数据记录，数据记录为工时的细分项--被HsAction类调用*/ 
public static void updatexf(int wid,String chanpin,String fenlei,String[] hj,String[] xf,String[] gsxf,String[] xzgsxf,String[] bl,String[] xfid){
	
	conn=getConnection();
	
	try{
		String sql="update xfdone  set gsxf=?,xzgsxf=?,blxsxf=? where wid=? and id=?";
		for(int i=0;i<xf.length;i++){
	    PreparedStatement  pst=conn.prepareStatement(sql);
		
		
		pst.setString(1, gsxf[i]);
		pst.setString(2, xzgsxf[i]);
		pst.setString(3, bl[i]);
		pst.setInt(4, wid);
		pst.setInt(5, Integer.parseInt(xfid[i]));
		
		
		pst.executeUpdate();
		
		pst.close();  
        
		}
		conn.close();
	}catch (SQLException e) {  
        System.out.println("更新数据失败" + e.getMessage());  
    }  
}


    /* 查询数据库，输出刚从Excel导入的数据记录------被UpAcion调用了2次----被JiucuoAction调用一次---被HsAction调用一次-*/  
    public static List<Data> query(int count1) {  
    	
    	List<Data> qlist=new ArrayList<Data>();
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
          //  String sql1 = "SELECT d.id,d.xid,d.name,d.province,d.danwei,d.yewu,d.jmanager,d.kmanager,d.fzr,d.zzy,d.zhulei,d.fenlei,d.xiaolei,d.fenxiang,d.chanpin,d.zhuanye,d.jiliang,d.xs1,d.xs2,d.xs3,c.zgs from data d,chanpin c where d.chanpin=c.chanpin ORDER BY d.id desc LIMIT "+count1;     // 查询数据的sql语句  
          //  String sql2="SELECT id,xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs from data ORDER BY id desc LIMIT "+count1; 
            String sql="SELECT * FROM data ORDER BY id desc LIMIT "+count1; 
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
                qdata.setDone(rs.getInt("done"));
             qlist.add(qdata); 
   		     
               
           } 
           
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("查询数据失败");  
        }  

		  
        return qlist;
    }  
    
    /*根据给定的产品名称，来查找“规则”数据库表，进行纠错*/
    public static List<Data> query(String str)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select * from chanpin where chanpin='"+str+"'";     // 查询数据的sql语句  
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
              
            rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
            //System.out.println("最后的查询结果为：");  
            
         while (rs.next()) { // 判断是否还有下一个数据  
                  
                // 根据字段名获取相应的值  
        	 Data qdata = new Data();//声明必须放在此循环内
        	 System.out.println(rs.getString("chanpin"));

                qdata.setZhulei(rs.getString("zhulei"));
                qdata.setFenlei(rs.getString("fenlei"));
                qdata.setXiaolei(rs.getString("xiaolei"));
                qdata.setFenxiang(rs.getString("fenxiang"));
                qdata.setChanpin(rs.getString("chanpin"));
                qdata.setZhuanye(rs.getString("zhuanye"));
                qdata.setJiliang(rs.getString("jiliang"));
                qdata.setGs(rs.getDouble("zgs"));

             qlist.add(qdata); 
   		                   
           }           
            conn.close();   //关闭数据库连接                
        } catch (SQLException e) {  
            System.out.println("规则表“chanpin”查询数据失败");  
        }  
    	return qlist;
    }
    
    
    /*根据给定的fid编号，fid是根据产品编号来的，查看环节细分--被XifenAction调用--*/
    public static List<Data> queryXf(String str,String fenlei)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select * from xf where chanpin='"+str+"' AND fenlei='"+fenlei+"'";     // 查询数据的sql语句  
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
             
            rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
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
                qdata.setXzgsxf(rs.getInt("gsxf"));
                qdata.setBlxsxf(1.0f);
             qlist.add(qdata);               
           }            
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("查询数据失败");  
        }  
    	return qlist;
    }
    /*根据给定的fid编号，fid是根据产品编号来的，查看环节细分--被XifenAction调用--*/
    public static List<Data> queryXf2(String str,String fenlei,int id)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select * from xfdone where wid=?";     // 查询数据的sql语句  
            pst = conn.prepareStatement(sql);  //创建用于执行静态sql语句的Statement对象，st属局部变量  
            pst.setInt(1, id);
          //  pst.setString(2, str.trim());
          //  pst.setString(3, fenlei.trim());
            
             
            rs = pst.executeQuery();    //执行sql查询语句，返回查询数据的结果集  
            //System.out.println("最后的查询结果为：");  
            
         while (rs.next()) { // 判断是否还有下一个数据  
                  
                // 根据字段名获取相应的值  
        	 Data qdata = new Data();//声明必须放在此循环内
                qdata.setId(rs.getInt("id"));//这个id是xfdone表里的id号，与data表的id是不一样的。
                //qdata.setXfid(rs.getInt("xfid"));
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
    /*根据给定的fid编号，fid是根据产品编号来的，查看环节细分*/
    public static List<Xishu> queryXs(String xs1 ,String xs2)
    {
    	List<Xishu> qlist=new ArrayList<Xishu>();
    	conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select * from xs where chanpin='"+xs1+"' AND fenlei='"+xs2+"'";     // 查询数据的sql语句  
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
             
            rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
            //System.out.println("最后的查询结果为：");             
         while (rs.next()) { // 判断是否还有下一个数据  
                  
                // 根据字段名获取相应的值  
        	Xishu xs=new Xishu();//声明必须放在此循环内        
        	xs.setFenlei(rs.getString("fenlei"));
        	xs.setChanpin(rs.getString("chanpin"));
        	xs.setXs1(rs.getString("xs1"));
        	
        	xs.setXs2(rs.getString("xs2"));
        	
        	xs.setXs3(rs.getString("xs3"));
        	               
             qlist.add(xs);               
           }            
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("查询数据失败");  
        }  
    	return qlist;
    }
    
    /*根据给定的fid编号，fid是根据产品编号来的，查看环节细分*/
    public static boolean queryXfdone(int id)
    {
    	List<Xishu> qlist=new ArrayList<Xishu>();
    	boolean flag=false;
    	conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "select * from xfdone where wid="+id;     // 查询数据的sql语句  
            st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量  
             
            rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集  
            //System.out.println("最后的查询结果为：");   
            
         if (!rs.next())
        	{
        	 flag=true;
        	}
         
         
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("查询数据失败");  
        }  
    	return flag;
    }
    
    /* 删除符合要求的记录，输出情况*/  
    public static void delete(int id) {  
  
        conn = getConnection(); //同样先要获取连接，即连接到数据库  
        try {  
            String sql = "delete from data  where id = ?";// 删除数据的sql语句  
            pst = conn.prepareStatement(sql);    //创建用于执行静态sql语句的Statement对象，st属局部变量  
            pst.setInt(1, id);
            int count = pst.executeUpdate();// 执行sql删除语句，返回删除数据的数量               
            System.out.println("staff表中删除 " + count + " 条数据\n");    //输出删除操作的处理结果  
            pst.close();  
            conn.close();   //关闭数据库连接  
              
        } catch (SQLException e) {  
            System.out.println("删除数据失败");  
        }  
          
    }  
      
    /* 获取数据库连接的函数*/  
    public static Connection getConnection() {  
            Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动  
              
            con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/dinge?useUnicode=true&characterEncoding=gbk", "root", "123");// 创建数据连接 
            
              
        } catch (Exception e) {  
            System.out.println("数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
    }  
	
	
}
