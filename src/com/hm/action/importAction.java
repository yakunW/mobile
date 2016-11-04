package com.hm.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;


import com.hm.entity.employee;
import com.hm.entity.project;
import com.hm.impl.employee.XSSFImpl;
import com.hm.impl.employee.exportExcel;
import com.hm.impl.employee.scoreR;
import com.hm.org.Database;
import com.hm.util.Judge;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class importAction extends ActionSupport {
	XSSFImpl XSSF = new XSSFImpl();
	private File file;// File对象，目的是获取页面上传的文件
	private String fileContextType;
	private String fileFileName;
	private String message = "";
	private String flag;
	private String pc;
	public static List<employee> empList = new ArrayList<employee>();
	public static List<project> proList = new ArrayList<project>();
	/*
	 * 打分表信息纠错
	 */
	public String checkScore() throws Exception{

		if (file != null) {
			String path = file.getAbsolutePath();// 获取文件的路径
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
//				System.out.println(this.getFileFileName()+"  \n" +inputStream);
				scoreR.readScore(inputStream);
//				System.out.println(new String(this.getFileFileName().getBytes("gbk"),"utf-8"));
				if(scoreR.getMes() != "")
					scoreR.setMes(new String(this.getFileFileName().getBytes("gbk"),"utf-8") + "的错误信息如下：\r\n" + scoreR.getMes()
							+"----------------------------------------------------------------\r\n");
				scoreError.setError(scoreError.getError() + scoreR.getMes().replace("\r\n", "<br>"));
//				System.out.println("cuowu" + scoreError.getError());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	/*
	 * 导入打分表信息
	 */
	public String importSc() throws Exception {
		
//			为没项记录添加批次信息
		for(int i = 0;i<empList.size();i++){
			 employee e = empList.get(i);
			 e.setPc(pc);
//			 System.out.println(e.getProID()+" "+e.getName()+" "+e.getIsLeader());
 	    }
		for (int i = 0; i < proList.size(); i++) {
 			project p = proList.get(i);
 			p.setPc(pc);
// 			System.out.println(p.getProID() + "  " + proList.size());
 		}

		Judge jg = new Judge();
		jg.check("score", empList, proList,pc);

//		System.out.println("action:"+empList.size());
		if (scoreError.getError().equals("") && jg.getMessage().equals("")){
			setMessage("没有错误信息");
		}
		else
			setMessage(scoreError.getError() + jg.getMessage());
		
		scoreError.setError("");
//		System.out.println(this.getMessage());

//		上传一次把原来的清空
		empList.clear();
		proList.clear();
		
		return "success";
	}
	/*
	 * 导入工时和成绩考核信息
	 */
	public String importSingle() throws Exception{
//		System.out.println("actionFlag:" + this.getFlag() + this.getPc());
		if (file != null) {
			String path = file.getAbsolutePath();// 获取文件的路径
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
				XSSF.readXSSF(inputStream, getFlag(),this.getPc());
				setMessage(XSSF.getMessage().replace("\r\n", "<br>"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return "success";
	}
	
	public String check_export() throws Exception{
		String str;
		if(getFlag().equals("check")){
			str = check();
		}
		else
			str = export();
		if (str == null) 
			setMessage("没有错误信息");
		return str;
	}

	/*
	 * 写excel
	 */
	public String export() throws Exception{
//	    setMessage(check());
		String[] tableHeader = { "序号", "姓名", "部门", "大区", "职务/岗位", "考核层级",
				"项目总工日", "分项打分加权汇总成绩", "大区综合评分", "评定等级" };
		short cellNumber = (short) tableHeader.length;// 表的列数
		XSSFWorkbook workbook = new XSSFWorkbook(); // 创建一个excel
		XSSFSheet sheet = workbook.createSheet("年度员工项目绩效考核成绩取定表");
		XSSFCellStyle style = workbook.createCellStyle();// 居中格式
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);

		XSSFFont font2 = workbook.createFont();// 字体

		XSSFCellStyle style1 = workbook.createCellStyle();// 换行格式
		style1.setWrapText(true);

		// 附件三
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		XSSFFont font = workbook.createFont();// 字体
		XSSFCellStyle style2 = workbook.createCellStyle();// 字体格式
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		font.setFontHeightInPoints((short) 14);
		style2.setFont(font);
		cell.setCellValue("附件三");
		cell.setCellStyle(style2);

		// 合并单元格1
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, cellNumber - 1));
		XSSFRow row1 = sheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);
		XSSFFont font1 = workbook.createFont();// 字体
		XSSFCellStyle style3 = workbook.createCellStyle();// 居中格式
		style3.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
		cell1.setCellType(XSSFCell.CELL_TYPE_STRING);
		font1.setFontHeightInPoints((short) 16);
		font1.setBold(true);
		style3.setFont(font1);
		cell1.setCellStyle(style3);
		cell1.setCellValue("大区年度员工项目绩效考核成绩取定表");

		// 大区:
		XSSFRow row2 = sheet.createRow(2);
		XSSFCell cell2 = row2.createCell(0);
		cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
		font2.setFontHeightInPoints((short) 12);
		style2.setFont(font2);
		cell2.setCellValue("大区:");
		cell2.setCellStyle(style2);

		// 表头
		XSSFRow row3 = sheet.createRow(3);
		for (int numCell = 0; numCell < cellNumber; numCell++) {
			XSSFCell cell4 = row3.createCell(numCell);
			cell4.setCellValue(tableHeader[numCell]);
			// cell4.setCellStyle(style);
			if (numCell == cellNumber - 3 || numCell == cellNumber - 2) {
				cell4.setCellStyle(style1);
				sheet.addMergedRegion(new CellRangeAddress(3, 4, numCell,
						numCell));
			} else
				sheet.addMergedRegion(new CellRangeAddress(3, 5, numCell,
						numCell));
		}

		XSSFRow row4 = sheet.createRow(5);
		for (int numCell = 7; numCell < 9; numCell++) {
			XSSFCell cell5 = row4.createCell(numCell);
			cell5.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			cell5.setCellValue(100);
			cell5.setCellStyle(style);
		}

		List<employee> emp = new ArrayList<employee>();
		emp = exportExcel.getWeight(pc);
		for (int numRow = 0; numRow < emp.size(); numRow++) {
			employee e = emp.get(numRow);
			XSSFRow row0 = sheet.createRow(numRow + 6);
			for (int numCell = 1; numCell < 8; numCell++) {
				XSSFCell cell0 = row0.createCell(numCell);
				switch (numCell) {
				case 1:
					cell0.setCellValue(e.getName());
					break;
				case 6:
					cell0.setCellValue(e.getProhours());
					break;
				case 7:
					cell0.setCellValue(e.getSumweight());
					break;
				}
			}
//			System.out.println(e.getName() + " " + e.getProhours() + " "
//					+ e.getSumweight());
		}
		String name = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currTime = new Date();
		name = formatter.format(currTime)+" - 年度员工项目绩效考核成绩取定表.xlsx";
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename是下载的xlsx的名，建议最好用英文
			response.setContentType("application/msexcel;charset=GBK");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 校验
	 * */
	public String check() throws Exception{
		String mes1 = " ", mes2 = "", mes3 = "", mes4 = "", mes5 = "";
		Database db = new Database();
		Connection conn = db.getConnection();
		String sql = "select proID,name,scores,hour from employee where pc=?";
		String sql1 = "select proID,score from project where pc=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, getPc());
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps1.setString(1, getPc());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(rs.getFloat(3) == 0 && rs.getFloat(4) != 0)
				mes1 = mes1 + rs.getString(2) + "在" + rs.getString(1)+"项目中已填报有工时并审批通过，但无考核成绩，请补充\r\n";
			if(rs.getFloat(3) != 0 && rs.getFloat(4) == 0){
				if(rs.getString(2).equals("jl"))
					mes2 = mes2 + rs.getString(1)+"项目的项目经理有考核成绩，但未填报工时，请核实\r\n";
				else
					mes3 = mes3 + rs.getString(2) + "在" + rs.getString(1)+"项目有考核成绩，但未填报工时，请核实\r\n";
			}
			if(rs.getFloat(3) == 0 && rs.getFloat(4) == 0)
				mes4 = mes4 + rs.getString(2)+"在" +rs.getString(1)+"项目没有考核成绩和工时，请补充\r\n";
		}
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			if(rs1.getFloat(2) == 0 )
				mes5 = mes5 + rs1.getString(1)+"项目的项目成绩为空，请补充\r\n";
		}
		rs.close();rs1.close();
//		if (!ps.isClosed())
			ps.close();
//		if (!ps1.isClosed())
			ps1.close();
//		if (!conn.isClosed())
			conn.close();
//		System.out.println(mes);
		mes1 += "****************************************************************************************\r\n";
		mes2 += "****************************************************************************************\r\n";
		mes3 += "****************************************************************************************\r\n";
		mes4 += "****************************************************************************************\r\n";
		mes5 += "****************************************************************************************\r\n";

		if(mes1.equals("") && mes2.equals("") && mes3.equals("") && mes4.equals("") && mes4.equals("")){
			mes1 = "校验全部正确\r\n";
			setMessage(mes1.replace("\r\n", "<br>"));
		}
		else
			setMessage(mes1.replace("\r\n", "<br>") + mes2.replace("\r\n", "<br>")
					 + mes3.replace("\r\n", "<br>") + mes4.replace("\r\n", "<br>")
					 + mes5.replace("\r\n", "<br>"));
		return "success";
	}
	
	/*
	 * 获取文件扩展名，区分excel不同版本
	 */
	public String getExtension(String fileName) {
		int position = fileName.lastIndexOf(".");
		return fileName.substring(position);
	}

	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContextType() {
		return fileContextType;
	}
	public void setFileContextType(String fileContextType) {
		this.fileContextType = fileContextType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}
}
