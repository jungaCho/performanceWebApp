package controller.performance;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import model.service.performance.PerformanceService;

//공연 삭제하는 커맨드
public class RemovePerformanceCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String pNo=req.getParameter("pNo");
		
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service=PerformanceService.getInstance();
			PerformanceVO performance=service.retirevePerformance(pNo);
			service.removePerformance(pNo);
			
			if(performance.getDetailFiles().size() != 0) {
				String path = req.getServletContext().getRealPath("/upload");
				File file = null;
				for(DetailFileVO detailFile : performance.getDetailFiles()) {
					String systemFileName = detailFile.getSystemFileName();
					file = new File(path + File.separator + systemFileName);
					if(file.exists()) {
						file.delete();
					}
				}
			}
			
			if(performance.getPosters().size() != 0) {
				String path = req.getServletContext().getRealPath("/upload");
				File file = null;
				for(PosterVO poster : performance.getPosters()) {
					String systemFileName = poster.getSystemFileName();
					file = new File(path + File.separator + systemFileName);
					if(file.exists()) {
						file.delete();
					}
				}
			}
			
			forward.setPath("/admin_p_selectPerformanceList.jsp");
			forward.setRedirect(true);
			return forward;
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
		
		
	}

}
