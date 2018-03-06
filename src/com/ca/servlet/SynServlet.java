package com.ca.servlet;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ca.listener.MessageHandler;
import com.ca.listener.MessageListener;

public class SynServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private int status = -1;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		status = -1;
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("Connection", "keep-alive");

		String user = request.getParameter("userId");

		BufferedWriter write = new BufferedWriter(response.getWriter());

		if (user == null || user.trim().isEmpty()) {
			return;
		}

		final Thread thread = new Thread() {

			@Override
			public void run() {
				super.run();

				try {
					Thread.sleep(40000);
				} catch (Exception e) {

				}

			}

		};

		thread.start();

		MessageListener lis = new MessageListener() {

			@Override
			public void handleMessage() {

				status = 1;
				thread.interrupt();

			}
		};

		lis.setUserId(Long.parseLong(user));

		MessageHandler.add(lis);

		try {
			thread.join(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (status == -1) {
			write.write("{\"code\":0}");

		} else {
			write.write("{\"code\":1}");
		}

		write.newLine();
		write.flush();
		write.close();

		MessageHandler.remove(lis);

	}

}
