package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.reservation.SeatVO;
import model.service.reservation.ReservationService;

//Ư�� �������� ��� �¼��� ��ȸ ��û�� Ŀ�ǵ�
public class ReservationSeatCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		String tNo = req.getParameter("tNo");
		
		try {
			
			ReservationService reservationService = ReservationService.getInstance();
			List<SeatVO> seats = reservationService.retrieveAllSeatByTheater(tNo);
			
			req.setAttribute("seats", seats);
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
