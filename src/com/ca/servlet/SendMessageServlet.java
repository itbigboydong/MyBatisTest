package com.ca.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ca.domain.Mess;
import com.ca.listener.MessageHandler;
import com.ca.server.DaoFactory;
import com.ca.server.MessService;
import com.ca.util.ErrorToJson;
import com.ca.util.MyBatisUtil;

public class SendMessageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634862319050458462L;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");

		ErrorToJson errorToJson = ErrorToJson
				.getErrorToJson("/com/ca/config/error.properties");

		PrintWriter write = res.getWriter();

		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlsessionfactory();
		SqlSession sqlSession = null;
		String result = null;
		try {

			sqlSession = sqlSessionFactory.openSession();

			result = handle(req, sqlSession);

			sqlSession.commit();
			
		} catch (Exception e) {
			
			sqlSession.rollback();
		} finally {
			
			if (sqlSession != null) {
				sqlSession.close();
			}
			
		}

		write.write(errorToJson.errorJsonMessage(result));
		write.flush();
		write.close();
	}

	private String handle(HttpServletRequest request, SqlSession sqlSession)
			throws UnsupportedEncodingException {

		String fromUser = request.getParameter("userId");

		if (fromUser == null || fromUser.trim().isEmpty()) {

			return "0002";
		}

		final String toUser = request.getParameter("receiveId");

		if (toUser == null || toUser.trim().isEmpty()) {

			return "0003";
		}

		String message = request.getParameter("message");

		if (message == null || message.trim().isEmpty()) {

			return "0004";
		}
		if (request.getHeader("User-Agent").contains("iPhone")) {
			message = new String(message.getBytes("iso8859-1"), "utf-8");
		}

		Mess mess = new Mess();
		mess.setFrom_user(fromUser);
		mess.setTo_user(toUser);
		mess.setMessage(message);
		mess.setStatus_id("1");
		mess.setCreate_time(null);
		MessService messService = DaoFactory.getDaoFactory().getMessService(
				sqlSession);
		messService.save(mess);

		Thread thread = new Thread() {

			@Override
			public void run() {
				super.run();
				MessageHandler.handle(Long.parseLong(toUser));
			}
		};

		thread.start();

		return "0001";
	}

}
