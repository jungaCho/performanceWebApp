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
        
		//1. ���� ������ ��ȣ�� ���ϴ�.
        int currentPage = 0;
        try {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        } catch(NumberFormatException ne) {
            currentPage = 1;
        }
        
        PagingVO paging = new PagingVO();
        
        //1. �� �������� ������ �Խñ� ���� �����Ѵ�.
        paging.setPostPerpage(9);
        //2. �� �������� ������ ������ ��� ���� �����Ѵ�.
        paging.setPageBlock(3);
        //3. ���� ������ ��ȣ�� ���Ѵ�.
        paging.setCurrentPage(currentPage);
        //4. �� �Խñ� ���� �����Ѵ�.

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
                forward.setPath("/performanceList.jsp"); //json�� ��ü�� ���ε��Ǿ��ִ� ������
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
