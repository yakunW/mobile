package com.hm.impl.cooper;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.entity.credit;


public class export_AnyCre {
	public XSSFWorkbook exAny(int year,int quarter){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("星级评定");
		cal_StarLevel cal = new cal_StarLevel();
		
		cal.cal("any",year,quarter,sheet);
		
		export_year(workbook,sheet);
		
		return workbook;
	}
	public void export_year(XSSFWorkbook workbook,XSSFSheet sheet){
//		格式
		XSSFCellStyle style = workbook.createCellStyle();// 居中格式
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

//	          第1行
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell ;
		int cellNum = 0;
		String h1[] = {"序号", "合作公司","合作公司曾用名"};
		String h2[] = {"综合排名", "传输", "无线","交换", "数据", "电源", "土建", "网优" };
		for(cellNum = 0; cellNum < h1.length; cellNum++){
			cell = row.createCell(cellNum);
			cell.setCellValue(h1[cellNum]);
			cell.setCellStyle(style);
		}
		int f = 0;
		for(cellNum = 3; cellNum < 25; cellNum++){
			cell = row.createCell(cellNum);
			cell.setCellValue(h2[f]);
			cell.setCellStyle(style);
			f++;
			cellNum += 2;
		}
//		第2行
		row = sheet.createRow(1);
		for(cellNum = 3; cellNum < 25; cellNum++){
			cell = row.createCell(cellNum);
			cell.setCellValue("分级");
			cell.setCellStyle(style);
			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue("星级");
			cell.setCellStyle(style);
			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue("动态积分");
			cell.setCellStyle(style);
		}
	}
	
//	名称
	public void wriName(List<credit> list,XSSFSheet sheet){
//		System.out.println("test???;");
		int cellNum ,rowNum = 2;
		XSSFRow row ;
		XSSFCell cell;
//		System.out.println(list.size()+"??????????/");
		for(int i = 0; i < list.size(); i++){
			cellNum = 0;
			row = sheet.createRow(rowNum);
//			序号
			cell = row.createCell(cellNum);
			cell.setCellValue(i + 1);
//			名称
			cellNum++;
            cell = row.createCell(cellNum);
			cell.setCellValue(list.get(i).getName());
//			曾用名
			cellNum++;
			cell = row.createCell(cellNum);
			cell.setCellValue(list.get(i).getOldName());
//			System.out.println(list.get(i).getName());
			rowNum++;
		}
	}
	
//	分类写
	public void write(List<credit> list,XSSFSheet sheet,int cellNum0){
		int cellNum = cellNum0,rowNum = 2;
		XSSFRow row ;
		XSSFCell cell;
//		System.out.println(cellNum0+"   "+list.size());
		for(int i = 0; i < list.size(); i++){
			cellNum = cellNum0;
			row = sheet.getRow(rowNum);
			if(list.get(i).getCredit() != -1){
//				分级
				cell = row.createCell(cellNum);
				cell.setCellValue(list.get(i).getLevel());
//				cell.setCellStyle(style1);
//				星级
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue( getStar(list.get(i).getStar()));
//				cell.setCellStyle(style);
//				动态积分
				cellNum++;
				cell = row.createCell(cellNum);
				cell.setCellValue(list.get(i).getCredit());
//				cell.setCellStyle(style1);
			}
//			System.out.println(list.get(i).getLevel()+"  "+list.get(i).getStar()+"  "+list.get(i).getCredit());
			rowNum++;
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
