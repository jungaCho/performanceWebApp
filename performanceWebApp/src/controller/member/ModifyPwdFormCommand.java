package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class ModifyPwdFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member_m_modifyPwd.jsp");
		forward.setRedirect(true);
		return forward;
	
	}	
}
