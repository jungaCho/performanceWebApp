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

	// ���� ������ ���ŵ� �¼� ������ ����Ѵ�.
	public void createReservation(ReservationVO reservation, List<ReservedSeatVO> seats) throws Exception {

		Connection conn = null;

		try {
			conn = DBConn.getConnection();

			// �ڵ� Ŀ�� ����
			conn.setAutoCommit(false);

			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			String rNo = reservationDAO.insertReservation(reservation, conn);

			for (int i = 0; i < seats.size(); i++) {
				seats.get(i).setrNo(rNo);
			}

			reservationDAO.insertReservedSeat(conn, seats);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	// ���Ÿ� ����Ѵ�.
	public void modifyReservation(String rNo) throws Exception {
		
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
			
			conn.setAutoCommit(false);

			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			reservationDAO.checkCanceledReservation(conn, rNo);
			reservationDAO.updateReservation(rNo, conn);
			reservationDAO.deleteReservedSeat(rNo, conn);

			
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(conn != null) conn.close();
		}
		
	}
	
	//Ư�� ȸ���� ���ų����� ��ȸ�Ѵ�.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, 
																String mNo) throws Exception{
			Connection conn = null;
			try {
				conn = DBConn.getConnection();
				
				conn.setAutoCommit(false);
				
				ReservationDAO reservationDAO = ReservationDAO.getInstance();
				List<TotalInfoVO> totalInfos = reservationDAO.selectReservationListByMember(conn, mNo, startRow, endRow);
				
				return totalInfos;			
			} catch (Exception e) {
				conn.rollback();
				throw e;
			} finally {
				if(conn != null) conn.close();
			}
		}
		
		//Ư�� �������� ��� �¼��� ��ȸ�Ѵ�.
		public List<SeatVO> retrieveAllSeatByTheater(String tNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<SeatVO> seats = reservationDAO.selectAllSeat(tNo);
			return seats;	
			
		}
		
		//��ü ȸ���� ���ų����� ��ȸ�Ѵ�.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String keyfield, String keyword) throws Exception{
		
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			return reservationDAO.selectReservationListByAdmin(keyfield, keyword, startRow, endRow);
			
		}
		
		//Ư�� ����ȸ���� ���ŵ� �¼��� ��ȸ�Ѵ�.
		public List<ReservedSeatVO> retrieveReservedSeatByOrders(String oNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<ReservedSeatVO> seats  = reservationDAO.selectReservedSeat(oNo);
			
			return seats;
			
		}
		
		public int selectTotalList() throws Exception {
			return ReservationDAO.getInstance().selectTotalPost();
		}

		
}
