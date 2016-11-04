package com.hm.impl.employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.util.Judge;



public class proScR {
	private static String mes;

	/*-----------------------------------标题判断-------------------------------*/
	public static boolean readPro(XSSFSheet sheet,String pc) throws Exception {
	
		mes = "";
		List<employee> empList = new ArrayList<employee>();
		List<project> proList = new ArrayList<project>();
		project pro = null;
		employee emp = null;
		String pid = "";
		boolean isExist = false;
//		System.out.println("行数：" + hourR.getnumRow(sheet));
		

		for (int numRows = 1; numRows <= hourR.getnumRow(sheet); numRows++) {
			XSSFRow row = sheet.getRow(numRows);
			isExist = false;
			for (int numCells = 0; numCells < 5; numCells++) {
				if(!isExist){
					XSSFCell cell = row.getCell(numCells);
					switch (numCells) {
					case 0: {
						if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								pid = String.valueOf(cell.getNumericCellValue());
							} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
								pid = cell.getStringCellValue();
							} else
								mes = mes + "第" + (numRows + 1)
										+ "行第1列项目编号格式不正确！\r\n";
							
							for(int i = 0;i < proList.size();i++){
								if(proList.get(i).getProID().equals(pid)){
									isExist = true;
									mes = mes + pid + "重复存在！\r\n";
									break;
								}
							}
							if(!isExist){
								pro = new project();
								pro.setProID(common.exchange(pid));
							}
								
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第1列项目编号不能为空！\r\n";
						break;
					}

					case 1: {
						if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
								pro .setProName(String.valueOf(cell
										.getNumericCellValue()));
							else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								pro.setProName(cell.getStringCellValue());
							else
								mes = mes + "第" + (numRows + 1)
										+ "行第2列项目名称格式不正确！\r\n";
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第2列项目名称不能为空！\r\n";
						break;
					}

					case 2: {
						if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								pro.setRegion(cell.getStringCellValue());
							else
								mes = mes + "第" + (numRows + 1)
										+ "行第3列集团大区格式不正确！\r\n";
						}
						break;
					}
					case 3: {
						if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA
									|| cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								if (0 <= cell.getNumericCellValue()
										&& cell.getNumericCellValue() <= 100) {
									emp = new employee();
									emp.setProID(pro.getProID());
									emp.setScores((float) cell
											.getNumericCellValue());
									emp.setPc(pc);
									empList.add(emp);
									// System.out.print((float)cell.getNumericCellValue()+" ");
								} else
									mes = mes + "第" + (row.getRowNum() + 1)
											+ "行第4列的项目经理成绩范围不正确！\r\n";
							} else
								mes = mes + "第" + (row.getRowNum() + 1)
										+ "行第4列的项目经理成绩格式不正确！\r\n";
						}
						break;
					}
					case 4: {
						if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
							if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA
									|| cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								if (0 <= cell.getNumericCellValue()
										&& cell.getNumericCellValue() <= 100) {
									pro.setScore((float) cell
											.getNumericCellValue());
									// System.out.print((float)cell.getNumericCellValue()+" ");
								} else
									mes = mes + "第" + (row.getRowNum() + 1)
											+ "行第5列的项目成绩范围不正确！\r\n";
							} else
								mes = mes + "第" + (row.getRowNum() + 1)
										+ "行第5列的项目成绩格式不正确！\r\n";
						}
						break;
					}
					}//switch
				}//isExist
				else
					break;
			}//cell
			if(!isExist){
				pro.setPc(pc);
				if (!proList.contains(pro))
					proList.add(pro);
			}
		}
//		导入数据库
		Judge jg = new Judge();
		jg.check("pro", empList, proList,pc);
		mes = mes + jg.getMessage();
		
//		System.out.println("promes" + mes);
		empList.clear();
		proList.clear();
		
		setMes(mes);
		
		if(mes.equals(""))
			return true;
		else 
			return false;
	}

	public static String getMes() {
		return mes;
	}

	public static void setMes(String mes) {
		proScR.mes = mes;
	}

}
