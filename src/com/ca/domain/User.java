package com.ca.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import net.sf.json.JSONObject;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6058015317857208775L;
	private Integer id;
	private String username;
	private String password;
	
	private String verify = null;
	private Timestamp verifyTime = null;
	
	
	
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public Timestamp getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Timestamp verifyTime) {
		this.verifyTime = verifyTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	@Override
	public String toString() {

		return JSONObject.fromObject(this).toString();

	}

}
