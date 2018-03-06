package com.ca.server;

import org.apache.ibatis.session.SqlSession;

import com.ca.domain.User;
import com.ca.mapper.IUserDAO;

public class UserServiceImpl implements UserService {

	private IUserDAO userDao = null;

	protected UserServiceImpl(SqlSession sqlSession) {

		userDao = sqlSession.getMapper(IUserDAO.class);
	}

	public User findByName(String username) {
		return userDao.findByName(username);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public User findVerify(String username) {
		
		return userDao.findVerify(username);
	}

}
