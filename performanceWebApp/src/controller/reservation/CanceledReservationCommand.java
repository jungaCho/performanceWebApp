package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import model.service.reservation.ReservationService;

public class CanceledReservationCommand implements Command{

	//예매 취소를 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {

		String rNo = req.getParameter("rNo");
		String pwd = req.getParameter("pwd");
		
		ActionForward forward = new ActionForward();
		try {
			ReservationService reservationService = ReservationService.getInstance();
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			//예매를 취소한다.
			// 취소 상태의 취소여부를 확인한다.
			if(member.getmPw().equals(pwd)) {
				reservationService.modifyReservation(rNo);
				forward.setPath("/member_r_layout3.jsp?nav=member_r_menu&article=member_r_reservationInfo");
				forward.setRedirect(true);
				return forward;
			}else {
				forward.setPath("/member_r_layout3.jsp?nav=member_r_menu&article=member_r_reservationInfo");
				forward.setRedirect(false);
				return forward;
			}
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
	}
	
}
