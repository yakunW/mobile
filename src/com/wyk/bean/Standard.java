package com.wyk.bean;

import java.io.Serializable;

public class Standard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String zhulei;
	private String fenlei;
	private String xiaolei;
	private String fenxiang;
	private String chanpin;
	private String zhuanye;
	private String jiliang;
	private double zgs;
	private double guimo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZhulei() {
		return zhulei;
	}
	public void setZhulei(String zhulei) {
		this.zhulei = zhulei;
	}
	public String getFenlei() {
		return fenlei;
	}
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
	public String getXiaolei() {
		return xiaolei;
	}
	public void setXiaolei(String xiaolei) {
		this.xiaolei = xiaolei;
	}
	public String getFenxiang() {
		return fenxiang;
	}
	public void setFenxiang(String fenxiang) {
		this.fenxiang = fenxiang;
	}
	public String getChanpin() {
		return chanpin;
	}
	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getJiliang() {
		return jiliang;
	}
	public void setJiliang(String jiliang) {
		this.jiliang = jiliang;
	}
	public double getZgs() {
		return zgs;
	}
	public void setZgs(double zgs) {
		this.zgs = zgs;
	}
	public double getGuimo() {
		return guimo;
	}
	public void setGuimo(double guimo) {
		this.guimo = guimo;
	}
}
