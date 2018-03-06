package com.ca.server;

import org.apache.ibatis.session.SqlSession;

import com.ca.domain.UserBaseInfo;
import com.ca.mapper.IUserBaseInfoDAO;

public class UserBaseInfoServiceImpl implements UserBaseInfoService {

	private IUserBaseInfoDAO iUserBaseInfoDAO = null;

	protected UserBaseInfoServiceImpl(SqlSession sqlSession) {
		iUserBaseInfoDAO = sqlSession.getMapper(IUserBaseInfoDAO.class);
	}

	public void save(UserBaseInfo userBaseInfo) {
		iUserBaseInfoDAO.save(userBaseInfo);

	}

	public UserBaseInfo findByUserId(long userId) {
		return iUserBaseInfoDAO.findByUserId(userId);
	}

}
