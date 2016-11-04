package com.wyk.bean;

import java.io.Serializable;

public class StandardXf implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fenlei;
	private String chanpin;
	private String hj;//工作环节
	private String xf;//工作环节细分
	private String gs;
	
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
	public String getHj() {
		return hj;
	}
	public void setHj(String hj) {
		this.hj = hj;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getGs() {
		return gs;
	}
	public void setGs(String gs) {
		this.gs = gs;
	}

}
