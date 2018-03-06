package com.ca.listener;

public abstract class MessageListener {

	private long userId = -1;

	
	public abstract void handleMessage();
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
}
