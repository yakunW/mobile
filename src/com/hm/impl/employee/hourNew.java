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
	/*------------------------------------���ݵ��ж�------------------------------------*/
	private static String mes;
	public static String contentH(XSSFSheet sheet,String pc) throws Exception {
		mes = "";
		List<employee> empList = new ArrayList<employee>();
		List<project> proList = new ArrayList<project>();
		
		project pro = null;
		employee emp = null;
		String mem = null;//������Ŀ��Ա
		int numRows = 0;
//		System.out.println("������" + hourR.getnumRow(sheet));
		for (numRows = 1; numRows <= hourR.getnumRow(sheet); numRows++) {
			XSSFRow row = sheet.getRow(numRows);
			pro = new project();
			pro.setPc(pc);
			
			XSSFCell cell0 = row.getCell(14);
			if (cell0 != null) 
				pro.setMemCount((int)cell0.getNumericCellValue());
			else
				mes = mes + "��" + (numRows + 1) + "�е�15����������Ϊ�գ�\r\n";
	
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
							mes = mes + "��" + (numRows + 1) + "�е�4����Ŀ��Ÿ�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�4����Ŀ��Ų���Ϊ�գ�\r\n";
					break;
				}
				case 4:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							pro.setProName(String.valueOf(cell.getNumericCellValue()));
						} else if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setProName(cell.getStringCellValue());
						} else
							mes = mes + "��" + (numRows + 1) + "�е�5����Ŀ���Ƹ�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�5����Ŀ���Ʋ���Ϊ�գ�\r\n";
					break;
				}
				case 5:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setRegion(cell.getStringCellValue());
						} else
							mes = mes + "��" + (numRows + 1) + "�е�6�м��Ŵ�����ʽ����ȷ��\r\n";
					}
					break;
				}
				case 6:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							pro.setLeaderN(cell.getStringCellValue());
						} else
							mes = mes + "��" + (numRows + 1)
									+ "�е�4����Ŀ�ܸ����˸�ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�7����Ŀ�ܸ����˲���Ϊ�գ�\r\n";
					numCells++;
					break;
				}
				case 8:{
					if ((cell != null) && (cell.getCellType() != XSSFCell.CELL_TYPE_BLANK)) {
						if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
							pro.setHours((float)cell.getNumericCellValue());		
						else
							mes = mes + "��" + (numRows + 1) + "�е�9���ܹ�ʱ��ʽ����ȷ��\r\n";
					} else
						mes = mes + "��" + (numRows + 1) + "�е�9���ܹ�ʱ����Ϊ�գ�\r\n";
					break;
				}
				}
			}
			/*
			 * ��ȡԱ����ʱ		
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
									mes = mes + "��" + (numRows + i) + "�е�10����Ա��ʽ����ȷ��\r\n";
							}
							else
								mes = mes + "��" + (numRows + i) + "�е�10����Ա����Ϊ�գ�\r\n";
							numCell += 6;
							
							break;
						}
						case 16:{
							if((cell1 != null) && (cell1.getCellType() != XSSFCell.CELL_TYPE_BLANK))
								emp.setHour((float)cell1.getNumericCellValue());
							else
								mes = mes + "��" + (numRows + i) + "�е�17����Ŀ��ʱ����Ϊ�գ�\r\n";
							break;
						}
						case 17:{
							if((cell1 != null) && (cell1.getCellType() != XSSFCell.CELL_TYPE_BLANK))
								emp.setProhours((float)cell1.getNumericCellValue());
							else
								mes = mes + "��" + (numRows + i) + "�е�18����Ŀ�ܹ�ʱ����Ϊ�գ�\r\n";
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

//		�������ݿ�
		Judge jg = new Judge();
		jg.check("hour", empList, proList,pc);
		mes = mes + jg.getMessage();
		
		empList.clear();
		proList.clear();
		
		return mes;
	}
	
}
