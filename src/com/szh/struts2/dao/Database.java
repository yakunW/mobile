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
    //static Data data=new Data();//������static�ģ����������������
  
    public static void main(String[] args) {
//    	insert("2012003","�й��ƶ�һ����Ŀ�����������ͺ��أ�","����","�й��ƶ�","������",
//"����","����","������","����","��ͳҵ��","�������","ͨ���豸��Դ"
//,"���ߴ���վ��Դ","���ߴ���վ��Դ","�������롢ֱ����Դ��С��UPS"
//        		,"վ",11,11,11,34,34,34,new Timestamp(System.currentTimeMillis())); 
    	System.out.println("�й��ƶ�һ����Ŀ�����������ͺ��أ�");
//������Ӽ�¼  
////        update();   //���¼�¼����  
//       delete(1);   //ɾ����¼  

//    	ResultSet rst=query();    //��ѯ��¼����ʾ  
//      try{ while(rst.next())
//       {
//    	   String name=rst.getString("name");
//    	   System.out.println(name);
//    	   
//       }
//      }
//      catch (SQLException e) {  
//          System.out.println("��ѯ����ʧ��");  
//      }  
    }  
      
    /* �������ݼ�¼���������������ݼ�¼��*/  
    public static void insert(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double jishu,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,Timestamp date) {  
          
        conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // �������ݵ�sql���  
              
            pst = conn.prepareStatement(sql);    // ��������ִ�о�̬sql����prepareStatement����  
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
            
                
            int count =pst.executeUpdate();  // ִ�в��������sql��䣬�����ز������ݵĸ���  
              
            System.out.println("��data���в��� " + count + " ������"); //�����������Ĵ�����  
            
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }  
    /* �������ݼ�¼���������������ݼ�¼��*/
    public static void insert1(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang, double xs1,double xs2,double xs3,double gs,Timestamp date) {  
          
        conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
  
        try {  
//            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy," +
//            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,jishu,xs1,xs2,xs3,gs,xzgs,bjgs,date)"  
//                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // �������ݵ�sql���  
//            
            String sql1="INSERT into data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,"+
            "zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs,date)"+
            		"SELECT ?,?,?,?,?,?,?,?,?,c.zhulei,?,c.xiaolei,c.fenxiang,?,c.zhuanye,c.jiliang,"+
            "?,?,?,c.zgs,c.zgs,c.zgs*"+xs1+"*"+xs2+"*"+xs3+",? FROM chanpin c where fenlei='"+fenlei+"' AND chanpin='"+chanpin+"'";
            
            
            pst = conn.prepareStatement(sql1);    // ��������ִ�о�̬sql����prepareStatement����  
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
            int count =pst.executeUpdate();  // ִ�в��������sql��䣬�����ز������ݵĸ���                
            System.out.println("��data���в��� " + count + " ������"); //�����������Ĵ�����  
            
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }  
    /* �������ݼ�¼���������������ݼ�¼��--��UpAction---ExcelDataReader�����*/  
    public static void insert2(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,String sjjd,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double jdxs,double jishu,double xs1,double xs2,double xs3,double gs,Timestamp date,int done) {  
          
        conn = getConnection(); // ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
  
        try {  
            String sql = "INSERT INTO data(xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,sjjd," +
            		"zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,jdxs,jishu,xs1,xs2,xs3,gs,xzgs,bjgs,wygs,hzgs,date,done)"  
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  // �������ݵ�sql���  
              
            pst = conn.prepareStatement(sql);    // ��������ִ�о�̬sql����prepareStatement����  
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
            
                
            int count =pst.executeUpdate();  // ִ�в��������sql��䣬�����ز������ݵĸ���  
              
            System.out.println("��data���в��� " + count + " ������"); //�����������Ĵ�����  
            
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }  
    /* �������ݼ�¼�����ݼ�¼Ϊ��ʱ��ϸ����--��HsAction�����*/ 
    public static void insertxf(int wid,String chanpin,String fenlei,String[] hj,String[] xf,String[] gsxf,String[] xzgsxf,String[] bl){
    	System.out.println("���ݿ����insertxf : "+fenlei+chanpin);
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
            System.out.println("��������ʧ��" + e.getMessage());  
        }  
    }
    
      
    /* ���·���Ҫ��ļ�¼�������ظ��µļ�¼��Ŀ*/  
    public static void update(String xid,String name,String province,String danwei,String yewu,String jmanager,String kmanager,String fzr,String zzy,
    		String zhulei,String fenlei,String xiaolei,String fenxiang,String chanpin,String zhuanye,String jiliang,double xs1,double xs2,double xs3,double gs,double xzgs,double bjgs,int id) {  
        
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  // �������ݵ�sql���  
              
            pst =conn.prepareStatement(sql);    //��������ִ�о�̬sql����Statement����pst���ֲ�����  
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
              
            int count = pst.executeUpdate();// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
              
            System.out.println("staff���и��� " + count + " ������");      //������²����Ĵ�����  
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
    }  
    /* ���·���Ҫ��ļ�¼��������õĹ�ʱ����data����*/  
    public static void update(Data data) {  
        
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "update data set xid=?,name=?,province=?,danwei=?,yewu=?,jmanager=?,kmanager=?,fzr=?,zzy=?," +
            		"zhulei=?,fenlei=?,xiaolei=?,fenxiang=?,chanpin=?,zhuanye=?,jiliang=?,xs1=?,xs2=?,xs3=?,gs=?,xzgs=?,bjgs=?"  
                    + "where id=?";  // �������ݵ�sql���  
              
            PreparedStatement pst =conn.prepareStatement(sql);
            //��������ִ�о�̬sql����Statement����pst���ֲ�����  
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
              
            int count = pst.executeUpdate();// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
              
            System.out.println("staff���и��� " + count + " ������");      //������²����Ĵ�����  
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
    }  
    /*-----------��UpAction����-----------*/
    public static int update(List<Data> dlist) {  
        
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        int count=0,sum=0;
        String sql = "update data d,chanpin c set d.zhulei=c.zhulei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
        		"d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs,d.xzgs=c.zgs,d.bjgs=(c.zgs*d.jdxs*d.jishu*d.xs1*d.xs2*d.xs3),d.wygs=d.bjgs,d.hzgs=? "+
        		" where d.id=? and c.chanpin=? and c.fenlei=?";  // �������ݵ�sql���   
          

        try { 
            for(Data data:dlist)
            {System.out.println(data.getId());
        	
        	PreparedStatement pst =conn.prepareStatement(sql);
            //��������ִ�о�̬sql����Statement����pst���ֲ�����  
//            System.out.println(data.getJmanager());
//            System.out.println(data.getGs());
        	   pst.setDouble(1, 0);
               pst.setInt(2, data.getId());
               pst.setString(3, data.getChanpin());
               pst.setString(4, data.getFenlei());
              

//            System.out.println(data.getId());
              
             count = pst.executeUpdate();// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
             sum+=count; 
            System.out.println("staff���и��� " + count + " ������");      //������²����Ĵ�����  
            
            pst.close();  }
            
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
            return sum;
    }  
    /*-----------��HsAction����--------------------*/
public static int update(int id,String fenlei,String chanpin,double xzgs,double bjgs,double x1,double x2,double x3,double jishu,double jdxs, double mul) {  
        
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        int sum=0;
        String sql = "update data d,chanpin c set d.done=1,d.xzgs=? , d.bjgs=? , d.xs1=? , d.xs2=? ," +
        		" d.xs3=? ,d.jishu=?,d.jdxs=?, d.zhulei=c.zhulei,d.fenlei=c.fenlei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
        		"d.chanpin=c.chanpin,d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs,d.wygs=? ,d.hzgs=? " +
        		" where d.id=? and c.chanpin=? and c.fenlei=?"; 
        try {  
          // �������ݵ�sql���  
              
            PreparedStatement pst =conn.prepareStatement(sql);
            //��������ִ�о�̬sql����Statement����pst���ֲ�����  
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
            

              
            int count = pst.executeUpdate();// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
            sum+=count; 
            System.out.println("data���и��� " + count + " ������");      //������²����Ĵ�����  
            System.out.println("data���и��µ�idΪ��  " + id ); 
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��������ʧ��");  
        }  
        return sum;
    }  
/*-----------��JiuCuoAction����--------------------*/
public static int update(int id,String fenlei,String chanpin,double jdxs,double jishu,double x1,double x2,double x3) {  
    
    conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
    int sum=0;
    String sql = "update data d,chanpin c set  d.xs1=? , d.xs2=? ," +
    		" d.xs3=? ,d.jishu=?,d.jdxs=?, d.zhulei=c.zhulei,d.fenlei=c.fenlei,d.xiaolei=c.xiaolei,d.fenxiang=c.fenxiang," +
    		"d.chanpin=c.chanpin,d.zhuanye=c.zhuanye,d.jiliang=c.jiliang,d.gs=c.zgs ,d.xzgs=c.zgs , d.bjgs=(c.zgs*d.jdxs*d.jishu*d.xs1*d.xs2*d.xs3),d.wygs=d.bjgs" +
    		" where d.id=? and c.chanpin=? and c.fenlei=?"; 
    try {  
      // �������ݵ�sql���  
          
        PreparedStatement pst =conn.prepareStatement(sql);
        //��������ִ�о�̬sql����Statement����pst���ֲ�����  
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
          
        int count = pst.executeUpdate();// ִ�и��²�����sql��䣬���ظ������ݵĸ���  
        sum+=count;   
        System.out.println("staff���и��� " + count + " ������");      //������²����Ĵ�����  
        System.out.println("staff���и��µ�idΪ��  " + id ); 
        pst.close();  
        conn.close();   //�ر����ݿ�����  
          
    } catch (SQLException e) {  
        System.out.println("��������ʧ��");  
    }  
    return sum;
}  

/* �������ݼ�¼�����ݼ�¼Ϊ��ʱ��ϸ����--��HsAction�����*/ 
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
        System.out.println("��������ʧ��" + e.getMessage());  
    }  
}


    /* ��ѯ���ݿ⣬����մ�Excel��������ݼ�¼------��UpAcion������2��----��JiucuoAction����һ��---��HsAction����һ��-*/  
    public static List<Data> query(int count1) {  
    	
    	List<Data> qlist=new ArrayList<Data>();
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
          //  String sql1 = "SELECT d.id,d.xid,d.name,d.province,d.danwei,d.yewu,d.jmanager,d.kmanager,d.fzr,d.zzy,d.zhulei,d.fenlei,d.xiaolei,d.fenxiang,d.chanpin,d.zhuanye,d.jiliang,d.xs1,d.xs2,d.xs3,c.zgs from data d,chanpin c where d.chanpin=c.chanpin ORDER BY d.id desc LIMIT "+count1;     // ��ѯ���ݵ�sql���  
          //  String sql2="SELECT id,xid,name,province,danwei,yewu,jmanager,kmanager,fzr,zzy,zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,xs1,xs2,xs3,gs,xzgs,bjgs from data ORDER BY id desc LIMIT "+count1; 
            String sql="SELECT * FROM data ORDER BY id desc LIMIT "+count1; 
            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
              
            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");  
            
         while (rs.next()) { // �ж��Ƿ�����һ������  
                  
                // �����ֶ�����ȡ��Ӧ��ֵ  
        	 Data qdata = new Data();//����������ڴ�ѭ����
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
           
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  

		  
        return qlist;
    }  
    
    /*���ݸ����Ĳ�Ʒ���ƣ������ҡ��������ݿ�����о���*/
    public static List<Data> query(String str)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "select * from chanpin where chanpin='"+str+"'";     // ��ѯ���ݵ�sql���  
            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
              
            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");  
            
         while (rs.next()) { // �ж��Ƿ�����һ������  
                  
                // �����ֶ�����ȡ��Ӧ��ֵ  
        	 Data qdata = new Data();//����������ڴ�ѭ����
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
            conn.close();   //�ر����ݿ�����                
        } catch (SQLException e) {  
            System.out.println("�����chanpin����ѯ����ʧ��");  
        }  
    	return qlist;
    }
    
    
    /*���ݸ�����fid��ţ�fid�Ǹ��ݲ�Ʒ������ģ��鿴����ϸ��--��XifenAction����--*/
    public static List<Data> queryXf(String str,String fenlei)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "select * from xf where chanpin='"+str+"' AND fenlei='"+fenlei+"'";     // ��ѯ���ݵ�sql���  
            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
             
            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");  
            
         while (rs.next()) { // �ж��Ƿ�����һ������  
                  
                // �����ֶ�����ȡ��Ӧ��ֵ  
        	 Data qdata = new Data();//����������ڴ�ѭ����
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
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  
    	return qlist;
    }
    /*���ݸ�����fid��ţ�fid�Ǹ��ݲ�Ʒ������ģ��鿴����ϸ��--��XifenAction����--*/
    public static List<Data> queryXf2(String str,String fenlei,int id)
    {
    	List<Data> qlist=new ArrayList<Data>();
    	conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "select * from xfdone where wid=?";     // ��ѯ���ݵ�sql���  
            pst = conn.prepareStatement(sql);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
            pst.setInt(1, id);
          //  pst.setString(2, str.trim());
          //  pst.setString(3, fenlei.trim());
            
             
            rs = pst.executeQuery();    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");  
            
         while (rs.next()) { // �ж��Ƿ�����һ������  
                  
                // �����ֶ�����ȡ��Ӧ��ֵ  
        	 Data qdata = new Data();//����������ڴ�ѭ����
                qdata.setId(rs.getInt("id"));//���id��xfdone�����id�ţ���data���id�ǲ�һ���ġ�
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
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  
    	return qlist;
    }
    /*���ݸ�����fid��ţ�fid�Ǹ��ݲ�Ʒ������ģ��鿴����ϸ��*/
    public static List<Xishu> queryXs(String xs1 ,String xs2)
    {
    	List<Xishu> qlist=new ArrayList<Xishu>();
    	conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "select * from xs where chanpin='"+xs1+"' AND fenlei='"+xs2+"'";     // ��ѯ���ݵ�sql���  
            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
             
            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");             
         while (rs.next()) { // �ж��Ƿ�����һ������  
                  
                // �����ֶ�����ȡ��Ӧ��ֵ  
        	Xishu xs=new Xishu();//����������ڴ�ѭ����        
        	xs.setFenlei(rs.getString("fenlei"));
        	xs.setChanpin(rs.getString("chanpin"));
        	xs.setXs1(rs.getString("xs1"));
        	
        	xs.setXs2(rs.getString("xs2"));
        	
        	xs.setXs3(rs.getString("xs3"));
        	               
             qlist.add(xs);               
           }            
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  
    	return qlist;
    }
    
    /*���ݸ�����fid��ţ�fid�Ǹ��ݲ�Ʒ������ģ��鿴����ϸ��*/
    public static boolean queryXfdone(int id)
    {
    	List<Xishu> qlist=new ArrayList<Xishu>();
    	boolean flag=false;
    	conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "select * from xfdone where wid="+id;     // ��ѯ���ݵ�sql���  
            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  
             
            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
            //System.out.println("���Ĳ�ѯ���Ϊ��");   
            
         if (!rs.next())
        	{
        	 flag=true;
        	}
         
         
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("��ѯ����ʧ��");  
        }  
    	return flag;
    }
    
    /* ɾ������Ҫ��ļ�¼��������*/  
    public static void delete(int id) {  
  
        conn = getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
        try {  
            String sql = "delete from data  where id = ?";// ɾ�����ݵ�sql���  
            pst = conn.prepareStatement(sql);    //��������ִ�о�̬sql����Statement����st���ֲ�����  
            pst.setInt(1, id);
            int count = pst.executeUpdate();// ִ��sqlɾ����䣬����ɾ�����ݵ�����               
            System.out.println("staff����ɾ�� " + count + " ������\n");    //���ɾ�������Ĵ�����  
            pst.close();  
            conn.close();   //�ر����ݿ�����  
              
        } catch (SQLException e) {  
            System.out.println("ɾ������ʧ��");  
        }  
          
    }  
      
    /* ��ȡ���ݿ����ӵĺ���*/  
    public static Connection getConnection() {  
            Connection con = null;  //���������������ݿ��Connection����  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// ����Mysql��������  
              
            con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/dinge?useUnicode=true&characterEncoding=gbk", "root", "123");// ������������ 
            
              
        } catch (Exception e) {  
            System.out.println("���ݿ�����ʧ��" + e.getMessage());  
        }  
        return con; //���������������ݿ�����  
    }  
	
	
}
