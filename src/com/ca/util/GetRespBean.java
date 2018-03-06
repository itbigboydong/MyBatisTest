package com.ca.util;

import java.io.Serializable;



@SuppressWarnings("serial")
public class GetRespBean implements Serializable {
	 private String fromUserId;
	 private String message;
	 private String time;
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	 
	 

}
