package com.wsj.eshop.util;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	
	/**
	 * 发送邮件的方法
	 * @param to 收件人
	 * @param code  发送内容(激活码)
	 */
	public static void sendMail(String to,String code) {
		
		// 1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.qq.com");// 指定邮件服务器
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		Session session = Session.getInstance(props, new Authenticator() {

			//发件人账号和登录邮箱服务器的授权码
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("910744380", "eertfbxlyrhlbbba");
			}
			
		});
		//2.创建邮件对象
		Message message=new MimeMessage(session);
		try {
			// 设置发件人:
			message.setFrom(new InternetAddress("910744380@qq.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 抄送 CC   密送BCC
			// 设置标题
			message.setSubject("来自XX官方激活邮件");
			// 设置邮件正文:
			message.setContent("<h1>官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://localhost:8080/Project_eshop/user_active.action?code="+code+"'>激活</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		sendMail("2283613941@qq.com", "邮件发送测试");
	}
}
