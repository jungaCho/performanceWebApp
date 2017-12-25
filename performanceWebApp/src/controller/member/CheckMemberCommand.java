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

public class CheckMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		
		String pwd = req.getParameter("pwd");
	
		ActionForward forward = new ActionForward();
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			
			member = MemberService.getInstance().retrieveMember(member.getmNo());
			
			if(!pwd.equals(member.getmPw())) {
				forward.setPath("/checkMemberForm.do");
				forward.setRedirect(true);
				return forward;
			} else {
				forward.setPath("/myPageForm.do");
				forward.setRedirect(true);
				return forward;
			}
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}
	
}
