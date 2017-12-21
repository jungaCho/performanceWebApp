package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.PerformanceVO;
import model.service.performance.PerformanceService;

//���� ����ȸ ��û�� ó���� Ŀ�ǵ� Ŭ���� ����
public class DetailPerformanceCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 1. ���� ��ȸ�ϰ��� �ϴ� ���� ��ȣ�� ���Ѵ�.
		String pNo = req.getParameter("pNo");

		ActionForward forward = new ActionForward();
		try {
			PerformanceService performanceService = PerformanceService.getInstance();

			PerformanceVO performance = performanceService.retirevePerformance(pNo);

			req.setAttribute("performance", performance);

			forward.setPath("admin_p_detailPerformance.jsp");
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
