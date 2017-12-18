package model.service.member;

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
	
	
}
