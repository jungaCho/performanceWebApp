package domain.reservation;

public class SeatVO {
	private String seatNo;
	private String seatNumber;
	private String tNo;
	
	public SeatVO() {
		
	}

	public SeatVO(String seatNo, String seatNumber, String tNo) {
		super();
		this.seatNo = seatNo;
		this.seatNumber = seatNumber;
		this.tNo = tNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String gettNo() {
		return tNo;
	}

	public void settNo(String tNo) {
		this.tNo = tNo;
	}
	
}
