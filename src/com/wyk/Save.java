package com.wyk;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wyk.dao.Database;

public class Save {
	FileInputStream fis=null;
	Workbook workbook=null;
	Sheet sheet=null;
	Row row=null;
	Cell cell=null;
	int rowNumber=-1,columnNumber=-1;
	String content=null;
	List<String> ls=null;
	FileOutputStream out=null;
	
	public void parseExcel(String path) throws IOException  {
		try{		
		workbook=new HSSFWorkbook(new FileInputStream(path));	
		sheet=(HSSFSheet) workbook.getSheetAt(0);
		}
		catch(IOException e){
			System.out.println("1");
			e.printStackTrace();
		}
		catch(OfficeXmlFileException e){//XSSFWorkbook����excel2007��2007���ϵİ汾
			System.out.println("2");
			workbook=new XSSFWorkbook(new FileInputStream(path));
			sheet=(XSSFSheet) workbook.getSheetAt(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			rowNumber=sheet.getPhysicalNumberOfRows(); 
			System.out.println("������"+rowNumber );	    
			columnNumber=sheet.getRow(0).getLastCellNum();
	    	System.out.println("������"+columnNumber);	
			System.out.println("������"+sheet.getSheetName());	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
	}
	
	
	public void saveTable(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="insert into standard values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql2="set names utf8";
		
		Database database=new Database();	
		
		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql2);
			database.ps.execute();
			database.ps=database.con.prepareStatement(sql);
			
			for(int i=1;i<rowNumber;i++){
				row=sheet.getRow(i);
				for(int j=0,k=1;j<columnNumber;j++,k++){
					
					
					content=row.getCell(j).toString().trim();
					content=new String(content.getBytes("utf-8"),"utf-8");
					database.ps.setString(k,content);
					
				}
		
					database.ps.executeUpdate();
			
		}
			
		database.con.close();
		database.ps.close();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveProduct_Table(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="insert into chanpin(zhulei,fenlei,xiaolei,fenxiang,chanpin,zhuanye,jiliang,zgs,guimo) values(?,?,?,?,?,?,?,?,?)";
		String sql2="set names utf8";
		
		Database database=new Database();

		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql2);
			database.ps.execute();
			database.ps=database.con.prepareStatement(sql);
			
			for(int i=1;i<rowNumber;i++){ 
				row=sheet.getRow(i);
				for(int j=0,k=1;j<columnNumber;j++,k++){
					
					cell=row.getCell(j);				
					if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
						database.ps.setDouble(k, Double.valueOf(cell.toString()));
					}
					else{
						database.ps.setString(k, new String(cell.toString().getBytes("utf-8"),"utf-8"));
					}				
				}
		
					database.ps.executeUpdate();
			
		}
			
		database.con.close();
		database.ps.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		
	}
	
	
	public void saveGS_Table(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="insert into gs(id,fid,chanpin,hj,xf,gs) values(?,?,?,?,?,?)";
		String sql2="set names utf8";	
		Database database=new Database();
		
		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql2);
			database.ps.execute();
			database.ps=database.con.prepareStatement(sql);
			String fid="1001";
			String id="100101";
			
			
			row=sheet.getRow(1);
			database.ps.setInt(1,Integer.valueOf(id) );
			database.ps.setInt(2, Integer.valueOf(fid));
			
				for(int i=0,k=3;i<columnNumber;k++,i++){
					cell=row.getCell(i);
					if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
						database.ps.setDouble(k, Double.valueOf(cell.toString()));
					}
					else{
						database.ps.setString(k, new String(cell.toString().getBytes("utf-8"),"utf-8"));
					}
				}
		
				database.ps.executeUpdate();
		
			for(int i=2;i<rowNumber;i++){ 
				System.out.println(row.getCell(0));
				System.out.println(sheet.getRow(i-1).getCell(0));
				if(row.getCell(0).toString().trim().equals(sheet.getRow(i-1).getCell(0).toString().trim())){ 
					id=String.valueOf(Integer.parseInt(id)+1);
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				else{
					fid=String.valueOf(Integer.parseInt(fid)+1);
					id=fid+"01";
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				for(int j=0,k=3;j<columnNumber;j++,k++){	
					cell=row.getCell(j);
					if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
					database.ps.setDouble(k, Double.valueOf(cell.toString()));
				}
				else{
					database.ps.setString(k, new String(cell.toString().getBytes("utf-8"),"utf-8"));
				}				
 			
				}	
					database.ps.executeUpdate();		
		}
			
		database.con.close();
		database.ps.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		
	}
	
	
	
	public void saveXS_Table(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String sql="insert into xs(id,fid,chanpin,xs1,xs2,xs3) values(?,?,?,?,?,?)";
		String sql2="set names utf8";	
		Database database=new Database();
		
		try{
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql2);
			database.ps.execute();
			database.ps=database.con.prepareStatement(sql);
			String fid="1001";
			String id="100101";
			
			
			row=sheet.getRow(1);
			database.ps.setInt(1,Integer.valueOf(id) );
			database.ps.setInt(2, Integer.valueOf(fid));
			
				for(int i=0,k=3;i<columnNumber;k++,i++){
					cell=row.getCell(i);
					if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						database.ps.setDouble(k, Double.valueOf(cell.toString()));
					}
					else{
						database.ps.setString(k, new String(cell.toString().getBytes("utf-8"),"utf-8"));
					}
				}
		
				database.ps.executeUpdate();
		
			for(int i=2;i<rowNumber;i++){
                row=sheet.getRow(i);
				System.out.println(row.getCell(0));
				System.out.println(sheet.getRow(i-1).getCell(0));
				if(row.getCell(0).toString().trim().equals(sheet.getRow(i-1).getCell(0).toString().trim())){ 
					id=String.valueOf(Integer.parseInt(id)+1);
 
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				else{
					fid=String.valueOf(Integer.parseInt(fid)+1);
					id=fid+"01";
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				for(int j=0,k=3;j<columnNumber;j++,k++){	
					cell=row.getCell(j);
					if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
					database.ps.setDouble(k, Double.valueOf(cell.toString()));
				}
				else{
					database.ps.setString(k, new String(cell.toString().getBytes("utf-8"),"utf-8"));
				}				
 				
				}	
					database.ps.executeUpdate();		
		}
			
		database.con.close();
		database.ps.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		} 
		
	}
	
	

	public void bianli(String path){
		System.out.println("��ʼ������");
		try{
			this.parseExcel(path);
		 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		
		for(int rowIndex=0;rowIndex<rowNumber;rowIndex++){
			 row=sheet.getRow(rowIndex);
			for(int columnIndex=0;columnIndex<columnNumber;columnIndex++){
				cell=row.getCell(columnIndex);				
				 System.out.println(cell);
			}	 
		
		}
	}
	
	public String check(Cell cell){
		if(null==cell||Cell.CELL_TYPE_BLANK==cell.getCellType()){
			return "��";
		}
		else{
			return cell.toString().replaceAll("[\\t\\n\\r]", "");
		}
	}
	
	public void copy(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		
		String cell2 = null;
		for(int rowIndex=0;rowIndex<rowNumber;rowIndex++){
			 row=sheet.getRow(rowIndex);
				cell=row.getCell(1);
				if(cell.toString().trim().equals("")){
 
					cell.setCellType(2);
				
					cell.setCellValue(cell2);
					try{
						workbook.write(out);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
				}
				else{
					System.out.println(cell );
					cell2=cell.toString();
				}			
			}
	}
	

	
	public String addChanpinXsStandard(String path){//��Ӳ�Ʒϵ����׼����ģ���е�������ӵ�chanpin���xf����
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "��������Ϊ��";
		}
		String sql1="select id from chanpin";
		String sqlChanpin="insert into chanpin values(?,?,?,?,?,?,?,?,?,?)";
		String sqlXs="insert into xs values(?,?,?,?,?,?)";
		Database databaseChanpin=new Database();
		Database databaseXs=new Database();
		int lastRowId=0;
		try{
			
		databaseChanpin.con=databaseChanpin.connect();
		databaseChanpin.ps=databaseChanpin.con.prepareStatement(sql1);
		databaseChanpin.rs=databaseChanpin.ps.executeQuery();
		
		databaseXs.con=databaseXs.connect();
		databaseXs.ps=databaseXs.con.prepareStatement(sqlXs);
		if(databaseChanpin.rs.next()){//������ʱ�����ݿⲻΪ��
			databaseChanpin.rs.last();
			lastRowId=databaseChanpin.rs.getInt(1);
		}
		else{//������ʱ�����ݿ�Ϊ��
			lastRowId=1000;//��id�ĳ�ֵ��Ϊ1000����Ϊ�ں���Ĵ��������Ȱ�lastRowId��1�����Բ��ܸ�ֵ1001
		}
			databaseChanpin.ps=databaseChanpin.con.prepareStatement(sqlChanpin);
			for(int i=1;i<rowNumber;i++){
				row=sheet.getRow(i);
				lastRowId=lastRowId+1;
				System.out.println(i);
				databaseChanpin.ps.setInt(1, lastRowId);
				for(int j=0,k=2;j<columnNumber-3;k++,j++){
//						System.out.println(cell);
						if(row.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC){
							databaseChanpin.ps.setDouble(k, Double.parseDouble(row.getCell(j).toString()));
						}
						else{
							databaseChanpin.ps.setString(k, this.check(row.getCell(j)));
						}	
				}
				databaseXs.ps.setInt(1, lastRowId);//id
				databaseXs.ps.setString(2, this.check(row.getCell(1)));//����
				databaseXs.ps.setString(3, this.check(row.getCell(4)));//��Ʒ
				databaseXs.ps.setString(4, this.check(row.getCell(9)));//ϵ��1
				databaseXs.ps.setString(5, this.check(row.getCell(10)));//ϵ��2
				databaseXs.ps.setString(6, this.check(row.getCell(11)));//ϵ��3
				if(databaseChanpin.ps.executeUpdate()<1){
					return "������ӹ����г��ִ���";//δ��������ݿ����Ѿ���ӽ�ȥ��
				}
				if(databaseXs.ps.executeUpdate()<1){
					return "������ӹ����г��ִ���";
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			databaseChanpin.close();
		}
		return "������ӳɹ�";
	}
	

	public String addXfStandard(String path){//��ӹ�ʱ��׼����xf���������
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "��������Ϊ��";
		}
		String sql1="select id,fid,fenlei,chanpin from xf order by fid";
		String sql2="insert into xf values(?,?,?,?,?,?,?)";
		String id=null;
		String fid=null;
		Database database=new Database();
		int lastRowId=0;
		int lastRowFid=0;
		String lastRowFenlei=null;
		String lastRowChanpin=null;
		try{		
		database.con=database.connect();
		database.ps=database.con.prepareStatement(sql1);
		database.rs=database.ps.executeQuery();
		int p,q;
		if(database.rs.next()){//������ݿⲻΪ�գ�ȡ�����ݿ������һ�������е�fid,����,��Ʒ
			database.rs.last();
			lastRowId=database.rs.getInt(1);
			lastRowFid=database.rs.getInt(2);
			lastRowFenlei=database.rs.getString(3);
			lastRowChanpin=database.rs.getString(4);
			id=String.valueOf(lastRowId);
			fid=String.valueOf(lastRowFid);
		}
		else{//������ݿ�Ϊ��
			lastRowId=100100;
			lastRowFid=1000;
			lastRowFenlei="��";
			lastRowChanpin="��";
			id=String.valueOf(lastRowId);
			fid=String.valueOf(lastRowFid);
			
		}
			database.ps=database.con.prepareStatement(sql2);
			String[] temp=new String[columnNumber];
			String[] temp2=new String[columnNumber];	
		
			for(int i=1;i<rowNumber;i++){			
				row=sheet.getRow(i);			
				for(int j=0;j<columnNumber;j++){										
					if((row.getCell(j)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(j).getCellType())){//excel�еĵ�Ԫ��û�и�ʽ����û������ʱΪnull���и�ʽû������ʱΪblank
						if(j==columnNumber-1)//���һ��,����ʱ��Ϊ��ʱ����0
							temp2[j]="0";
						else
						temp2[j]=temp[j];//Ϊ�յ��������һ�У���Ϊtemp�м�¼��ֵ				
					}
					else{//��Ԫ��Ϊ��ʱ������temp�У�temp2�д��뵱ǰȡ�����Ҵ����������
						temp[j]=row.getCell(j).toString().trim();
						temp2[j]=temp[j];
						System.out.print(temp2[j]+"     ");
					}				
				}
				
				if(temp2[0].equals(lastRowFenlei)&&temp2[1].equals(lastRowChanpin)){//�����excel��ȡ���ķ���Ͳ�Ʒ�������ݿ������һ�е���ͬ����fid�Ų��䣬id�ż�1
					id=String.valueOf(Integer.parseInt(id)+1);	
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				else{//��ͬ����fid��1��id����fidƴ�ӡ�01��
					fid=String.valueOf(Integer.parseInt(fid)+1);
					id=fid+"01";		
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
					lastRowFenlei=temp2[0];
					lastRowChanpin=temp2[1];
				}
				for(p=0,q=3;p<columnNumber-1;p++,q++){//��temp2[]�е����ݴ������ݿ�
					database.ps.setString(q, temp2[p]);				
		
				}	
				database.ps.setDouble(q,Double.parseDouble(temp2[p]));
				if(database.ps.executeUpdate()<1){
					return "������ӹ����г��ִ���";
				}
				
		}
		}
		catch(Exception e){
			e.printStackTrace();
			return "������ӹ����г��ִ���";
		}
		finally{
			database.close();
		}
		return "������ӳɹ�";
	}
	
	public String upadateChanpinXs(String path){//�������ݿ��е�chanpin���ϵ�������з���Ͳ�Ʒ�ֶ���ΪΨһ�Բ��ұ�ʾ�����޸�
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "��������Ϊ��";
		}
		String updateXs="update xs set xs1=?,xs2=?,xs3=? where fenlei=? and chanpin=?";
		String updateChanpinGs="update chanpin set zhulei=?,xiaolei=?,fenxiang=?,zhuanye=?,jiliang=?,zgs=?,jishu=? where fenlei=? and chanpin=?";
		Database databaseXs = new Database();
		Database databaseChanpin=new Database();
		
		try{
			databaseXs.con=databaseXs.connect();
			databaseXs.ps=databaseXs.con.prepareStatement(updateXs);		
			databaseChanpin.con=databaseChanpin.connect();
			databaseChanpin.ps=databaseChanpin.con.prepareStatement(updateChanpinGs);
			
			for(int i=1;i<rowNumber;i++){
				row=sheet.getRow(i);
				databaseChanpin.ps.setString(1, this.check(row.getCell(0)));//����
				databaseChanpin.ps.setString(2, this.check(row.getCell(2)));//С��
				databaseChanpin.ps.setString(3, this.check(row.getCell(3)));//����
				databaseChanpin.ps.setString(4, this.check(row.getCell(5)));//רҵ
				databaseChanpin.ps.setString(5, this.check(row.getCell(6)));//������λ
				databaseChanpin.ps.setDouble(6, Double.parseDouble(this.check(row.getCell(7))));//�ܹ�ʱ
				databaseChanpin.ps.setDouble(7, Double.parseDouble(this.check(row.getCell(8))));//����
				databaseChanpin.ps.setString(8, this.check(row.getCell(1)));//����
				databaseChanpin.ps.setString(9, this.check(row.getCell(4)));//��Ʒ
				
				databaseXs.ps.setString(1, this.check(row.getCell(9)));//ϵ��һ
				databaseXs.ps.setString(2, this.check(row.getCell(10)));//ϵ����
				databaseXs.ps.setString(3, this.check(row.getCell(11)));//ϵ����
				databaseXs.ps.setString(4, this.check(row.getCell(1)));//����
				databaseXs.ps.setString(5, this.check(row.getCell(4)));//��Ʒ
				
				if(databaseChanpin.ps.executeUpdate()<1){//�޸�ʧ��
					return "�����޸Ĺ����г��ִ���";
				}
				if(databaseXs.ps.executeUpdate()<1){
					return "�����޸Ĺ����г��ִ���";
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return "�����޸Ĺ����г��ִ���";
		}
		finally{
			databaseXs.close();
		}
		return "�����޸ĳɹ�";

	}
	
	
	
	/*public void updateXS(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		String sql1="update xs set xs3=?,xs2=?,xs1=? where chanpin=? and fenlei=?";
		
		Database database = new Database();
		try{
			
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql1);
			
			for(int i=1;i<rowNumber;i++){
				row=sheet.getRow(i);
				for(int j=columnNumber-1,k=1;j>=0;j--,k++){
                    database.ps.setString(k, new String(this.check(row.getCell(j)).getBytes("utf-8"),"utf-8"));	
				}
				database.ps.executeUpdate();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		database.close();
	}*/
	
	public String updateXf(String path){
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "��������Ϊ��";
		}
		String fenlei=null;
		String chanpin=null;
		int fid=-1;
		int id=-1;
		String sqlId="select fid from xf where fenlei=? and chanpin=?";
		String sql="update xf set hj=?,xf=?,gsxf=? where fid=? and id=?";
		String temp=null;
		Database databaseId=new Database();
		Database database=new Database();
		
		try{
			databaseId.con=databaseId.connect();
			databaseId.ps=databaseId.con.prepareStatement(sqlId);
			database.con=database.connect();
			database.ps=database.con.prepareStatement(sql);
			int i;
			for(i=1;i<rowNumber;){
				fenlei=sheet.getRow(i).getCell(0).toString();//��excel���ж�������Ͳ�Ʒ
				chanpin=sheet.getRow(i).getCell(1).toString();
				databaseId.ps.setString(1, fenlei);//����
				databaseId.ps.setString(2, chanpin);//��Ʒ
				databaseId.rs=databaseId.ps.executeQuery();
				while(databaseId.rs.next()){
					fid=databaseId.rs.getInt("fid");//�ҵ�fid
				}
				id=Integer.parseInt(String.valueOf(fid)+"01");//�ҵ�id
			   databaseId.rs.last();
			    int count=databaseId.rs.getRow();//�����������
			for(int j=i,num=1;j<rowNumber&&num<=count;j++,id++,i=j,num++){//j���б�
				row=sheet.getRow(j);
				if((row.getCell(2)!=null)&&(Cell.CELL_TYPE_BLANK!=row.getCell(2).getCellType())){//�����i�еĹ������ڲ��ա���j=0ʱ��һ����Ϊ��
					temp=row.getCell(2).toString();//							
			}
				database.ps.setString(1, temp);//��������
				database.ps.setString(2, row.getCell(3).toString());//����ϸ��
				if((row.getCell(4)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(4).getCellType())){
					database.ps.setDouble(3, 0);//��ʱϸ��
				}
				else{
					database.ps.setDouble(3, Double.parseDouble(row.getCell(4).toString()));
				}
				database.ps.setInt(4, fid);
				database.ps.setInt(5, id);
				
				if(database.ps.executeUpdate()<1){
					return "�����޸Ĺ����г��ִ���";
				}
		    }
		}
	}
		catch(Exception e){
			e.printStackTrace();
			return "�����޸Ĺ����г��ִ���";
		}
		finally{
			databaseId.close();
			database.close();
		}
		return "�����޸ĳɹ�";
	}
	
	
	
	  public String updateGs1(String path){
			try{
				this.parseExcel(path);
			}
			catch(Exception e){
				e.printStackTrace();
			}	
			if(rowNumber<=1){
				return "��������Ϊ��";
			}
			String fenlei=null;
			String chanpin=null;
			double fid=-1;
			double id=-1;
			String sqlId="select id from xf where fenlei=? and chanpin=?";
			String sql="update xf set hj=?,xf=?,gsxf=? where fid=? and id=?";
			String[] temp=new String[columnNumber];
			Database databaseId=new Database();
			Database database=new Database();
			
			try{
				databaseId.con=databaseId.connect();
				databaseId.ps=databaseId.con.prepareStatement(sqlId);
				database.con=database.connect();
				database.ps=database.con.prepareStatement(sql);
				
				for(int i=1;i<rowNumber;i++){
					row=sheet.getRow(i);
					fenlei=row.getCell(0).toString();//��excel���ж�ȡ����Ͳ�Ʒ
					chanpin=row.getCell(1).toString();
					if((row.getCell(2)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(2).getCellType())){//�����i�еĹ�������Ϊ�ա���i=0ʱ��һ����Ϊ��
						databaseId.ps.setString(1, temp[0]);//����
						databaseId.ps.setString(2, temp[1]);//��Ʒ
						databaseId.rs=databaseId.ps.executeQuery();
						while(databaseId.rs.next()){
							fid=databaseId.rs.getDouble("fid");//�ҵ�fid
						}
						id=Double.parseDouble(String.valueOf(fid)+"01");//�ҵ�id
						databaseId.ps.setString(1, temp[2]);//��������
						databaseId.ps.setString(2, temp[0]);//����
						databaseId.ps.setString(3, temp[1]);//��Ʒ
						databaseId.ps.executeUpdate();//���޸Ĺ�������
						
						database.ps.setString(1, temp[3]);//����ϸ��
						database.ps.setString(3, temp[0]);//����
						database.ps.setString(4, temp[1]);//��Ʒ
						database.ps.setString(5, temp[2]);//��������,���޸ĺ�Ĺ�������ȥ����
					
						if((row.getCell(4)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(4).getCellType())){//��ʱ��Ϊ�գ���0
							database.ps.setDouble(2, 0);//��ʱϸ��
						}
						else{
							database.ps.setDouble(2, Double.parseDouble(row.getCell(4).toString()));//��ʱϸ��
							System.out.println("��ʱϸ����"+row.getCell(4));
						}
						if(database.ps.executeUpdate()<1){//�޸Ļ���ϸ�ֺ͹�ʱϸ��
							return "�����޸Ĺ����г��ִ���";
						}
					}
					else{//��i�еĹ������ڲ�Ϊ��
						for(int j=0;j<columnNumber;j++){
							temp[j]=row.getCell(j).toString();//���ݲ�Ϊ��ʱ���ݴ浽temp���� 
//							System.out.println(temp[j]);
							}
							databaseId.ps.setString(1, temp[2]);//��������
							databaseId.ps.setString(2, temp[0]);//����
							databaseId.ps.setString(3, temp[1]);//��Ʒ
							databaseId.ps.executeUpdate();//���޸Ĺ�������
							
							database.ps.setString(1, temp[3]);//����ϸ��
							System.out.println("��ʱϸ����"+temp[4]);
							database.ps.setDouble(2, Double.parseDouble(temp[4]));//��ʱϸ��
							database.ps.setString(3, temp[0]);//����
							database.ps.setString(4, temp[1]);//��Ʒ
							database.ps.setString(5, temp[2]);//��������,���޸ĺ�Ĺ�������ȥ����
							if(database.ps.executeUpdate()<1){//�޸Ļ���ϸ�ֺ͹�ʱϸ��
								return "�����޸Ĺ����г��ִ���";
							}				
				    }
			   }
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				database.close();
				databaseId.close();
			}
			return "�����޸ĳɹ�";
			
		}
	
	public static void main(String[] args){
		 Save rl=new Save();
		 rl.addChanpinXsStandard("��Ʒϵ�����ģ��.xlsx");//���Գɹ�
		 rl.addXfStandard("��ʱ���ģ��.xlsx");//���Գɹ�
//		 rl.updateGsZgs("");
//		 rl.updateXS("ϵ������ģ��.xlsx");
//		 rl.test2();


		
	}

}
