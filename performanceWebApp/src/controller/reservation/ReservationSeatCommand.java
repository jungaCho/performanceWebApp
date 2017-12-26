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

//Ư�� �������� ��� �¼��� ��ȸ ��û�� Ŀ�ǵ�
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
			
			//��� �¼� ��ȸ�ϴ�
			ReservationService reservationService = ReservationService.getInstance();
			List<SeatVO> seats = reservationService.retrieveAllSeatByTheater(tNo);
			
			//���� �������� ��ȸ�ϴ�.
			PerformanceService performanceService = PerformanceService.getInstance();
			PerformanceVO performance = performanceService.retirevePerformance(pNo);
			
			
			//���ŵ� �¼��� ��ȸ�ϴ�.
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
