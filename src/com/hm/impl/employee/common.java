package com.hm.impl.employee;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class common {
	
//	��Ŀ��ŵĸ�ʽͳһ
	public static String exchange(String proid){
		String proid0 = proid;
//		��Ӣ��ȫ��
		if(proid.contains("��"))
			proid0 = proid.replace("��", "(");
		if(proid.contains("��"))
			proid0 = proid.replace("��", ")");
		if(proid.contains("��"))
			proid0 = proid.replace("��", "-");
//		���İ��
		if(proid.contains("��"))
			proid0 = proid.replace("��", "(");
		if(proid.contains("��"))
			proid0 = proid.replace("��", ")");
		return proid0;

	}
	
//	�ж�һ���Ƿ�Ϊ��
	public static boolean isBlankRow(XSSFRow row){
        if(row == null) return true;
        boolean result = true;
        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++){
            XSSFCell cell = row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL);
            String value = "";
            if(cell != null){
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                //case Cell.CELL_TYPE_BLANK:
                //    break;
                default:
                    break;
                }  
                if(!value.trim().equals("")){
                    result = false;
                    break;
                }
            }
        }    
        return result;
    }
}
