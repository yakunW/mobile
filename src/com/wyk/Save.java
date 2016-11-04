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
		catch(OfficeXmlFileException e){//XSSFWorkbook解析excel2007及2007以上的版本
			System.out.println("2");
			workbook=new XSSFWorkbook(new FileInputStream(path));
			sheet=(XSSFSheet) workbook.getSheetAt(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			rowNumber=sheet.getPhysicalNumberOfRows(); 
			System.out.println("行数是"+rowNumber );	    
			columnNumber=sheet.getRow(0).getLastCellNum();
	    	System.out.println("列数是"+columnNumber);	
			System.out.println("表名是"+sheet.getSheetName());	
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
		System.out.println("开始遍历了");
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
			return "空";
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
	

	
	public String addChanpinXsStandard(String path){//添加产品系数标准，把模板中的数据添加到chanpin表和xf表中
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "表中内容为空";
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
		if(databaseChanpin.rs.next()){//如果添加时，数据库不为空
			databaseChanpin.rs.last();
			lastRowId=databaseChanpin.rs.getInt(1);
		}
		else{//如果添加时，数据库为空
			lastRowId=1000;//把id的初值设为1000，因为在后面的代码中首先把lastRowId加1，所以不能赋值1001
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
				databaseXs.ps.setString(2, this.check(row.getCell(1)));//分类
				databaseXs.ps.setString(3, this.check(row.getCell(4)));//产品
				databaseXs.ps.setString(4, this.check(row.getCell(9)));//系数1
				databaseXs.ps.setString(5, this.check(row.getCell(10)));//系数2
				databaseXs.ps.setString(6, this.check(row.getCell(11)));//系数3
				if(databaseChanpin.ps.executeUpdate()<1){
					return "数据添加过程中出现错误";//未出错的数据可能已经添加进去了
				}
				if(databaseXs.ps.executeUpdate()<1){
					return "数据添加过程中出现错误";
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			databaseChanpin.close();
		}
		return "数据添加成功";
	}
	

	public String addXfStandard(String path){//添加工时标准，向xf表添加数据
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "表中内容为空";
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
		if(database.rs.next()){//如果数据库不为空，取出数据库中最后一行数据中的fid,分类,产品
			database.rs.last();
			lastRowId=database.rs.getInt(1);
			lastRowFid=database.rs.getInt(2);
			lastRowFenlei=database.rs.getString(3);
			lastRowChanpin=database.rs.getString(4);
			id=String.valueOf(lastRowId);
			fid=String.valueOf(lastRowFid);
		}
		else{//如果数据库为空
			lastRowId=100100;
			lastRowFid=1000;
			lastRowFenlei="空";
			lastRowChanpin="空";
			id=String.valueOf(lastRowId);
			fid=String.valueOf(lastRowFid);
			
		}
			database.ps=database.con.prepareStatement(sql2);
			String[] temp=new String[columnNumber];
			String[] temp2=new String[columnNumber];	
		
			for(int i=1;i<rowNumber;i++){			
				row=sheet.getRow(i);			
				for(int j=0;j<columnNumber;j++){										
					if((row.getCell(j)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(j).getCellType())){//excel中的单元格没有格式，且没有数据时为null，有格式没有数据时为blank
						if(j==columnNumber-1)//最后一列,即工时列为空时，置0
							temp2[j]="0";
						else
						temp2[j]=temp[j];//为空但不是最后一列，置为temp中记录的值				
					}
					else{//单元格不为空时，存入temp中，temp2中存入当前取出的且处理过的数据
						temp[j]=row.getCell(j).toString().trim();
						temp2[j]=temp[j];
						System.out.print(temp2[j]+"     ");
					}				
				}
				
				if(temp2[0].equals(lastRowFenlei)&&temp2[1].equals(lastRowChanpin)){//如果从excel中取出的分类和产品名和数据库中最后一行的相同，则fid号不变，id号加1
					id=String.valueOf(Integer.parseInt(id)+1);	
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
				}
				else{//不同，则fid加1，id等于fid拼接“01”
					fid=String.valueOf(Integer.parseInt(fid)+1);
					id=fid+"01";		
					database.ps.setInt(1, Integer.parseInt(id));
					database.ps.setInt(2, Integer.parseInt(fid));
					lastRowFenlei=temp2[0];
					lastRowChanpin=temp2[1];
				}
				for(p=0,q=3;p<columnNumber-1;p++,q++){//把temp2[]中的数据存入数据库
					database.ps.setString(q, temp2[p]);				
		
				}	
				database.ps.setDouble(q,Double.parseDouble(temp2[p]));
				if(database.ps.executeUpdate()<1){
					return "数据添加过程中出现错误";
				}
				
		}
		}
		catch(Exception e){
			e.printStackTrace();
			return "数据添加过程中出现错误";
		}
		finally{
			database.close();
		}
		return "数据添加成功";
	}
	
	public String upadateChanpinXs(String path){//更新数据库中的chanpin表和系数表，其中分类和产品字段作为唯一性查找标示不能修改
		try{
			this.parseExcel(path);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		if(rowNumber<=1){
			return "表中内容为空";
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
				databaseChanpin.ps.setString(1, this.check(row.getCell(0)));//主类
				databaseChanpin.ps.setString(2, this.check(row.getCell(2)));//小类
				databaseChanpin.ps.setString(3, this.check(row.getCell(3)));//分项
				databaseChanpin.ps.setString(4, this.check(row.getCell(5)));//专业
				databaseChanpin.ps.setString(5, this.check(row.getCell(6)));//计量单位
				databaseChanpin.ps.setDouble(6, Double.parseDouble(this.check(row.getCell(7))));//总工时
				databaseChanpin.ps.setDouble(7, Double.parseDouble(this.check(row.getCell(8))));//基数
				databaseChanpin.ps.setString(8, this.check(row.getCell(1)));//分类
				databaseChanpin.ps.setString(9, this.check(row.getCell(4)));//产品
				
				databaseXs.ps.setString(1, this.check(row.getCell(9)));//系数一
				databaseXs.ps.setString(2, this.check(row.getCell(10)));//系数二
				databaseXs.ps.setString(3, this.check(row.getCell(11)));//系数三
				databaseXs.ps.setString(4, this.check(row.getCell(1)));//分类
				databaseXs.ps.setString(5, this.check(row.getCell(4)));//产品
				
				if(databaseChanpin.ps.executeUpdate()<1){//修改失败
					return "数据修改过程中出现错误";
				}
				if(databaseXs.ps.executeUpdate()<1){
					return "数据修改过程中出现错误";
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return "数据修改过程中出现错误";
		}
		finally{
			databaseXs.close();
		}
		return "数据修改成功";

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
			return "表中内容为空";
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
				fenlei=sheet.getRow(i).getCell(0).toString();//从excel表中读出分类和产品
				chanpin=sheet.getRow(i).getCell(1).toString();
				databaseId.ps.setString(1, fenlei);//分类
				databaseId.ps.setString(2, chanpin);//产品
				databaseId.rs=databaseId.ps.executeQuery();
				while(databaseId.rs.next()){
					fid=databaseId.rs.getInt("fid");//找到fid
				}
				id=Integer.parseInt(String.valueOf(fid)+"01");//找到id
			   databaseId.rs.last();
			    int count=databaseId.rs.getRow();//结果集的数量
			for(int j=i,num=1;j<rowNumber&&num<=count;j++,id++,i=j,num++){//j是行标
				row=sheet.getRow(j);
				if((row.getCell(2)!=null)&&(Cell.CELL_TYPE_BLANK!=row.getCell(2).getCellType())){//如果第i行的工作环节不空。当j=0时，一定不为空
					temp=row.getCell(2).toString();//							
			}
				database.ps.setString(1, temp);//工作环节
				database.ps.setString(2, row.getCell(3).toString());//环节细分
				if((row.getCell(4)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(4).getCellType())){
					database.ps.setDouble(3, 0);//工时细分
				}
				else{
					database.ps.setDouble(3, Double.parseDouble(row.getCell(4).toString()));
				}
				database.ps.setInt(4, fid);
				database.ps.setInt(5, id);
				
				if(database.ps.executeUpdate()<1){
					return "数据修改过程中出现错误";
				}
		    }
		}
	}
		catch(Exception e){
			e.printStackTrace();
			return "数据修改过程中出现错误";
		}
		finally{
			databaseId.close();
			database.close();
		}
		return "数据修改成功";
	}
	
	
	
	  public String updateGs1(String path){
			try{
				this.parseExcel(path);
			}
			catch(Exception e){
				e.printStackTrace();
			}	
			if(rowNumber<=1){
				return "表中内容为空";
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
					fenlei=row.getCell(0).toString();//从excel表中读取分类和产品
					chanpin=row.getCell(1).toString();
					if((row.getCell(2)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(2).getCellType())){//如果第i行的工作环节为空。当i=0时，一定不为空
						databaseId.ps.setString(1, temp[0]);//分类
						databaseId.ps.setString(2, temp[1]);//产品
						databaseId.rs=databaseId.ps.executeQuery();
						while(databaseId.rs.next()){
							fid=databaseId.rs.getDouble("fid");//找到fid
						}
						id=Double.parseDouble(String.valueOf(fid)+"01");//找到id
						databaseId.ps.setString(1, temp[2]);//工作环节
						databaseId.ps.setString(2, temp[0]);//分类
						databaseId.ps.setString(3, temp[1]);//产品
						databaseId.ps.executeUpdate();//先修改工作环节
						
						database.ps.setString(1, temp[3]);//环节细分
						database.ps.setString(3, temp[0]);//分类
						database.ps.setString(4, temp[1]);//产品
						database.ps.setString(5, temp[2]);//工作环节,用修改后的工作环节去查找
					
						if((row.getCell(4)==null)||(Cell.CELL_TYPE_BLANK==row.getCell(4).getCellType())){//工时若为空，置0
							database.ps.setDouble(2, 0);//工时细分
						}
						else{
							database.ps.setDouble(2, Double.parseDouble(row.getCell(4).toString()));//工时细分
							System.out.println("工时细分是"+row.getCell(4));
						}
						if(database.ps.executeUpdate()<1){//修改环节细分和工时细分
							return "数据修改过程中出现错误";
						}
					}
					else{//第i行的工作环节不为空
						for(int j=0;j<columnNumber;j++){
							temp[j]=row.getCell(j).toString();//数据不为空时，暂存到temp数组 
//							System.out.println(temp[j]);
							}
							databaseId.ps.setString(1, temp[2]);//工作环节
							databaseId.ps.setString(2, temp[0]);//分类
							databaseId.ps.setString(3, temp[1]);//产品
							databaseId.ps.executeUpdate();//先修改工作环节
							
							database.ps.setString(1, temp[3]);//环节细分
							System.out.println("工时细分是"+temp[4]);
							database.ps.setDouble(2, Double.parseDouble(temp[4]));//工时细分
							database.ps.setString(3, temp[0]);//分类
							database.ps.setString(4, temp[1]);//产品
							database.ps.setString(5, temp[2]);//工作环节,用修改后的工作环节去查找
							if(database.ps.executeUpdate()<1){//修改环节细分和工时细分
								return "数据修改过程中出现错误";
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
			return "数据修改成功";
			
		}
	
	public static void main(String[] args){
		 Save rl=new Save();
		 rl.addChanpinXsStandard("产品系数添加模板.xlsx");//测试成功
		 rl.addXfStandard("工时添加模板.xlsx");//测试成功
//		 rl.updateGsZgs("");
//		 rl.updateXS("系数更新模板.xlsx");
//		 rl.test2();


		
	}

}
