package controller.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import domain.performance.PerformanceVO;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import domain.reservation.SeatVO;
import model.service.reservation.ReservationService;

public class UploadReservationCommand implements Command{

	//예매정보 등록을 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
		String selectTd =req.getParameter("selectTd");
		String cardNumber = req.getParameter("cardNumber");
		int cardCoNo = Integer.parseInt(req.getParameter("cardCoNo"));
		String oNo = req.getParameter("oNo");
		String seatNo = req.getParameter("seatNo");
			
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String mNo = member.getmNo();
		String oTime = req.getParameter("oTime");
		String title = req.getParameter("title");
		String sDate = req.getParameter("sDate");
		
		
		ActionForward forward = new ActionForward();
		
		try {
		
			//예매 정보를 등록한다.
			ReservationService reservationService = ReservationService.getInstance();
			
			ReservationVO reservation = new ReservationVO(cardNumber, totalPrice, cardCoNo, mNo, oNo);
			
			List<ReservedSeatVO> reservedSeat = new ArrayList<ReservedSeatVO>();
			for(String seatNoA : seatNo.split(",")) {
				ReservedSeatVO seat = new ReservedSeatVO();
				seat.setSeatNo(seatNoA);
				reservedSeat.add(seat);
			}
			reservation.setReservedSeat(reservedSeat);
			
		
			ReservationVO vo = reservationService.createReservation(reservation);
			
			req.setAttribute("vo", vo);

			
			forward.setPath("/member_r_layout2.jsp?nav=member_r_menu&article=member_r_reservationList");
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
