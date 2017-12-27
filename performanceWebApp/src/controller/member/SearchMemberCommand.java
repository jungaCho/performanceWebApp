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

public class SearchMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// �˻����ǰ� �˻���, ���������� ��µǾ���ϴ� strow endrow�� �Ű������� �޾ƿ´�.
		// �ش� ������� ��� json���� �̵��Ѵ�.

		String sortkey = req.getParameter("sortkey");
		String keyword = req.getParameter("keyword");
		int startRow = Integer.parseInt(req.getParameter("startRow"));
		int endRow = Integer.parseInt(req.getParameter("endRow"));

		ActionForward forward = new ActionForward();

		try {

			AdminService service = AdminService.getInstance();

			List<MemberVO> sortedMembers = service.findMember(sortkey, keyword, startRow, endRow);
			req.setAttribute("sortedMembers", sortedMembers);

			for (MemberVO memberss : sortedMembers) {
				System.out.println(memberss.toString());
			}

			if (sortedMembers != null) {
				forward.setPath("/sortedMember.jsp");
				forward.setRedirect(false);
			} else {
				forward.setPath("/admin_m_SelectList.jsp");
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
