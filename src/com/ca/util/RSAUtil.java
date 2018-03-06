package com.ca.util;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;


public class RSAUtil {
	
	private static class In{
		private static RSAUtil rsaUtil = new RSAUtil();
	}
	
	private RSAUtil(){
		
	}

	public static RSAUtil getRSAUtil(){
		return In.rsaUtil;
	}
	
	public byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (publicKey != null) {
			// Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// 根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	public byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		if (privateKey != null) {
			// Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance("RSA");
			// 根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	
    public  byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptedData, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
	
	public KeyPair getKeyPair() throws NoSuchAlgorithmException {

		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为1024位
		keyPairGen.initialize(1024);
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();

		return keyPair;
	}
	
//	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		
//		RSAUtil rsaUtil = new RSAUtil();
//		
//		KeyPair keyPair = rsaUtil.getKeyPair();
//		
//		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();               
//        //得到公钥  
//        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();  
//		
//        String msg = "94690312@qq.com";
//        
//        byte[] srcBytes = msg.getBytes();  
//        byte[] resultBytes = rsaUtil.encrypt(publicKey, srcBytes);  
//          
//        //用私钥解密  
//        byte[] decBytes = rsaUtil.decrypt(privateKey, resultBytes);  
//          
//        System.out.println("明文是:" + msg);  
//        System.out.println("加密后是:" + new String(resultBytes));  
//        System.out.println("解密后是:" + new String(decBytes)); 
//        System.out.println(new BASE64Encoder().encodeBuffer(privateKey.getEncoded()));
//        System.out.println(new BASE64Encoder().encodeBuffer(publicKey.getEncoded()));
//        
//        
//	}
	

}
