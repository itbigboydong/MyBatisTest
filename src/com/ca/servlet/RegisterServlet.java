package com.ca.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sun.misc.BASE64Encoder;

import com.ca.domain.User;
import com.ca.domain.UserBaseInfo;
import com.ca.domain.UserStatus;
import com.ca.server.DaoFactory;
import com.ca.server.UserBaseInfoService;
import com.ca.server.UserService;
import com.ca.server.UserStatusService;
import com.ca.util.ErrorToJson;
import com.ca.util.MD5Utils;
import com.ca.util.Mail;
import com.ca.util.MyBatisUtil;
import com.ca.util.RSAUtil;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 3574544780718741967L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlsessionfactory();

		SqlSession sqlSession = sqlSessionFactory.openSession();
		String result = null;
		try {

			result = handle(request, sqlSession);
			sqlSession.commit();

		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();

		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}

		out.write(result);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private String handle(HttpServletRequest request, SqlSession sqlSession)
			throws IOException, NoSuchAlgorithmException, InvalidKeyException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, MessagingException {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");

		String tel = request.getParameter("tel");

		ErrorToJson errorToJson = ErrorToJson
				.getErrorToJson("/com/ca/config/error.properties");

		DaoFactory daoFactory = DaoFactory.getDaoFactory();

		UserService userService = daoFactory.getUserService(sqlSession);

		User user = userService.findByName(userName);
		// 邮箱正则表达式
		String rel = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

		// 密码正则表达式
		String rel2 = "^[a-zA-Z]\\w{5,17}$";

		if (userName == null || userName.trim().isEmpty()
				|| !userName.matches(rel)) {

			return errorToJson.errorJsonMessage("0102");
		}

		if (passWord == null || passWord.trim().isEmpty()
				|| !passWord.matches(rel2)) {

			return errorToJson.errorJsonMessage("0103");
		}
		//  ^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
		if (user == null) {

			user = new User();
			user.setUsername(userName);
			user.setPassword(passWord);

			userService.save(user);

			UserBaseInfo userBaseInfo = new UserBaseInfo();

			userBaseInfo.setUserId(user.getId());
			userBaseInfo.setTel(tel);

			UserBaseInfoService userBaseInfoService = daoFactory
					.getUserBaseInfoService(sqlSession);
			userBaseInfoService.save(userBaseInfo);

			UserStatusService userStatusService = daoFactory
					.getUserStatusService(sqlSession);
			UserStatus userStatus = new UserStatus();
			userStatus.setUserId(user.getId());
			userStatus.setStatusId(2);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Timestamp currentTime = Timestamp.valueOf(sdf.format(new Date()));

			userStatus.setAuthStartTime(null);
			userStatus.setMailVerifyTime(currentTime);

			userStatus.setAuthEndTime(null);

			RSAUtil rsaUtil = RSAUtil.getRSAUtil();
			KeyPair keyPair = rsaUtil.getKeyPair();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

			userStatus.setPrivateKey(new BASE64Encoder()
					.encodeBuffer(rsaPrivateKey.getEncoded()));
			userStatus.setPublicKey(new BASE64Encoder()
					.encodeBuffer(rsaPublicKey.getEncoded()));

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userName", userName);
			jsonObject.put("randomNum",
					String.valueOf(new Random(1000).nextInt()));

			String resultString = jsonObject.toString();
			String message = MD5Utils.update(resultString.getBytes());
			userStatus.setMailVerify(message);
			userStatusService.save(userStatus);

			Mail mail = Mail.getMail();

			
			message = "您已经完成注册，请点击此链接完成邮箱验证！http://192.168.0.168/UAServer1/servlet/CheckMail?code="
					+ message + "&userName=" + userName;
			mail.sendMail(userName, "HNCA用户注册邮箱验证", message);

			return errorToJson.errorJsonMessage("0001");
		} else {
			return errorToJson.errorJsonMessage("0104");
		}

	}

}
