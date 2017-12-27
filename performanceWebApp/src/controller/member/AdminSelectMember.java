package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.AdminService;



public class AdminSelectMember implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//ȸ������ ��ȸ - DB�� ������ ��� ������� List<MemberVO> �� ��� ���ϵ� �� �����ͼ� req�� ���ε�.
		
		ActionForward forward = new ActionForward();
		try {
			List<MemberVO> memberList = AdminService.getInstance().retrieveMembers();
			
			req.setAttribute("memberList",memberList);
			forward.setPath("/admin_m_layout.jsp?article=admin_m_SelectList");
			forward.setRedirect(false);
		}catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			
		}
		
		return forward;
	}

	
	
}
