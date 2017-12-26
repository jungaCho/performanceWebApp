package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class AuthEmailProcessCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();

		try {

			String email = (String) req.getAttribute("email");
			String authNumber = (String) req.getAttribute("authNumber");

			forward.setPath("/sendSuccess.jsp?email=" + email + "&authNumber=" + authNumber);
			forward.setRedirect(false);
			return forward;

		} catch (Exception e) {
			// 모든 에러는 error.jsp에서 잡는다
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}
