package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.AdminService;



public class AdminSelectMember implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//회원정보 조회 - DB에 접근해 모든 멤버들을 List<MemberVO> 에 담아 리턴된 값 가져와서 req에 바인딩.
		
		ActionForward forward = new ActionForward();
		try {
			List<MemberVO> memberList = AdminService.getInstance().retrieveMembers();
			
			req.setAttribute("memberList",memberList);
			forward.setPath("/admin_m_layout.jsp?article=admin_m_SelectList");
			forward.setRedirect(false);
		}catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			
		}
		
		return forward;
	}

	
	
}
