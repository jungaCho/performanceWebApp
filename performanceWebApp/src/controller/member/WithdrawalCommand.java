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

public class WithdrawalCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		String mNo = member.getmNo();
		String wdReason = req.getParameter("wdReason");
				
		ActionForward forward = new ActionForward();
		try {
			MemberService.getInstance().removeMember(mNo, wdReason);
			
			session.invalidate();
			
			forward.setPath("/member_index.jsp");
			forward.setRedirect(true);
			return forward;
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}		
	}
	
}
