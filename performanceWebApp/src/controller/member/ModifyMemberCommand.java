package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.MemberService;

public class ModifyMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String pwd = req.getParameter("id");
		String name = req.getParameter("id");
		String email = req.getParameter("id");
		String address = req.getParameter("id");
		
		
		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			MemberVO member = new MemberVO();
			
			service.modifyMember(member);
			
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		return null;
	}

	
}
