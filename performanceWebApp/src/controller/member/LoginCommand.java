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

public class LoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");

		ActionForward forward = new ActionForward();
		try {
			MemberService service = MemberService.getInstance();
			String mNo = service.processLogin(id, pwd);

			if (mNo != "") {
				// ���ǿ����� "userID"��� �Ӽ��̸����� ���̵� ���ε��Ѵ�.
				HttpSession session = req.getSession();
				session.setAttribute("usermNo", mNo);
				
				System.out.println("usermNo : " + session.getAttribute("usermNo"));
				// �α��� ���� ����ȭ������ �̵��Ѵ�.
				forward.setPath("/member_index.jsp");
				forward.setRedirect(true);
				return forward;
			} else {
				// �α��� ����ȭ������ ��� �̵��Ѵ�.
				forward.setPath("/loginForm.do");
				forward.setRedirect(true);
				return forward;
			}
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}

	}
}
