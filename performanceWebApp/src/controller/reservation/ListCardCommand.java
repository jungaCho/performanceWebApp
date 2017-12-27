package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.reservation.ReservationVO;

public class ListCardCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {

		int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
		String selectTd =req.getParameter("selectTd");
		String oNo = req.getParameter("oNo");
		String oTime = req.getParameter("oTime");
		String title = req.getParameter("title");
		String sDate = req.getParameter("sDate");
		
		ReservationVO reservation = new ReservationVO();
		ActionForward forward = new ActionForward();


		req.setAttribute("reservation", reservation);
		req.setAttribute("totalPrice", totalPrice);
		req.setAttribute("selectTd", selectTd);
		req.setAttribute("oNo", oNo);	
		req.setAttribute("oTime", oTime);
		req.setAttribute("title", title);
		req.setAttribute("sDate", sDate);
		
		forward.setPath("/member_r_reservationStart3.jsp");
		forward.setRedirect(false);
		return forward;
		
		
	}
	
	

}
