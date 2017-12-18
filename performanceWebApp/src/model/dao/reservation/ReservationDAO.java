package model.dao.reservation;

public class ReservationDAO {

	//ΩÃ±€≈Ê ∆–≈œ
	private static ReservationDAO instance = new ReservationDAO();
	
	private ReservationDAO() {
		
	}
	
	public static ReservationDAO getInstance() {
		return instance;
	}
	
}
