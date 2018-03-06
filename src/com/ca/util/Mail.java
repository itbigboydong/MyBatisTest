package com.ca.util;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private Properties properties = new Properties();

	private static class Singleton {
		private static Mail mail = new Mail();
	}

	private Mail() {
	}

	public static Mail getMail() {
		return Singleton.mail;
	}

	public void sendMail(String toUser, String title, String strContent)
			throws MessagingException, IOException {
		properties.load(Mail.class.getResourceAsStream("/com/ca/config/mail.properties"));
		Properties props = new Properties();
		props.put("mail.smtp.host", properties.get("mailserver"));
		props.put("mail.smtp.auth", "true");
		Session mailSession = Session.getInstance(props);

		// 设置session,和邮件服务器进行通讯。
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(title); // 设置邮件主题
		message.setText(strContent); // 设置邮件正文
		message.setSentDate(new Date()); // 设置邮件发送日期
		InternetAddress address = new InternetAddress(
				(String) properties.get("username"),"HNCA");
		message.setFrom(address); // 设置邮件发送者的地址
		InternetAddress toAddress = new InternetAddress(toUser); // 设置邮件接收方的地址
		message.addRecipient(Message.RecipientType.TO, toAddress);
		Transport transport = null;
		transport = mailSession.getTransport("smtp");

		message.saveChanges();
		transport.connect((String) properties.get("mailserver"),
				(String) properties.get("username"),
				(String) properties.get("password"));
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

	}

	
	public static void main(String[] args) throws MessagingException, IOException {
		Mail.getMail().sendMail("946903112@qq.com", "ddd", "dfdfdf");
	}
	
}
