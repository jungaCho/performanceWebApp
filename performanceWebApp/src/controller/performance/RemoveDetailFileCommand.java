package controller.performance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.DetailFileVO;
import model.service.performance.PerformanceService;

public class RemoveDetailFileCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String fileNo=req.getParameter("fileNo");
		String pNo=req.getParameter("pNo");
		
		ActionForward forward=new ActionForward();
		try {
			PerformanceService service = PerformanceService.getInstance();
			List<DetailFileVO>files=service.retrieveDetailFile(pNo);
			
			for(int i=0;i<files.size();i++) {
				DetailFileVO file= files.get(i);
				if(file.getFileNo().equals(fileNo)) {
					files.remove(i);
				}
			}
			
			req.setAttribute("DetailFiles", files); 
			forward.setPath("/admin_p_modifyDetailFileView.jsp");
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
