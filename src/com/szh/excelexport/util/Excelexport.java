package com.szh.excelexport.util;
 
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.ss.usermodel.*;

import com.szh.struts2.bean.Data;

import java.io.FileOutputStream;  
import java.io.OutputStream;
import java.util.List;

public class Excelexport {

	public   static   String   outputFile="C:\\Users\\sun\\Desktop\\data.xlsx";  
	   public   static   void   main(String   argv[])  
	      {  
		   //SexportExcel();
	      }  
	   public static void exportExcel(List<Data> dlist, OutputStream   fOut)
	   {
		   try  
		      {  
		    //   �����µ�Excel   ������  
			   Workbook workbook = new XSSFWorkbook(); 
		    //   ��Excel�������н�һ����������Ϊȱʡֵ  
		    //   ��Ҫ�½�һ��Ϊ"Ч��ָ��"�Ĺ����������Ϊ��  
		    //   HSSFSheet   sheet   =   workbook.createSheet("Ч��ָ��");  
		    Sheet   sheet = workbook.createSheet();  
		    
		    
		    //   ������0��λ�ô����У���˵��У�  
		    Row   row   =   sheet.createRow((short)0);  
		    
		    //������0��λ�ô�����Ԫ�����϶ˣ�  
		    Cell   cell   =   row.createCell((short)0);  
		    //   ���嵥Ԫ��Ϊ�ַ�������  
		    cell.setCellType(XSSFCell.CELL_TYPE_STRING);  
		    //   �ڵ�Ԫ��������һЩ����  
		    cell.setCellValue("��Ŀ���");
		    Cell   cell1   =   row.createCell((short)1);  		   
		    cell1.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell1.setCellValue("��Ŀ����");
		    Cell   cell2   =   row.createCell((short)2);  		   
		    cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell2.setCellValue("��Ŀʡ��");
		    Cell   cell3   =   row.createCell((short)3);  		   
		    cell3.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell3.setCellValue("���赥λ");
		    Cell   cell4   =   row.createCell((short)4);  		   
		    cell4.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell4.setCellValue("ҵ�����������/��Ժ��");
		    Cell   cell5   =   row.createCell((short)5);  		   
		    cell5.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell5.setCellValue("�ƻ�����");
		    Cell   cell6   =   row.createCell((short)6);  		   
		    cell6.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell6.setCellValue("�ͻ�����");
		    Cell   cell7   =   row.createCell((short)7);  		   
		    cell7.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell7.setCellValue("��Ŀ������");
		    Cell   cell8   =   row.createCell((short)8);  		   
		    cell8.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell8.setCellValue("��רҵ");
		    Cell   cell9   =   row.createCell((short)9);  		   
		    cell9.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell9.setCellValue("����");
		    Cell   cell10   =   row.createCell((short)10);  		   
		    cell10.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell10.setCellValue("����");
		    Cell   cell11   =   row.createCell((short)11);  		   
		    cell11.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell11.setCellValue("С��");
		    Cell   cell12   =   row.createCell((short)12);  		   
		    cell12.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell12.setCellValue("����");
		    Cell   cell13   =   row.createCell((short)13);  		   
		    cell13.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell13.setCellValue("��Ʒ");
		    Cell   cell14   =   row.createCell((short)14);  		   
		    cell14.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell14.setCellValue("��Ʒ�����ĳ���רҵ");
		    Cell   cell15   =   row.createCell((short)15);  		   
		    cell15.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell15.setCellValue("������λ");
		    Cell   cell16   =   row.createCell((short)16);  		   
		    cell16.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell16.setCellValue("��Ŀ��ģϵ��");
		    Cell   cell17   =   row.createCell((short)17);  		   
		    cell17.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell17.setCellValue("��Ŀ����ϵ��");
		    Cell   cell18   =   row.createCell((short)18);  		   
		    cell18.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell18.setCellValue("��Ŀ����ϵ��");
		    Cell   cell19   =   row.createCell((short)19);  		   
		    cell19.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell19.setCellValue("��ʱ");
		    Cell   cell20   =   row.createCell((short)20);  		   
		    cell20.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell20.setCellValue("������ʱ");
		    Cell   cell21   =   row.createCell((short)21);  		   
		    cell21.setCellType(XSSFCell.CELL_TYPE_STRING);
		    cell21.setCellValue("���۹�ʱ");
		    
		    for(int i=0;i<dlist.size();i++)
		    {
		    	
		    		Row rowi=sheet.createRow(i+1);
		    		Cell celli=rowi.createCell((short)0);
		    		celli.setCellType(XSSFCell.CELL_TYPE_STRING);
		    		celli.setCellValue(dlist.get(i).getXid());
		    		
		    		Cell   celli1   =   rowi.createCell((short)1);  		   
				    celli1.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli1.setCellValue(dlist.get(i).getName());
				    Cell   celli2   =   rowi.createCell((short)2);  		   
				    celli2.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli2.setCellValue(dlist.get(i).getProvince());
				    Cell   celli3   =   rowi.createCell((short)3);  		   
				    celli3.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli3.setCellValue(dlist.get(i).getDanwei());
				    Cell   celli4   =   rowi.createCell((short)4);  		   
				    celli4.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli4.setCellValue(dlist.get(i).getYewu());
				    Cell   celli5   =   rowi.createCell((short)5);  		   
				    celli5.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli5.setCellValue(dlist.get(i).getJmanager());
				    Cell   celli6   =   rowi.createCell((short)6);  		   
				    celli6.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli6.setCellValue(dlist.get(i).getKmanager());
				    Cell   celli7   =   rowi.createCell((short)7);  		   
				    celli7.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli7.setCellValue(dlist.get(i).getFzr());
				    Cell   celli8   =   rowi.createCell((short)8);  		   
				    celli8.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli8.setCellValue(dlist.get(i).getZzy());
				    Cell   celli9   =   rowi.createCell((short)9);  		   
				    celli9.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli9.setCellValue(dlist.get(i).getZhulei());
				    Cell   celli10   =   rowi.createCell((short)10);  		   
				    celli10.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli10.setCellValue(dlist.get(i).getFenlei());
				    Cell   celli11   =   rowi.createCell((short)11);  		   
				    celli11.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli11.setCellValue(dlist.get(i).getXiaolei());
				    Cell   celli12   =   rowi.createCell((short)12);  		   
				    celli12.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli12.setCellValue(dlist.get(i).getFenxiang());
				    Cell   celli13   =   rowi.createCell((short)13);  		   
				    celli13.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli13.setCellValue(dlist.get(i).getChanpin());
				    Cell   celli14   =   rowi.createCell((short)14);  		   
				    celli14.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli14.setCellValue(dlist.get(i).getZhuanye());
				    Cell   celli15   =   rowi.createCell((short)15);  		   
				    celli15.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli15.setCellValue(dlist.get(i).getJiliang());
				    Cell   celli16   =   rowi.createCell((short)16);  		   
				    celli16.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli16.setCellValue(dlist.get(i).getXs1());
				    Cell   celli17   =   rowi.createCell((short)17);  		   
				    celli17.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli17.setCellValue(dlist.get(i).getXs2());
				    Cell   celli18   =   rowi.createCell((short)18);  		   
				    celli18.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli18.setCellValue(dlist.get(i).getXs3());
				    Cell   celli19   =   rowi.createCell((short)19);  		   
				    celli19.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli19.setCellValue(dlist.get(i).getGs());
				    Cell   celli20   =   rowi.createCell((short)20);  		   
				    celli20.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli20.setCellValue(dlist.get(i).getXzgs());
				    Cell   celli21   =   rowi.createCell((short)21);  		   
				    celli21.setCellType(XSSFCell.CELL_TYPE_STRING);
				    celli21.setCellValue(dlist.get(i).getBjgs());
		    	 
		    }
		    
		   		    
		    //   �½�һ����ļ���  
		   // FileOutputStream   fOut   =   new   FileOutputStream(outputFile);  
		    //   ����Ӧ��Excel   ����������  
		    //fOut.flush(); 
		    workbook.write(fOut);  
		    fOut.flush();   
		    //   �����������ر��ļ�  
		    fOut.close();  
		    System.out.println("�ļ�����...");  
		   
		   
		   
		   }catch(Exception   e)   {  
		    System.out.println("������   ExcelCreate()   :   "   +   e   );  
		   }  
		   
	   }
}
