package com.ca.server;

import org.apache.ibatis.session.SqlSession;

/**
 * 
 * 持久层工厂类，用来建造持久层操作对象
 *
 * @author zhy
 * @date 2016-5-4
 */
public class DaoFactory {

	// 私有化构造方法，不允许外界创建
	private DaoFactory() {
	}

	private static class Instance {
		private static DaoFactory daoFactory = new DaoFactory();
	}
	
	// 获取唯一的工厂
	public static DaoFactory getDaoFactory() {
		return Instance.daoFactory;
	}

	
	/**
	 * 获取MessService的实现对象
	 * @param sqlSession
	 * @return
	 */
	public MessService getMessService(SqlSession sqlSession){
		return new MessServiceImpl(sqlSession);
	}
	
	/**
	 * 获取UserService的实现对象
	 * @param sqlSession
	 * @return
	 */
	public UserService getUserService(SqlSession sqlSession){
		return new UserServiceImpl(sqlSession);
	}
	
	/**
	 * 创建UserStatusService的对象
	 * 
	 * @param sqlSession
	 * @return
	 */
	public UserStatusService getUserStatusService(SqlSession sqlSession){
		return new UserStatusServiceImpl(sqlSession);
	}
	
	/**
	 * 创建UserBaseInfoService实例
	 * @param sqlSession
	 * @return
	 */
	public UserBaseInfoService getUserBaseInfoService(SqlSession sqlSession){
		return new UserBaseInfoServiceImpl(sqlSession);
	}
	
}
