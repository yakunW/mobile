package com.hm.impl.employee;

import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFImpl {
	private String message = "";
	private String pc;
	public void readXSSF(InputStream inputStream, String flag,String pc)
			throws Exception {
//	    System.out.println("Impl:" + pc);
		this.setPc(pc);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
			if (null != workbook.getSheetAt(numSheets)) {
				XSSFSheet sheet = workbook.getSheetAt(numSheets);
				if (sheet.getFirstRowNum() != sheet.getLastRowNum())
					read(sheet, flag);
			}
		}
	}

	/*---------------------------每个Sheet具体读--------------------------*/
	public void read(XSSFSheet sheet, String flag) throws Exception {
		if (flag.equals("hour")) {
//			System.out.println("Impl");
			if (!hourR.readH(sheet,pc)) {
				this.setMessage(hourR.getMes());
			} else
				this.setMessage("没有错误信息\r\n");
		}
		if (flag.equals("pro")) {
//			 System.out.println(!proScR.readPro(sheet,pc));
			if (!proScR.readPro(sheet,pc)) {
				this.setMessage(proScR.getMes());
			}
			else
				this.setMessage("没有错误信息\r\n");
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

}
