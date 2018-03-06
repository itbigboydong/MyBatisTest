package com.ca.server;


import org.apache.ibatis.session.SqlSession;

import com.ca.domain.UserStatus;
import com.ca.mapper.IUserStatusDAO;

public class UserStatusServiceImpl implements UserStatusService {

	private IUserStatusDAO iUserStatusDAO = null;
	
	protected UserStatusServiceImpl(SqlSession sqlSession){
		
		iUserStatusDAO = sqlSession.getMapper(IUserStatusDAO.class);
		
	}
	public void save(UserStatus userStatus) {
		
		iUserStatusDAO.save(userStatus);
	}

	public UserStatus findByUserId(long userId) {
		
		return iUserStatusDAO.findByUserId(userId);
	}
	public void updateStatus(long userId, int newStatusId) {
		iUserStatusDAO.updateStatus(userId, newStatusId);
		
	}
	
	
}
