package domain.performance;

public class OrderVO {
	private String oNo; //회차번호
	private String oTime; //공연시간
	private String sNo; //일정번호
	
	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderVO(String oNo, String oTime, String sNo) {
		super();
		this.oNo = oNo;
		this.oTime = oTime;
		this.sNo = sNo;
	}

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}

	public String getoTime() {
		return oTime;
	}

	public void setoTime(String oTime) {
		this.oTime = oTime;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	@Override
	public String toString() {
		return "OrderVO [oNo=" + oNo + ", oTime=" + oTime + ", sNo=" + sNo + "]";
	}
	
	
}
