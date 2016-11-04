package com.szh.struts2.action;
/*
 * 调用动态方法方式3，通配符
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
	//登录
	public  String Login() {
		if (username.equals("admin")&&password.equals("admin")) {
			return "LoginSuccess";
		}else {
			return "LoginFailure";
		}
	}
	
	//注册
	public String Register() {
		if (password.equals(rpassword)) {
			return "RegisterSuccess";
		}else {
			return "RegisterFailure";
		}
	}

}
