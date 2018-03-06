package com.ca.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ca.domain.Mess;
import com.ca.server.DaoFactory;
import com.ca.server.MessService;
import com.ca.util.ErrorToJson;
import com.ca.util.GetRespBean;
import com.ca.util.MyBatisUtil;

public class GetServlet extends HttpServlet {

	private static final long serialVersionUID = -8801018405267511535L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlsessionfactory();

		SqlSession sqlSession = sqlSessionFactory.openSession();
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

		pw.write(result);
		pw.close();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}

	private String handle(HttpServletRequest request, SqlSession sqlSession)
			throws IOException {

		ErrorToJson errorToJson = ErrorToJson
				.getErrorToJson("/com/ca/config/error.properties");

		String userId = request.getParameter("userId");

		if (userId == null || userId.trim().isEmpty()) {

			return errorToJson.errorJsonMessage("0002");
		}

		DaoFactory daoFactory = DaoFactory.getDaoFactory();

		MessService messService = daoFactory.getMessService(sqlSession);
		List<Mess> messes = messService.findByUserId(userId);

		List<GetRespBean> lists = new ArrayList<GetRespBean>();
		if (messes.size() > 0) {
			messService.UpdateStatus(userId);

			for (Mess mess : messes) {

				String fromUserId = mess.getFrom_user();
				String message = mess.getMessage();
				Timestamp t = mess.getCreate_time();

				GetRespBean gr = new GetRespBean();
				gr.setFromUserId(fromUserId);
				SimpleDateFormat sp = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String td = sp.format(t);
				gr.setMessage(message);
				gr.setTime(td);
				lists.add(gr);

			}

		}
		String jsonArray = JSONArray.fromObject(lists).toString();

		return jsonArray;
	}

}
