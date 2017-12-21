package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.member.MemberService;

public class FindPwd implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String mId = req.getParameter("id");
		String mName = req.getParameter("name");
		String email = req.getParameter("email");
		
		ActionForward forward = new ActionForward();
		
		try {
		
			MemberService service = MemberService.getInstance();
			boolean isExist = service.findPwd(mId, mName, email);
			
			req.setAttribute("name", mName);
			
			//ȸ���� ��� -> ���񽺿� ���ǵǾ��ִ� javamail API�� ȣ�����ش�. 
			if(isExist) {
				
				forward.setPath("member_m_sendSuccess.jsp");
				forward.setRedirect(false);
				return forward;
			}
			
			
		} catch (Exception e) {

			// ��� ������ error.jsp���� ��´�
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		
		return null;
	}
	
	

}
