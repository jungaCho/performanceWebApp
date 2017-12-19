package domain.reservation;

public class ReservedSeatVO {
	
	private String rNo;
	private String seatNo;
	private String oNo;
	
	public ReservedSeatVO() {
		super();
	}

	public ReservedSeatVO(String rNo, String seatNo, String oNo) {
		super();
		this.rNo = rNo;
		this.seatNo = seatNo;
		this.oNo = oNo;
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

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}
	
	

}
