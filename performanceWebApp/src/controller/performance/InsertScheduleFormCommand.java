package controller.performance;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.performance.PerformanceService;

public class InsertScheduleFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service=PerformanceService.getInstance();
			ArrayList<String> titles=(ArrayList<String>)service.retrieveTitle();
			
			req.setAttribute("titles", titles);
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
