package com.ca.domain;


import java.sql.Timestamp;

import net.sf.json.JSONObject;



public class Mess {
	
	private Integer id;
	private String from_user;
	private String to_user;
	private String message;
	private String status_id;
	private Timestamp create_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrom_user() {
		return from_user;
	}

	public void setFrom_user(String fromUser) {
		from_user = fromUser;
	}

	public String getTo_user() {
		return to_user;
	}

	public void setTo_user(String toUser) {
		to_user = toUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String statusId) {
		status_id = statusId;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}

	@Override
	public String toString() {

		return JSONObject.fromObject(this).toString();

	}

}
