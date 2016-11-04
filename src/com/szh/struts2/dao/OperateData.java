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

	
	
	/* ��ѯ���ݿ⣬�鿴���ݿ���data�����е�����*/  
	public static List<Data> query() {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<Data> qlist=new ArrayList<Data>();
	    conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    try {        
	        String sql="SELECT * FROM data ORDER BY id desc  "; 
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
	            qdata.setDate(rs.getTimestamp("date"));
	            qdata.setDone(rs.getInt("done"));
	         qlist.add(qdata); 		                
	       } 	       
	        conn.close();   //�ر����ݿ�����  	          
	    } catch (SQLException e) {  
	        System.out.println("��ѯ����ʧ��");  
	    }  		  
	    return qlist;
	}  
	/* ��ѯ���ݿ⣬�鿴���ݿ���newdata�����е�����*/  
	public static List<Data> querypiliang() {  		
	      Connection conn=null;
	      Statement st=null;
	      ResultSet rs=null;
		
		List<Data> qlist=new ArrayList<Data>();
	    conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    try {        
	        String sql="SELECT * FROM newdata ORDER BY id desc  "; 
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
	        conn.close();   //�ر����ݿ�����  	          
	    } catch (SQLException e) {  
	        System.out.println("newdata��ѯ����ʧ��");  
	    }  		  
	    return qlist;
	}  
	
	 public static List<String> queryname()
	    {
		    Connection conn=null;
	        Statement st=null;
	        ResultSet rs=null;
	    	List<String> namelist=new ArrayList<String>();
	    	conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	        try {  
	            String sql = "select distinct chanpin from chanpin ";     // ��ѯ���ݵ�sql���  
	            st = (Statement) conn.createStatement();    //��������ִ�о�̬sql����Statement����st���ֲ�����  	              
	            rs = st.executeQuery(sql);    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
	            //System.out.println("���Ĳ�ѯ���Ϊ��");  
	            
	         while (rs.next()) { // �ж��Ƿ�����һ������  
	                  
	                // �����ֶ�����ȡ��Ӧ��ֵ  
	        	    //����������ڴ�ѭ����
	        	 String name="";
	        	 name=rs.getString("chanpin");
                 namelist.add(name);	               	             	   		                   
	           }           
	            conn.close();   //�ر����ݿ�����                
	        } catch (SQLException e) {  
	            System.out.println("�����chanpin����ѯ����ʧ��");  
	        }  
	    	return namelist;
	    }
	 
	 /* ��ѯ���ݿ⣬���������鿴���ݿ���data�����е�����*/  
		public static List<Data> query(String date1,String date2,String zhulei,String fenlei,String fenxiang,String chanpin) 
		{  		
		      Connection conn=null;
		      Statement st=null;
		      ResultSet rs=null;

			List<Data> qlist=new ArrayList<Data>();
		    conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
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
		            qdata.setDate(rs.getTimestamp("date"));
		         qlist.add(qdata); 		                
		       } 	       
		        conn.close();   //�ر����ݿ�����  	          
		    } catch (SQLException e) {  
		        System.out.println("��ѯ����ʧ��");  
		    }  		  
		    return qlist;
		}  
		
		
		/*��SearchXfAction����--*/
	    public static List<Data> querysXf(int id)
	    {
	    	List<Data> qlist=new ArrayList<Data>();
	    	Connection conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from xfdone where wid=?";     // ��ѯ���ݵ�sql���  
	            PreparedStatement pst = conn.prepareStatement(sql);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
	            pst.setInt(1, id);

	            
	             
	            rs = pst.executeQuery();    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
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
	    /*��SXfAction����--*/
	    public static List<Data> queryXf(String fenlei,String chanpin)
	    {
	    	List<Data> qlist=new ArrayList<Data>();
	    	Connection conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from xf where fenlei=? AND chanpin=?";     // ��ѯ���ݵ�sql���  
	            PreparedStatement pst = conn.prepareStatement(sql);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
	            pst.setString(1, fenlei.trim());
	            pst.setString(2, chanpin.trim());
	            System.out.println("sql zhixing qian");
	            rs = pst.executeQuery();    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
	            System.out.println("sql zhixing");  
	            
	         while (rs.next()) { // �ж��Ƿ�����һ������  
	                  
	                // �����ֶ�����ȡ��Ӧ��ֵ  
	        	 Data qdata = new Data();//����������ڴ�ѭ����
	                qdata.setId(rs.getInt("id"));
	                qdata.setFenlei(rs.getString("fenlei"));
	                qdata.setChanpin(rs.getString("chanpin"));
	                qdata.setHj(rs.getString("hj"));
	                qdata.setXf(rs.getString("xf"));
	                qdata.setGsxf(rs.getInt("gsxf"));
	                //qdata.setXzgsxf(rs.getInt("xzgsxf"));//xf����û������������
	               // qdata.setBlxsxf(rs.getFloat("blxsxf"));
	             qlist.add(qdata);               
	           }           
	             pst.close();
	            conn.close();   //�ر����ݿ�����  
	              
	        } catch (SQLException e) {  
	            System.out.println("queryXf(str,str)��ѯ����ʧ��");  
	        }  
	    	return qlist;
	    }
		
	    /*��SearchOpAction����--*/
	    public static Data queryOp(int id)
	    {
	    	Data qdata = new Data();
	    	Connection conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select * from data where id=?";     // ��ѯ���ݵ�sql���  
	            PreparedStatement pst = conn.prepareStatement(sql);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
	            pst.setInt(1, id);

	            
	             
	            rs = pst.executeQuery();    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
	            //System.out.println("���Ĳ�ѯ���Ϊ��");  
	            
	        if (rs.next()) { // �ж��Ƿ�����һ������  
	                  
	                // �����ֶ�����ȡ��Ӧ��ֵ  
	        	 //����������ڴ�ѭ����
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
	            conn.close();   //�ر����ݿ�����  
	              
	        } catch (SQLException e) {  
	            System.out.println("��ѯ����ʧ��");  
	        }  
	    	return qdata;
	    }
	    public static int queryID()
	    {
	    	int id=0;
	    	Connection conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	    	ResultSet rs=null;
	        try {  
	            String sql = "select max(id) as id from data";     // ��ѯ���ݵ�sql���  
	            PreparedStatement pst = conn.prepareStatement(sql);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
	            

	            
	             
	            rs = pst.executeQuery();    //ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����  
	            //System.out.println("���Ĳ�ѯ���Ϊ��");  
	            
	        if (rs.next()) { // �ж��Ƿ�����һ������  
	                  
	                // �����ֶ�����ȡ��Ӧ��ֵ  
	        	 //����������ڴ�ѭ����
	        	id=rs.getInt("id");                	                           
	           }           
	             pst.close();
	            conn.close();   //�ر����ݿ�����  
	              
	        } catch (SQLException e) {  
	            System.out.println("querID��ѯ����ʧ��");  
	        }  
	    	return id;
	    }
		
	    /* ɾ������Ҫ��ļ�¼��������*/  
	    public static void delete(String[] check) {  
	         Connection conn=null;
	         PreparedStatement pst = null;   
		     ResultSet rs=null;
	         conn = Database.getConnection(); //ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�  
	         String sql = "delete from data  where id = ?";// ɾ�����ݵ�sql���  
	        try {  
	            
	            pst = conn.prepareStatement(sql); 
	            conn.setAutoCommit(false);  //��������ִ�о�̬sql����Statement����st���ֲ�����  
	            for(int i =0 ;i<check.length;i++){   
	            	 pst.setInt(1, Integer.parseInt(check[i])); 
	                 pst.addBatch();                 
	            } 	           
	            //int count = pst.executeUpdate();// ִ��sqlɾ����䣬����ɾ�����ݵ�����            
	            pst.executeBatch(); //����ִ��   
	            conn.commit();//�ύ���� 
	           // System.out.println("staff����ɾ�� " + count + " ������\n");    //���ɾ�������Ĵ�����  
	            pst.close();  
	            conn.close();   //�ر����ݿ�����  
	              
	        } catch (SQLException e) {  
	            System.out.println("ɾ������ʧ��");  
	        }  
	          
	    }  
}
