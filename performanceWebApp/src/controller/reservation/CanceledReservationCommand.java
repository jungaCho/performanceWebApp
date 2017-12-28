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
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			boolean isTrue = ReservationService.getInstance().checkCanceldReservations(rNo);
			int checkCount = 0;
			System.out.println("isTrue = " + isTrue);
			if(pwd.equals(member.getmPw()) && isTrue==true) {
				checkCount = 0;
				System.out.println("checkCount = " + checkCount);
				req.setAttribute("checkCount", checkCount);
				forward.setPath("/zzCancelReservation.jsp");
				forward.setRedirect(false);
			} else if(member.getmPw().equals(pwd) && isTrue==false) {
					checkCount = 1;
					System.out.println("checkCount = " + checkCount);
					req.setAttribute("checkCount", checkCount);
					forward.setPath("/zzCancelReservation.jsp");
					forward.setRedirect(false);
			} else {
				checkCount = 2;
				System.out.println("checkCount = " + checkCount);
				req.setAttribute("checkCount", checkCount);
				forward.setPath("/zzCancelReservation.jsp");
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
