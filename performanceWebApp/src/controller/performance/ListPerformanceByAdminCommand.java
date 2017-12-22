package controller.performance;

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

public class ListPerformanceByAdminCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		//1. ���� ������ ��ȣ�� ���ϴ�.
        int currentPage = 0;
        try {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        } catch(NumberFormatException ne) {
            currentPage = 1;
        }
        
        PagingVO paging = new PagingVO();
        
        //1. �� �������� ������ �Խñ� ���� �����Ѵ�.
        paging.setPostPerpage(10);
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

			int startRow = paging.getStartRow();
			int endRow = paging.getEndRow();
			System.out.printf("startRow : %d, endRow : %d%n", startRow, endRow);

			
			List<PerformanceVO> performances = performs.retrievePerformanceListByAdmin(startRow, endRow);
			
			System.out.println(performances.size());
			
			req.setAttribute("performances", performances);
			req.setAttribute("paging", paging);

			forward.setPath("/admin_layout.jsp?nav=admin_menu&article=admin_p_selectPerformanceList");
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
