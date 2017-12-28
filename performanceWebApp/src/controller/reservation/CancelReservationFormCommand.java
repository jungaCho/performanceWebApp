package controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;

public class CancelReservationFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("/member_r_layout3.jsp?nav=member_r_menu&article=member_r_reservationConfirm");
		forward.setRedirect(false);
		return forward;
	}

	
}
