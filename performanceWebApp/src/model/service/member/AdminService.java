package model.service.member;

import java.util.List;

import domain.member.MemberVO;
import model.dao.member.AdminDAO;


public class AdminService {
	
	private static AdminService instance = new AdminService();
	
	private AdminService() {
		
	}
	
	public static AdminService getInstance() {
		return instance;
	}
	
	
	public boolean processAdminLogin(String aId, String aPw) throws Exception {
	
		return AdminDAO.getInstance().adminLogin(aId, aPw);
		
	}
	

	
	public List<MemberVO> retrieveMembers() throws Exception {
		return AdminDAO.getInstance().selectMember();
	}
	
	public List<MemberVO> retrieveMemberList(String sortkey, int startRow, int endRow) throws Exception {
		return AdminDAO.getInstance().selectMemberList(sortkey, startRow, endRow);
	}
	
	public MemberVO retrieveMemberDetail(String mNo) throws Exception {
		return AdminDAO.getInstance().selectMemberDetail(mNo);
	}
	
	public List<MemberVO> findMember(String sortkey, String keyfield, 
										String keyword, int startRow, int endRow) throws Exception {
		return AdminDAO.getInstance().searchByMember(sortkey, keyfield, keyword, startRow, endRow);
	}

}
