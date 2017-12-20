package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.MemberService;

public class RetrieveMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String mNo = (String)req.getAttribute("usermNo");
		
		ActionForward forward = new ActionForward();
		try {
			MemberVO member = MemberService.getInstance().retrieveMember(mNo);			
			
			req.setAttribute("member", member);
			
			System.out.println("member : " + req.getAttribute("member"));
			
			forward.setPath("/member_m_layout.jsp?nav=member_m_menu&article=member_m_selectMemberForm");
			forward.setRedirect(false);
			return forward;
			
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}		
	}
	

}
