package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.AdminService;

public class SelectMemberListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// ������Ʈ���� sortkey�޾ƿ���

		String sortkey = req.getParameter("sortkey");

		ActionForward forward = new ActionForward();

		try {

			// sortkey���ش��ϴ� Ŀ�ǵ�� ���� ȸ�������� request�� ���ε������ְ�
			// json�������� �̵��Ѵ�.

			AdminService service = AdminService.getInstance();
			List<MemberVO> sortedMembers = service.retrieveMemberList(sortkey, 1, 15);
			
			HttpSession session = req.getSession();		
			session.setAttribute("sortedMembers", sortedMembers);

			if (sortedMembers != null) {
				forward.setPath("/sendSuccess.jsp?success=true");
				forward.setRedirect(false);
			} else {
				forward.setPath("/sendSuccess.jsp?success=false");
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
