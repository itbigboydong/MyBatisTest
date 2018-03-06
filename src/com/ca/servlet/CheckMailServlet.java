package com.ca.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ca.domain.User;
import com.ca.domain.UserStatus;
import com.ca.server.DaoFactory;
import com.ca.server.UserService;
import com.ca.server.UserStatusService;
import com.ca.util.MyBatisUtil;

public class CheckMailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5451817738758594115L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		// PrintWriter out = response.getWriter();
		BufferedWriter out = new BufferedWriter(response.getWriter());

		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlsessionfactory();

		SqlSession sqlSession = null;

		String resultString = null;

		try {

			sqlSession = sqlSessionFactory.openSession();

			resultString = handle(request, sqlSession);

			sqlSession.commit();

		} catch (Exception e) {

			e.printStackTrace();

			if (sqlSession != null) {
				sqlSession.rollback();
			}

		} finally {

			if (sqlSession != null) {
				sqlSession.close();
			}

		}

		// handle(request);
		out.write(resultString);
		out.flush();
		out.close();
	}

	private String handle(HttpServletRequest request, SqlSession sqlSession)
			throws Exception {

		String code = request.getParameter("code");
		String userNameString = request.getParameter("userName");
		String mainPath = new File(CheckMailServlet.class.getResource("/")
				.getFile()).getParentFile().getParentFile().getPath();
		
		if (code == null || code.trim().isEmpty()) {
			return fileToString(mainPath + File.separator + "fail.html");
		}
		if (userNameString == null || userNameString.trim().isEmpty()) {
			return fileToString(mainPath + File.separator + "fail.html");
		}

		DaoFactory daoFactory = DaoFactory.getDaoFactory();
		UserStatusService userStatusService = daoFactory
				.getUserStatusService(sqlSession);
		UserService userService = daoFactory.getUserService(sqlSession);
		User user = userService.findByName(userNameString);

		if (user == null) {
			return fileToString(mainPath + File.separator + "fail.html");
		}

		long userId = user.getId();

		UserStatus userStatus = userStatusService.findByUserId(userId);

		if (userStatus == null) {
			return fileToString(mainPath + File.separator + "fail.html");
		}

		if (userStatus.getStatusId() == 1) {
			return "您已经通过验证！";
		}

		if (!userStatus.getMailVerify().equals(code)) {
			return fileToString(mainPath + File.separator + "fail.html");
		}

		userStatusService.updateStatus(userId, 1);

		return fileToString(mainPath + File.separator + "success.html");

	}

	private String fileToString(String path) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "utf-8"));

		String str = null;
		StringBuffer result = new StringBuffer();
		while ((str = reader.readLine()) != null) {
			result.append(str);
		}
		reader.close();
		return result.toString();
	}

}
