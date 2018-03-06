package com.ca.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ca.domain.User;
import com.ca.server.DaoFactory;
import com.ca.server.UserService;
import com.ca.util.ErrorToJson;
import com.ca.util.MyBatisUtil;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1208843736023401996L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");

		PrintWriter out = resp.getWriter();
		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlsessionfactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		ErrorToJson errorToJson = ErrorToJson
				.getErrorToJson("/com/ca/config/error.properties");
		
		String result = null;
		try {

			result = handle(req, sqlSession);
			sqlSession.commit();

		} catch (Exception e) {

			sqlSession.rollback();

		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

		out.write(errorToJson.errorJsonMessage(result));
		out.flush();
		out.close();

	}

	private String handle(HttpServletRequest request, SqlSession sqlSession) {

		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		UserService service = DaoFactory.getDaoFactory().getUserService(
				sqlSession);
		User user = service.findByName(username);

		if (user.getUsername().equals(username)
				&& user.getPassword().equals(pwd)) {
			return "0001";
		} else {

			return "0002";

		}

	}

}
