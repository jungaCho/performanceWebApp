package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

//������ ������ ���� ����ȸ ��û Ŀ�ǵ�
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
			
			System.out.println("performance : " + performance);
			
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