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
	 * �����ʼ��ķ���
	 * @param to �ռ���
	 * @param code  ��������(������)
	 */
	public static void sendMail(String to,String code) {
		
		// 1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.qq.com");// ָ���ʼ�������
		props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue
		Session session = Session.getInstance(props, new Authenticator() {

			//�������˺ź͵�¼�������������Ȩ��
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("910744380", "eertfbxlyrhlbbba");
			}
			
		});
		//2.�����ʼ�����
		Message message=new MimeMessage(session);
		try {
			// ���÷�����:
			message.setFrom(new InternetAddress("910744380@qq.com"));
			// �����ռ���:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���� CC   ����BCC
			// ���ñ���
			message.setSubject("����XX�ٷ������ʼ�");
			// �����ʼ�����:
			message.setContent("<h1>�ٷ������ʼ�!������������ɼ������!</h1><h3><a href='http://localhost:8080/Project_eshop/user_active.action?code="+code+"'>����</a></h3>", "text/html;charset=UTF-8");
			// 3.�����ʼ�:
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
		sendMail("2283613941@qq.com", "�ʼ����Ͳ���");
	}
}
