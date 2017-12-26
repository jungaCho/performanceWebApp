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
		MemberService service = MemberService.getInstance();
		ActionForward forward = new ActionForward();
		
		String mId = req.getParameter("id");
		
		try {
		boolean isTrue = service.checkID(mId);
		if(isTrue) {
			forward.setPath("/login");
			forward.setRedirect(isRedirect);
		}
		return null;
		}catch(Exception e) {
			
		}
		return null;
	}
	
}
