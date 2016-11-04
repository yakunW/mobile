/*
 * author:xuxiaoyin  
*/
package com.xxy.struts2.bean;

import java.io.Serializable;

public class TChanpin implements Serializable{
	
	private String jiliang;
	
	private String zgs;

	public String getJiliang() {
		return jiliang;
	}

	public void setJiliang(String jiliang) {
		this.jiliang = jiliang;
	}

	public String getZgs() {
		return zgs;
	}

	public void setZgs(String zgs) {
		this.zgs = zgs;
	}

	public TChanpin(String jiliang, String zgs) {
		super();
		this.jiliang = jiliang;
		this.zgs = zgs;
	}

	public TChanpin() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jiliang == null) ? 0 : jiliang.hashCode());
		result = prime * result + ((zgs == null) ? 0 : zgs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TChanpin other = (TChanpin) obj;
		if (jiliang == null) {
			if (other.jiliang != null)
				return false;
		} else if (!jiliang.equals(other.jiliang))
			return false;
		if (zgs == null) {
			if (other.zgs != null)
				return false;
		} else if (!zgs.equals(other.zgs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TChanpin [jiliang=" + jiliang + ", zgs=" + zgs + "]";
	}
	
	
	

}
