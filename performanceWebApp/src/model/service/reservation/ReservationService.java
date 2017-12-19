package model.service.reservation;

public class ReservationService {
	
	private static ReservationService instance = new ReservationService();

	
	private ReservationService() {

		}

	public static ReservationService getInstance() {
		return instance;
	}
}
