package domain.reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationVO {

	String rNo;
	String rStatus;
	String rDate;
	String cardNumber;
	String approveNumber;
	int totalPrice;
	String cancelDate;
	int cardCoNo;
	String mNo;
	String oNo;
	List<ReservedSeatVO> reservedSeat = new ArrayList<ReservedSeatVO>();
	
	public List<ReservedSeatVO> getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(List<ReservedSeatVO> reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	public ReservationVO() {
		
	}
	
public ReservationVO(String cardNumber, int totalPrice, int cardCoNo, String mNo, String oNo, String rNo,String approveNumber) {
		super();
		this.cardNumber = cardNumber;
		this.totalPrice = totalPrice;
		this.cardCoNo = cardCoNo;
		this.mNo = mNo;
		this.oNo = oNo;
		this.rNo = rNo;
		this.approveNumber = approveNumber;
	}

public ReservationVO(String cardNumber, int totalPrice, int cardCoNo, String mNo, String oNo) {
	super();
	this.cardNumber = cardNumber;
	this.totalPrice = totalPrice;
	this.cardCoNo = cardCoNo;
	this.mNo = mNo;
	this.oNo = oNo;
}


	public ReservationVO(String rNo, String rStatus, String rDate, String cardNumber, String approveNumber,
			int totalPrice, String cancelDate, int cardCoNo, String mNo, String oNo) {
		super();
		this.rNo = rNo;
		this.rStatus = rStatus;
		this.rDate = rDate;
		this.cardNumber = cardNumber;
		this.approveNumber = approveNumber;
		this.totalPrice = totalPrice;
		this.cancelDate = cancelDate;
		this.cardCoNo = cardCoNo;
		this.mNo = mNo;
		this.oNo = oNo;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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
				+ ", approveNumber=" + approveNumber + ", totalPrive=" + totalPrice + ", cancelDate=" + cancelDate
				+ ", cardCoNo=" + cardCoNo + ", mNo=" + mNo + ", oNo=" + oNo + "]";
	}
	
	
	
}
