package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.service.member.MemberService;

public class loginFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//요청파라미터의 id와 pw를 가져와 세션에 바인딩시키고 다른 페이지로 넘어가게만든다.
		
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		ActionForward forward = new ActionForward();
		
		//회원여부를 확인한다.
		
		try {
		
			MemberService service = MemberService.getInstance();
			Boolean isSucess = service.processLogin(id,pwd);
			
		 if(isSucess){ //회원이 맞을 경우에

			//세션영역에 "userID"라는 속성이름으로 아이디를 바인딩한다.

			HttpSession session = req.getSession();

			session.setAttribute("userId", id); // 세션영역에 id데이터를 바인딩한다.

			//로그인 성공 메인화면으로 이동한다.
			
			forward.setPath("/member_index.jsp");
			forward.setRedirect(false);
			return forward;
			
		 } else {
			 
			//로그인 원래화면으로 계속 이동한다. 
				
				forward.setPath("/member_m_loginForm.jsp");
				forward.setRedirect(true);
				return forward;
			 
		 }


			} catch (Exception e) {
				
				// 모든 에러는 error.jsp에서 잡는다
				req.setAttribute("exception", e);
				forward.setPath("/error.jsp");
				forward.setRedirect(false);
				return forward;
			}
		


	}
}
