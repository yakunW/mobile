package com.hm.impl.employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.util.Judge;


public class hourOld {
	private static String mes;
	/*------------------------------------内容的判断------------------------------------*/
	public static String contentH(XSSFSheet sheet,String pc) throws Exception {
		mes = "";
		
//		System.out.println("content判断");
		int numMem = 0;// 读出的员工个数
		String arr[] = null;
		int index[] = null;
		
		List<employee> empList = new ArrayList<employee>();
		List<project> proList = new ArrayList<project>();
		project pro = null;
//		System.out.println("行数：" + hourR.getnumRow(sheet));

		for (int numRows = 1; numRows < hourR.getnumRow(sheet); numRows++) {
			
			pro = new project();
			XSSFRow row = sheet.getRow(numRows);
			for (int numCells = 0; numCells < 9; numCells++) {

				XSSFCell cell = row.getCell(numCells);
				switch (numCells) {

				case 0: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setProID(String.valueOf(cell
									.getNumericCellValue()));
							// System.out.print(pro.getProID()+" ");
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
							pro.setProID(common.exchange(cell.getStringCellValue()));

						else
							mes = mes + "第" + (numRows + 1)
									+ "行第1列项目编号格式不正确！\r\n";

					} else
						mes = mes + "第" + (numRows + 1) + "行第1列项目编号不能为空！\r\n";
					break;
				}

				case 1: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setProName(String.valueOf(cell
									.getNumericCellValue()));
							// System.out.print(String.valueOf(cell.getNumericCellValue())+" ");
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setProName(cell.getStringCellValue());
							// System.out.print(cell.getStringCellValue()+" ");
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第2列项目名称格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第2列项目名称不能为空！\r\n";
					break;
				}
				case 2: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setRegion(cell.getStringCellValue());
							// System.out.print(cell.getStringCellValue()+" ");
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第3列集团大区格式不正确！\r\n";
					}
					break;
				}
				case 3: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setLeaderN(cell.getStringCellValue());
							// System.out.print(cell.getStringCellValue()+" ");
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第4列项目总负责人格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第4列项目总负责人不能为空！\r\n";

					break;
				}
				case 4: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setHours((float) cell.getNumericCellValue());
//							System.out.println((float) cell.getNumericCellValue()+" ");
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第5列工时合计格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第5列工时合计不能为空！\r\n";

					break;
				}
				case 5: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						pro.setMemberNs(cell.getStringCellValue());
						String value = cell.getStringCellValue();
						value = value.replace('[', '(');
						value = value.replace(']', ')');
						arr = value.split(",");
						numMem = arr.length;
						index = getIndex(sheet, arr);
					} else
						mes = mes + "第" + (numRows + 1) + "行第6列项目组成员不能为空！\r\n";
					break;
				}
				case 6: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							if (cell.getNumericCellValue() == numMem) {
								pro.setMemCount(numMem);
								;// System.out.print((int)cell.getNumericCellValue()+" ");
							} else
								mes = mes + "第" + (numRows + 1)
										+ "行第7列项目组人数不正确！\r\n";
						} else
							mes = mes + "第" + (numRows + 1)
									+ "行第7列项目组人数格式不正确！\r\n";
					} else
						mes = mes + "第" + (numRows + 1) + "行第7列项目组人数不能为空！\r\n";
					break;
				}
				case 7: {
					if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
//						String str = "IF(E" + (row.getRowNum() + 1) + "=I"
//								+ (row.getRowNum() + 1) + ",1,0)";
//						if (cell.getCellFormula().equals(str)) {
							if (cell.getNumericCellValue() == 1)
								mes = mes
										+ getH(sheet, row, arr, index, empList,
												pro.getProID(),
												pro.getLeaderN(),pc);
							else
								mes = mes + "第" + (row.getRowNum() + 1)
										+ "行的工时合计不相等！\r\n";
//						} else
//							mes = mes + "第" + (row.getRowNum() + 1)
//									+ "行第7列的是否相等公式不正确！\r\n";
					} else
						mes = mes + "第" + (row.getRowNum() + 1)
								+ "行第7列的是否相等格式不正确！\r\n";
					break;
				}
				}
				pro.setPc(pc);
				if (!proList.contains(pro))
					proList.add(pro);
			}
		}
		
//		for (int i = 0; i < proList.size(); i++) {
// 			project p = proList.get(i);
//// 			p.setPc(pc);
// 			System.out.println(p.getProID() + "  " + pro.getProName());
// 		}
//		for(int i = 0;i<empList.size();i++){
//			 employee e = empList.get(i);
////			 e.setPc(pc);
//			 System.out.println(e.getProID()+" "+e.getName()+" "+e.getIsLeader()+" "+e.getProhours());
//	    }
		
		Judge jg = new Judge();
		jg.check("hour", empList, proList,pc);
		mes = mes + jg.getMessage();

		empList.clear();
		proList.clear();

		return mes;
	}

	

	/*-------------------------------------------获取每个员工所在的列号----------------------------------*/
	public static int[] getIndex(XSSFSheet sheet, String arr[]) {
		XSSFRow row = sheet.getRow(0);
		int[] index = new int[arr.length];
		int numcell = 9;
		for (int i = 0; i < arr.length; i++) {
			for (numcell = 9; numcell < row.getLastCellNum(); numcell++) {
				XSSFCell cell = row.getCell(numcell);
				if (cell.getStringCellValue().equals(arr[i])) {
					index[i] = numcell;
					break;
				}
			}
			if (numcell == row.getLastCellNum())
				index[i] = -1;
		}
		return index;
	}

	/*--------------------------------获取员工的具体工时---------------------------------*/
	public static String getH(XSSFSheet sheet, XSSFRow row0, String arr[],
			int index[], List<employee> empList, String proID, String leaderN,String pc) {
		mes = "";
		employee emp = null;
		XSSFRow row = sheet.getRow(hourR.getnumRow(sheet));// 最后一行,获取总工时
		try {
			for (int i = 0; i < index.length; i++) {
				if (index[i] != -1) {
					emp = new employee();
					emp.setProID(proID);
					XSSFCell cell = row.getCell(index[i]); // 总工时
					XSSFCell cell0 = row0.getCell(index[i]);// 单项工时
					emp.setName(arr[i]);
					if (arr[i].equals(leaderN))
						emp.setIsLeader(1);
					else
						emp.setIsLeader(0);
					if (cell0 != null && cell0.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
						if (cell0.getCellType() == XSSFCell.CELL_TYPE_NUMERIC 
								|| cell0.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
							if (0 <= cell0.getNumericCellValue()) {
								emp.setHour((float) cell0.getNumericCellValue());
							} else
								mes = mes + "第" + (row0.getRowNum() + 1) + "行第"
										+ index[i] + "列" + arr[i]
										+ "的工时范围不正确！\r\n";
						} 
						else
							mes = mes + "第" + (row0.getRowNum() + 1) + "行第" + index[i] + "列" + arr[i] + "的工时格式不正确！\r\n";
					} else {
						mes = mes + "第" + (row0.getRowNum() + 1) + "行第" + index[i] + "列" + arr[i] + "的工时不能为空！\r\n";
					}
					if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA 
								|| cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							if (0 <= cell.getNumericCellValue()) {
								emp.setProhours((float) cell
										.getNumericCellValue());
							} else {
								mes = mes + "第" + (row.getRowNum() + 1) + "行第" + index[i] + "列" + arr[i]
										+ "的总工时不正确！\r\n";
							}
						} 
						else {
							mes = mes + "第" + (row.getRowNum() + 1) + "行第"
									+ index[i] + "列" + arr[i]
									+ "的总工时格式不正确！\r\n";
						}
					} else {
						mes = mes + "第" + (row.getRowNum() + 1) + "行第"
								+ index[i] + "列" + arr[i] + "的总工时不能为空！\r\n";
					}
					emp.setPc(pc);
					empList.add(emp);
				} 
				else
					mes = mes + "第" + (row0.getRowNum() + 1) + "行的" + arr[i]
							+ "不存在！\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mes;
	}
}
