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
	/*------------------------------------���ݵ��ж�------------------------------------*/
	public static String contentH(XSSFSheet sheet,String pc) throws Exception {
		mes = "";
		
//		System.out.println("content�ж�");
		int numMem = 0;// ������Ա������
		String arr[] = null;
		int index[] = null;
		
		List<employee> empList = new ArrayList<employee>();
		List<project> proList = new ArrayList<project>();
		project pro = null;
//		System.out.println("������" + hourR.getnumRow(sheet));

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
							mes = mes + "��" + (numRows + 1)
									+ "�е�1����Ŀ��Ÿ�ʽ����ȷ��\r\n";

					} else
						mes = mes + "��" + (numRows + 1) + "�е�1����Ŀ��Ų���Ϊ�գ�\r\n";
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
							mes = mes + "��" + (numRows + 1)
									+ "�е�2����Ŀ���Ƹ�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�2����Ŀ���Ʋ���Ϊ�գ�\r\n";
					break;
				}
				case 2: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setRegion(cell.getStringCellValue());
							// System.out.print(cell.getStringCellValue()+" ");
						} else
							mes = mes + "��" + (numRows + 1)
									+ "�е�3�м��Ŵ�����ʽ����ȷ��\r\n";
					}
					break;
				}
				case 3: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setLeaderN(cell.getStringCellValue());
							// System.out.print(cell.getStringCellValue()+" ");
						} else
							mes = mes + "��" + (numRows + 1)
									+ "�е�4����Ŀ�ܸ����˸�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�4����Ŀ�ܸ����˲���Ϊ�գ�\r\n";

					break;
				}
				case 4: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setHours((float) cell.getNumericCellValue());
//							System.out.println((float) cell.getNumericCellValue()+" ");
						} else
							mes = mes + "��" + (numRows + 1)
									+ "�е�5�й�ʱ�ϼƸ�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�5�й�ʱ�ϼƲ���Ϊ�գ�\r\n";

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
						mes = mes + "��" + (numRows + 1) + "�е�6����Ŀ���Ա����Ϊ�գ�\r\n";
					break;
				}
				case 6: {
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							if (cell.getNumericCellValue() == numMem) {
								pro.setMemCount(numMem);
								;// System.out.print((int)cell.getNumericCellValue()+" ");
							} else
								mes = mes + "��" + (numRows + 1)
										+ "�е�7����Ŀ����������ȷ��\r\n";
						} else
							mes = mes + "��" + (numRows + 1)
									+ "�е�7����Ŀ��������ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�7����Ŀ����������Ϊ�գ�\r\n";
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
								mes = mes + "��" + (row.getRowNum() + 1)
										+ "�еĹ�ʱ�ϼƲ���ȣ�\r\n";
//						} else
//							mes = mes + "��" + (row.getRowNum() + 1)
//									+ "�е�7�е��Ƿ���ȹ�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (row.getRowNum() + 1)
								+ "�е�7�е��Ƿ���ȸ�ʽ����ȷ��\r\n";
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

	

	/*-------------------------------------------��ȡÿ��Ա�����ڵ��к�----------------------------------*/
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

	/*--------------------------------��ȡԱ���ľ��幤ʱ---------------------------------*/
	public static String getH(XSSFSheet sheet, XSSFRow row0, String arr[],
			int index[], List<employee> empList, String proID, String leaderN,String pc) {
		mes = "";
		employee emp = null;
		XSSFRow row = sheet.getRow(hourR.getnumRow(sheet));// ���һ��,��ȡ�ܹ�ʱ
		try {
			for (int i = 0; i < index.length; i++) {
				if (index[i] != -1) {
					emp = new employee();
					emp.setProID(proID);
					XSSFCell cell = row.getCell(index[i]); // �ܹ�ʱ
					XSSFCell cell0 = row0.getCell(index[i]);// ���ʱ
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
								mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
										+ index[i] + "��" + arr[i]
										+ "�Ĺ�ʱ��Χ����ȷ��\r\n";
						} 
						else
							mes = mes + "��" + (row0.getRowNum() + 1) + "�е�" + index[i] + "��" + arr[i] + "�Ĺ�ʱ��ʽ����ȷ��\r\n";
					} else {
						mes = mes + "��" + (row0.getRowNum() + 1) + "�е�" + index[i] + "��" + arr[i] + "�Ĺ�ʱ����Ϊ�գ�\r\n";
					}
					if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA 
								|| cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							if (0 <= cell.getNumericCellValue()) {
								emp.setProhours((float) cell
										.getNumericCellValue());
							} else {
								mes = mes + "��" + (row.getRowNum() + 1) + "�е�" + index[i] + "��" + arr[i]
										+ "���ܹ�ʱ����ȷ��\r\n";
							}
						} 
						else {
							mes = mes + "��" + (row.getRowNum() + 1) + "�е�"
									+ index[i] + "��" + arr[i]
									+ "���ܹ�ʱ��ʽ����ȷ��\r\n";
						}
					} else {
						mes = mes + "��" + (row.getRowNum() + 1) + "�е�"
								+ index[i] + "��" + arr[i] + "���ܹ�ʱ����Ϊ�գ�\r\n";
					}
					emp.setPc(pc);
					empList.add(emp);
				} 
				else
					mes = mes + "��" + (row0.getRowNum() + 1) + "�е�" + arr[i]
							+ "�����ڣ�\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mes;
	}
}
