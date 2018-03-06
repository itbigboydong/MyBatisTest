package com.ca.server;

import com.ca.domain.UserBaseInfo;

public interface UserBaseInfoService {

	public void save(UserBaseInfo userBaseInfo);
	public UserBaseInfo findByUserId(long userId);
	
}
