package com.szh.struts2.bean;
import java.io.Serializable;
import java.sql.Timestamp;

public class Data implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; 
	private String xid;
	private String name;
	private String province;
	private String danwei;
	private String yewu;
	private String jmanager;
	private String kmanager;
	private String fzr;
	private String zzy;
	private String sjjd;
	private String zhulei;
	private String fenlei;
	private String xiaolei;
	private String fenxiang;
	private String chanpin;
	private String zhuanye;
	private String jiliang;
	
	private int xfid;
	private String hj;
	private String xf;
	private int gsxf;
	private int xzgsxf;
	private float blxsxf;
	
	private double jdxs;
	private double jishu;
	private double xs1;
	private double xs2;
	private double xs3;
	private double gs;
	private double xzgs;
	private double bjgs;
	private double wygs;
	private double hzgs;
	private Timestamp date;
	private int done;
	
	private int flag=0;//flag为0表示该数据对象中没有错误，在校验过程中若出现错误，值变为1。
	 
	
	
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
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
	
	
	public Data()
	{
		super();
	}
	
	
	public Data(String xid, String name, String province, String danwei,
			String yewu, String jmanager, String kmanager, String fzr,
			String zzy, String zhulei, String fenlei, String xiaolei,
			String fenxiang, String chanpin, String zhuanye, String jiliang,double xs1,
			double xs2 ,double xs3,double gs,double xzgs,double bjgs) {
		super();
		this.xid = xid;
		this.name = name;
		this.province = province;
		this.danwei = danwei;
		this.yewu = yewu;
		this.jmanager = jmanager;
		this.kmanager = kmanager;
		this.fzr = fzr;
		this.zzy = zzy;
		this.zhulei = zhulei;
		this.fenlei = fenlei;
		this.xiaolei = xiaolei;
		this.fenxiang = fenxiang;
		this.chanpin = chanpin;
		this.zhuanye = zhuanye;
		this.jiliang = jiliang;
		this.xs1=xs1;
		this.xs2=xs2;
		this.xs3=xs3;
		this.gs=gs;
		this.xzgs=xzgs;
		this.bjgs=bjgs;
	}


	public String getXid() {
		return xid;
	}


	public void setXid(String xid) {
		this.xid = xid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getDanwei() {
		return danwei;
	}


	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}


	public String getYewu() {
		return yewu;
	}


	public void setYewu(String yewu) {
		this.yewu = yewu;
	}


	public String getJmanager() {
		return jmanager;
	}


	public void setJmanager(String jmanager) {
		this.jmanager = jmanager;
	}


	public String getKmanager() {
		return kmanager;
	}


	public void setKmanager(String kmanager) {
		this.kmanager = kmanager;
	}


	public String getFzr() {
		return fzr;
	}


	public void setFzr(String fzr) {
		this.fzr = fzr;
	}


	public String getZzy() {
		return zzy;
	}


	public void setZzy(String zzy) {
		this.zzy = zzy;
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


	public double getXs1() {
		return xs1;
	}


	public void setXs1(double xs1) {
		this.xs1 = xs1;
	}


	public double getXs2() {
		return xs2;
	}


	public void setXs2(double xs2) {
		this.xs2 = xs2;
	}


	public double getXs3() {
		return xs3;
	}


	public void setXs3(double xs3) {
		this.xs3 = xs3;
	}


	public double getGs() {
		return gs;
	}


	public void setGs(double gs) {
		this.gs = gs;
	}


	public double getXzgs() {
		return xzgs;
	}


	public void setXzgs(double xzgs) {
		this.xzgs = xzgs;
	}


	public double getBjgs() {
		return bjgs;
	}


	public void setBjgs(double bjgs) {
		this.bjgs = bjgs;
	}


	public Timestamp getDate() {
		return date;
	}


	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public double getJishu() {
		return jishu;
	}


	public void setJishu(double jishu) {
		this.jishu = jishu;
	}


	public String getSjjd() {
		return sjjd;
	}


	public void setSjjd(String sjjd) {
		this.sjjd = sjjd;
	}


	public double getJdxs() {
		return jdxs;
	}


	public void setJdxs(double jdxs) {
		this.jdxs = jdxs;
	}


	public double getWygs() {
		return wygs;
	}


	public void setWygs(double wygs) {
		this.wygs = wygs;
	}


	public double getHzgs() {
		return hzgs;
	}


	public void setHzgs(double hzgs) {
		this.hzgs = hzgs;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getDone() {
		return done;
	}


	public void setDone(int done) {
		this.done = done;
	}


	public int getGsxf() {
		return gsxf;
	}


	public void setGsxf(int gsxf) {
		this.gsxf = gsxf;
	}


	public int getXzgsxf() {
		return xzgsxf;
	}


	public void setXzgsxf(int xzgsxf) {
		this.xzgsxf = xzgsxf;
	}


	public float getBlxsxf() {
		return blxsxf;
	}


	public void setBlxsxf(float blxsxf) {
		this.blxsxf = blxsxf;
	}


	public int getXfid() {
		return xfid;
	}


	public void setXfid(int xfid) {
		this.xfid = xfid;
	}

    
}
