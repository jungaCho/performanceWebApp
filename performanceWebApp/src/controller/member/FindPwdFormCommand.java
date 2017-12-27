package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class FindPwdFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		HttpSession session = req.getSession();
		session.getAttribute("findIdSession");
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member_m_findPwd.jsp");
		forward.setRedirect(false);
		return forward;

	}
}
