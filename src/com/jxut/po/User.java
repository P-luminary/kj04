package com.jxut.po;
// µÃÂ¿‡
public class User {
	private int id;
	private String username;
	private String realname;
	private String password;
	private String sex;
	private String birthday;
	private String tel;
	private String address;
	private String type;
	private String if_valid;
	private String photo;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", realname=" + realname + ", password=" + password
				+ ", sex=" + sex + ", birthday=" + birthday + ", tel=" + tel + ", address=" + address + ", type=" + type
				+ ", if_valid=" + if_valid + ", photo=" + photo + "]";
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIf_valid() {
		return if_valid;
	}
	public void setIf_valid(String if_valid) {
		this.if_valid = if_valid;
	}
}
