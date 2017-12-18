package model.service.member;

import java.util.List;

import domain.member.MemberVO;
import model.dao.member.MemberDAO;

public class MemberService {
	private static MemberService instance = new MemberService();
	
	private  MemberService() {
		
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
	
	public void removeMember(MemberVO member) throws Exception {
		MemberDAO.getInstance().deleteMember(member);	
	}
	
	public boolean processLogin(String mId, String mPw) throws Exception {
		return MemberDAO.getInstance().loginMember(mId, mPw);
	}
	
	public boolean checkID(String mId) throws Exception {
		return MemberDAO.getInstance().checkOverLapId(mId);
	}
	
	public boolean checkEmail(String email) throws Exception {
		return MemberDAO.getInstance().checkOverLapEmail(email);
	}
	
	public boolean findId(String mName, String email) throws Exception {
		return MemberDAO.getInstance().searchID(mName, email);
	}
	
	public boolean findPwd(String mId, String mName, String email) throws Exception {
		MemberVO member = new MemberVO();
		if (MemberDAO.getInstance().searchPwd(mId, mName, email) == member.getEmail()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<MemberVO> retrieveMemberList(int startRow, int endRow) throws Exception {
		return MemberDAO.getInstance().selectMemberList(startRow, endRow);
	}
	
	public MemberVO retrieveMemberDetail(String mNo) throws Exception {
		return MemberDAO.getInstance().selectMemberDetail(mNo);
	}
	
	public List<MemberVO> findMember(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		return MemberDAO.getInstance().searchByMember(keyfield, keyword, startRow, endRow);
	}
}
