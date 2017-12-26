package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class SendEmailSuccessCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		
		boolean isSuccess = true;
		
		if(isSuccess) {
			
			req.setAttribute("isSuccess", isSuccess);
		}
			
		forward.setPath("/signUpForm.do");
		forward.setRedirect(false);
		return forward;
	}

}
