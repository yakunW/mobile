/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.bean;
import java.io.Serializable;
import java.sql.Timestamp;

public class XfData implements Serializable{

	private int id; 
	
	private String fenlei;
	
	private String chanpin;
	
	private String xid;
	
	private String huanjie;
	
	private String xifen;
	
	private double biaozhun;
	
	private double xiuzheng;
	
	private float bili;
	
	

	public XfData() {
		super();
	}

	public XfData(String fenlei, String chanpin, String xid, String huanjie,
			String xifen, double biaozhun, double xiuzheng, float bili) {
		super();
		this.fenlei = fenlei;
		this.chanpin = chanpin;
		this.xid = xid;
		this.huanjie = huanjie;
		this.xifen = xifen;
		this.biaozhun = biaozhun;
		this.xiuzheng = xiuzheng;
		this.bili = bili;
	}

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getChanpin() {
		return chanpin;
	}

	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
	}

	public String getXid() {
		return xid;
	}

	public void setXid(String xid) {
		this.xid = xid;
	}

	public String getHuanjie() {
		return huanjie;
	}

	public void setHuanjie(String huanjie) {
		this.huanjie = huanjie;
	}

	public String getXifen() {
		return xifen;
	}

	public void setXifen(String xifen) {
		this.xifen = xifen;
	}

	public double getBiaozhun() {
		return biaozhun;
	}

	public void setBiaozhun(double biaozhun) {
		this.biaozhun = biaozhun;
	}

	public double getXiuzheng() {
		return xiuzheng;
	}

	public void setXiuzheng(double xiuzheng) {
		this.xiuzheng = xiuzheng;
	}

	public float getBili() {
		return bili;
	}

	public void setBili(float bili) {
		this.bili = bili;
	}

	
	
	
}
