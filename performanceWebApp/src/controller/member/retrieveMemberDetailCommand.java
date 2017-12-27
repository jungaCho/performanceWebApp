package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.member.AdminService;

public class retrieveMemberDetailCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 회원정보 상세조회

		// 회원번호에 해당하는 회원 상세정보를 출력해준다.-> 팝업으로.

		String mNo = req.getParameter("mNo");
		ActionForward forward = new ActionForward();

		try {
			
			AdminService service = AdminService.getInstance();
			MemberVO member2 = service.retrieveMemberDetail(mNo);
			int rNo = member2.getRankNo();
			String rName= member2.getrName();
			member2.setRName(rNo, rName);
			
			HttpSession session = req.getSession();

			session.setAttribute("member2", member2);

			if (member2.getmNo() != null) {
				forward.setPath("/admin_m_memberdetail.jsp");
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
