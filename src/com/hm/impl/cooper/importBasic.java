package com.hm.impl.cooper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.entity.company;
import com.hm.org.Database;

public class importBasic {


	Database db = new Database();
	Connection conn = db.getConnection();
	private List<company> exist = new ArrayList<company>();
	private List<company> noexist = new ArrayList<company>();
	
	public void read(InputStream inputStream,int class0)throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		List<company> comlist = new ArrayList<company>();
		int year = 0,quarter = 0;
		String sql = null;
		for(int i = 0; i < 5; i++){
//			System.out.println("i="+i);
			switch(i){
			case 0:
				year = 2011;
				quarter = 4;
				break;
			case 1:
				year = 2012;
				quarter = 3;
				break;
			case 2:
				year = 2012;
				quarter = 4;
				break;
			case 3:
				year = 2013;
				quarter = 1;
				break;
			case 4:
				year = 2013;
				quarter = 2;
				break;
			}//switch
			XSSFSheet sheet = workbook.getSheetAt(i);
			XSSFRow row0 = sheet.getRow(1);
			XSSFCell cell0 = null;
			XSSFRow row = null;
			XSSFCell cell = null;
			String name = null;
			for(int rowNum = 3; ; rowNum++){
//				System.out.print("rowNum="+rowNum);
				row = sheet.getRow(rowNum);
				int cellNum = 1;
				cell = row.getCell(cellNum);
				if(cell.getStringCellValue().equals("-") || cell.getStringCellValue().equals("－"))
					break;
				else{//公司名称存在
					name = cell.getStringCellValue();
					int quarRange = 0;
					cellNum = 3;//quarter
					cell = row.getCell(cellNum);
					try{
						if(cell.getStringCellValue().equals("-") || cell.getStringCellValue().equals("－"))
							quarRange = 0;
					}catch(Exception e){
						quarRange = (int) cell.getNumericCellValue();
					}
					//存在合作省份的才进行计算
					company com = null;
//					System.out.println("quarRange="+quarRange);
					if(quarRange > 0){
						for(cellNum = 5; cellNum < 50; cellNum++){
							cell = row.getCell(cellNum);
							double credit = 0;
							try{
								if(cell.getStringCellValue().equals("-") || cell.getStringCellValue().equals("－"))
									continue;
							}catch(Exception e){
								com = new company();
								com.setYear(year);
								com.setQuarter(quarter);
								com.setName(name);
						
								credit =  cell.getNumericCellValue();
//								System.out.print(" name="+name);	
//								System.out.print(" credit="+credit);
								cell0 = row0.getCell(cellNum);
//								System.out.println(cell0.getStringCellValue() + "省");
							}
							//添加积分，合作省份
							switch(class0){
							case 1:
								com.setSum(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								
								break;	
							case 2:
								com.setTrans(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,trans=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 3:
								com.setWireless(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,wireless=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 4:
								com.setSwitchx(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,switchx=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 5:
								com.setData(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,data=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 6:
								com.setPower(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,power=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 7:
								com.setCivil(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,civil=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							case 8:
								com.setNetwork(credit);
								com.setProvince(cell0.getStringCellValue() + "省");
								sql = "UPDATE company SET province=?,network=? " +
										"where year=? and quarter=? and name=? and province=?";
								break;
							}//switch 添加积分,省份结束
							comlist.add(com);
						}//for(cellNum = 5; cellNum < 50; cellNum++)
					}//if(quarRange > 0)
					//添加没有参与合作的公司，重点是合作省份的处理
					else if(quarRange == 0){
//						System.out.println("--------------------------");
						com = new company();
						com.setName(name);
						com.setYear(year);
						com.setQuarter(quarter);
						com.setProvince("null");
						com.setSum(-1);
						com.setTrans(-1);
						com.setWireless(-1);
						com.setSwitchx(-1);
						com.setData(-1);
						com.setPower(-1);
						com.setCivil(-1);
						com.setNetwork(-1);
//						System.out.println("name="+name+" year="+year+" quarter="+quarter+" province="+com.getProvince());
						comlist.add(com);
					}
					else ;
				}//名称存在
			}//行循环
		}//sheet循环
//		for(int j =0; j < comlist.size(); j++){
//			System.out.println(comlist.get(j).getYear()+" "+comlist.get(j).getQuarter()+"  "+comlist.get(j).getName()
//					+" pro="+comlist.get(j).getProvince());
//		}
		if(class0 == 1){
			for(int j =0; j < comlist.size(); j++){
				comlist.get(j).setTrans(-1);
				comlist.get(j).setWireless(-1);
				comlist.get(j).setSwitchx(-1);
				comlist.get(j).setData(-1);
				comlist.get(j).setPower(-1);
				comlist.get(j).setCivil(-1);
				comlist.get(j).setNetwork(-1);
			}
			comWrite comw = new comWrite();
			comw.write(comlist);
		}
		else{
			isExist(comlist);
			if(noexist.size() != 0){
				for(int j = 0; j < noexist.size(); j++){
					noexist.get(j).setSum(-1);
					if(class0 != 2)
						noexist.get(j).setTrans(-1);
					if(class0 != 3)
						noexist.get(j).setWireless(-1);
					if(class0 != 4)
						noexist.get(j).setSwitchx(-1);
					if(class0 != 5)
						noexist.get(j).setData(-1);
					if(class0 != 6)
						noexist.get(j).setPower(-1);
					if(class0 != 7)
						noexist.get(j).setCivil(-1);
					if(class0 != 8)
						noexist.get(j).setNetwork(-1);
				}
				insert(noexist);
			}
			if(exist.size() != 0)
				update(exist,sql,class0);
		}
		try {
			if (!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("0"+e.getMessage());
		}
		comlist.clear();
		exist.clear();
		noexist.clear();
	}
	
//	判断数据是否已经存在，不存在进行插入，存在进行更新
	public void isExist(List<company> com){
		List<company> sqlList = new ArrayList<company>();
		
		String sql ="select year,quarter,name,province from company";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//			将数据库中的记录存到list中，方便比较
			while(rs.next()){
				company sqlcom = new company();
				sqlcom.setYear(rs.getInt(1));
				sqlcom.setQuarter(rs.getInt(2));
				sqlcom.setName(rs.getString(3));
				sqlcom.setProvince(rs.getString(4));
				sqlList.add(sqlcom);
			}
		} catch (SQLException e) {
			System.out.println("1"+e.getMessage());
		}
		
		int j ;
		for(int i = 0; i < com.size(); i++){
			j = 0;
			for(j = 0; j < sqlList.size(); j++){
//				if判断是否存在
				if(com.get(i).getYear() == sqlList.get(j).getYear()
						&& com.get(i).getQuarter() == sqlList.get(j).getQuarter()
						&& com.get(i).getName().equals(sqlList.get(j).getName())){
//					均为空时
					if(sqlList.get(j).getProvince().equals("null") && com.get(i).getProvince().equals("null"))
						break;
					else if(sqlList.get(j).getProvince().equals("null") && !com.get(i).getProvince().equals("null")){
						exist.add(com.get(i));//需要将省份更新
						System.out.println("更新name="+com.get(i).getName()+" p="+com.get(i).getProvince());
						break;
					}
					else if(!sqlList.get(j).getProvince().equals("null") && com.get(i).getProvince().equals("null"))
						break;
					else {
						if(sqlList.get(j).getProvince().equals(com.get(i).getProvince())){
							exist.add(com.get(i));
							break;
						}
					}
				}//if判断是否存在      
			}//for  j
			if(j == sqlList.size()){
				noexist.add(com.get(i));//不存在
//				System.out.println("不存在name="+com.get(i).getName()+" p="+com.get(i).getProvince());
			}
		}
		
	}
	
//		传入的数据不存在的时候进行插入
		public void insert(List<company> list){
			String sql = "INSERT INTO company(year,quarter,name,province,sum,trans,wireless,switchx,data,power,civil,network)" +
					" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			int i = 0;
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				for(i = 0; i< list.size(); i++){

					ps.setInt(1, list.get(i).getYear());
					ps.setInt(2, list.get(i).getQuarter());
					ps.setString(3, list.get(i).getName());
					ps.setString(4, list.get(i).getProvince());
					ps.setDouble(5, list.get(i).getSum());
					ps.setDouble(6, list.get(i).getTrans());
					ps.setDouble(7, list.get(i).getWireless());
					ps.setDouble(8, list.get(i).getSwitchx());
					ps.setDouble(9, list.get(i).getData());
					ps.setDouble(10, list.get(i).getPower());
					ps.setDouble(11, list.get(i).getCivil());
					ps.setDouble(12, list.get(i).getNetwork());
					ps.executeUpdate();
					
				}
//				if (!ps.isClosed())
//					ps.close();
			}catch(SQLException e){
				System.out.println("2"+e.getMessage());
			}
		}
		
//		传入已存在的数据时，进行Update
		public void update(List<company> exist,String sql,int class0){
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				for(int i = 0; i< exist.size(); i++){
				
					ps.setString(1, exist.get(i).getProvince());
					if(class0 == 2)
						ps.setDouble(2, exist.get(i).getTrans());
					else if(class0 == 3)
						ps.setDouble(2, exist.get(i).getWireless());
					else if(class0 == 4)
						ps.setDouble(2, exist.get(i).getSwitchx());
					else if(class0 == 5)
						ps.setDouble(2, exist.get(i).getData());
					else if(class0 == 6)
						ps.setDouble(2, exist.get(i).getPower());
					else if(class0 == 7)
						ps.setDouble(2, exist.get(i).getCivil());
					else
						ps.setDouble(2, exist.get(i).getNetwork());
					
					ps.setInt(3, exist.get(i).getYear());
					ps.setInt(4, exist.get(i).getQuarter());
					ps.setString(5, exist.get(i).getName());
					ps.setString(6, exist.get(i).getProvince());
					ps.executeUpdate();
					
				}
//				if (!ps.isClosed())
//					ps.close();
			}catch(SQLException e){
				System.out.println("3"+e.getMessage());
			}
		}
		
}
