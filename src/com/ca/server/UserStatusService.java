package com.ca.server;

import com.ca.domain.UserStatus;

public interface UserStatusService {

	public void save(UserStatus userStatus);
	
	public UserStatus findByUserId(long userId);
	
	public void updateStatus(long userId,int newStatusId);
	
	
}
