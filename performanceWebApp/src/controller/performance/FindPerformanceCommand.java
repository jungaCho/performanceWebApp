package controller.performance;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

public class FindPerformanceCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		int startRow = 1;
		int endRow = 10;
		// 1. 검색 조건 및 검색어를 구한다.
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");

		ActionForward forward = new ActionForward();
		try {
			PerformanceService service = PerformanceService.getInstance();
			ArrayList<PerformanceVO> performances = service.findPerformance(keyfield, keyword, startRow, endRow);

			req.setAttribute("performances", performances);
			System.out.println("~~~~"+performances.get(0).getTitle());
			System.out.println("~~~~"+performances.get(1).getTitle());
			
			forward.setPath("/admin_p_selectPerforamcneListView.jsp");
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
