package com.szh.struts2.action;
/*
 * ���ö�̬������ʽ3��ͨ���
 */
public class UserAction {
	private String username;
	private String password;
	private String rpassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRpassword() {
		return rpassword;
	}
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}
	//��¼
	public  String Login() {
		if (username.equals("admin")&&password.equals("admin")) {
			return "LoginSuccess";
		}else {
			return "LoginFailure";
		}
	}
	
	//ע��
	public String Register() {
		if (password.equals(rpassword)) {
			return "RegisterSuccess";
		}else {
			return "RegisterFailure";
		}
	}

}
