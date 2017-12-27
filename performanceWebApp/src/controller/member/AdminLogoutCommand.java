package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;

public class AdminLogoutCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		HttpSession session = req.getSession();
		String aId = (String) session.getAttribute("aId");

		ActionForward forward = new ActionForward();

		try {

			if (aId != null) {
		
				session.invalidate();

				forward.setPath("/admin_m_login.jsp");
				forward.setRedirect(false);
			}
			
			return forward;

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}
