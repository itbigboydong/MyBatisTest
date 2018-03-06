package com.ca.server;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ca.domain.Mess;
import com.ca.mapper.IMessDAO;

public class MessServiceImpl implements MessService {

	private IMessDAO messDao = null;

	protected MessServiceImpl(SqlSession sqlSession) {

		messDao = sqlSession.getMapper(IMessDAO.class);

	}

	public void save(Mess mess) {
		messDao.save(mess);
	}

	public void UpdateStatus(String userId) {
		messDao.updateStatus(userId);

	}

	public List<Mess> findByUserId(String userId) {
		List<Mess> messes = messDao.findbyUserId(userId);
		return messes;
	}

}
