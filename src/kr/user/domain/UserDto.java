package kr.user.domain;

import java.sql.Date;

public class UserDto {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private Date reg_date;
	private int auth;
	
	public boolean isCheckedPasswd(String userPasswd) {
		
		if(auth==1 && pwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", reg_date=" + reg_date + ", auth=" + auth + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
}
