package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.MemberService;
import model.service.reservation.ReservationService;

public class RetrieveMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String mNo = member.getmNo(); 
				
		ActionForward forward = new ActionForward();
		try {
			MemberVO vo = MemberService.getInstance().retrieveMember(member.getmNo());			
			int reservedCount = ReservationService.getInstance().selectTotalList(mNo);
			
			req.setAttribute("member", vo);
			req.setAttribute("reservedCount", reservedCount);
			
			System.out.println("session : " + member.getmNo());
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
