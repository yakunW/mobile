package com.wyk.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.wyk.bean.Standard;
import com.wyk.dao.Database;

public class DeleteStandardAction {
	private String[] selectdelete;
	
	public String deleteStandard(){
	
		if(null==selectdelete||selectdelete.length==0){
//			System.out.println("未选中");
			return "success";
		}
		else{
			List<Standard> standardList=new ArrayList<Standard>();
			ShowStandardAction getStandard=new ShowStandardAction();
			
			String sqlChanpin="delete from chanpin where id=?";
			String sqlXs="delete from xs where fid=?";
			String sqlGs="delete from xf where fid=?";
			Database databaseChanpin=new Database();
			Database databaseGs=new Database();
			Database databaseXs=new Database();
			
			try{
				databaseChanpin.con=databaseChanpin.connect();
				databaseGs.con=databaseGs.connect();
				databaseXs.con=databaseXs.connect();
				databaseChanpin.ps=databaseChanpin.con.prepareStatement(sqlChanpin);
				databaseGs.ps=databaseGs.con.prepareStatement(sqlGs);
				databaseXs.ps=databaseXs.con.prepareStatement(sqlXs);
				for(int i=0;i<selectdelete.length;i++){
					databaseChanpin.ps.setInt(1, Integer.parseInt(selectdelete[i]));
					databaseGs.ps.setInt(1, Integer.parseInt(selectdelete[i]));
					databaseXs.ps.setInt(1, Integer.parseInt(selectdelete[i]));
					databaseChanpin.ps.addBatch();
					databaseGs.ps.addBatch();
					databaseXs.ps.addBatch();
				}
				databaseChanpin.ps.executeBatch();
				databaseGs.ps.executeBatch();
				databaseXs.ps.executeBatch();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			standardList=getStandard.getStandard();//删除操作后，重新从数据库读取内容
			HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
			HttpSession session=(HttpSession)req.getSession();
			session.setAttribute("standardList", standardList);//将重新取出的内容更新至session
			session.setAttribute("standardListBackup", standardList);//更新session中对standardList的备份
		}
		
		
		return "success";
	}

	
	
	
	
	
	public String[] getSelectdelete() {
		return selectdelete;
	}

	public void setSelectdelete(String[] selectdelete) {
		this.selectdelete = selectdelete;
	}

}
