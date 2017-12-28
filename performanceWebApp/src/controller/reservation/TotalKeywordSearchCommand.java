package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import domain.reservation.TotalInfoVO;
import model.service.reservation.ReservationService;

public class TotalKeywordSearchCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		int startRow = 1;
		int endRow = 10;
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		
		ActionForward forward = new ActionForward();
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String mNo = member.getmNo();

			List<TotalInfoVO> totalInfos = ReservationService.getInstance().
												retrieveReservationByMember(startRow, endRow, keyfield, keyword, mNo);
			
			req.setAttribute("totalInfos", totalInfos);
			forward.setPath("/");
			forward.setRedirect(false);
			return forward;
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}
	
	
}
