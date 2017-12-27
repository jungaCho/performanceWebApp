package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import domain.pub.PagingVO;
import model.dao.performance.PerformanceDAO;
import model.service.performance.PerformanceService;

public class ListPerformanceByMemberCommand implements Command{

	//공연 목록 조회를 요청하는 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		//1. 현재 페이지 번호를 구하다.
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (NumberFormatException ne) {
			currentPage = 1;
		}	
		PagingVO paging = new PagingVO();
		
		paging.setPostPerpage(5);
		paging.setPageBlock(5);
		paging.setCurrentPage(currentPage);
		
		ActionForward forward = new ActionForward();	
		try {
			PerformanceService performanceService = PerformanceService.getInstance();
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			paging.setTotalPost(performanceDao.selectTotalPost());
			
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			
			System.out.printf("startRow : %d, endRow: %d%n", startRow, endRow);
			
			
			List<PerformanceVO> performance = performanceService.retrievePerformanceList(startRow, endRow);
			
			req.setAttribute("performance", performance);
			req.setAttribute("paging", paging);
			
			forward.setPath("/member_r_layout.jsp?nav=member_r_menu&article=member_r_reservation");
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
