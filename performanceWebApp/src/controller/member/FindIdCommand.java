package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.MemberService;

public class FindIdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ActionForward forward = new ActionForward();

		String mName = req.getParameter("name");
		String email = req.getParameter("email");

		try {

			// ���̵�ã�� ���� �޼ҵ带 ������ ��� = ���� ���̵� ��ȯ.

			MemberService service = MemberService.getInstance();
			MemberVO member = service.findId(mName, email);

			req.setAttribute("member", member);

			if (member.getmId() != null) {

				forward.setPath("/isExistId.jsp");
				forward.setRedirect(false);
			} else {
				forward.setPath("/isNotExistId.jsp");
				forward.setRedirect(false);
			}
			
			return forward;

		} catch (Exception e) {

			// ��� ������ error.jsp���� ��´�
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}

	}

}
