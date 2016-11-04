package com.szh.excelimport.util;

//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.szh.struts2.dao.Database;


public class ImportExcel
{
	 
        public static void main(String[] args) throws Exception
        {

        }
        
        /**
         * �������Excel˳���ȡ��������
         */
      public static int ExcelImp(String fileName) throws Exception
        {

                InputStream importExcelStream = new FileInputStream("D:\\tmp\\upload\\"+fileName); //����·��

                int count=importexcel(importExcelStream,fileName); 

                return count;
        }
      public static int importexcel(InputStream  ExcelStream,String fileName)//(InputStream importExcelStream)
      {
    	    int i=1;
    	    List<Integer> list=new ArrayList<Integer>();
    	       
    	   try{  
    	    //   ������Excel�������ļ�������  
    	    Workbook   workbook;
    		char x=fileName.charAt(fileName.length()-1);
    		if(x=='x')
    		{ workbook =new XSSFWorkbook(ExcelStream);}
    		else
    		{ workbook=new HSSFWorkbook(ExcelStream);}
    	    //   �����Թ���������á�  
    	    //   �����ǰ������ã������Ǽٶ����ű�����ȱʡ��"Sheet1"��  
    	    Sheet   sheet   =   workbook.getSheetAt(0);  //ȱʡ�ĵ�һ������sheet1��ʼ��
    	    //   Ҳ����getSheetAt(int   index)���������ã�  
    	    //   ��Excel�ĵ��У���һ�Ź������ȱʡ������0��  
    	    //   �����Ϊ��HSSFSheet   sheet   =   workbook.getSheetAt(0);  
    	    //   ��ȡ���϶˵�Ԫ  
    	    //int i=1;
    	    boolean flag=true;
    	    
    	    outer:
    	    while(flag){ //��ѭ���в���excel����
    	    
    	    Row   row   =   sheet.getRow(i);  
    	    if(row==null)break;//�ж���������ǿգ��ͽ���ѭ����
    	    Cell  cell0 = row.getCell((short)0); 
    	    if(cell0==null){
    	     cell0 = row.createCell((short)0); 
    	     break outer;
    	    }
    	    cell0.setCellType(Cell.CELL_TYPE_STRING);
    	    if(cell0.getStringCellValue().equals("End"))
    	    {
    	    	break outer;
    	    }
    	    //   �����Ԫ���ݣ�cell.getStringCellValue()����ȡ���ڵ�Ԫ��ֵ  
    	    //System.out.println("���϶˵�Ԫ�ǣ�   "   +   cell0.getStringCellValue());    
    	    Cell cell1=row.getCell((short)1);
    	    if(cell1!=null) cell1.setCellType(Cell.CELL_TYPE_STRING);//�Ƚ�����ת��String��ȡ�����ݡ�
    	    Cell cell2=row.getCell((short)2);
    	    if(cell2!=null) cell2.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell3=row.getCell((short)3);
    	    if(cell3!=null) cell3.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell4=row.getCell((short)4);
    	    if(cell4!=null) cell4.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell5=row.getCell((short)5);
    	    if(cell5!=null) cell5.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell6=row.getCell((short)6);
    	    if(cell6!=null) cell6.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell7=row.getCell((short)7);
    	    if(cell7!=null) cell7.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell8=row.getCell((short)8);
    	    if(cell8!=null) cell8.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell9=row.getCell((short)9);
    	    if(cell9!=null) cell9.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell10=row.getCell((short)10);
    	    if(cell10!=null) cell10.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell11=row.getCell((short)11);
    	    if(cell11!=null) cell11.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell12=row.getCell((short)12);
    	    if(cell12!=null) cell12.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell13=row.getCell((short)13);
    	    if(cell13!=null) cell13.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell14=row.getCell((short)14);
    	    if(cell14!=null) cell14.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell15=row.getCell((short)15);
    	    if(cell15!=null) cell15.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell16=row.getCell((short)16);
    	    if(cell16!=null) cell16.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell17=row.getCell((short)17);
    	    if(cell17!=null) cell17.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell18=row.getCell((short)18);
    	    if(cell18!=null) cell18.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell19=row.getCell((short)19);
    	    if(cell19!=null) cell19.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell20=row.getCell((short)20);
    	    if(cell20!=null) cell20.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell21=row.getCell((short)21);
    	    if(cell21!=null) cell21.setCellType(Cell.CELL_TYPE_STRING);
    	    Cell cell22=row.getCell((short)22);
    	    if(cell22!=null) cell22.setCellType(Cell.CELL_TYPE_STRING);
    	    Database.insert2(cell1.getStringCellValue(),cell2.getStringCellValue(),cell3.getStringCellValue(),cell4.getStringCellValue(),cell5.getStringCellValue(),cell6.getStringCellValue(),cell7.getStringCellValue(),cell8.getStringCellValue(),cell9.getStringCellValue(),cell10.getStringCellValue(),cell11.getStringCellValue(),cell12.getStringCellValue(),cell13.getStringCellValue(),cell14.getStringCellValue(),
    	    		cell15.getStringCellValue(),cell16.getStringCellValue(),cell17.getStringCellValue(),Double.parseDouble(cell18.getStringCellValue()),Double.parseDouble(cell19.getStringCellValue()),Double.parseDouble(cell20.getStringCellValue()),Double.parseDouble(cell21.getStringCellValue()),Double.parseDouble(cell22.getStringCellValue()),0.0,new Timestamp(System.currentTimeMillis()),0);
    	   // Database.insert2(name[1], name[2], name[3],name[4],name[5], name[6],name[7],name[8],name[9],name[10],name[11], name[12], name[13], name[14], name[15], name[16],name[17], Double.parseDouble(name[18]), Double.parseDouble(name[19]), Double.parseDouble(name[20]), Double.parseDouble(name[21]),Double.parseDouble(name[22]),0.0,new Timestamp(System.currentTimeMillis()));
    	    i++;    	 
    	    System.out.println(i);
    	    }
    	    
    	   }catch(Exception   e)   {  
    	    System.out.println("������xlRead()   :   "   +   e   );  
    	   }  
    	   
    	  return (i-1);
      }
     
}