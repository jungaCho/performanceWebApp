package domain.performance;

public class OrderVO {
	private String oNo; //ȸ����ȣ
	private String oTime; //�����ð�
	private String sNo; //������ȣ
	
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
