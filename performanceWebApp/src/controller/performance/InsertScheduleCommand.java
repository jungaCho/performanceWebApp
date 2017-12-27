package controller.performance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import controller.ActionForward;
import controller.Command;
import domain.performance.OrderVO;
import domain.performance.ScheduleVO;
import model.service.performance.PerformanceService;

public class InsertScheduleCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// 일정 등록 요청 처리
		// 1. 일정 정보를 가져온다.
		String tNo = req.getParameter("tNo");
		String pNo = req.getParameter("pNo");
		String paraSDate = req.getParameter("sDate");
		String paraOTime = req.getParameter("oTime");
		System.out.println("%%%"+pNo);
		
		String[] sDates=paraSDate.split("/");
		String[] oTimes=paraOTime.split("/");
		ActionForward forward = new ActionForward();
		List<ScheduleVO> schedules=new ArrayList<ScheduleVO>();
		List<OrderVO> orders=new ArrayList<OrderVO>();
		
		try {
			for(int i=0; i<sDates.length; i++) {
				ScheduleVO schedule = new ScheduleVO();
				schedule.setsDate(sDates[i]);	
				schedule.settNo(tNo);
				schedule.setpNo(pNo); 
				schedules.add(schedule);
			}
			
			for(int i=0; i<oTimes.length; i++) {
				String[] oTime = oTimes[i].split(",");
				for(int j=0; j<oTime.length; j++) {
					OrderVO order = new OrderVO();
					order.setoTime(oTime[j]);
					schedules.get(i).addOrders(order);
				}				
			}
		
			PerformanceService service = PerformanceService.getInstance();

			service.createSchedule(schedules);

			forward.setPath("/admin_p_insertScheduleView.jsp");
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
