package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.OrderVO;
import domain.performance.PerformanceVO;
import domain.performance.ScheduleVO;
import model.service.performance.PerformanceService;

//예매할 공연에 대한 상세조회 요청 커맨드
public class ReservationStartCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		String pNo = req.getParameter("pNo");
		System.out.println("pNo : " + pNo);
		
		ActionForward forward = new ActionForward();		
		try {
			PerformanceService performanceService = PerformanceService.getInstance();
			PerformanceVO performance = performanceService.retirevePerformance(pNo);

			
			
			req.setAttribute("performance", performance);
			
			forward.setPath("/member_r_reservationStart.jsp");
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
