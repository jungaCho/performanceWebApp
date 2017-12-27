package controller.performance;

import java.io.IOException;
import java.util.HashMap;
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

public class ListPerformanceByMemberCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		int startRow = Integer.parseInt(req.getParameter("startRow"));
        int endRow = Integer.parseInt(req.getParameter("endRow"));
        String mode = req.getParameter("mode");
        
		//1. 현재 페이지 번호를 구하다.
        int currentPage = 0;
        try {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        } catch(NumberFormatException ne) {
            currentPage = 1;
        }
        
        PagingVO paging = new PagingVO();
        
        //1. 한 페이지에 보여줄 게시글 수를 설정한다.
        paging.setPostPerpage(9);
        //2. 한 페이지에 보여줄 페이지 목록 수를 설정한다.
        paging.setPageBlock(3);
        //3. 현재 페이지 번호를 구한다.
        paging.setCurrentPage(currentPage);
        //4. 총 게시글 수를 설정한다.

		ActionForward forward = new ActionForward();
		try {
			PerformanceService performs = PerformanceService.getInstance();
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			paging.setTotalPost(performanceDao.selectTotalPost());

            HashMap<String, Object> map = new HashMap<String, Object>();
            
            map.put("startRow",startRow);
            map.put("endRow",endRow);
            map.put("mode",mode);

            PerformanceService service = PerformanceService.getInstance();
            List<PerformanceVO> perfomances = service.retrievePerformanceListByMember(map);

            if(perfomances != null) {
                req.setAttribute("perfomances", perfomances);
                forward.setPath("/performanceList.jsp"); //json에 객체들 바인딩되어있는 페이지
                forward.setRedirect(false);
                return forward;
            } else {
            	forward.setPath("/member_p_layout2.jsp?article=member_p_selectPerformance");
    			forward.setRedirect(false);
    			return forward;
            }
			
		/*	req.setAttribute("performances", performances);
			req.setAttribute("paging", paging);
*/
			
			
		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);
			return forward;
		}

	}

}
