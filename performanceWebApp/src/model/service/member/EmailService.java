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
			message.setSubject("[가입 승인] 공연예매프로그램");

			// Text
			message.setText("하단의 인증번호를 입력하시면 가입 승인이 완료됩니다.");
			message.setText("인증번호 : " + authNumber);		
				
			// send the message
			Transport.send(message);
					
			return true;

		} catch (MessagingException e) {			
			return false;
		}
		
	}
	
	
	
	//임시비밀번호를 이메일로 발급하다.
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
			message.setSubject("[임시 비밀번호 발급] 공연예매프로그램");

			// Text
			message.setText("하단의 임시비밀번호로 로그인해주시고, 새 비밀번호를 설정하시기 바랍니다.<br>");
			message.setText("임시비밀번호 : " + tempPwd);		
				
			// send the message
			Transport.send(message);
					
			return true;

		} catch (MessagingException e) {			
			return false;
		}
		
	}
	
	
}
