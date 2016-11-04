package com.szh.struts2.bean;

import java.io.Serializable;

public class Xishu implements Serializable{
     private String chanpin;
     private String fenlei;
     private String xs1;
     private String xs2;
     private String xs3;
     
     private double xs1value;
     private double xs2value;
     private double xs3value;
	public String getChanpin() {
		return chanpin;
	}
	public void setChanpin(String chanpin) {
		this.chanpin = chanpin;
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
	public double getXs1value() {
		return xs1value;
	}
	public void setXs1value(double xs1value) {
		this.xs1value = xs1value;
	}
	public double getXs3value() {
		return xs3value;
	}
	public void setXs3value(double xs3value) {
		this.xs3value = xs3value;
	}
	public double getXs2value() {
		return xs2value;
	}
	public void setXs2value(double xs2value) {
		this.xs2value = xs2value;
	}
	public String getFenlei() {
		return fenlei;
	}
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
     
     
}
