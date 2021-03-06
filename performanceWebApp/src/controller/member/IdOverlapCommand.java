package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.MemberService;

public class IdOverlapCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String mId = req.getParameter("id");

		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			boolean isTrue = service.checkID(mId);

			if (isTrue) {
				req.setAttribute("isExist", true);
				forward.setPath("/overlapID.jsp");
				forward.setRedirect(false);
			} else {
				req.setAttribute("isExist", false);
				forward.setPath("/overlapID.jsp");
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
