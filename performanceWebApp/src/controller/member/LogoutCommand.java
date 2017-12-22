package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;

public class LogoutCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		HttpSession session = req.getSession();
		session.getAttribute("member");
		
		ActionForward forward = new ActionForward();
		
		session.invalidate();
		
		forward.setPath("/member_index.jsp");
		forward.setRedirect(false);
		return forward;
		
	
	}	
}
