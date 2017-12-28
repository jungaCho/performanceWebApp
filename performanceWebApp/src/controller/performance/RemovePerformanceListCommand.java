package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.performance.PerformanceService;

public class RemovePerformanceListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ActionForward forward=new ActionForward();
		try {
			String[] pNos = req.getParameterValues("checked");
			PerformanceService service=PerformanceService.getInstance();
			
			service.removePerformanceList(pNos);
			forward.setPath("/admin_p_selectPerformanceList.do");
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
