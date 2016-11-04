package com.hm.impl.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hm.org.Database;

public class hourR {
	private static String mes;
//	static employee sco = new employee();
	static Database db = new Database();
	static Connection conn = db.getConnection();
	static PreparedStatement ps = null;
	static PreparedStatement ps0 = null;
	static PreparedStatement ps1 = null;
	static PreparedStatement ps2 = null;

	/*-----------------固定表头的判断--------------------*/
	public static boolean readH(XSSFSheet sheet,String pc)
			throws Exception {
//		System.out.println("Title判断");
		mes = "";
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		/*-----------------判断工时表的新旧格式--------------------*/
		if(cell.getStringCellValue().equals("项目编号"))
			mes = mes + hourOld.contentH(sheet,pc);
		else
			mes = mes + hourNew.contentH(sheet,pc);
	
		setMes(mes);

		if(mes.equals(""))
			return true;
		else 
			return false;
	}

	/*-------------------------------------------获取总行数----------------------------------*/
	public static int getnumRow(XSSFSheet sheet) {
		int numRow = 0;
		for (int numRows = 1;; numRows++) {
			XSSFRow row0 = sheet.getRow(numRows);
			if (common.isBlankRow(row0))
				break;
			else
				numRow = numRows;
		}
		return numRow;
	}
	
	public static String getMes() {
		return mes;
	}

	public static void setMes(String mes) {
		hourR.mes = mes;
	}

}
