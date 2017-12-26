package controller.performance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.performance.PerformanceService;

public class InsertPerformanceFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service=PerformanceService.getInstance();
			
			forward.setPath("/admin_layout.jsp?article=admin_p_insertPerformanceForm.jsp");
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
