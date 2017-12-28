package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.performance.PerformanceService;

//공연 삭제하는 커맨드
public class RemovePerformanceCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("call RemovePerformanceCommand");
		String pNo=req.getParameter("pNo");
		
		ActionForward forward=new ActionForward();
		
		try {
			PerformanceService service=PerformanceService.getInstance();
		
			service.removePerformance(pNo);
			
			forward.setPath("/admin_p_selectPerformanceList.do");
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
