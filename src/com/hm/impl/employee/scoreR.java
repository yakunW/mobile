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

	/*-----------------�̶���ͷ���ж�--------------------*/
	public static void readScore(InputStream inputStream)
			throws Exception {
//	    System.out.println("��ͷ��ֱ�");
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
		 * 		project��Ϣ���
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
				if (cell.getStringCellValue().equals("��Ŀ���")) {
					numcell++;
					XSSFCell cell0 = row1.getCell(numcell);
					if (cell0 == null || cell0.getCellType() == XSSFCell.CELL_TYPE_BLANK)
						mes = mes + "��Ŀ��Ų���Ϊ�գ�\r\n";
					else if(cell0.getCellType() == XSSFCell.CELL_TYPE_STRING){
						pro.setProID(common.exchange(cell0.getStringCellValue()));
					}
					else if(cell0.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
						pro.setProID(String.valueOf(cell0.getNumericCellValue()));
					}
//					System.out.println("------------------------"+pro.getProID());

//					��ȡ��Ŀ���ƣ���ʽ�����֣���һ����Ԫ��д��������Ԫ��д
					numcell++;
					XSSFCell cell1 = row1.getCell(numcell);
//					��Ŀ��źϲ���Ԫ����
					if(cell1.getStringCellValue().equals(""))
						numcell++;
					
					XSSFCell cell10 = row1.getCell(numcell);

					int position = cell10.getStringCellValue().lastIndexOf("��");
					if (cell10.getStringCellValue().equals("��Ŀ���ƣ�")) {
						numcell++;
						XSSFCell cell2 = row1.getCell(numcell);
						if (cell2 == null || cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							mes = mes + "��Ŀ���Ʋ���Ϊ�գ�\r\n";
						else
							pro.setProName(cell2.getStringCellValue());
					} 
					else {
						pro.setProName(cell10.getStringCellValue().substring(
								position + 1));
//						if(pro.getProName().equals("��Ŀ���ƣ�"))
//							mes = mes + "��Ŀ���Ʋ���Ϊ�գ�\r\n";
					}
				}
//				��ȡ��Ŀ������ʽ������				
				if (cell.getStringCellValue().contains("��Ŀ����")) {
					int position = cell.getStringCellValue().lastIndexOf("��");
					if (cell.getStringCellValue().equals("��Ŀ����")) {
						numcell++;
						XSSFCell cell2 = row1.getCell(numcell);
						if (cell2 == null ||cell2.getCellType() == XSSFCell.CELL_TYPE_BLANK)
							mes = mes + "��Ŀ������Ϊ�գ�\r\n";
						else 
							pro.setLeaderN(cell2.getStringCellValue());
					} 
					else {
						pro.setLeaderN(cell.getStringCellValue().substring(
								position + 1));
//						if(pro.getLeaderN().equals(null))
//							mes = mes + "��Ŀ������Ϊ�գ�\r\n";
					}
				}				
			} 
			else
				continue;
		}
		if(pro.getLeaderN() == null){
			mes = mes + "��Ŀ������Ϊ�գ�\r\n";
		}
		if(pro.getProID() == null){
			mes = mes + "��Ŀ��Ų���Ϊ�գ�\r\n";
		}
		if(pro.getProName() == null){
			mes = mes + "��Ŀ���Ʋ���Ϊ�գ�\r\n";
		}
		if(pro.getProID() != null && pro.getProName() != null && pro.getLeaderN() != null){
			int i = 0;
		//		�������ϴ��б������ظ���
			for( i = 0;i < importAction.proList.size();i++){
				if(importAction.proList.get(i).getProID().equals(pro.getProID())){
					mes = mes + pro.getProID()+"�ظ��ϴ���\r\n";
					break;
				}
			}
			
			if(i == importAction.proList.size()){
				importAction.proList.add(pro);
		//			
			/*
			 *      employee��Ϣ���
			 */
					
				XSSFRow row = sheet.getRow(4);// ������
				XSSFRow row0 = sheet.getRow(17);// �ɼ���
				int numCells = 0;
		//		          ����ȷ������
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
				
				XSSFCell cell = row.getCell(numcell);// ������
				XSSFCell cell1 = row0.getCell(numcell - 1);// ���ɼ�
		
		//			�Ƿ��п���
				if(cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK){
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						if(!cell.getStringCellValue().equals(pro.getLeaderN()) && !cell.getStringCellValue().contains("����")){
		//						System.out.println(cell.getStringCellValue());
							emp = new employee();
							emp.setProID(pro.getProID());
							emp.setIsLeader(0);
							
							if (cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)//û�кϲ�
								cell1 = row0.getCell(numcell );
							
							if (cell1.getCellType() == XSSFCell.CELL_TYPE_FORMULA || cell1.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) 
								emp.setScores((float) cell1.getNumericCellValue());// ��ӳɼ�
							else if(cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)
								mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
								+ (numcell + 1) + "�гɼ�����Ϊ�գ�\r\n";
							else
								mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
										+ (numcell + 1) + "�гɼ���ʽ����ȷ��\r\n";
							
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
					if ((cell.getStringCellValue().contains("����"))||(cell.getCellType() == XSSFCell.CELL_TYPE_BLANK)) 
						break;
					else{
						if ((cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
								&&(!cell.getStringCellValue().equals(pro.getLeaderN()))) {
		//							��ȡԱ���ľ���ɼ�
							cell1 = row0.getCell(numcell);
							
							if ((cell1 != null)) {
								emp = new employee();
								emp.setProID(pro.getProID());
								emp.setIsLeader(0);
								if (cell1.getCellType() == XSSFCell.CELL_TYPE_FORMULA || cell1.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) 
									emp.setScores((float) cell1.getNumericCellValue());// ��ӳɼ�
								else if(cell1.getCellType() == XSSFCell.CELL_TYPE_BLANK)
									mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
									+ (numcell + 1) + "�гɼ�����Ϊ�գ�\r\n";
								else
									mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
											+ (numcell + 1) + "�гɼ���ʽ����ȷ��\r\n";
							} else
								mes = mes + "��" + (row0.getRowNum() + 1) + "�е�"
										+ (numcell + 1) + "�гɼ�����Ϊ�գ�\r\n";
							
							emp.setName(cell.getStringCellValue());
							importAction.empList.add(emp);	
						}
						else
							mes = mes + "��" + (row.getRowNum() + 1) + "�е�"
							+ (numcell + 1) + "������ʽ����ȷ��\r\n";
					}	
				}
			}
		}
//		System.out.println(importAction.empList.size());
//		if(mes != ""){
//			mes = mes + pro.getProID()+ "�Ĵ�����Ϣ���ϣ�\r\n";
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
