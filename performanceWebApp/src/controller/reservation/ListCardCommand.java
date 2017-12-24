package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import domain.reservation.ReservationVO;

public class ListCardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		String cardNumber = req.getParameter("cardNumber");

		
		PerformanceVO performance = new PerformanceVO();
		ReservationVO reservation = new ReservationVO();
		ActionForward forward = new ActionForward();
				
		req.setAttribute("performance", performance);
		req.setAttribute("reservation", reservation);
		
		forward.setPath("/member_r_reservationStart3.jsp");
		forward.setRedirect(false);
		return forward;
		
		
	}
	
	

}
