package domain.performance;

import java.util.ArrayList;
import java.util.List;

public class ScheduleVO {
	private String sNo;		//일정번호
	private String sDate; 	//공연일정
	private String pNo ;	//공연번호
	private String tNo;		//공연장 번호
	private List<OrderVO> orders= new ArrayList<OrderVO>();		//회차목록
	
	
}
