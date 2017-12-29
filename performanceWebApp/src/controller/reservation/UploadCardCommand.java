package controller.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import controller.Command;
import domain.member.MemberVO;
import domain.performance.PerformanceVO;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import model.service.reservation.ReservationService;

public class UploadCardCommand implements Command{

	//예매정보 등록을 요청할 커맨드
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
		int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
		String selectTd =req.getParameter("selectTd");
		String cardNumber = req.getParameter("cardNumber");
		int cardCoNo = Integer.parseInt(req.getParameter("cardCoNo"));
		String oNo = req.getParameter("oNo");		
		String sDate = req.getParameter("sDate");
		String seatNo = req.getParameter("seatNo");
		
		System.out.println("cardNumber : " + cardNumber);
		System.out.println("cardCoNo : " + cardCoNo);
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = req.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String mNo = member.getmNo();
		String oTime = req.getParameter("oTime");
		String title = req.getParameter("title");
		
		ReservationVO reservation = new ReservationVO(cardNumber, totalPrice, cardCoNo,mNo, oNo);
		req.setAttribute("reservation", reservation);
			
		forward.setPath("/member_r_reservationStart4.jsp");
		forward.setRedirect(false);
		return forward;
			
		
	}

	
}
