package domain.reservation;

public class ReservedSeatVO {
	
	private String rNo;
	private String seatNo;
	private String seatNumber;

	
	public ReservedSeatVO() {
		super();
	}

	public ReservedSeatVO(String rNo, String seatNo) {
		super();
		this.rNo = rNo;
		this.seatNo = seatNo;
	}

	public String getrNo() {
		return rNo;
	}

	public void setrNo(String rNo) {
		this.rNo = rNo;
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
	
	
	


}
