package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class ModifyPerformanceFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//���� ��ȣ ��������
		String pNo = req.getParameter("pNo");
		
		ActionForward forward = new ActionForward();
		try {
			PerformanceService service = PerformanceService.getInstance();
			PerformanceVO performance = service.retirevePerformance(pNo);
			
			req.setAttribute("performance", performance);
			
			forward.setPath("/admin_p_modifyPerformanceForm.jsp");
			forward.setRedirect(false);
			
			return forward;
		} catch(Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);			
			return forward;
		}
	}

	
	
}