package model.service.reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import conn.DBConn;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import domain.reservation.SeatVO;
import domain.reservation.TotalInfoVO;
import model.dao.reservation.ReservationDAO;

public class ReservationService {

	private static ReservationService instance = new ReservationService();

	private ReservationService() {

	}

	public static ReservationService getInstance() {
		return instance;
	}

	// 예매 정보와 예매된 좌석 정보를 등록한다.
	public ReservationVO createReservation(ReservationVO reservation) throws Exception {

		Connection conn = null;

		try {
			conn = DBConn.getConnection();

			// 자동 커밋 해제
			conn.setAutoCommit(false);

			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			ReservationVO vo = reservationDAO.insertReservation(conn, reservation);

			System.out.println("예매번호 : " + vo.getrNo());
			reservationDAO.insertReservedSeat(conn, reservation.getReservedSeat());

			conn.commit();
			
			return vo;

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)	conn.close();
		}

	}

	// 예매를 취소한다.
	public void modifyReservation(String rNo) throws Exception {

		Connection conn = null;

		try {
			conn = DBConn.getConnection();

			conn.setAutoCommit(false);

			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			Boolean isSuccess = reservationDAO.checkCanceledReservation(conn, rNo);
			reservationDAO.updateReservation(rNo, conn);
			reservationDAO.deleteReservedSeat(rNo, conn);
			reservationDAO.insertRefund(conn, rNo);

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	// 특정 회원의 예매내역을 조회한다.
	public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String keyfield, 
															 String keyword, String mNo) throws Exception {
		return ReservationDAO.getInstance().selectReservationListByMember(keyfield, keyword, mNo, startRow, endRow);
	}
	
	//특정 회원의 예매내역을 조회한다.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String mNo) throws Exception{
			return ReservationDAO.getInstance().selectReservationListByMember(mNo, startRow, endRow);	
		}
		
		//특정 공연장의 모든 좌석을 조회한다.
		public List<SeatVO> retrieveAllSeatByTheater(String tNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<SeatVO> seats = reservationDAO.selectAllSeat(tNo);
			return seats;	
			
		}
		
		//전체 회원의 예매내역을 조회한다.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow) throws Exception{
		
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			return reservationDAO.selectReservationListByAdmin(startRow, endRow);
			
		}
		
		//특정 공연회차의 예매된 좌석을 조회한다.
		public List<ReservedSeatVO> retrieveReservedSeatByOrders(String oNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<ReservedSeatVO> seats  = reservationDAO.selectReservedSeat(oNo);
			
			return seats;
			
		}
		
		//게시글 수를 조회하다.
		public int selectTotalList() throws Exception {
			return ReservationDAO.getInstance().selectTotalPost();
		}

		
/*	// 특정 공연장의 모든 좌석을 조회한다.
	public List<SeatVO> retrieveAllSeatByTheater(String tNo) throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		List<SeatVO> seats = reservationDAO.selectAllSeat(tNo);
		return seats;

	}

	// 전체 회원의 예매내역을 조회한다.
	public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String keyfield, String keyword)
			throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		return reservationDAO.selectReservationListByAdmin(keyfield, keyword, startRow, endRow);

	}

	// 특정 공연회차의 예매된 좌석을 조회한다.
	public List<ReservedSeatVO> retrieveReservedSeatByOrders(String oNo) throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		List<ReservedSeatVO> seats = reservationDAO.selectReservedSeat(oNo);

		return seats;

	}
*/
}
