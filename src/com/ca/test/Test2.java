package com.ca.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test2 {

	public static void main(String[] args) throws Exception {

		while (true) {
			URL url = new URL(
					"http://192.168.0.121:8080/Keep/servlet/Syn?user=5&pass=8");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "keep-alive");

			con.connect();

			BufferedReader rea = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String str = null;

			System.out.println("start");
			while ((str = rea.readLine()) != null) {
				System.err.println(str);

			}
			rea.close();
		}

	}

}
