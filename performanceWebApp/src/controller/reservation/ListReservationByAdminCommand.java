package controller.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.pub.PagingVO;
import domain.reservation.TotalInfoVO;
import model.service.reservation.ReservationService;

public class ListReservationByAdminCommand implements Command{

	//전체 회원의 예매내역을 조회를 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(req.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		PagingVO paging = new PagingVO();
		
		//한 페이지에 보여줄 게시글 수
		paging.setPostPerpage(5);
		//한 페이지에 보여줄 페이지 목록 수
		paging.setPageBlock(10);
		//현재 페이지 번호 설정
		paging.setCurrentPage(currentPage);
		
		//1. 검색 조건 및 검색어를 구한다.
		String keyfield = req.getParameter("keyfield");
		String keyword = req.getParameter("keyword");
		
		
		
		ActionForward forward = new ActionForward();
		try {
		
			ReservationService reservationService = ReservationService.getInstance();
			paging.setTotalPost(reservationService.selectTotalList());
		
			System.out.println("currentPage : " + paging.getCurrentPage());
			System.out.println("postPerpage : " + paging.getPostPerpage());
			System.out.println("총 게시글 수 : " + paging.getTotalPost());
			System.out.println("총 페이지 수 : " + paging.getTotalPage());
			System.out.println("startPage : " + paging.getStartPage());
			System.out.println("endPage : " + paging.getEndPage());
			
			
			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			
			System.out.printf("startRow : %d%n", startRow);
			System.out.printf("endRow : %d%n", endRow);
			
			List<TotalInfoVO> totalInfos = reservationService.retrieveReservationByMember(keyfield, keyword, startRow, endRow);
			
			System.out.printf("totalInfos.size : %d%n", totalInfos.size());
			
			req.setAttribute("totalInfos", totalInfos);
			req.setAttribute("paging", paging);
			
			forward.setPath("/admin_r_layout.jsp?nav=admin_r_menu&article=admin_r_retrieveReservationList");
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
