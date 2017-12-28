package controller.performance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

//공연 상세조회 요청을 처리할 커맨드 클래스 구현
public class DetailPerformanceByMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 1. 상세히 조회하고자 하는 공연 번호를 구한다.
		String pNo = req.getParameter("pNo");
		System.out.println("pNo :  "+pNo); 
		ActionForward forward = new ActionForward();
		try {
			PerformanceService performanceService = PerformanceService.getInstance();
			PerformanceVO performance = performanceService.retirevePerformance(pNo);
			List<DetailFileVO> detailFiles = (List<DetailFileVO>)performance.getDetailFiles();
			
			req.setAttribute("performance", performance);
			req.setAttribute("detailFiles", detailFiles);

			forward.setPath("/member_p_layout2.jsp?article=member_p_detailPerformance");
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