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

public class LoginFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// ��û�Ķ������ id�� pw�� ������ ���ǿ� ���ε���Ű�� �ٸ� �������� �Ѿ�Ը����.

		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");

		ActionForward forward = new ActionForward();

		// ȸ�����θ� Ȯ���Ѵ�.

		try {

			MemberService service = MemberService.getInstance();

			Boolean isSuccess = service.processLogin(id, pwd);

			if (isSuccess) { // ȸ���� ���� ��쿡
				// ���ǿ����� "userID"��� �Ӽ��̸����� ���̵� ���ε��Ѵ�.
				HttpSession session = req.getSession();
				MemberVO member = new MemberVO();
				session.setAttribute("usermNo", member.getmNo()); // ���ǿ����� id�����͸� ���ε��Ѵ�.

				// �α��� ���� ����ȭ������ �̵��Ѵ�.
				forward.setPath("/member_index.jsp");
				forward.setRedirect(false);
				return forward;					
				}
					
				// �α��� ����ȭ������ ��� �̵��Ѵ�.
				forward.setPath("/member_m_loginForm.jsp");
				forward.setRedirect(true);
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
