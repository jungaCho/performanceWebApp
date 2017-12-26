package model.service.member;

import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.member.MemberVO;
import model.dao.member.MemberDAO;

public class MemberService {
	private static MemberService instance = new MemberService();

	private MemberService() {

	}

	public static MemberService getInstance() {
		return instance;
	}

	public void createMember(MemberVO member) throws Exception {
		MemberDAO.getInstance().insertMember(member);
	}

	public MemberVO retrieveMember(String mNo) throws Exception {
		return MemberDAO.getInstance().selectMember(mNo);
	}

	public void modifyMember(MemberVO member) throws Exception {
		MemberDAO.getInstance().updateMember(member);
	}

	public void removeMember(String mNo, String wdReason) throws Exception {
		MemberDAO.getInstance().deleteMember(mNo, wdReason);
	}

	/*
	 * public String processLogin(String mId, String mPw) throws Exception { return
	 * MemberDAO.getInstance().loginMember(mId, mPw); }
	 */
	public MemberVO processLogin(String mId, String mPw) throws Exception {
		return MemberDAO.getInstance().loginMember(mId, mPw);
	}

	public boolean checkID(String mId) throws Exception {
		return MemberDAO.getInstance().checkOverLapId(mId);
	}

	public boolean checkEmail(String email) throws Exception {
		return MemberDAO.getInstance().checkOverLapEmail(email);
	}
	

	public void sendEmail(String email) throws Exception {
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
			message.setText("하단 url을 누르시면 이메일 인증이 완료됩니다.");
			message.setText("'http://localhost:9000/performanceWebApp/sendSuccess.do'");
				
			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	//임시비밀번호를 발

	public MemberVO findId(String mName, String email) throws Exception {
		return MemberDAO.getInstance().searchID(mName, email);
	}

	// 비밀번호 발급 전에 회원인지 아닌지 체크해줌.
	public boolean findPwd(String mId, String mName, String email) throws Exception {
		return MemberDAO.getInstance().searchPwd(mId, mName, email);
	}

}
