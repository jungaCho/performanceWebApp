package controller.reservation;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import domain.reservation.TotalInfoVO;
import model.service.reservation.ReservationService;

public class CanceledReservationCommand implements Command{

	//예매 취소를 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {

		String pwd = req.getParameter("pwd");
		
		ActionForward forward = new ActionForward();
		try {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String rNo = (String)session.getAttribute("rNo");
			
			System.out.println("rNo : " + rNo);

			int checkCount = 0;
			
			if(pwd.equals(member.getmPw())) {
				ReservationService.getInstance().modifyReservation(rNo); 
				checkCount = 0;
				req.setAttribute("checkCount", checkCount);
			} else {
				checkCount = 1;
				req.setAttribute("checkCount", checkCount);
			}
			System.out.println("checkCount = " + checkCount);
			forward.setPath("/zzCancelReservation.jsp");
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
