package com.ca.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sun.misc.BASE64Decoder;

import com.ca.util.RSAUtil;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

public class Test3 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//
		// Properties prop = new Properties();
		// prop.setProperty("mail.host", "smtp.126.com");
		// prop.setProperty("mail.transport.protocol", "smtp");
		// prop.setProperty("mail.smtp.auth", "true");
		// // 使用JavaMail发送邮件的5个步骤
		// // 1、创建session
		// Session session = Session.getInstance(prop);
		// // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		// session.setDebug(true);
		// // 2、通过session得到transport对象
		// Transport ts = session.getTransport();
		// //
		// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		// ts.connect("smtp.126.com", "zhanghongyuan1993@126.com", "Zhang123");
		// // 4、创建邮件
		// Message message = createSimpleMail(session);
		// // 5、发送邮件
		// ts.sendMessage(message, message.getAllRecipients());
		// ts.close();
		//
		//
		// new Test3().sendMail();
		AALOAD();
		//System.out.println(new Timestamp(System.currentTimeMillis()));
		// String rel =
		// "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

	}

	public static void AALOAD() throws Exception{
		
		String code = "ThtesqtQUdnFPDeDMEy/lVjFRPsoBW/e9MqhwhRrrb85oXYbXSjIbzYGTGBTYWU2TminwPMr9fh1yQv4iLVRfVD6FG5+h5XaR/1AHrU8I68YmtZN7R7Owe/rklcLX8HEKA/VxL3LhwSIjxhyXuHCjjrdR3AnrS8deD/eepIXt0o=";
		//code = new String(new BASE64Decoder().decodeBuffer(code));
		String aaString = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJK2PoUspUGOp7QVzXBSnwJjSyvX"
 +"6xlvDMQ4SbpmgaDwCzkdVv6nxAvqPfvsOxILZFyMmB8Nk310+uzGONxaqawhmntrGoPOSlkleeW5"
 +"JjniLMF12g0AjUJXk4RCtDxuqs9vye1dfQJ9lMGBdnK7rjMLe614/6zQFFnV8Nwg9OkdAgMBAAEC"
 +"gYAeaDU3BtTNbJKniWlF4WnhZdD7qkmowNJljMEDKCGvyHCbCtCwAPdp/MeSAhkQGVpYsC8LKJYs"
 +"B2XGUyMP0J6fomXMV3EECduSbIG5A+eJLtm+CsDOczbtktdJnbZSUEZ08HvRNzt+kz9AOHueEXK3"
+ "lVFTPsxMTlzrnFTMZslOhQJBAPrVLOcldVcscoEqAzZ8cYJqm7M5V2WF1PSykiOX/TbcFCIP7Mru"
+ "BwtXKXKUdlmThCCqngwxZD+rq4K6687XHj8CQQCVu/alVCZdpJOxv0yIRO4nujcDzcmtZdYa4qvN"
+ "lDoeN6/iJugIW7jFJvf52jq1lQASeP788CZTSJW5j8AK15mjAkEAuP6mWmgPDdcTMF4a9oF7ehNE"
+ "eD+PTKoS8NIO5D+BmodgNesG2ngJK/0B7aa/Trt0lF8hp5558FedbBKOPgP8IwJAG2mD6BSVyYL9"
+"vwbEnhXwe7ltpPahu2rvXZCt3z8JvMp5GrAAvrb8Bqvxrf4zzVZv4vHfzLKycYDSWFxmzhVWfQJB"
+ "ALdD8K2f9FpPAO+vxhjQEs4iXnqE/bB7d23rSuB8//cf3gAJL7FAm64mKv3dxkaEFjjqdWVv8XRC"
+ "J9hhW1wUBw4=";
		//RSAUtil rsaUtil = RSAUtil.getRSAUtil();
		//PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(aaString));
		//RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
		
		//code = new String(rsaUtil.decrypt(rsaPrivateKey, code.getBytes()));
		//System.out.println(code);
		
		System.out.println(new String(RSAUtil.getRSAUtil().decryptByPrivateKey(new BASE64Decoder().decodeBuffer(code), aaString)));;
	}

	public static MimeMessage createSimpleMail(Session session)
			throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("zhanghongyuan1993@126.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				"946903112@qq.com"));
		// 邮件的标题
		message.setSubject("只包含文本的简单邮件");
		// 邮件的文本内容
		message.setContent("你好啊！", "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	public void sendMail() throws MessagingException,
			UnsupportedEncodingException {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.126.com");
		props.put("mail.smtp.auth", "true");
		Session mailSession = Session.getInstance(props);

		// 设置session,和邮件服务器进行通讯。
		mailSession.setDebug(true);
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject("hah"); // 设置邮件主题
		message.setText("zhongguo中国"); // 设置邮件正文

		message.setSentDate(new Date()); // 设置邮件发送日期
		InternetAddress address = new InternetAddress(
				"zhanghongyuan1993@126.com", "zhy");
		message.setFrom(address); // 设置邮件发送者的地址
		InternetAddress toAddress = new InternetAddress("946903112@qq.com"); // 设置邮件接收方的地址
		message.addRecipient(Message.RecipientType.TO, toAddress);
		Transport transport = null;
		transport = mailSession.getTransport("smtp");

		message.saveChanges();
		transport.connect("smtp.126.com", "zhanghongyuan1993@126.com",
				"Zhang123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

		System.out.println("send success!");

	}

}
