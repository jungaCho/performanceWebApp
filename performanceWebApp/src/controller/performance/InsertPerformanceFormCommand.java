package controller.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class InsertPerformanceFormCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) {
		ActionForward forward=new ActionForward();
		forward.setPath("/admin_layout.jsp?article=admin_p_insertPerformanceForm.do");
		forward.setRedirect(false);
		return forward;
	}
}
