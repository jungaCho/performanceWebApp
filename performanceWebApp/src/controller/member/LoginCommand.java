package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

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
			MemberVO member = service.processLogin(id, pwd);
			
			System.out.println("ȸ����ȣ : " + member.getmNo());
			System.out.println("Ż�𿩺� : " + member.getWithdrawal());
			
			int loginSuccess = 0;
			if (member.getmNo() != null ) {
				if(member.getWithdrawal().equals("F")) {
				// ���ǿ����� "userID"��� �Ӽ��̸����� ���̵� ���ε��Ѵ�.
					HttpSession session = req.getSession();
					session.setAttribute("member", member);
	
					System.out.println("member : " + session.getAttribute("member"));
					
					req.setAttribute("loginSuccess", loginSuccess);
					// �α��� ���� ����ȭ������ �̵��Ѵ�.
					//forward.setPath("/member_index.jsp");
					forward.setPath("/loginSuccess.jsp");
					forward.setRedirect(false); 
				} else {
					loginSuccess=1;
					req.setAttribute("loginSuccess", loginSuccess);
					//forward.setPath("/loginForm.do");
					forward.setPath("/loginSuccess.jsp");
					forward.setRedirect(false);
				}
				return forward;
			} else {
				loginSuccess=2;
				req.setAttribute("loginSuccess", loginSuccess);
				//forward.setPath("/loginForm.do");
				forward.setPath("/loginSuccess.jsp");
				forward.setRedirect(false);
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
