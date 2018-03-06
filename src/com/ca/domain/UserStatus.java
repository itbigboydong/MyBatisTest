package com.ca.domain;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

public class UserStatus {

	private long id = -1;
	private long userId = -1;
	private int statusId = -1;
	private String publicKey = null;
	private String privateKey = null;
	private Timestamp createTime = null;
	private Timestamp authStartTime = null;
	private Timestamp authEndTime = null;

	private String mailVerify = null;
	private Timestamp mailVerifyTime = null;
	
	
	
	public String getMailVerify() {
		return mailVerify;
	}

	public void setMailVerify(String mailVerify) {
		this.mailVerify = mailVerify;
	}

	public Timestamp getMailVerifyTime() {
		return mailVerifyTime;
	}

	public void setMailVerifyTime(Timestamp mailVerifyTime) {
		this.mailVerifyTime = mailVerifyTime;
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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getAuthStartTime() {
		return authStartTime;
	}

	public void setAuthStartTime(Timestamp authStartTime) {
		this.authStartTime = authStartTime;
	}

	public Timestamp getAuthEndTime() {
		return authEndTime;
	}

	public void setAuthEndTime(Timestamp authEndTime) {
		this.authEndTime = authEndTime;
	}

	public UserStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}



}
