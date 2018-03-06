package com.ca.mapper;

import com.ca.domain.UserStatus;

public interface IUserStatusDAO {

	public void save(UserStatus userStatus);
	
	public UserStatus findByUserId(long userId);
	
	public void updateStatus(long userId, int newStatusId);
	
}
