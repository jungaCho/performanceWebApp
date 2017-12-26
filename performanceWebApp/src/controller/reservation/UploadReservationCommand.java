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

public class UploadReservationCommand implements Command{

	//�������� ����� ��û�� Ŀ�ǵ�
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException {
		
			int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
			String selectTd =req.getParameter("selectTd");
			String cardNumber = req.getParameter("cardNumber");
			int cardCoNo = Integer.parseInt(req.getParameter("cardCoNo"));
			String oNo = req.getParameter("oNo");		
			String sDate = req.getParameter("sDate");
			String rNo = req.getParameter("rNo");
			String approveNumber = req.getParameter("approveNumber");
			
			System.out.println("cardNumber : " + cardNumber);
			System.out.println("cardCoNo : " + cardCoNo);
			
			PerformanceVO performance = new PerformanceVO();
			
			ActionForward forward = new ActionForward();
			
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String mNo = member.getmNo();
			String oTime = req.getParameter("oTime");
			String title = req.getParameter("title");
			
			ReservationVO reservation = new ReservationVO(cardNumber, totalPrice, cardCoNo,mNo, oNo ,rNo, approveNumber);
			List<ReservedSeatVO> reservedSeats = new ArrayList<ReservedSeatVO>();
			
			try {
			
				//���� ������ ����Ѵ�.
				ReservationService reservationService = ReservationService.getInstance();
				reservationService.createReservation(reservation, reservedSeats);
				
				req.setAttribute("reservation", reservation);
				req.setAttribute("reservedSeats", reservedSeats);
				req.setAttribute("totalPrice", totalPrice);
				req.setAttribute("selectTd", selectTd);
				req.setAttribute("cardNumber", cardNumber);
				req.setAttribute("cardCoNo", cardCoNo);
				req.setAttribute("oNo", oNo);
				req.setAttribute("mNo", mNo);
				req.setAttribute("performance", performance);
				req.setAttribute("oTime", oTime);
				req.setAttribute("title", title);
				req.setAttribute("sDate", sDate);
				req.setAttribute("rNo", rNo);
				req.setAttribute("approveNumber", approveNumber);
				
				forward.setPath("/member_r_layout2.jsp?nav=member_r_menu&article=member_r_reservationList");
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