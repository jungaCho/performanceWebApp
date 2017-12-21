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

	//�������� ����� ��û�� Ŀ�ǵ�
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
			ActionForward forward = new ActionForward();
			
			ReservationVO reservation = new ReservationVO();
			List<ReservedSeatVO> reservedSeats = new ArrayList<ReservedSeatVO>();
			
			try {
			
				//���� ������ ����Ѵ�.
				ReservationService reservationService = ReservationService.getInstance();
				reservationService.createReservation(reservation, reservedSeats);
				
				req.setAttribute("reservation", reservation);
				req.setAttribute("reservedSeats", reservedSeats);
				
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
