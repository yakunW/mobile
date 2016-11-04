package com.hm.impl.cooper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.hm.org.Database;
import com.opensymphony.xwork2.ActionContext;

public class renameImpl {
	
	Database db = new Database();
	Connection conn = db.getConnection();
	PreparedStatement ps;

	public void rename(InputStream inputStream) throws IOException{
//		System.out.println("什么情况？");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("合作公司名录");
		
		final int cellNum = 1;int rowNum = 2;
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(cellNum-1);//newName
		XSSFCell cell0 = row.getCell(cellNum);//曾用名
//		System.out.println("oldname"+cell0.getStringCellValue());	
		String fileBody = "";
		while(row != null ){
			
			cell = row.getCell(cellNum-1);
			cell0 = row.getCell(cellNum);
			rowNum++;
			row = sheet.getRow(rowNum);
			fileBody = fileBody + cell.getStringCellValue()+"\r\n";
			if(cell0.getCellType() != XSSFCell.CELL_TYPE_BLANK){
//				System.out.println(cell0.getStringCellValue()+"   "+cell.getStringCellValue());
				modify(cell0.getStringCellValue(),cell.getStringCellValue());
			}
		}
		export_txt(fileBody);
		try {
//			if(!ps.isClosed())
				ps.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
////	手动修改时，判断曾用名是否存在
//	public boolean isExist(String oldName){
//		String sql = "SELECT name FROM company WHERE name=?";
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, oldName);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
////				if(!ps.isClosed())
//					ps.close();
//				return true;
//			}
//			else
//				return false;
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
//	}
	
	
//	修改名称
	public void modify(String oldName,String newName){
		int count = 0;
		String find_sql = "SELECT count(*) FROM company WHERE name=?";
		try{
			ps = conn.prepareStatement(find_sql);
			ps.setString(1, oldName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
//				System.out.println("oldname"+oldName);
			}
		}catch (SQLException e) {
//			System.out.println("oldname:"+oldName);
			System.out.println(e.getMessage());
		}
//		try {
		String sql = "UPDATE company SET name=?,oldName=? WHERE name=?";
//		System.out.println(count);
		for(int i = 0; i < count; i++){
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, newName);
				ps.setString(2, oldName);
				ps.setString(3, oldName);
				ps.executeUpdate();
			} catch (SQLException e) {
	//			System.out.println("oldname:"+oldName);
				System.out.println(e.getMessage());
			}
		}
	}
	
//	导出utf-16格式txt格式
	public void export_txt(String fileBody){
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String root = sc.getRealPath("");//项目根目录的绝对路径
		FileOutputStream fos = null;  
        OutputStreamWriter osw = null;  
        String filePath = root+"\\newName\\CompList";
        try {  
            fos = new FileOutputStream(filePath);  
//            System.out.println(filePath);
            osw = new OutputStreamWriter(fos, "UTF-16");  
            osw.write(fileBody);  
//            System.out.println("完毕");
        } catch (Exception e) {  
            e.printStackTrace();  
          
        }finally{  
            if(osw!=null){  
                try {  
                    osw.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if(fos!=null){  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
	}
	
}
