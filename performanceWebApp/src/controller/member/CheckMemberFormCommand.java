package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class CheckMemberFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		forward.setPath("/member_m_layout.jsp?nav=member_m_menu&article=member_m_checkMember");
		forward.setRedirect(false);
		return forward;

	}
}
