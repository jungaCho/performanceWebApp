package controller.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.service.member.EmailService;

public class sendTempPwdCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String email = req.getParameter("email");

		Random random = new Random();

		String randomN[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9".split(",");

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			buffer.append(randomN[random.nextInt(randomN.length)]);
		}

		String tempPwd = buffer.toString();
		System.out.println("tempPwd : " + tempPwd);

		HttpSession session = req.getSession();
		session.setAttribute("tempPwd", tempPwd);

		ActionForward forward = new ActionForward();
		try {

			EmailService service = EmailService.getInstance();
			boolean flag = service.sendEmailPwd(email,tempPwd);

			System.out.println("flag : " + flag);
			
			if (flag) {
				forward.setPath("/sendSuccess.jsp?success=" + flag);
				forward.setRedirect(true);
			} else {
				forward.setPath("/sendSuccess.jsp?success=" + flag);
				forward.setRedirect(true);
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
