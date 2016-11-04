package com.hm.impl.cooper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.org.Database;

public class exportCre {
	

	Database db = new Database();
	Connection conn = db.getConnection();
	int numOp = 0;//参与合作的公司数量
	int numA = 0;//A级公司数量
	
	public XSSFWorkbook export_year(int class0){
//		sql查询参与合作的公司数量；sql0查询A级公司数量；sql2查询年份季度；sql3查询具体内容,sql4,sql5查询没有没有参与合作的公司名称
		String sql,sql0,sql2 = null,sql3=null,sql4=null,sql5=null;
		sql4 = "SELECT DISTINCT name FROM company WHERE name NOT IN (SELECT DISTINCT name FROM gather) ";
		switch(class0){
		case 1:
			sql = "SELECT COUNT(*) FROM gather WHERE sumRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE sumRange!=0 AND sumLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE sumProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,sumRange,sumLevel,sumStar,sumCredit,sumCredit1,sumCredit2,sumCredit3,sumCredit4," +
					"sumCredit5,sumCredit6,sumCredit7,sumCredit8 FROM gather WHERE sumRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE sumRange=0 ";
			getNum(sql,sql0);
			break;
		case 2:
			sql = "SELECT COUNT(*) FROM gather WHERE transRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE transRange!=0 AND transLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE transProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,transRange,transLevel,transStar,transCredit,transCredit1,transCredit2,transCredit3,transCredit4," +
					"transCredit5,transCredit6,transCredit7,transCredit8 FROM gather WHERE transRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE transRange=0 ";
			getNum(sql,sql0);
			break;
		case 3:
			sql = "SELECT COUNT(*) FROM gather WHERE wirelessRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE wirelessRange!=0 AND wirelessLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE wirelessProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,wirelessRange,wirelessLevel,wirelessStar,wirelessCredit,wirelessCredit1," +
					"wirelessCredit2,wirelessCredit3,wirelessCredit4," +
					"wirelessCredit5,wirelessCredit6,wirelessCredit7,wirelessCredit8 FROM gather WHERE wirelessRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE wirelessRange=0 ";
			getNum(sql,sql0);
			break;
		case 4:
			sql = "SELECT COUNT(*) FROM gather WHERE switchxRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE switchxRange!=0 AND switchxLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE switchxProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,switchxRange,switchxLevel,switchxStar,switchxCredit,switchxCredit1,switchxCredit2,switchxCredit3,switchxCredit4," +
				"switchxCredit5,switchxCredit6,switchxCredit7,switchxCredit8 FROM gather WHERE switchxRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE switchxRange=0 ";
			getNum(sql,sql0);
			break;
		case 5:
			sql = "SELECT COUNT(*) FROM gather WHERE dataRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE dataRange!=0 AND dataLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE dataProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,dataRange,dataLevel,dataStar,dataCredit,dataCredit1,dataCredit2,dataCredit3,dataCredit4," +
				"dataCredit5,dataCredit6,dataCredit7,dataCredit8 FROM gather WHERE dataRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE dataRange=0 ";
			getNum(sql,sql0);
			
			break;
		case 6:
			sql = "SELECT COUNT(*) FROM gather WHERE powerRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE powerRange!=0 AND powerLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE powerProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,powerRange,powerLevel,powerStar,powerCredit,powerCredit1,powerCredit2,powerCredit3,powerCredit4," +
				"powerCredit5,powerCredit6,powerCredit7,powerCredit8 FROM gather WHERE powerRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE powerRange=0 ";
			getNum(sql,sql0);
			break;
		case 7:
			sql = "SELECT COUNT(*) FROM gather WHERE civilRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE civilRange!=0 AND civilLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE civilProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,civilRange,civilLevel,civilStar,civilCredit,civilCredit1,civilCredit2,civilCredit3,civilCredit4," +
				"civilCredit5,civilCredit6,civilCredit7,civilCredit8 FROM gather WHERE civilRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE civilRange=0 ";
			getNum(sql,sql0);
			break;
		case 8:
			sql = "SELECT COUNT(*) FROM gather WHERE networkRange!=0";
			sql0 = "SELECT COUNT(*) FROM gather WHERE networkRange!=0 AND networkLevel='A'";
			sql2 = " SELECT DISTINCT year,quarter FROM quar_cre  WHERE networkProvince!='null' ORDER BY year DESC,quarter DESC";
			sql3 = "SELECT name,networkRange,networkLevel,networkStar,networkCredit,networkCredit1,networkCredit2,networkCredit3,networkCredit4," +
				"networkCredit5,networkCredit6,networkCredit7,networkCredit8 FROM gather WHERE networkRange!=0";
			sql5 = "SELECT DISTINCT name FROM gather WHERE networkRange=0 ";
			getNum(sql,sql0);
			break;
		}
		int num = 0,numB = numOp - numA;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM gather");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				num = rs.getInt(1);
		} catch (SQLException e) {
				System.out.println("2"+e.getMessage());
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("星级评定");
		
		XSSFCellStyle style = workbook.createCellStyle();// 居中格式
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);;
		
		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		
//		第6行标题
		XSSFRow row = sheet.createRow(6);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("合作公司");
		cell.setCellStyle(style);
//		cell = row.createCell(2);
//		cell.setCellValue("合作公司改名前");
//		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("当年合作范围");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("A级（3省区及以上）");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("B级（少于3省区）");
		cell.setCellStyle(style);
		
		XSSFRow row0 = sheet.createRow(7);
		XSSFCell cell0 = row0.createCell(3);
		cell0.setCellValue("动态星级");
		cell0.setCellStyle(style);
		cell0 = row0.createCell(4);
		cell0.setCellValue("动态积分");
		cell0.setCellStyle(style);
		cell0 = row0.createCell(5);
		cell0.setCellValue("动态星级");
		cell0.setCellStyle(style);
		cell0 = row0.createCell(6);
		cell0.setCellValue("动态积分");
		cell0.setCellStyle(style);
		int i= 0;
		try {
			cell0 = row.createCell(7);
			int year = 0;
			PreparedStatement ps0 = conn.prepareStatement(sql2);
			ResultSet rs0 = ps0.executeQuery();
			while(rs0.next() && i < 8){
				if(year != rs0.getInt(1)){
					year = rs0.getInt(1);
					cell = row.createCell(7 + i);
					cell.setCellValue(year);
					cell.setCellStyle(style);
				}
				else
					cell.setCellStyle(style);
				cell0 = row0.createCell(7 + i);
				switch(rs0.getInt(2)){
				case 1:
					cell0.setCellValue("一季度");
					cell0.setCellStyle(style);
					break;
				case 2:
					cell0.setCellValue("二季度");
					cell0.setCellStyle(style);
					break;
				case 3:
					cell0.setCellValue("三季度");
					cell0.setCellStyle(style);
					break;
				case 4:
					cell0.setCellValue("四季度");
					cell0.setCellStyle(style);
					break;
				}
				i++;
			}
			rs0.close();
			ps0.close();
		} catch (SQLException e) {
				System.out.println("1"+e.getMessage());
		}
//		合并单元格
		sheet.addMergedRegion(new CellRangeAddress(6, 7, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(6, 7, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(6, 7, 2, 2));
//		sheet.addMergedRegion(new CellRangeAddress(6, 7, 3, 3));
		
//		具体内容
//		sql3 = "SELECT name,sumRange,sumLevel,sumStar,sumCredit,sumCredit1,sumCredit2,sumCredit3,sumCredit4" +
//		",sumCredit5,sumCredit6,sumCredit7,sumCredit8 FROM gather WHERE sumRange!=0";
		int numA5=0,numA4=0,numA3=0,numA2=0,numA1=0,numA0=0;
		int numB5=0,numB4=0,numB3=0,numB2=0,numB1=0,numB0=0;
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql3);
			ResultSet rs1 = ps1.executeQuery();
			int rowNum = 8,cellNum ;
			while(rs1.next()){
				cellNum = 0;
				row = sheet.createRow(rowNum);
//				序号
				cell = row.createCell(cellNum);
				cell.setCellValue(rowNum-7);
				cell.setCellStyle(style);
//				公司名称
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(rs1.getString(1));
				cell.setCellStyle(style1);

//				合作公司改名前
//				cellNum++;
//				cell = row.createCell(cellNum);
//				cell.setCellValue(rs1.getString(1));
//				cell.setCellStyle(style1);
				
//				合作范围
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(rs1.getInt(2));
				cell.setCellStyle(style);
//				A/B级
				//for语句保证每个单元格都能划线
				XSSFCell cellA = cell;
				for(int ii = 0; ii < 4; ii++){
					cellNum++;
					cellA = row.createCell(cellNum);
					cellA.setCellValue(" ");
					cellA.setCellStyle(style);
				}
				cellNum -= 4;
				
				if(rs1.getString(3).equals("A")){
					switch(rs1.getInt(4)){
					case 0:
						numA0++;
						break;
					case 1:
						numA1++;
						break;
					case 2:
						numA2++;
						break;
					case 3:
						numA3++;
						break;
					case 4:
						numA4++;
						break;
					case 5:
						numA5++;
						break;
					}
					cellNum++;
					cell = row.createCell(cellNum);
					cellNum++;
					cellA = row.createCell(cellNum);
				}
				if(rs1.getString(3).equals("B")){
					switch(rs1.getInt(4)){
					case 0:
						numB0++;
						break;
					case 1:
						numB1++;
						break;
					case 2:
						numB2++;
						break;
					case 3:
						numB3++;
						break;
					case 4:
						numB4++;
						break;
					case 5:
						numB5++;
						break;
					}
					cellNum += 3; 
					cell = row.createCell(cellNum);
					
					cellNum++;
					cellA = row.createCell(cellNum);
				}
				cell.setCellValue(getStar(rs1.getInt(4)));
				cell.setCellStyle(style);
				cellA.setCellValue(rs1.getDouble(5));
				cellA.setCellStyle(style);

//				各季度分
				int flag = 6;
				cellNum = 6;
				for(int j = 0; j < i; j++){//i表示最近有几个季度
					cellNum++;
					cell = row.createCell(cellNum);
					if(rs1.getDouble(flag) != -1)
						cell.setCellValue(rs1.getDouble(flag));
					else
						cell.setCellValue(" ");
					
					cell.setCellStyle(style);
//					else
//						cell.setCellValue("-");
//					System.out.println(14-flag);
					flag++;
				}	
				rowNum++;
//				rs1.close();
//				ps1.close();
			}
			
//			没有参加合作的公司的名单
			
			PreparedStatement ps2 = conn.prepareStatement(sql4);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				cellNum = 0;
				row = sheet.createRow(rowNum);
//				序号
				cell = row.createCell(cellNum);
				cell.setCellValue(rowNum-7);
				cell.setCellStyle(style);
//				公司名称
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(rs2.getString(1));
				cell.setCellStyle(style);
				rowNum++;
			}
			ps2 = conn.prepareStatement(sql5);
			rs2 = ps2.executeQuery();
			while(rs2.next()){
				cellNum = 0;
				row = sheet.createRow(rowNum);
//				序号
				cell = row.createCell(cellNum);
				cell.setCellValue(rowNum-7);
				cell.setCellStyle(style);
//				公司名称
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(rs2.getString(1));
				cell.setCellStyle(style);
				rowNum++;
			}
			
//			第0行
			row = sheet.createRow(0);
			cell = row.createCell(1);
			cell.setCellValue("合作公司参与合作情况");
			cell.setCellStyle(style);
			
			cell = row.createCell(3);
			cell.setCellValue("★★★★★");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA5);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB5);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("0~10%");
			cell.setCellStyle(style);
//			第1行
			row = sheet.createRow(1);
			cell = row.createCell(1);
			cell.setCellValue("入围公司数量");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(num);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("★★★★");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA4);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB4);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("10~20%");
			cell.setCellStyle(style);
//			第2行
			row = sheet.createRow(2);
			cell = row.createCell(1);
			cell.setCellValue("参与过合作的公司数量");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(numOp);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("★★★");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA3);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB3);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("20~50%");
			cell.setCellStyle(style);
//			第3行
			row = sheet.createRow(3);
			cell = row.createCell(1);
			cell.setCellValue("参与过合作的A级公司数量");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(numA);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("★★");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA2);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB2);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("50~70%");
			cell.setCellStyle(style);
//			第4行
			row = sheet.createRow(4);
			cell = row.createCell(1);
			cell.setCellValue("参与过合作的B级公司数量");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(numOp-numA);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("★");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA1);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB1);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("70~90%");
			cell.setCellStyle(style);
//			第五行
			row = sheet.createRow(5);
			cell = row.createCell(1);
			cell.setCellValue("从未参与合作的公司数量");
			cell.setCellStyle(style);
			cell = row.createCell(2);
			cell.setCellValue(num-numOp);
			cell.setCellStyle(style);
			cell = row.createCell(3);
			cell.setCellValue("☆");
			cell.setCellStyle(style);
			
			cell = row.createCell(4);
			cell.setCellValue(numA0);
			cell.setCellStyle(style);
			cell = row.createCell(5);
			cell.setCellValue(numB0);
			cell.setCellStyle(style);
			cell = row.createCell(6);
			cell.setCellValue("90~100%");
			cell.setCellStyle(style);
			rs2.close();
			ps2.close();
		} catch (SQLException e) {
				System.out.println("3"+e.getMessage());
		}
		return workbook;
		
	}
	
//	获得参与合作的公司数量
	public void getNum(String sql,String sql0){
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				numOp = rs.getInt(1);
			ps = conn.prepareStatement(sql0);
			rs = ps.executeQuery();
			if(rs.next())
				numA = rs.getInt(1);
//			if(!ps.isClosed())
			rs.close();
			ps.close();
		} catch (SQLException e) {
				System.out.println(" 4"+e.getMessage());
		}
	}
	
//	获得动态星级
	public String getStar(int star){
		String str = null;
		switch(star){
		case 0:
			str = "☆";
			break;
		case 1:
			str = "★";
			break;
		case 2:
			str = "★★";
			break;
		case 3:
			str = "★★★";
			break;
		case 4:
			str = "★★★★";
			break;
		case 5:
			str = "★★★★★";
			break;
		}
		return str;
	}
}
