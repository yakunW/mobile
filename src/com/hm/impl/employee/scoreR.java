package com.hm.impl.employee;

import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.action.importAction;
import com.hm.entity.employee;
import com.hm.entity.project;



public class scoreR {
	private static String mes;

	/*-----------------固定表头的判断--------------------*/
	public static void readScore(InputStream inputStream)
			throws Exception {
//	    System.out.println("表头打分表");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		System.out.println(workbook.getNumberOfSheets());
		for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
			if (null != workbook.getSheetAt(numSheets)) {
				XSSFSheet sheet = workbook.getSheetAt(numSheets);
				if (sheet.getFirstRowNum() != sheet.getLastRowNum()){
					content(sheet);
				}
			}
		}	
		
	}
	
	
	public static void content(XSSFSheet sheet){
		/*
		 * 		project信息获得
		 */
		mes = "";
//		System.out.println("------------------------");
		project pro = new project();
		XSSFRow row1 = sheet.getRow(2);
		pro.setLeaderN(null);
		pro.setProID(null);
		pro.setProName(null);
		for (int numcell = 0; numcell < row1.getLastCellNum(); numcell++) {
			XSSFCell cell = row1.getCell(numcell);
			if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
				if (cell.getStringCellValue().equals("项目编号")) {
					numcell++;
					XSSFCell cell0 = row1.getCell(numcell);
					if (cell0 == null || cell0.getCellType() == XSSFCell.CELL_TYPE_BLANK)
						mes = mes + "项目编号不能为空！\r\n";
					else if(cell0.getCellType() == XSSFCell.CELL_TYPE_STRING){
						pro.setProID(common.exchange(cell0.getStringCellValue()));
					}
					else if(cell0.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
						pro.setProID(String.valueOf(cell0.getNumericCellValue()));
					}
//					System.out.println("------------------------"+pro.getProID());

//					读取项目名称，格式有两种，在一个单元格写或两个单元格写
					numcell++;
					XSSFCell cell1 = row1.getCell(numcell);
//					项目编号合并单元格处理
					if(cell1.getStringCellValue().equals(""))
						numcell++;
					
					XSSFCell cell10 = row1.getCell(numcell);

					int position = cell10.getStringCellValue().lastIndexOf("：");
					if (cell10.getStringCellValue().equals("项目名称：")) {
						numcell++;
						XSSFCell cell2 = row1.getCell(numcell);
						if (cell2 == null || cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							mes = mes + "项目名称不能为空！\r\n";
						else
							pro.setProName(cell2.getStringCellValue());
					} 
					else {
						pro.setProName(cell10.getStringCellValue().substring(
								position + 1));
//						if(pro.getProName().equals("项目名称："))
//							mes = mes + "项目名称不能为空！\r\n";
					}
				}
//				读取项目经理，格式有两种				
				if (cell.getStringCellValue().contains("项目经理：")) {
					int position = cell.getStringCellValue().lastIndexOf("：");
					if (cell.getStringCellValue().equals("项目经理：")) {
						numcell++;
						XSSFCell cell2 = row1.getCell(numcell);
						if (cell2 == null ||cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							mes = mes + "项目经理不能为空！\r\n";
						else 
							pro.setLeaderN(cell2.getStringCellValue());
					} 
					else {
						pro.setLeaderN(cell.getStringCellValue().substring(
								position + 1));
//						if(pro.getLeaderN().equals(null))
//							mes = mes + "项目经理不能为空！\r\n";
					}
				}				
			} 
			else
				continue;
		}
		if(pro.getLeaderN() == null){
			mes = mes + "项目经理不能为空！\r\n";
		}
		if(pro.getProID() == null){
			mes = mes + "项目编号不能为空！\r\n";
		}
		if(pro.getProName() == null){
			mes = mes + "项目名称不能为空！\r\n";
		}
		if(pro.getProID() != null && pro.getProName() != null && pro.getLeaderN() != null){
			int i = 0;
		//		避免在上传列表中有重复项
			for( i = 0;i < importAction.proList.size();i++){
				if(importAction.proList.get(i).getProID().equals(pro.getProID())){
					mes = mes + pro.getProID()+"重复上传！\r\n";
					break;
				}
			}
			
			if(i == importAction.proList.size()){
				importAction.proList.add(pro);
		//			
			/*
			 *      employee信息获得
			 */
					
				XSSFRow row = sheet.getRow(4);// 姓名行
				XSSFRow row0 = sheet.getRow(17);// 成绩行
				int numCells = 0;
		//		          初步确定列数
				XSSFCell cell0 = row.getCell(row.getPhysicalNumberOfCells() - 1);
				if (cell0.getStringCellValue().equals("69-60"))
					numCells = row.getPhysicalNumberOfCells() - 6;
				else
					numCells = row.getPhysicalNumberOfCells();
		//			System.out.println("numCells" + numCells);
				employee emp = null;
				emp = new employee();
				emp.setProID(pro.getProID());
				emp.setName(pro.getLeaderN());
				emp.setIsLeader(1);
				importAction.empList.add(emp);
		//			System.out.println("nameL"+pro.getLeaderN());
				
				int numcell = 4;
				
				XSSFCell cell = row.getCell(numcell);// 读姓名
				XSSFCell cell1 = row0.getCell(numcell - 1);// 读成绩
		
		//			是否有空列
				if(cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK){
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						if(!cell.getStringCellValue().equals(pro.getLeaderN()) && !cell.getStringCellValue().contains("姓名")){
		//						System.out.println(cell.getStringCellValue());
							emp = new employee();
							emp.setProID(pro.getProID());
							emp.setIsLeader(0);
							
							if (cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)//没有合并
								cell1 = row0.getCell(numcell );
							
							if (cell1.getCellType() == XSSFCell.CELL_TYPE_FORMULA || cell1.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) 
								emp.setScores((float) cell1.getNumericCellValue());// 添加成绩
							else if(cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)
								mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
								+ (numcell + 1) + "列成绩不能为空！\r\n";
							else
								mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
										+ (numcell + 1) + "列成绩格式不正确！\r\n";
							
							emp.setName(cell.getStringCellValue());
		//						System.out.println("name"+cell.getStringCellValue()+"  "+(float) cell1.getNumericCellValue());
		//							emp.setPc(pc);
							if(!emp.getName().equals(""))
								importAction.empList.add(emp);	
						}
					} 	
				}
				
				for (numcell = 5; numcell < numCells; numcell++) {
					cell = row.getCell(numcell);
					if ((cell.getStringCellValue().contains("姓名"))||(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)) 
						break;
					else{
						if ((cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								&&(!cell.getStringCellValue().equals(pro.getLeaderN()))) {
		//							获取员工的具体成绩
							cell1 = row0.getCell(numcell);
							
							if ((cell1 != null)) {
								emp = new employee();
								emp.setProID(pro.getProID());
								emp.setIsLeader(0);
								if (cell1.getCellType() == XSSFCell.CELL_TYPE_FORMULA || cell1.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) 
									emp.setScores((float) cell1.getNumericCellValue());// 添加成绩
								else if(cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)
									mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
									+ (numcell + 1) + "列成绩不能为空！\r\n";
								else
									mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
											+ (numcell + 1) + "列成绩格式不正确！\r\n";
							} else
								mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
										+ (numcell + 1) + "列成绩不能为空！\r\n";
							
							emp.setName(cell.getStringCellValue());
							importAction.empList.add(emp);	
						}
						else
							mes = mes + "第" + (row.getRowNum() + 1) + "行第"
							+ (numcell + 1) + "姓名格式不正确！\r\n";
					}	
				}
			}
		}
//		System.out.println(importAction.empList.size());
//		if(mes != ""){
//			mes = mes + pro.getProID()+ "的错误信息如上！\r\n";
//			mes = mes + "----------------------------------------------------------------\r\n";
//		}
//		for(int ii = 0;ii<importAction.empList.size();ii++){
////			 employee e = empList.get(i);
////			 e.setPc(pc);
//			 System.out.println(importAction.empList.get(ii).getName()+" "+importAction.empList.get(ii).getScores());
//	    }
		setMes(mes);
	}
	
	public static String getMes() {
		return mes;
	}

	public static void setMes(String mes) {
		scoreR.mes = mes;
	}
}
