package com.ca.mapper;

import com.ca.domain.UserBaseInfo;

public interface IUserBaseInfoDAO {

	public void save(UserBaseInfo userBaseInfo);
	
	public UserBaseInfo findByUserId(long userId);
	
	
}
