package controller.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import model.service.reservation.ReservationService;

public class UploadCardCommand implements Command{

	//예매정보 등록을 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
			int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
			String selectTd =req.getParameter("selectTd");
			String cardNumber = req.getParameter("cardNumber");
			String cardCompany = req.getParameter("cardCompany");
			String oNo = req.getParameter("oNo");
			
			System.out.println("cardNumber : " + cardNumber);
			System.out.println("cardCompany : " + cardCompany);
			ActionForward forward = new ActionForward();
			
			
			ReservationVO reservation = new ReservationVO();
			List<ReservedSeatVO> reservedSeats = new ArrayList<ReservedSeatVO>();
			
			try {
			
				//예매 정보를 등록한다.
				ReservationService reservationService = ReservationService.getInstance();
				reservationService.createReservation(reservation, reservedSeats);
				
				req.setAttribute("reservation", reservation);
				req.setAttribute("reservedSeats", reservedSeats);
				req.setAttribute("totalPrice", totalPrice);
				req.setAttribute("selectTd", selectTd);
				req.setAttribute("cardNumber", cardNumber);
				req.setAttribute("cardCompany", cardCompany);
				req.setAttribute("oNo", oNo);
				
				forward.setPath("/member_r_reservationStart4.jsp");
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
