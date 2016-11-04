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
	private File file;// File����Ŀ���ǻ�ȡҳ���ϴ����ļ�
	private String fileContextType;
	private String fileFileName;
	private String message = "";
	private String flag;
	private String pc;
	public static List<employee> empList = new ArrayList<employee>();
	public static List<project> proList = new ArrayList<project>();
	/*
	 * ��ֱ���Ϣ����
	 */
	public String checkScore() throws Exception{

		if (file != null) {
			String path = file.getAbsolutePath();// ��ȡ�ļ���·��
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(path);
//				System.out.println(this.getFileFileName()+"  \n" +inputStream);
				scoreR.readScore(inputStream);
//				System.out.println(new String(this.getFileFileName().getBytes("gbk"),"utf-8"));
				if(scoreR.getMes() != "")
					scoreR.setMes(new String(this.getFileFileName().getBytes("gbk"),"utf-8") + "�Ĵ�����Ϣ���£�\r\n" + scoreR.getMes()
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
	 * �����ֱ���Ϣ
	 */
	public String importSc() throws Exception {
		
//			Ϊû���¼���������Ϣ
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
			setMessage("û�д�����Ϣ");
		}
		else
			setMessage(scoreError.getError() + jg.getMessage());
		
		scoreError.setError("");
//		System.out.println(this.getMessage());

//		�ϴ�һ�ΰ�ԭ�������
		empList.clear();
		proList.clear();
		
		return "success";
	}
	/*
	 * ���빤ʱ�ͳɼ�������Ϣ
	 */
	public String importSingle() throws Exception{
//		System.out.println("actionFlag:" + this.getFlag() + this.getPc());
		if (file != null) {
			String path = file.getAbsolutePath();// ��ȡ�ļ���·��
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
			setMessage("û�д�����Ϣ");
		return str;
	}

	/*
	 * дexcel
	 */
	public String export() throws Exception{
//	    setMessage(check());
		String[] tableHeader = { "���", "����", "����", "����", "ְ��/��λ", "���˲㼶",
				"��Ŀ�ܹ���", "�����ּ�Ȩ���ܳɼ�", "�����ۺ�����", "�����ȼ�" };
		short cellNumber = (short) tableHeader.length;// �������
		XSSFWorkbook workbook = new XSSFWorkbook(); // ����һ��excel
		XSSFSheet sheet = workbook.createSheet("���Ա����Ŀ��Ч���˳ɼ�ȡ����");
		XSSFCellStyle style = workbook.createCellStyle();// ���и�ʽ
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);

		XSSFFont font2 = workbook.createFont();// ����

		XSSFCellStyle style1 = workbook.createCellStyle();// ���и�ʽ
		style1.setWrapText(true);

		// ������
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		XSSFFont font = workbook.createFont();// ����
		XSSFCellStyle style2 = workbook.createCellStyle();// �����ʽ
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		font.setFontHeightInPoints((short) 14);
		style2.setFont(font);
		cell.setCellValue("������");
		cell.setCellStyle(style2);

		// �ϲ���Ԫ��1
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, cellNumber - 1));
		XSSFRow row1 = sheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);
		XSSFFont font1 = workbook.createFont();// ����
		XSSFCellStyle style3 = workbook.createCellStyle();// ���и�ʽ
		style3.setAlignment(XSSFCellStyle.ALIGN_CENTER_SELECTION);
		cell1.setCellType(XSSFCell.CELL_TYPE_STRING);
		font1.setFontHeightInPoints((short) 16);
		font1.setBold(true);
		style3.setFont(font1);
		cell1.setCellStyle(style3);
		cell1.setCellValue("�������Ա����Ŀ��Ч���˳ɼ�ȡ����");

		// ����:
		XSSFRow row2 = sheet.createRow(2);
		XSSFCell cell2 = row2.createCell(0);
		cell2.setCellType(XSSFCell.CELL_TYPE_STRING);
		font2.setFontHeightInPoints((short) 12);
		style2.setFont(font2);
		cell2.setCellValue("����:");
		cell2.setCellStyle(style2);

		// ��ͷ
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
		name = formatter.format(currTime)+" - ���Ա����Ŀ��Ч���˳ɼ�ȡ����.xlsx";
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String( name.getBytes("gb2312"), "ISO8859-1"));// filename�����ص�xlsx���������������Ӣ��
			response.setContentType("application/msexcel;charset=GBK");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			workbook.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * У��
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
				mes1 = mes1 + rs.getString(2) + "��" + rs.getString(1)+"��Ŀ������й�ʱ������ͨ�������޿��˳ɼ����벹��\r\n";
			if(rs.getFloat(3) != 0 && rs.getFloat(4) == 0){
				if(rs.getString(2).equals("jl"))
					mes2 = mes2 + rs.getString(1)+"��Ŀ����Ŀ�����п��˳ɼ�����δ���ʱ�����ʵ\r\n";
				else
					mes3 = mes3 + rs.getString(2) + "��" + rs.getString(1)+"��Ŀ�п��˳ɼ�����δ���ʱ�����ʵ\r\n";
			}
			if(rs.getFloat(3) == 0 && rs.getFloat(4) == 0)
				mes4 = mes4 + rs.getString(2)+"��" +rs.getString(1)+"��Ŀû�п��˳ɼ��͹�ʱ���벹��\r\n";
		}
		ResultSet rs1 = ps1.executeQuery();
		while(rs1.next()){
			if(rs1.getFloat(2) == 0 )
				mes5 = mes5 + rs1.getString(1)+"��Ŀ����Ŀ�ɼ�Ϊ�գ��벹��\r\n";
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
			mes1 = "У��ȫ����ȷ\r\n";
			setMessage(mes1.replace("\r\n", "<br>"));
		}
		else
			setMessage(mes1.replace("\r\n", "<br>") + mes2.replace("\r\n", "<br>")
					 + mes3.replace("\r\n", "<br>") + mes4.replace("\r\n", "<br>")
					 + mes5.replace("\r\n", "<br>"));
		return "success";
	}
	
	/*
	 * ��ȡ�ļ���չ��������excel��ͬ�汾
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
