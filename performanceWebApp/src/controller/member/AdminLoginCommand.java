package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import model.service.member.AdminService;

public class AdminLoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 입력받은 관리자 사번, 비밀번호가 DB에 있는 관리자 정보와 일치 할 시 관리자 메인 페이지로 이동한다.

		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");

		ActionForward forward = new ActionForward();

		try {

			boolean isExist = AdminService.getInstance().processAdminLogin(id, pwd);

			if (isExist) {
				forward.setPath("/admin_index.jsp");
				forward.setRedirect(false);
				
				HttpSession session = req.getSession();
				session.setAttribute("aId", id);
	
				
			} else {

				forward.setPath("/isNotExistId.jsp");
				forward.setRedirect(false);
				
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
