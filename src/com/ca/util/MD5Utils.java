package com.ca.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {


	public static String update(byte[] code) throws NoSuchAlgorithmException {
		
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};  
		
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 使用指定的字节更新摘要
		mdInst.update(code);
		// 获得密文
		byte[] md = mdInst.digest();

		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}

		return new String(str);

	}

}
