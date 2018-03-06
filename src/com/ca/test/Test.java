package com.ca.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// URL url = new
		// URL("http://218.28.16.110:7777/DisMapService/servlet/GetBaseInfo");
		URL url = new URL("http://192.168.0.110:8080/Keep/servlet/SendServlet?user=12&pass=12");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Connection", "keep-alive");
		con.connect();
		// con.getOutputStream().write("itemName=��ҵ������Ŀ".getBytes("utf-8"));
		final BufferedWriter wri = new BufferedWriter(new OutputStreamWriter(
				con.getOutputStream(), "UTF-8"));

		BufferedReader rea = new BufferedReader(new InputStreamReader(
				con.getInputStream(), "UTF-8"));
		String str = null;

		Thread thread = new Thread() {

			@Override
			public void run() {
				super.run();

				Scanner sc = new Scanner(System.in);
				System.out.println("�����������id��");
				String receive = sc.nextLine();
				try {
					wri.write(receive);
					wri.newLine();
					wri.flush();
					String mes = null;
					while (true) {
						System.out.println("��������Ϣ��");
						mes = sc.nextLine();

						wri.write(mes);
						wri.newLine();
						wri.flush();
						if (mes.equalsIgnoreCase("end")) {
							break;
						}

					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		thread.run();
		
		while ((str = rea.readLine()) != null) {

			System.err.println(str);

		}

	}

}
