package model.dao.reservation;

public class ReservationDAO {

	//�̱��� ����
	private static ReservationDAO instance = new ReservationDAO();
	
	private ReservationDAO() {
		
	}
	
	public static ReservationDAO getInstance() {
		return instance;
	}
	
}
