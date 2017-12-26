package controller.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import domain.performance.OrderVO;
import domain.performance.ScheduleVO;
import model.service.performance.PerformanceService;

public class InsertScheduleCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// ���� ��� ��û ó��
		// 1. ���� ������ �����´�.
		String tNo = req.getParameter("tNo");
		String pNo = req.getParameter("title");
		String sDate = req.getParameter("sDate");
		String oTime = req.getParameter("oTime");
		
		OrderVO order = new OrderVO();
		oTime = order.getoTime();
		
		List<ScheduleVO> schedules = new ArrayList<ScheduleVO>(sDate, pNo, tNo, order);		
		ActionForward forward = new ActionForward();
		try {
			// 2. DB�� ���� ���� ���
			PerformanceService service = PerformanceService.getInstance();
			service.createSchedule(schedules);

			// 3.

		} catch (Exception e) {
			req.setAttribute("exception", e);
			forward.setPath("/error.jsp");
			forward.setRedirect(false);

			return forward;
		}
	}
}
