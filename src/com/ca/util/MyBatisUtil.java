package com.ca.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	private static final SqlSessionFactory sqlSessionFactory = buileSqlSessionFactory();

	private static SqlSessionFactory buileSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

	public static SqlSessionFactory getSqlsessionfactory() {
		return sqlSessionFactory;
	}
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
