package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import domain.reservation.SeatVO;
import model.service.performance.PerformanceService;
import model.service.reservation.ReservationService;

//특정 공연장의 모든 좌석을 조회 요청할 커맨드
public class ReservationSeatCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		String tNo = req.getParameter("tNo");
		String pNo =req.getParameter("pNo");
		String oNo = req.getParameter("oNo");
		ReservationVO reservation = new ReservationVO(); 
		
		try {
			
			//모든 좌석 조회하다
			ReservationService reservationService = ReservationService.getInstance();
			List<SeatVO> seats = reservationService.retrieveAllSeatByTheater(tNo);
			
			//공연 상세정보를 조회하다.
			PerformanceService performanceService = PerformanceService.getInstance();
			PerformanceVO performance = performanceService.retirevePerformance(pNo);
			
			
			//예매된 좌석을 조회하다.
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			List<ReservedSeatVO> reservedSeats = reservationService.retrieveReservedSeatByOrders(oNo);
			for(ReservedSeatVO reservedSeat : reservedSeats ) {
				System.out.println("reservedSeat : " + reservedSeat.getSeatNumber());
			}
			
			req.setAttribute("seats", seats);
			req.setAttribute("performance", performance);
			req.setAttribute("reservedSeats", reservedSeats);
			req.setAttribute("reservation", reservation);
			req.setAttribute("oNo", oNo);
			
			forward.setPath("/member_r_reservationStart2.jsp");
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
