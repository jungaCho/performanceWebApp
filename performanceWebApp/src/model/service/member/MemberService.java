package model.service.member;

import java.sql.Connection;

import conn.DBConn;
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

	public MemberVO findId(String mName, String email) throws Exception {
		return MemberDAO.getInstance().searchID(mName, email);
	}

	// 비밀번호 발급 전에 회원인지 아닌지 체크해줌.
	/*public boolean findPwd(String mId, String mName, String email) throws Exception {
		return MemberDAO.getInstance().searchPwd(mId, mName, email);
	}
	*/
	public boolean sendPwdService(String tempPwd, String mId, String mName, String email) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);
			
			boolean isTrue = MemberDAO.getInstance().searchPwd(conn, mId, mName, email);
			
			MemberDAO.getInstance().sendPwd(conn, tempPwd, mId, mName, email);
				
			conn.commit();
			
			return isTrue;
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(conn!=null)
				conn.close();
		}
	}
}
