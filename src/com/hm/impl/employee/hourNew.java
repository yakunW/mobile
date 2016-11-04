package com.hm.impl.employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.util.Judge;



public class hourNew {
	/*------------------------------------内容的判断------------------------------------*/
	private static String mes;
	public static String contentH(XSSFSheet sheet,String pc) throws Exception {
		mes = "";
		List<employee> empList = new ArrayList<employee>();
		List<project> proList = new ArrayList<project>();
		
		project pro = null;
		employee emp = null;
		String mem = null;//所有项目成员
		int numRows = 0;
//		System.out.println("行数：" + hourR.getnumRow(sheet));
		for (numRows = 1; numRows <= hourR.getnumRow(sheet); numRows++) {
			XSSFRow row = sheet.getRow(numRows);
			pro = new project();
			pro.setPc(pc);
			
			XSSFCell cell0 = row.getCell(14);
			if (cell0 != null) 
				pro.setMemCount((int)cell0.getNumericCellValue());
			else
				mes = mes + "第" + (numRows + 1) + "行第15列人数不能为空！\r\n";
	
			for (int numCells = 3; numCells < 9; numCells++) {
				XSSFCell cell = row.getCell(numCells);
				switch(numCells){
				case 3:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
							pro.setProID(String.valueOf(cell.getNumericCellValue()));			
						else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
							pro.setProID(common.exchange(cell.getStringCellValue()));
						else
							mes = mes + "第" + (numRows + 1) + "行第4列项目编号格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第4列项目编号不能为空！\r\n";
					break;
				}
				case 4:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setProName(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setProName(cell.getStringCellValue());
						} else
							mes = mes + "第" + (numRows + 1) + "行第5列项目名称格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第5列项目名称不能为空！\r\n";
					break;
				}
				case 5:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setRegion(cell.getStringCellValue());
						} else
							mes = mes + "第" + (numRows + 1) + "行第6列集团大区格式不正确！\r\n";
					}
					break;
				}
				case 6:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setLeaderN(cell.getStringCellValue());
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第4列项目总负责人格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第7列项目总负责人不能为空！\r\n";
					numCells++;
					break;
				}
				case 8:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
							pro.setHours((float)cell.getNumericCellValue());		
						else
							mes = mes + "第" + (numRows + 1) + "行第9列总工时格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第9列总工时不能为空！\r\n";
					break;
				}
				}
			}
			/*
			 * 获取员工工时		
			 */
			mem = "";
			for(int i = 0; i < pro.getMemCount(); i++ ){
				
				emp = new employee();
//				System.out.println("numRows:"+numRows+"  Count:"+pro.getMemCount());
				//for (int numRow = numRows; numRow <= (numRows+pro.getMemCount()); numRow++) {
					XSSFRow row0 = sheet.getRow(numRows + i);
//					System.out.println("row0;"+row0.getRowNum());
					emp.setProID(pro.getProID());
					emp.setPc(pc);
					
					for (int numCell = 9; numCell < 18; numCell++) {
						XSSFCell cell1 = row0.getCell(numCell);
						switch(numCell){
						case 9:{
							if((cell1 != null) && (cell1.getCellType() != XSSFCell.CELL_TYPE_BLANK)){
								if(cell1.getCellType() == XSSFCell.CELL_TYPE_STRING){
									emp.setName(cell1.getStringCellValue());
									mem = mem + emp.getName();
									if(i != (pro.getMemCount() - 1))
										mem += ",";
									if(emp.getName().equals(pro.getLeaderN()))
										emp.setIsLeader(1);
									else
										emp.setIsLeader(0);
								}
								else
									mes = mes + "第" + (numRows + i) + "行第10列人员格式不正确！\r\n";
							}
							else
								mes = mes + "第" + (numRows + i) + "行第10列人员不能为空！\r\n";
							numCell += 6;
							
							break;
						}
						case 16:{
							if((cell1 != null) && (cell1.getCellType() != XSSFCell.CELL_TYPE_BLANK))
								emp.setHour((float)cell1.getNumericCellValue());
							else
								mes = mes + "第" + (numRows + i) + "行第17列项目工时不能为空！\r\n";
							break;
						}
						case 17:{
							if((cell1 != null) && (cell1.getCellType() != XSSFCell.CELL_TYPE_BLANK))
								emp.setProhours((float)cell1.getNumericCellValue());
							else
								mes = mes + "第" + (numRows + i) + "行第18列项目总工时不能为空！\r\n";
							break;
					   }
					   }		
				   }
			  //}
			  if(!empList.contains(emp))
				  empList.add(emp);
			}
			pro.setMemberNs(mem);
			
			if (!proList.contains(pro))
				proList.add(pro);
			
			numRows += pro.getMemCount();numRows--;
		}
		
//		for (int i = 0; i < proList.size(); i++) {
//			project p = proList.get(i);
////			p.setPc(pc);
//			System.out.println(p.getProID() + "  " + pro.getProName());
//		}
//	for(int i = 0;i<empList.size();i++){
//		 employee e = empList.get(i);
////		 e.setPc(pc);
//		 System.out.println(e.getProID()+" "+e.getName()+" "+e.getIsLeader()+" "+e.getProhours());
//    }
		
//		System.out.println("mes"+mes);

//		导入数据库
		Judge jg = new Judge();
		jg.check("hour", empList, proList,pc);
		mes = mes + jg.getMessage();
		
		empList.clear();
		proList.clear();
		
		return mes;
	}
	
}
