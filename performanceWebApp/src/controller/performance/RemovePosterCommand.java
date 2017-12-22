package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.performance.PerformanceService;

public class RemovePosterCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String posterNo=req.getParameter("posterNo");
		String pNo=req.getParameter("pNo");
		
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service = PerformanceService.getInstance();
			service.removePoster(posterNo);
			
			forward.setPath("/admin_p_modifyPosterView.jsp");
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
