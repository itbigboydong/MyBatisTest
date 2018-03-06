package com.ca.servlet;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserBaseInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4976611727001197106L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		BufferedWriter write = new BufferedWriter(response.getWriter());

		write.write(handle(request));
		write.newLine();
		write.flush();
		write.close();

	}

	private String handle(HttpServletRequest request) {
		
		
		
		
		return null;
	}

}
