package domain.reservation;

public class ReservationVO {

	String rNo;
	String rStatus;
	String rDate;
	String cardNumber;
	String approveNumber;
	int totalPrive;
	String cancelDate;
	int cardCoNo;
	String mNo;
	String oNo;
	
	public ReservationVO() {
		super();
	}

	public String getrNo() {
		return rNo;
	}

	public void setrNo(String rNo) {
		this.rNo = rNo;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getApproveNumber() {
		return approveNumber;
	}

	public void setApproveNumber(String approveNumber) {
		this.approveNumber = approveNumber;
	}

	public int getTotalPrive() {
		return totalPrive;
	}

	public void setTotalPrive(int totalPrive) {
		this.totalPrive = totalPrive;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public int getCardCoNo() {
		return cardCoNo;
	}

	public void setCardCoNo(int cardCoNo) {
		this.cardCoNo = cardCoNo;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
	}

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}

	@Override
	public String toString() {
		return "ReservationVO [rNo=" + rNo + ", rStatus=" + rStatus + ", rDate=" + rDate + ", cardNumber=" + cardNumber
				+ ", approveNumber=" + approveNumber + ", totalPrive=" + totalPrive + ", cancelDate=" + cancelDate
				+ ", cardCoNo=" + cardCoNo + ", mNo=" + mNo + ", oNo=" + oNo + "]";
	}
	
	
	
}
