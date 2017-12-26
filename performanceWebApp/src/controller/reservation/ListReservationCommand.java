package controller.reservation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class ListReservationCommand implements Command{

	//공연 목록 조회를 요청하는 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		HashMap<String,Object> map = new HashMap<String, Object>();
		
		try {
		
			PerformanceService performanceService = PerformanceService.getInstance();
			List<PerformanceVO> performance = performanceService.retrievePerformanceListByMember(map);
			
			req.setAttribute("performance", performance);
			forward.setPath("/member_r_layout.jsp?nav=member_r_menu&article=member_r_reservation");
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
