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
	public ReservationVO createReservation(ReservationVO reservation) throws Exception {

		Connection conn = null;

		try {
			conn = DBConn.getConnection();

			// �ڵ� Ŀ�� ����
			conn.setAutoCommit(false);

			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			ReservationVO vo = reservationDAO.insertReservation(conn, reservation);

			System.out.println("���Ź�ȣ : " + vo.getrNo());
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

	// ���Ÿ� ����Ѵ�.
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

	// Ư�� ȸ���� ���ų����� ��ȸ�Ѵ�.
	public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String keyfield, 
															 String keyword, String mNo) throws Exception {
		return ReservationDAO.getInstance().selectReservationListByMember(keyfield, keyword, mNo, startRow, endRow);
	}
	
	//Ư�� ȸ���� ���ų����� ��ȸ�Ѵ�.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String mNo) throws Exception{
			return ReservationDAO.getInstance().selectReservationListByMember(mNo, startRow, endRow);	
		}
		
		//Ư�� �������� ��� �¼��� ��ȸ�Ѵ�.
		public List<SeatVO> retrieveAllSeatByTheater(String tNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<SeatVO> seats = reservationDAO.selectAllSeat(tNo);
			return seats;	
			
		}
		
		//��ü ȸ���� ���ų����� ��ȸ�Ѵ�.
		public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow) throws Exception{
		
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			return reservationDAO.selectReservationListByAdmin(startRow, endRow);
			
		}
		
		//Ư�� ����ȸ���� ���ŵ� �¼��� ��ȸ�Ѵ�.
		public List<ReservedSeatVO> retrieveReservedSeatByOrders(String oNo) throws Exception {
			
			ReservationDAO reservationDAO = ReservationDAO.getInstance();
			List<ReservedSeatVO> seats  = reservationDAO.selectReservedSeat(oNo);
			
			return seats;
			
		}
		
		//�Խñ� ���� ��ȸ�ϴ�.
		public int selectTotalList() throws Exception {
			return ReservationDAO.getInstance().selectTotalPost();
		}

		
/*	// Ư�� �������� ��� �¼��� ��ȸ�Ѵ�.
	public List<SeatVO> retrieveAllSeatByTheater(String tNo) throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		List<SeatVO> seats = reservationDAO.selectAllSeat(tNo);
		return seats;

	}

	// ��ü ȸ���� ���ų����� ��ȸ�Ѵ�.
	public List<TotalInfoVO> retrieveReservationByMember(int startRow, int endRow, String keyfield, String keyword)
			throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		return reservationDAO.selectReservationListByAdmin(keyfield, keyword, startRow, endRow);

	}

	// Ư�� ����ȸ���� ���ŵ� �¼��� ��ȸ�Ѵ�.
	public List<ReservedSeatVO> retrieveReservedSeatByOrders(String oNo) throws Exception {

		ReservationDAO reservationDAO = ReservationDAO.getInstance();
		List<ReservedSeatVO> seats = reservationDAO.selectReservedSeat(oNo);

		return seats;

	}
*/
}
