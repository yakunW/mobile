package com.hm.impl.cooper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hm.entity.credit;
import com.hm.entity.star;
import com.hm.org.Database;


public class cal_StarLevel {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	
	int length = 0;
	int year[] = new int[8];
	int quarter[] = new int[8];
	export_AnyCre exAny = new export_AnyCre();
	static List<credit> list = new ArrayList<credit>();
	
	
	public void cal(String flag,int year,int quarter,XSSFSheet sheet){
		
		if(flag.equals("recent"))
			getFirst8();//			
		else
			get8(year,quarter);
		
		Map<String, String> map = new HashMap<String, String>();
//		sql0查询，sql添加
		String sql0 ="SELECT DISTINCT name FROM quar_cre WHERE year=? AND quarter=?";
		
		try {
			PreparedStatement ps0 = conn.prepareStatement(sql0);
			for(int i = 0; i < getLength(); i++){
				ps0.setInt(1, getYear()[i]);
				ps0.setInt(2, getQuarter()[i]);
//				System.out.println(getYear()[i]+"  "+getQuarter()[i]);
				ResultSet rs0 = ps0.executeQuery();
				while(rs0.next())
					map.put(rs0.getString(1), rs0.getString(1));
			}
			ps0.close();
		} catch (SQLException e) {
			System.out.println("名称查询错误");
		}
		try {
			if(flag.equals("recent")){
				PreparedStatement ps0 = conn.prepareStatement(sql0);
				ps0 = conn.prepareStatement("TRUNCATE TABLE gather");
				ps0.executeUpdate();
				ps0.close();
			}
		} catch (SQLException e) {
			System.out.println("删除有误");
		}
//		获得8个季度的公司的名称
		Collection<String> names = map.values();
		Iterator<String> it=names.iterator();
		String name;
		while(it.hasNext()){
			name = it.next();
			credit cre = new credit();
			cre.setName(name);
			list.add(cre);
//			System.out.println("name:" + name);
		}
//			System.out.println("test"+list.size());

		sum(flag,sheet);
		trans(flag,sheet);
		wireless(flag,sheet);
		switchx(flag,sheet);
		data(flag,sheet);
		power(flag,sheet);
		civil(flag,sheet);
		network(flag,sheet);
		
		list.clear();
	}
	
//  获取最近的八个季度
	public void getFirst8(){
		String sql = "SELECT DISTINCT year,quarter FROM quar_cre ORDER BY year DESC ,quarter DESC";
		int year0[] = new int[8];
		int quarter0[] = new int[8];
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next()){
				
				if( i >= 8)
					break;
				else{
					year0[i] = rs.getInt(1);
					quarter0[i] = rs.getInt(2);
					i++;
				}
				
			}
			setYear(year0);
			setQuarter(quarter0);
			setLength(i);
//			System.out.println("length:"+i);
//			for(int j = 0; j < length; j++)
//				System.out.println(year[j]+"  "+quarter[j]);
//			if (!ps.isClosed())
			rs.close();	
			ps.close();
		} catch (SQLException e) {
			System.out.println("1"+e.getMessage());
		}
	}
	
//	获取以某年某季度为结尾的八个季度
	public void get8(int year,int quarter){
		String sql = "SELECT DISTINCT year,quarter FROM quar_cre ORDER BY year DESC ,quarter DESC";
		int year0[] = new int[8];
		int quarter0[] = new int[8];
		
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0,j = 0,count = -1;
			while(rs.next()){
				j++;
				if((rs.getInt(1) == year && rs.getInt(2) <= quarter) || (rs.getInt(1) < year ))
					count = j;
				if(count != -1 && j >= count){
					if( i >= 8)
						break;
					else{
						year0[i] = rs.getInt(1);
						quarter0[i] = rs.getInt(2);
						i++;
					}
				}
			}
			setYear(year0);
			setQuarter(quarter0);
			setLength(i);
//			System.out.println("length:"+i);
			rs.close();	
			ps.close();
		} catch (SQLException e) {
			System.out.println("2"+e.getMessage());
		}
	}
//	综合
	public void sum(String flag,XSSFSheet sheet){
		
//		sumsql用于统计合作范围,sql用于添加各项积分,find_sql用于查找各季度的积分,sqlRange用于改名后公司的合作省的查找
		String sum_sql = "SELECT sumprovince FROM quar_cre WHERE sumprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT sumCredit FROM quar_cre WHERE sumprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET sumRange=?,sumLevel=?,sumStar=?,sumCredit=?,sumCredit1=?,sumCredit2=?," +
				"sumCredit3=?,sumCredit4=?,sumCredit5=?,sumCredit6=?,sumCredit7=?,sumCredit8=? WHERE name=?";
		
		getRange(sum_sql);
		getCredit(find_sql,sum_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent")){
			String sql_Name = "INSERT INTO gather(name,oldName) VALUES (?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql_Name);
				System.out.println(list.size());
				for(int i = 0;i < list.size(); i++){
					ps.setString(1, list.get(i).getName());
					ps.setString(2, list.get(i).getOldName());
//					System.out.println(list.get(i).getName()+"  "+list.get(i).getOldName());
					ps.executeUpdate();
				}
//				if (!ps.isClosed())
//					ps.close();
			} catch (SQLException e) {
				System.out.println("3"+e.getMessage());
			}
			update(sql);
		}
		else{
//			System.out.println(list.size());
			exAny.wriName(list, sheet);
			exAny.write(list, sheet,3);
		}
	}
	
//	传输
	public void trans(String flag,XSSFSheet sheet){
		String trans_sql = "SELECT transprovince FROM quar_cre WHERE transprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT transCredit FROM quar_cre WHERE transprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET transRange=?,transLevel=?,transStar=?,transCredit=?,transCredit1=?,transCredit2=?," +
				"transCredit3=?,transCredit4=?,transCredit5=?,transCredit6=?,transCredit7=?,transCredit8=? WHERE name=?";
		
		getRange(trans_sql);
		getCredit(find_sql,trans_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,6);
	}
//	交换
	public void switchx(String flag,XSSFSheet sheet){
		String switchx_sql = "SELECT switchxProvince FROM quar_cre WHERE switchxProvince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT switchxCredit FROM quar_cre WHERE switchxProvince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET switchxRange=?,switchxLevel=?,switchxStar=?,switchxCredit=?,switchxCredit1=?,switchxCredit2=?," +
				"switchxCredit3=?,switchxCredit4=?,switchxCredit5=?,switchxCredit6=?,switchxCredit7=?,switchxCredit8=? WHERE name=?";
		
		getRange(switchx_sql);
		getCredit(find_sql,switchx_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,12);
	}
//	无线
	public void wireless(String flag,XSSFSheet sheet){
		String wireless_sql = "SELECT wirelessProvince FROM quar_cre WHERE wirelessProvince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT wirelessCredit FROM quar_cre WHERE wirelessProvince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET wirelessRange=?,wirelessLevel=?,wirelessStar=?,wirelessCredit=?,wirelessCredit1=?,wirelessCredit2=?," +
				"wirelessCredit3=?,wirelessCredit4=?,wirelessCredit5=?,wirelessCredit6=?,wirelessCredit7=?,wirelessCredit8=? WHERE name=?";
		
		getRange(wireless_sql);
		getCredit(find_sql,wireless_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,9);
	}
//	数据
	public void data(String flag,XSSFSheet sheet){
		String data_sql = "SELECT dataprovince FROM quar_cre WHERE dataprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT dataCredit FROM quar_cre WHERE dataprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET dataRange=?,dataLevel=?,dataStar=?,dataCredit=?,dataCredit1=?,dataCredit2=?," +
				"dataCredit3=?,dataCredit4=?,dataCredit5=?,dataCredit6=?,dataCredit7=?,dataCredit8=? WHERE name=?";
		
		getRange(data_sql);
		getCredit(find_sql,data_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,15);
	}
//	电源
	public void power(String flag,XSSFSheet sheet){
		String power_sql = "SELECT powerprovince FROM quar_cre WHERE powerprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT powerCredit FROM quar_cre WHERE powerprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET powerRange=?,powerLevel=?,powerStar=?,powerCredit=?,powerCredit1=?,powerCredit2=?," +
				"powerCredit3=?,powerCredit4=?,powerCredit5=?,powerCredit6=?,powerCredit7=?,powerCredit8=? WHERE name=?";
		
		getRange(power_sql);
		getCredit(find_sql,power_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,18);
	}
//	土建
	public void civil(String flag,XSSFSheet sheet){
		String civil_sql = "SELECT civilprovince FROM quar_cre WHERE civilprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT civilCredit FROM quar_cre WHERE civilprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET civilRange=?,civilLevel=?,civilStar=?,civilCredit=?,civilCredit1=?,civilCredit2=?," +
				"civilCredit3=?,civilCredit4=?,civilCredit5=?,civilCredit6=?,civilCredit7=?,civilCredit8=? WHERE name=?";
		
		getRange(civil_sql);
		getCredit(find_sql,civil_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,21);
	}
//	网络
	public void network(String flag,XSSFSheet sheet){
		String network_sql = "SELECT networkprovince FROM quar_cre WHERE networkprovince!='null' AND year=? AND quarter=? AND name=?";
		
		String find_sql = "SELECT networkCredit FROM quar_cre WHERE networkprovince!='null' AND year=? AND quarter=? AND name=?";

		String sql = "UPDATE gather SET networkRange=?,networkLevel=?,networkStar=?,networkCredit=?,networkCredit1=?,networkCredit2=?," +
				"networkCredit3=?,networkCredit4=?,networkCredit5=?,networkCredit6=?,networkCredit7=?,networkCredit8=? WHERE name=?";
		
		getRange(network_sql);
		getCredit(find_sql,network_sql);
		getLevel();
		getStar1();
		if(flag.equals("recent"))
			update(sql);
		else
			exAny.write(list, sheet,24);
	}
	
//	获取合作范围
	public void getRange(String sql){
		Map<String, String> map = new HashMap<String, String>();
		try{
			String pro[] = new String[20];
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int k = 0; k < list.size(); k++){
				for(int i = 0; i < length; i++){
					
					ps.setInt(1, getYear()[i]);
					ps.setInt(2, getQuarter()[i]);
					ps.setString(3, list.get(k).getName());
					ResultSet rs = ps.executeQuery();
					if(rs.next()){				
						pro = rs.getString(1).split(",");
						for(int j = 0; j < pro.length ; j++){
							map.put(pro[j], pro[j]);
						}	
					}
				}
				list.get(k).setRange(map.size());
				map.clear();
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("4"+e.getMessage());
		}
	}

//	获得星级
	public void getLevel(){
		for(int k = 0; k < list.size(); k++){
			if(list.get(k).getRange() > 2)
				list.get(k).setLevel("A");
			else if(list.get(k).getRange() > 0)
				list.get(k).setLevel("B");
			else
				list.get(k).setLevel("C");
		}
	}
	
//	获得积分
	public void getCredit(String sql,String sum_sql){
	    List<String> oldNames = new ArrayList<String>();
        List<String> newNames = new ArrayList<String>();
		ResultSet rs = null;

		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			double credit ;boolean isExist = true;
			for(int j = 0; j < list.size(); j++){
				isExist = true;
				credit = 0;
//				System.out.println("length:"+getLength());
				for(int i = 0; i <= getLength()-1; i++){
					ps.setInt(1, getYear()[i]);
					ps.setInt(2, getQuarter()[i]);
					ps.setString(3, list.get(j).getName());
					rs = ps.executeQuery();
					switch(i){
					case 0:
						if(rs.next()){
							list.get(j).setCredit1(rs.getDouble(1));
							credit += list.get(j).getCredit1();
							isExist = false;
						}
						else
							list.get(j).setCredit1(-1);
						break;
					case 1:
						if(rs.next()){
							list.get(j).setCredit2(rs.getDouble(1));
							credit += list.get(j).getCredit2();
							isExist = false;
						}
						else
							list.get(j).setCredit2(-1);
						break;
					case 2:
						if(rs.next()){
							list.get(j).setCredit3(rs.getDouble(1));
							credit += list.get(j).getCredit3();
							isExist = false;
						}
						else
							list.get(j).setCredit3(-1);
						break;
					case 3:
						if(rs.next()){
							list.get(j).setCredit4(rs.getDouble(1));
							credit += list.get(j).getCredit4();
							isExist = false;
						}
						else
							list.get(j).setCredit4(-1);
						break;
					case 4:
						if(rs.next()){
							list.get(j).setCredit5(rs.getDouble(1));
							credit += list.get(j).getCredit5();
							isExist = false;
						}
						else
							list.get(j).setCredit5(-1);
						break;
					case 5:
						if(rs.next()){
							list.get(j).setCredit6(rs.getDouble(1));
							credit += list.get(j).getCredit6();
							isExist = false;
						}
						else
							list.get(j).setCredit6(-1);
						break;
					case 6:
						if(rs.next()){
							list.get(j).setCredit7(rs.getDouble(1));
							credit += list.get(j).getCredit7();
							isExist = false;
						}
						else
							list.get(j).setCredit7(-1);
						break;
					case 7:
						if(rs.next()){
							list.get(j).setCredit8(rs.getDouble(1));
							credit += list.get(j).getCredit8();
							isExist = false;
						}
						else
							list.get(j).setCredit8(-1);
						break;
					}//switch
//					System.out.print(" c="+credit);
				}//for(int i = 0; i <= getLength()-1; i++)
				
				if(isExist)
					list.get(j).setCredit(-1);
				else{
					list.get(j).setCredit(credit);
//				    System.out.println(list.get(j).getName()+"  credit="+credit);
				    credit = 0;
				    }
				
			}//for j
		} catch (SQLException e) {
			System.out.println("5"+e.getMessage());
		}
//			新旧名称
		Map<String, String> map = new HashMap<String, String>();
		String sql_Name = "SELECT name,oldName FROM company WHERE oldName is not null";
		try{
			PreparedStatement ps0 = conn.prepareStatement(sql_Name);
//			PreparedStatement ps1 = null;
        
			rs = ps0.executeQuery();
//			System.out.println(list.size()+"...");
			while(rs.next()){
				oldNames.add(rs.getString("oldName"));
				newNames.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("8"+e.getMessage());
		}
//			获得积分
			boolean isFirst = true;
			List<Integer> removeNum = new ArrayList<Integer>();
			for(int i = 0; i < newNames.size(); i++){
				isFirst = true;
				for(int j = 0; j < list.size(); j++){
					if(oldNames.get(i).equals(list.get(j).getName())
							|| newNames.get(i).equals(list.get(j).getName())){
						if(isFirst){
//							System.out.println("oldname:"+oldNames.get(i)+"  "+"name:"+newNames.get(i));
							list.get(j).setName(newNames.get(i));
							getRangeN(oldNames.get(i),sum_sql,map);
							getRangeN(newNames.get(i),sum_sql,map);
							list.get(j).setRange(map.size());
							list.get(j).setOldName(oldNames.get(i));
							list.get(j).setCredit(getnewCredit(oldNames.get(i)) + getnewCredit(newNames.get(i)));
							map.clear();
							isFirst = false;
						}
						else{
							list.remove(j);
						}
					}
				}
			}
//			for(int i = 0; i < removeNum.size(); i++){
//				list.remove(removeNum.get(i));
//			}
			
//			rs.close();
//			ps.close();
//			ps0.close();
		
	}

//	获得新积分
	public double getnewCredit(String name){
		double credit = 0;
		for(int j = 0; j < list.size(); j++){
			if(name.equals(list.get(j).getName()))
				credit += list.get(j).getCredit();
		}
		return credit;
	}
	
//  获得新的合作范围
	public void getRangeN(String name,String sql,Map<String, String> map){
		try{
			String pro[] = new String[20]; 
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs =  null;
			for(int i = 0; i < length; i++){
				ps.setInt(1, getYear()[i]);
				ps.setInt(2, getQuarter()[i]);
				ps.setString(3, name);
				rs = ps.executeQuery();
				if(rs.next()){				
					pro = rs.getString(1).split(",");
					for(int j = 0; j < pro.length ; j++){
						map.put(pro[j], pro[j]);
//						System.out.print(pro[j] +"  ");
					}	
				}
			}
		}catch (SQLException e) {
			System.out.println("6"+e.getMessage());
		}
	}
//	获得星级
	public void getStar1(){
		int numA = 0, numB = 0;
		int num1 = 0, num2 = 0;
//		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getLevel().equals("A") && list.get(i).getRange() != 0)
				numA++;
			if(list.get(i).getLevel().equals("B") && list.get(i).getRange() != 0)
				numB++;
		}
//		System.out.println(numA+"   "+numB);
		star creA[] = new star[numA];
		star creB[] = new star[numB];
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getLevel().equals("A") && list.get(i).getRange() != 0){
				star s = new star();
				s.setCredit0(list.get(i).getCredit());
				s.setIndex(i);
				creA[num1] = s;
				num1++;
			}
			if(list.get(i).getLevel().equals("B") && list.get(i).getRange() != 0){
				star s = new star();
				s.setCredit0(list.get(i).getCredit());
				s.setIndex(i);
				creB[num2] = s;
				num2++;
			}
//			System.out.print(list.get(i).getCredit()+"   ");
		}
		getStar0(creA);
		getStar0(creB);
	}
	
	public void getStar0(star cre[]){
		star temp;
//		冒泡排序，逆序
	    for (int i = cre.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (cre[j + 1].getCredit0() > cre[j].getCredit0()) {
                    temp = cre[j];
                    cre[j] = cre[j + 1];
                    cre[j + 1] = temp;
                }
            }
        }
	    
	    int num;
	    num = cre.length;
	    int star[] = new int[5];//存下标
		star[0] = (int) Math.floor(num*0.1);
		star[1] = (int) Math.floor(num*0.2);
		star[2] = (int) Math.floor(num*0.5);
		star[3] = (int) Math.floor(num*0.7);
		star[4] = (int) Math.floor(num*0.9);
	   
		double flag = -2;
	    for(int i = 0; i<cre.length; i++){
	    	if(flag == cre[i].getCredit0()){
	    		list.get(cre[i].getIndex()).setStar(list.get(cre[i-1].getIndex()).getStar());
	    		continue;
	    	}
	    	else{
	    		flag = cre[i].getCredit0();
	    		if(i < star[0])
		    		list.get(cre[i].getIndex()).setStar(5);
		    	else if(i < star[1])
		    		list.get(cre[i].getIndex()).setStar(4);
		    	else if(i < star[2])
		    		list.get(cre[i].getIndex()).setStar(3);
		    	else if(i < star[3])
		    		list.get(cre[i].getIndex()).setStar(2);
		    	else if(i < star[4])
		    		list.get(cre[i].getIndex()).setStar(1);
		    	else 
		    		list.get(cre[i].getIndex()).setStar(0);
	    	}
	    }
	}
	
//	更新数据库，添加合作范围、动态积分、星级等信息
	public void update(String sql){
//		String sql = "UPDATE gather SET sumRange=?,sumLevel=?,sumStar=?,sumCredit=?,sumCredit1=?," +
//				"sumCredit2=?," +
//		"sumCredit3=?,sumCredit4=?,sumCredit5=?,sumCredit6=?,sumCredit7=?,sumCredit8=? WHERE name=?";
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
//			System.out.println(sql);
			for(int i = 0; i < list.size(); i++){
				ps.setInt(1, list.get(i).getRange());
				ps.setString(2, list.get(i).getLevel());
				ps.setInt(3, list.get(i).getStar());
				ps.setDouble(4, list.get(i).getCredit());
				ps.setDouble(5, list.get(i).getCredit1());
				ps.setDouble(6, list.get(i).getCredit2());
				ps.setDouble(7, list.get(i).getCredit3());
				ps.setDouble(8, list.get(i).getCredit4());
				ps.setDouble(9, list.get(i).getCredit5());
				ps.setDouble(10, list.get(i).getCredit6());
				ps.setDouble(11, list.get(i).getCredit7());
				ps.setDouble(12, list.get(i).getCredit8());
				ps.setString(13, list.get(i).getName());
				ps.executeUpdate();
				
//				System.out.println(list.get(i).getRange()+"  "+list.get(i).getLevel()+"  "+
//						list.get(i).getCredit()+"  "+list.get(i).getCredit1()+"  "+
//						list.get(i).getCredit2()+"  "+list.get(i).getCredit3()+"  "+
//						list.get(i).getCredit4()+"  "+list.get(i).getCredit5()+"   "+
//						list.get(i).getCredit6()+"  "+list.get(i).getCredit7()+"   "+
//						list.get(i).getCredit8()+"  "+list.get(i).getName());
			}
//			if (!ps.isClosed())
				ps.close();
		} catch (SQLException e) {
			System.out.println("7"+e.getMessage());
		}
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int[] getYear() {
		return year;
	}

	public void setYear(int[] year) {
		this.year = year;
	}

	public int[] getQuarter() {
		return quarter;
	}

	public void setQuarter(int[] quarter) {
		this.quarter = quarter;
	}
	
}
