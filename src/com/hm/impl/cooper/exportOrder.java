package com.hm.impl.cooper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.org.Database;

public class exportOrder {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	int starNum[][] = new int[7][16];//�洢������ĸ���
	
	public XSSFWorkbook export(int order){
		System.out.println("export");
		String sql;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet ;
		if(order == 1){
			sheet = workbook.createSheet("�ۺ�����");
			sql = "SELECT name,sumRange,sumLevel,sumStar,transRange,transLevel,transStar" +
			",wirelessRange,wirelessLevel,wirelessStar,switchxRange,switchxLevel,switchxStar" +
			",dataRange,dataLevel,dataStar,powerRange,powerLevel,powerStar," +
			"civilRange,civilLevel,civilStar,networkRange,networkLevel,networkStar" +
			" FROM gather ORDER BY sumLevel,sumStar desc";
			}
		else{
			sheet = workbook.createSheet("��˾����");
			sql = "SELECT name,sumRange,sumLevel,sumStar,transRange,transLevel,transStar" +
			",wirelessRange,wirelessLevel,wirelessStar,switchxRange,switchxLevel,switchxStar" +
			",dataRange,dataLevel,dataStar,powerRange,powerLevel,powerStar," +
			"civilRange,civilLevel,civilStar,networkRange,networkLevel,networkStar" +
			" FROM gather ORDER BY name";
		}
		
		XSSFCellStyle style = workbook.createCellStyle();// ���и�ʽ
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);

		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		
		
		int rowNum = 0,cellNum = 1;
//		��0�б���
		XSSFRow row = sheet.createRow(rowNum);
		XSSFCell cell = row.createCell(cellNum);
		cell.setCellValue("������˾��̬�Ǽ������ۺ�&רҵ�������");
		XSSFCellStyle style3 = workbook.createCellStyle();// ���и�ʽ
		style3.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		XSSFFont font1 = workbook.createFont();// ����
		font1.setFontHeightInPoints((short) 16);
		font1.setBold(true);
		style3.setFont(font1);
		cell.setCellStyle(style3);

//		���������������ݵĳ�ʼ��
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 16; j++)
				starNum[i][j] = 0;
		}

//		��������
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int num = 1;
			rowNum = 11;
			while(rs.next()){

				cellNum = 1;
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum);
				cell.setCellValue(num);
				cell.setCellStyle(style);

				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(rs.getString("name"));
				cell.setCellStyle(style1);

				cellNum++;
				cell = row.createCell(cellNum);
				
//				�ۺ�
				cellNum++;
				write(cellNum, row, cell, style, rs.getInt("sumRange"), rs.getString("sumLevel"), rs.getInt("sumStar"));
				getNum(0,rs.getString("sumLevel"),rs.getInt("sumStar"));
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("transRange"), rs.getString("transLevel"), rs.getInt("transStar"));
				getNum(1,rs.getString("transLevel"),rs.getInt("transStar"));
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("wirelessRange"), rs.getString("wirelessLevel"), rs.getInt("wirelessStar"));
				getNum(2,rs.getString("wirelessLevel"),rs.getInt("wirelessStar"));			
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("switchxRange"), rs.getString("switchxLevel"), rs.getInt("switchxStar"));
				getNum(3,rs.getString("switchxLevel"),rs.getInt("switchxStar"));		
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("dataRange"), rs.getString("dataLevel"), rs.getInt("dataStar"));
				getNum(4,rs.getString("dataLevel"),rs.getInt("dataStar"));			
				
//				��Դ
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("powerRange"), rs.getString("powerLevel"), rs.getInt("powerStar"));
				getNum(5,rs.getString("powerLevel"),rs.getInt("powerStar"));		
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("civilRange"), rs.getString("civilLevel"), rs.getInt("civilStar"));
				getNum(6,rs.getString("civilLevel"),rs.getInt("civilStar"));	
				
//				����
				cellNum +=2;
				write(cellNum, row, cell, style, rs.getInt("networkRange"), rs.getString("networkLevel"), rs.getInt("networkStar"));
				getNum(7,rs.getString("networkLevel"),rs.getInt("networkStar"));
				
//				System.out.println("num="+num+",rowNum="+rowNum+",cellNum="+cellNum);
				rowNum++;
				num++;
			}
			
//			��һ��
			row = sheet.createRow(1);
			String[] header1 = { "���", "������˾", "��Χ���"};
			String[] header2 = {"�ۺ�����", "����", "����",
					"����", "����", "��Դ", "����", "����" };
			for(cellNum = 1; cellNum <= header1.length; cellNum++){
				cell = row.createCell(cellNum);
				cell.setCellValue(header1[cellNum-1]);
				cell.setCellStyle(style);
			}
			int f = 0;
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				cell.setCellValue(header2[f]);
				cell.setCellStyle(style);
				f++;
				cellNum++;
			}
//			��2��
			row = sheet.createRow(2);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				cell.setCellValue("�ּ�");
				cell.setCellStyle(style);
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("�Ǽ�");
				cell.setCellStyle(style);
			}
//			��3��
			row = sheet.createRow(3);
			cell = row.createCell(3);
			cell.setCellValue("�Ǽ�");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				cell.setCellValue("A��");
				cell.setCellStyle(style);
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue("B��");
				cell.setCellStyle(style);
			}
//			��4��
			row = sheet.createRow(4);
			cell = row.createCell(3);
			cell.setCellValue("������");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[0][cellNum-4] != 0){
					cell.setCellValue(starNum[0][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			��5��
			row = sheet.createRow(5);
			cell = row.createCell(3);
			cell.setCellValue("�����");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[1][cellNum-4] != 0){
					cell.setCellValue(starNum[1][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			��6��
			row = sheet.createRow(6);
			cell = row.createCell(3);
			cell.setCellValue("����");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[2][cellNum-4] != 0){
					cell.setCellValue(starNum[2][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			��7��
			row = sheet.createRow(7);
			cell = row.createCell(3);
			cell.setCellValue("���");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[3][cellNum-4] != 0){
					cell.setCellValue(starNum[3][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			��8��
			row = sheet.createRow(8);
			cell = row.createCell(3);
			cell.setCellValue("��");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[4][cellNum-4] != 0){
					cell.setCellValue(starNum[4][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			��9��
			row = sheet.createRow(9);
			cell = row.createCell(3);
			cell.setCellValue("��");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[5][cellNum-4] != 0){
					cell.setCellValue(starNum[5][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
			for(int i = 0; i < 16; i++){
				for(int j = 0; j < 6; j++)
					starNum[6][i] += starNum[j][i];
			}
//			��10��
			row = sheet.createRow(10);
			cell = row.createCell(3);
			cell.setCellValue("�ϼ�");
			cell.setCellStyle(style);
			for(cellNum = 4; cellNum < 20; cellNum++){
				cell = row.createCell(cellNum);
				if(starNum[6][cellNum-4] != 0){
					cell.setCellValue(starNum[6][cellNum-4]);
					cell.setCellStyle(style);
				}
				else{
					cell.setCellValue(" ");
					cell.setCellStyle(style);
				}
			}
//			�ϲ���Ԫ��
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 11));
			sheet.addMergedRegion(new CellRangeAddress(1, 10, 1, 1));
			sheet.addMergedRegion(new CellRangeAddress(1, 10, 2, 2));
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
				System.out.println(e.getMessage());
		}
		return workbook;
	}	
	
//	�˸�С��ľ������ݵ���д
	public void write(int cellNum,XSSFRow row,XSSFCell cell,XSSFCellStyle style,int range,String level,int star){
		cell = row.createCell(cellNum);
		if(range != 0){
			cell.setCellValue(level);
			cell.setCellStyle(style);
		}
		else{
			cell.setCellValue("-");
			cell.setCellStyle(style);
		}
		cellNum++;
		cell = row.createCell(cellNum);
		if(range != 0){
			cell.setCellValue(getStar(star));
			cell.setCellStyle(style);
		}
		else{
			cell.setCellValue("-");
			cell.setCellStyle(style);
		}
	}
	
	
//	��ö�̬�Ǽ�
	public String getStar(int star){
		String str = null;
		switch(star){
		case 0:
			str = "��";
			break;
		case 1:
			str = "��";
			break;
		case 2:
			str = "���";
			break;
		case 3:
			str = "����";
			break;
		case 4:
			str = "�����";
			break;
		case 5:
			str = "������";
			break;
		}
		return str;
	}
//	��ø����Ǽ�������
	public void getNum(int flag,String level,int star){
		if(level.equals("A")){
			switch(star){
			case 0:
				starNum[5][flag*2]++;
				break;
			case 1:
				starNum[4][flag*2]++;
				break;
			case 2:
				starNum[3][flag*2]++;
				break;
			case 3:
				starNum[2][flag*2]++;
				break;
			case 4:
				starNum[1][flag*2]++;
				break;
			case 5:
				starNum[0][flag*2]++;
				break;
			}
		}
		if(level.equals("B")){
			switch(star){
			case 0:
				starNum[5][flag*2+1]++;
				break;
			case 1:
				starNum[4][flag*2+1]++;
				break;
			case 2:
				starNum[3][flag*2+1]++;
				break;
			case 3:
				starNum[2][flag*2+1]++;
				break;
			case 4:
				starNum[1][flag*2+1]++;
				break;
			case 5:
				starNum[0][flag*2+1]++;
				break;
			}
		}
	}
}
