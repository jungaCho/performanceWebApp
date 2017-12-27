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

public class FindIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String mName = req.getParameter("name");
		String email = req.getParameter("email");

		ActionForward forward = new ActionForward();
		try {
			// 아이디찾기 관련 메소드를 실행한 결과 = 관련 아이디 반환.
			MemberService service = MemberService.getInstance();
			MemberVO findIdSession = service.findId(mName, email);
			
			HttpSession session = req.getSession();
			session.setAttribute("findIdSession", findIdSession);

			boolean idCheck = true;
			if (findIdSession.getmId() != null) {
				req.setAttribute("idCheck", idCheck);
				forward.setPath("/isNotExistId.jsp");
				forward.setRedirect(false);
			} else {
				idCheck = false;
				req.setAttribute("idCheck", idCheck);
				forward.setPath("/isNotExistId.jsp");
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
