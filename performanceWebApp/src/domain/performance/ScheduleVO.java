package domain.performance;

import java.util.ArrayList;
import java.util.List;

public class ScheduleVO {
	private String sNo;		//������ȣ
	private String sDate; 	//��������
	private String pNo ;	//������ȣ
	private String tNo;		//������ ��ȣ
	private List<OrderVO> orders= new ArrayList<OrderVO>();		//ȸ�����
	
	public ScheduleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScheduleVO(String sDate, String pNo, String tNo, List<OrderVO> orders) {
		super();
		this.sDate = sDate;
		this.pNo = pNo;
		this.tNo = tNo;
		this.orders = orders;
	}

	public ScheduleVO(String sNo, String sDate, String pNo, String tNo, List<OrderVO> orders) {
		super();
		this.sNo = sNo;
		this.sDate = sDate;
		this.pNo = pNo;
		this.tNo = tNo;
		this.orders = orders;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String gettNo() {
		return tNo;
	}

	public void settNo(String tNo) {
		this.tNo = tNo;
	}

	public List<OrderVO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderVO> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "ScheduleVO [sNo=" + sNo + ", sDate=" + sDate + ", pNo=" + pNo + ", tNo=" + tNo + ", orders=" + orders
				+ "]";
	}
	
	public void addOrders(OrderVO order) {
		this.orders.add(order);
	}
	
}
