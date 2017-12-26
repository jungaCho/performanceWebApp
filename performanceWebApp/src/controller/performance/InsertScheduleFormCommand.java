package controller.performance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class InsertScheduleFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service=PerformanceService.getInstance();
			//ArrayList<String> titles=(ArrayList<String>)service.retrieveTitle();
			List<PerformanceVO> performances = service.retrievePerformance();
			
			req.setAttribute("performances", performances);
			forward.setPath("/admin_layout.jsp?nav=admin_p_menu&article=admin_p_insertScheduleForm");
			forward.setRedirect(false);
			return forward;
		}catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}
}
