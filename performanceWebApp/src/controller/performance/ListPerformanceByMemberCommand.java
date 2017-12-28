package controller.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import model.dao.performance.PerformanceDAO;
import model.service.performance.PerformanceService;

public class ListPerformanceByMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// commandFactory에 SelectPerformanceByText.do 로 매핑부탁드려여

		/*
		 * int startRow = Integer.parseInt(req.getParameter("startRow")); int endRow =
		 * Integer.parseInt(req.getParameter("endRow")); String mode =
		 * req.getParameter("mod"); String genre = req.getParameter("genre"); String
		 * keyword = req.getParameter("keyword"); String month =
		 * req.getParameter("month");
		 */

		

		ActionForward forward = new ActionForward();

		try {

			HashMap<String, Object> map = new HashMap<String, Object>();

			int startRow = 1;
			int endRow = 9;
			String mode = "image";
			String genre = "";
			String keyword = "";
			String month = "";

			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("mode", mode);
			map.put("genre", genre);
			map.put("keyword", keyword);
			map.put("month", month);

			System.out.println(startRow);
			System.out.println(endRow);
			System.out.println(keyword);
			System.out.println(mode);
			System.out.println(genre);

			PerformanceService service = PerformanceService.getInstance();
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			List<PerformanceVO> performances = service.retrievePerformanceListByMember(map);
			
			System.out.println("===============================" + performances.size());
			
			
			List<PosterVO> posters = new ArrayList<PosterVO>();
			for(PerformanceVO performance : performances) {
				for(PosterVO poster : performance.getPosters()) {
					poster.setTitle(performance.getTitle());
					if(poster.getMainPoster() == 1) {						
						posters.add(poster);
						
					}
				}
			}
			
			

			if (performances != null) {
				req.setAttribute("performances", performances);
				req.setAttribute("posters", posters);
				forward.setPath("/member_p_layout2.jsp?article=member_p_selectPerformance");
				forward.setRedirect(false);

			} else {
				forward.setPath("/member_p_layout2.jsp?article=member_m_findId");
				forward.setRedirect(false);

			}

			return forward;

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}
	}

}
