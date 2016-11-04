package com.hm.impl.cooper;

import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hm.action.cooperAction;
import com.hm.entity.company;



public class readTwo {
	public void read(InputStream inputStream)throws Exception{
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("季度报表（试行期）");
		
		String province = null;
		XSSFRow row0 = sheet.getRow(4);
		XSSFCell cell0 = row0.getCell(0);
		province = cell0.getStringCellValue();
//		System.out.println("province:" + province);
		
		company com = null;
		int cellNum;
		
		for(int rowNum = 6; ; rowNum++){
			XSSFRow row = sheet.getRow(rowNum);
			cellNum = 0;
			XSSFCell cell = row.getCell(cellNum);
//			System.out.println(cell.getStringCellValue());
			if(cell.getStringCellValue().equals("-") || cell.getStringCellValue().equals("－"))
				break;
			else{

				com =new company();
				com.setProvince(province);
				com.setName(cell.getStringCellValue());
				cellNum = 2;//获得综合积分
				cell = row.getCell(cellNum);
				com.setSum(cell.getNumericCellValue());
				
				for(cellNum = 12;cellNum < 19; cellNum++){
					cell = row.getCell(cellNum);
					try{
						if(cell.getStringCellValue().equals("-")){
//							-1表示没有积分
							switch(cellNum){
							case 12:
								com.setTrans(-1);
								break;
							case 13:
								com.setWireless(-1);
								break;
							case 14:
								com.setSwitchx(-1);
								break;
							case 15:
								com.setData(-1);
								break;
							case 16:
								com.setPower(-1);
								break;
							case 17:
								com.setCivil(-1);
								break;
							case 18:
								com.setNetwork(-1);
								break;
							}
							continue;
						}
					}catch(Exception e){
//						通过获取异常的方法来读取数字
						switch(cellNum){
						case 12:
							com.setTrans(cell.getNumericCellValue());
							break;
						case 13:
							com.setWireless(cell.getNumericCellValue());
							break;
						case 14:
							com.setSwitchx(cell.getNumericCellValue());
							break;
						case 15:
							com.setData(cell.getNumericCellValue());
							break;
						case 16:
							com.setPower(cell.getNumericCellValue());
							break;
						case 17:
							com.setCivil(cell.getNumericCellValue());
							break;
						case 18:
							com.setNetwork(cell.getNumericCellValue());
							break;
						}
					}//else
				}//for cellNum
				cooperAction.comList.add(com);
			}//else
		}//for rowNum
		
		
//		for(int i =0;i<cooperAction.comList.size();i++){
//			System.out.println(cooperAction.comList.get(i).getName()+"  "+cooperAction.comList.get(i).getProvince()+"  "+
//					cooperAction.comList.get(i).getSum()+"  "+cooperAction.comList.get(i).getTrans()+"  "+cooperAction.comList.get(i).getWireless()+"  "
//					+cooperAction.comList.get(i).getSwitchx()+"  "
//					+cooperAction.comList.get(i).getData()+"  "+cooperAction.comList.get(i).getPower()+"  "+cooperAction.comList.get(i).getCivil()
//					+"  "+cooperAction.comList.get(i).getNetwork());
//		}
	}

}
