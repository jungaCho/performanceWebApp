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
			
			System.out.println("회원번호 : " + member.getmNo());
			System.out.println("탈퇴여부 : " + member.getWithdrawal());
			
			if (member.getmNo() != null ) {
				if(member.getWithdrawal().equals("F")) {
				// 세션영역에 "userID"라는 속성이름으로 아이디를 바인딩한다.
					HttpSession session = req.getSession();
					session.setAttribute("member", member);
	
					System.out.println("member : " + session.getAttribute("member"));
		
					// 로그인 성공 메인화면으로 이동한다.
					forward.setPath("/member_index.jsp");
					//forward.setPath("/member_m_newMember");
					forward.setRedirect(false); 
				} else {
					forward.setPath("/loginForm.do");
					forward.setRedirect(true);
				}
				return forward;
				
			} else {
				// 로그인 원래화면으로 계속 이동한다.
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
