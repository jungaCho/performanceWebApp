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

public class SignUpCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		
		ActionForward forward = new ActionForward();
		
		//ȸ������ ������, DB�� ȸ������ �־��ְ� �������� �̵��ϱ�.
	
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String name =req.getParameter("name");
		String email = req.getParameter("email");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		
		MemberVO member = new MemberVO(id,pwd,name,email,birthday,address);		
		
		try {
			MemberService service = MemberService.getInstance();
			service.createMember(member);
			
			forward.setPath("/member_m_newMember.jsp");
			forward.setRedirect(false);
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
