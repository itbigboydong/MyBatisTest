package com.ca.domain;

public class UserBaseInfo {

	private long id = -1;
	private long userId = -1;
	private String tel = null;
	@Override
	public String toString() {
		return "UserBaseInfo [id=" + id + ", userId=" + userId + ", tel=" + tel
				+ "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public UserBaseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
