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
import domain.pub.PagingVO;
import domain.reservation.TotalInfoVO;
import model.service.reservation.ReservationService;

public class TotalInfoRetrieveListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("call");
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (NumberFormatException ne) {
			currentPage = 1;
		}
		
		PagingVO paging = new PagingVO();
		
		paging.setPostPerpage(15);
		paging.setPageBlock(5);
		paging.setCurrentPage(currentPage);
		
		ActionForward forward = new ActionForward();
		try {
			paging.setTotalPost(ReservationService.getInstance().selectTotalList());			
			
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String mNo = member.getmNo();
		
			List<TotalInfoVO> totalInfos = ReservationService.getInstance().
												retrieveReservationByMember(startRow, endRow, mNo);
			
			req.setAttribute("totalInfos",totalInfos);
			req.setAttribute("paging", paging);
			forward.setPath("/member_r_layout3.jsp?nav=member_r_menu&article=member_r_reservationInfo");
			forward.setRedirect(false);
			return forward;
		} catch (Exception e) {		
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}
