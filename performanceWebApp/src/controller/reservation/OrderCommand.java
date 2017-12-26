package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.OrderVO;
import model.service.performance.PerformanceService;

//예매할 공연에 대한 상세조회 요청 커맨드
public class OrderCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		String sNo = req.getParameter("sNo");
		String pNo = req.getParameter("pNo");
		System.out.println("pNo : " + pNo);
		
		ActionForward forward = new ActionForward();		
		try {
			PerformanceService performanceService = PerformanceService.getInstance();
			List<OrderVO> orders = performanceService.retrieveOrders(sNo);
			
			/*for(ScheduleVO temp : performance.getSchedules()) {
				System.out.println(temp.getsNo());
				
				for(OrderVO temp2 : temp.getOrders()){
					System.out.println("oTime : " + temp2.getoTime());
					System.out.println("oNo : " + temp2.getoNo());
				}
				
			}*/

			
			req.setAttribute("orders", orders);
	
			forward.setPath("/orders.jsp");
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
