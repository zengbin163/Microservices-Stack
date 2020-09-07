package com.chihuo.webflux.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.mail.util.MailSSLSocketFactory;

public class Mail {

	public void sendGroupMail(String sendMail, JSONArray sendArray) throws Exception {
		for (int i = 0; i < sendArray.size(); i++) {
			JSONObject json = sendArray.getJSONObject(i);
			String toMail = json.getString("email");
			String title = json.getString("title");
			String content = json.getString("content");
			this.sendMail(sendMail, toMail, title, content);
		}
	}

	public void sendMail(String sendMail, String toEmail, String title, String content) throws Exception {
		// 创建一个配置文件并保存
		Properties properties = new Properties();

		properties.setProperty("mail.host", "smtp.qq.com");

		properties.setProperty("mail.transport.protocol", "smtp");

		properties.setProperty("mail.smtp.auth", "true");
		// 设置SSL加密
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 创建一个session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("532799609@qq.com", "pajimdrdngjtbhcd");
			}
		});
		// 开启debug模式
		session.setDebug(true);
		// 获取连接对象
		Transport transport = session.getTransport();
		// 连接服务器
		transport.connect("smtp.qq.com", "532799609@qq.com", "pajimdrdngjtbhcd");
		// 创建邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		// 邮件发送人
		mimeMessage.setFrom(new InternetAddress(sendMail));
		// 邮件接收人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		// 邮件标题
		mimeMessage.setSubject(title);
		// 邮件内容
		mimeMessage.setContent(content, "text/html;charset=UTF-8");
		// 发送邮件
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		// 关闭连接
		transport.close();
	}

}
