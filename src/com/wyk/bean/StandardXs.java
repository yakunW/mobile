package com.wyk.bean;

import java.io.Serializable;

public class StandardXs implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String fid;
	private String xs1;
	private String xs2;
	private String xs3;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getXs1() {
		return xs1;
	}
	public void setXs1(String xs1) {
		this.xs1 = xs1;
	}
	public String getXs2() {
		return xs2;
	}
	public void setXs2(String xs2) {
		this.xs2 = xs2;
	}
	public String getXs3() {
		return xs3;
	}
	public void setXs3(String xs3) {
		this.xs3 = xs3;
	}

}
