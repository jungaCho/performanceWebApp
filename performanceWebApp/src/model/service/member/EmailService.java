package model.service.member;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {	
	private static EmailService instance = new EmailService();
	
	private EmailService() {
	}
	
	public static EmailService getInstance() {
		return instance;
	}

	public boolean sendEmail(String email, String authNumber)  {
		String host = "smtp.naver.com";
		final String user = "ztz33";
		final String password = "vusgo123";
		

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {		
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Subject
			message.setSubject("[���� ����] �����������α׷�");

			// Text
			message.setText("�ϴ��� ������ȣ�� �Է��Ͻø� ���� ������ �Ϸ�˴ϴ�.");
			message.setText("������ȣ : " + authNumber);		
				
			// send the message
			Transport.send(message);
					
			return true;

		} catch (MessagingException e) {			
			return false;
		}
		
	}
	
	
	
	//�ӽú�й�ȣ�� �̸��Ϸ� �߱��ϴ�.
	public boolean sendEmailPwd(String email, String tempPwd)  {
		String host = "smtp.naver.com";
		final String user = "ztz33";
		final String password = "vusgo123";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {		
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Subject
			message.setSubject("[�ӽ� ��й�ȣ �߱�] �����������α׷�");

			// Text
			message.setText("�ϴ��� �ӽú�й�ȣ�� �α������ֽð�, �� ��й�ȣ�� �����Ͻñ� �ٶ��ϴ�.<br>");
			message.setText("�ӽú�й�ȣ : " + tempPwd);		
				
			// send the message
			Transport.send(message);
					
			return true;

		} catch (MessagingException e) {			
			return false;
		}
		
	}
	
	
}
