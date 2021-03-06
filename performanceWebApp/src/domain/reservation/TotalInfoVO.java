package domain.reservation;

import java.util.ArrayList;
import java.util.List;

public class TotalInfoVO {
	private String rNo;				//���Ź�ȣ
	private String rStatus;
	private String rDate;
	private String cardNumber;
	private String approveNumber;
	private int totalPrice;
	private String cardCoName;
	private String mId;
	private String mName;
	private String mNo;
	private String title;
	private String sDate;
	private String oTime;
	private String tName;
	private List<ReservedSeatVO> seats = new ArrayList<ReservedSeatVO>();
	
	public TotalInfoVO() {
		
	}

	public TotalInfoVO(String rNo, String rStatus, String rDate, String cardNumber, String approveNumber,
			int totalPrice, String cardCoName, String mId, String title, String sDate, String oTime, String tName,
			List<ReservedSeatVO> seats, String mName, String mNo) {
		super();
		this.rNo = rNo;
		this.rStatus = rStatus;
		this.rDate = rDate;
		this.cardNumber = cardNumber;
		this.approveNumber = approveNumber;
		this.totalPrice = totalPrice;
		this.cardCoName = cardCoName;
		this.mName = mName;
		this.mNo = mNo;
		this.mId = mId;
		this.title = title;
		this.sDate = sDate;
		this.oTime = oTime;
		this.tName = tName;
		this.seats = seats;
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

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCardCoName() {
		return cardCoName;
	}

	public void setCardCoName(String cardCoName) {
		this.cardCoName = cardCoName;
	}

	
	

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getoTime() {
		return oTime;
	}

	public void setoTime(String oTime) {
		this.oTime = oTime;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public List<ReservedSeatVO> getSeats() {
		return seats;
	}

	public void setSeats(List<ReservedSeatVO> seats) {
		this.seats = seats;
	}
	
	public void addSeats(ReservedSeatVO seat) {
		this.seats.add(seat);
	}
	
	
	
}	
