/*
 * author:sunzhenhua, xuxiaoyin
*/
package com.xxy.struts2.dao;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.PreparedStatement;  
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import com.xxy.struts2.bean.*;

public class Database {

	static Connection conn;  
	  
    static PreparedStatement pst; 
    static Statement st;
    static ResultSet rs;
    
   public static void main(String[] args)
   {
	  
	   
   }
   
    public static void insert(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,Timestamp date) {  
          
        conn = getConnection(); 
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
              
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
            
                
            int count =pst.executeUpdate();  
              
            System.out.println("��data���в��� " + count + " ������"); 
            
            pst.close();  
            conn.close();   
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }  
    /* �������ݼ�¼���������������ݼ�¼��*/
    public static void inserts(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double xs1,double xs2,double xs3,double gs,Timestamp date) {  
          
        conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
            
            String sql1="INSERT into data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,"+
            "zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,date)"+
            		"SELECT ?,?,?,?,?,?,?,?,?,c.zhulei,c.fenlei,c.xiaolei,c.fenxiang,?,c.zhuanye,c.jiliang,"+
            "?,?,?,c.zgs,? FROM chanpin c where chanpin='"+chanpin+"'";
            
            
            pst = conn.prepareStatement(sql1);   
            pst.setString(1, xid);
            pst.setString(2, name);
            pst.setString(3, province);
            pst.setString(4, danwei);
            pst.setString(5, yewu);
            pst.setString(6, jmanager);
            pst.setString(7, kmanager);
            pst.setString(8, fzr);
            pst.setString(9, zzy);
            pst.setString(10, chanpin);
            pst.setDouble(11, xs1);
            pst.setDouble(12, xs2);
            pst.setDouble(13, xs3);
            pst.setTimestamp(14, date);                           
            int count =pst.executeUpdate();             
            System.out.println("��data���в��� " + count + " ������");
            
            pst.close();  
            conn.close();  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }  
    /* �鿴xfdata�����Ƿ����ж�Ӧxid��chanpin������*/
    public static List<Integer> check(String xid, String fenlei, String chanpin){
    	conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
    	List<Integer> qlist=new ArrayList<Integer>();
        try {
        	String sql_s = "SELECT id FROM xfdata where xid='"+xid.trim()+"' and fenlei='"+fenlei.trim()+"' and chanpin='"+chanpin.trim()+"'";
        	System.out.println(sql_s);
        	st = (Statement)conn.createStatement();
    		rs=st.executeQuery(sql_s);
    		System.out.println("rs end");
    		while(rs.next())
    			{Integer id = rs.getInt("id");
    			qlist.add(id);
    			
    			}
    		return qlist;
        }catch(SQLException e){
        	System.out.println("��ѯxfdataʧ��");
        }
        return null;
    }
    
    /* ��xfdata��������*/
    public static void updateXfData(int xfid, double biaozhun, double xiuzheng, float bili){
    	conn = getConnection(); 
    	System.out.println("update");
    	try{
		String sql_u = "UPDATE xfdata set biaozhun=?, xiuzheng=?, bili=? where id=?";
		
		pst =conn.prepareStatement(sql_u); 
		pst.setDouble(1, biaozhun);
		pst.setDouble(2, xiuzheng);
		pst.setFloat(3, bili);
		pst.setInt(4, xfid);
		
		int count = pst.executeUpdate();
		 
        System.out.println("xfdata���и��� " + count + " ������");
    	}catch(SQLException e){
    		System.out.println("xfdata�����ʧ��");
    	}
       
    }
    
    /* ��xfdata��������*/
    public static void insertXfData(String xid, String fenlei, String chanpin, String huanjie, String xifen, double biaozhun, double xiuzheng, float bili) {  
    	conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ� 
        try{
    			System.out.println("insert");
    			String sql = "INSERT INTO xfdata(xid,fenlei,chanpin,huanjie,xifen,biaozhun,xiuzheng,bili)" 
                    + " VALUES (?,?,?,?,?,?,?,?)";  
      
                pst = conn.prepareStatement(sql);   
                pst.setString(1, xid);
                pst.setString(2, fenlei);
                pst.setString(3, chanpin);
                pst.setString(4, huanjie);
                pst.setString(5, xifen);
                pst.setDouble(6, biaozhun);
                pst.setDouble(7, xiuzheng);
                pst.setFloat(8, bili);
                                      
                int count =pst.executeUpdate();             
                System.out.println("��xfdata���в��� " + count + " ������");
    		
            pst.close();  
            conn.close();  
              
        } catch (SQLException e) {  
            System.out.println("xfdata��������ʧ��" + e.getMessage());  
        }  
    }  
      
    /* ���·���Ҫ��ļ�¼�������ظ��µļ�¼��Ŀ*/  
    public static void update(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,int id) {  
        
        conn = getConnection();
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  
              
            pst =conn.prepareStatement(sql);    
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
              
            int count = pst.executeUpdate();
              
            System.out.println("staff���и��� " + count + " ������");   
            pst.close();  
            conn.close();  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
    }  
    /* ���·���Ҫ��ļ�¼��������õĹ�ʱ����data����*/  
    public static void update(Data data) {  
        
        conn = getConnection(); 
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  // �������ݵ�sql���  
              
            PreparedStatement pst =conn.prepareStatement(sql);
         
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

              
            int count = pst.executeUpdate();
              
            System.out.println("staff���и��� " + count + " ������");     
            pst.close();  
            conn.close();   
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
    }  
    
public static void update(int id,String chanpin,double gs,double bjgs,double x1,double x2,double x3) {  
        
        conn = getConnection(); 
        try {  
            String sql = "update data d,chanpin c set d.xzgs=? , d.bjgs=? , d.xs1=? , d.xs2=? ," +
            		" d.xs3=? , d.zhulei=c.zhulei,d.fenlei=c.fenlei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
            		"d.chanpin=c.chanpin,d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs " +
            		" where d.id=? and c.chanpin='"+chanpin+"'";  
              
            PreparedStatement pst =conn.prepareStatement(sql);
           pst.setDouble(1, gs);
            pst.setDouble(2, bjgs);  
            pst.setDouble(3, x1); 
            pst.setDouble(4, x2);
            pst.setDouble(5, x3); 
            pst.setInt(6, id);

            int count = pst.executeUpdate();
              
            System.out.println("staff���и��� " + count + " ������");     
            pst.close();  
            conn.close();   
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
    }  
  
/* ��ѯ���ݿ⣬�鿴���ݿ���data�����е�����*/  



    /* ��ѯ���ݿ⣬����մ�Excel��������ݼ�¼*/  
   public static List<Data> query(int count1) {  
    	
    	List<Data> qlist=new ArrayList<Data>();
        conn = getConnection(); 
        try {  
            String sql1 = "SELECT d.id,d.xid,d.name,d.province,d.danwei,d.yewu,d.jmanager,d.kmanager,d.fzr,d.zzy,d.zhulei,d.fenlei,d.xiaolei,d.fenxiang,d.chanpin,d.zhuanye,d.jiliang,d.xs1,d.xs2,d.xs3,c.zgs from data d,chanpin c where d.chanpin=c.chanpin ORDER BY d.id desc LIMIT "+count1;     // ��ѯ���ݵ�sql���  
            String sql2="SELECT id,xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs from data ORDER BY id desc LIMIT "+count1; 
            String sql="SELECT * FROM data ORDER BY id desc LIMIT "+count1; 
            st = (Statement) conn.createStatement();   
              
            rs = st.executeQuery(sql);   
          
            
         while (rs.next()) { 
               
        	 Data qdata = new Data();
        	
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
                qdata.setZhulei(rs.getString("zhulei"));
                qdata.setFenlei(rs.getString("fenlei"));
                qdata.setXiaolei(rs.getString("xiaolei"));
                qdata.setFenxiang(rs.getString("fenxiang"));
                qdata.setChanpin(rs.getString("chanpin"));
                qdata.setZhuanye(rs.getString("zhuanye"));
                qdata.setJiliang(rs.getString("jiliang"));
                qdata.setXs1(rs.getDouble("xs1"));
                qdata.setXs2(rs.getDouble("xs2"));
                qdata.setXs3(rs.getDouble("xs3"));
                qdata.setGs(rs.getDouble("gs"));
                qdata.setXzgs(rs.getDouble("xzgs"));
                qdata.setBjgs(rs.getDouble("bjgs"));
             qlist.add(qdata); 
   		     
               
           } 
           
            conn.close();  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  

		  
        return qlist;
    }  
    
    /*���ݸ����Ĳ�Ʒ���ƣ������ҡ��������ݿ�����о���*/
    public static List<Data> query(String str)
    {
    	
    	System.out.println("ִ�е������");
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); 
        try {  
            String sql = "select * from chanpin where chanpin='"+str+"'";     
            st = (Statement) conn.createStatement();   
              
            rs = st.executeQuery(sql);   
       
         while (rs.next()) { 
               
        	 Data qdata = new Data();
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
            conn.close();                
        } catch (SQLException e) {  
            System.out.println("�����chanpin����ѯ����ʧ��");  
        }  
    	return qlist;
    }
    
    public static List<XfData> queryXf(String xid,String cp,String fl){
    	
    
    	List<XfData> qlist = new ArrayList<XfData>();
    	conn = getConnection();
    	System.out.println(xid+","+cp);
    	try{
    		String sql = "select * from xfdata where xid='"+xid+"' and chanpin='"+cp.trim()+"' and fenlei='"+fl.trim()+"'";
    		st = (Statement)conn.createStatement();
    		rs=st.executeQuery(sql);
    		System.out.println(rs.next());
    		if(rs.next()){
    			rs.beforeFirst();
    			while(rs.next()){
    				XfData xdata = new XfData();
        			xdata.setChanpin(rs.getString("chanpin"));
        			xdata.setFenlei(rs.getString("fenlei"));
        			xdata.setHuanjie(rs.getString("huanjie"));
        			xdata.setXifen(rs.getString("xifen"));
        			xdata.setBiaozhun(rs.getDouble("biaozhun"));
        			xdata.setXiuzheng(rs.getDouble("xiuzheng"));
        			xdata.setBili(rs.getFloat("bili"));
        			
        			qlist.add(xdata);
    			}
    			
    		}
    		else{
    	    		System.out.println("come in");
    	    		String sql1 = "select * from xf where chanpin='"+cp.trim()+"' and fenlei='"+fl.trim()+"'";
    	    		st = (Statement)conn.createStatement();
    	    		rs = st.executeQuery(sql1);
    	    		
    	    		while(rs.next()){
    	    			XfData xdata = new XfData();
    	    			xdata.setChanpin(rs.getString("chanpin"));
    	    			xdata.setFenlei(rs.getString("fenlei"));
    	    			xdata.setHuanjie(rs.getString("hj"));
    	    			xdata.setXifen(rs.getString("xf"));
    	    			xdata.setBiaozhun(rs.getDouble("gsxf"));
    	    			xdata.setXiuzheng(rs.getDouble("gsxf"));
    	    			xdata.setBili(1);
    	    			qlist.add(xdata);
    	    		}
    	    	}
 
    	    	conn.close();
    	}catch(SQLException e){
    		System.out.println("xfdata error");
    	}
    	return qlist;
    }
    
    public static List<Data> queryXf(String cp){
    	
        
    	List<Data> qlist = new ArrayList<Data>();
    	conn = getConnection();
    	
    	
    	try{
    		String sql = "select * from xf where chanpin='"+cp.trim()+"'";
    		st = (Statement)conn.createStatement();
    		rs = st.executeQuery(sql);
    		
    		while(rs.next()){
    			Data data = new Data();
    			data.setChanpin(rs.getString("chanpin"));
    			data.setHj(rs.getString("hj"));
    			data.setXf(rs.getString("xf"));
    			data.setGs(rs.getDouble("gsxf"));
    			data.setId(rs.getInt("id"));
    			qlist.add(data);
    		}
    		conn.close();
    	}catch(SQLException e){
    		System.out.println("��ѯ����ʧ��");
    		e.getStackTrace();
    	}
    	
    	return qlist;
    }
  
    /*���ݸ�����fid��ţ�fid�Ǹ��ݲ�Ʒ������ģ��鿴����ϸ��*/
    public static List<Xishu> queryXs(String xs1,String fenlei)
    {
    	List<Xishu> qlist=new ArrayList<Xishu>();
    	conn = getConnection();
        try {  
            String sql = "select * from xs where chanpin='"+xs1+"' AND fenlei="+fenlei+"'";     
            st = (Statement) conn.createStatement();   
             
            rs = st.executeQuery(sql);  
                    
         while (rs.next()) { 
        	Xishu xs=new Xishu();     
        	xs.setFenlei(rs.getString("fenlei"));
        	xs.setChanpin(rs.getString("chanpin"));
        	xs.setXs1(rs.getString("xs1"));
        	
        	xs.setXs2(rs.getString("xs2"));
        	
        	xs.setXs3(rs.getString("xs3"));
        	              
             qlist.add(xs);               
           }            
            conn.close();
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  
    	return qlist;
    }
    
    /* ɾ������Ҫ��ļ�¼��������*/  
    public static void delete(int id) {  
  
        conn = getConnection();   
        try {  
            String sql = "delete from data  where id = ?";
            pst = conn.prepareStatement(sql);   
            pst.setInt(1, id);
            int count = pst.executeUpdate();       
            System.out.println("staff����ɾ�� " + count + " ������\n");   
            pst.close();  
            conn.close(); 
              
        } catch (SQLException e) {  
            System.out.println("ɾ������ʧ��");  
        }  
          
    }  
      
    /* ��ȡ���ݿ����ӵĺ���*/  
    public static Connection getConnection() {  
            Connection con = null;  
        try {  
            Class.forName("com.mysql.jdbc.Driver");
              
            con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/dinge?useUnicode=true&characterEncoding=gbk", "root", "123");
            
              
        } catch (Exception e) {  
            System.out.println("���ݿ�����ʧ��" + e.getMessage());  
        }  
        return con;
    }  
	
	
}
