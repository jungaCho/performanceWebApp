package controller.performance;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import model.service.performance.PerformanceService;

public class ModifyPerformanceFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//공연 번호 가져오기
		String pNo = req.getParameter("pNo");
		
		ActionForward forward = new ActionForward();
		try {
			PerformanceService service = PerformanceService.getInstance();
			PerformanceVO performance = service.retirevePerformance(pNo);
			ArrayList<PosterVO> posters=(ArrayList<PosterVO>)service.retrievePoster(pNo);
			ArrayList<DetailFileVO> detailFiles=(ArrayList<DetailFileVO>)service.retrieveDetailFile(pNo);
			
			req.setAttribute("performance", performance);
			req.setAttribute("posters",posters);
			req.setAttribute("detailFiles",detailFiles);
			forward.setPath("/admin_layout.jsp?nav=admin_p_menu&article=admin_p_modifyPerformanceForm");
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
