package controller.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class ListPerformanceByAdminCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward=new ActionForward();
		try {
			PerformanceService performs=PerformanceService.getInstance();
			
			int startRow=1;
			int endRow=10;
			List<PerformanceVO>performances=performs.retrievePerformanceListByAdmin(startRow, endRow);
			
			req.setAttribute("performances", performances); 
			
			forward.setPath("/admin_p_selectPerformanceList.jsp");
			forward.setRedirect(false); 
			return forward;
		}catch(Exception e) {
			req.setAttribute("exception", e); 
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
	}

}
