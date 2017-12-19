package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import domain.performance.ScheduleVO;

public class ScheduleDAO {
	private static ScheduleDAO instance=new ScheduleDAO();
	
	private ScheduleDAO() {
		
	}
	
	public static ScheduleDAO getInstance() {
		return instance;
	}
	
	//공연일정을 등록한다.
	public void insertSchedule(Connection conn, ScheduleVO schedule)throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			
			sql.append("insert into schedule(s_no,s_date,p_no,t_no)   ");
			sql.append("values('S'||lpad(schedule_seq.nextVal,5,0), ?,?,?)  ");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, schedule.getsDate());
			pstmt.setString(2, schedule.getpNo());
			pstmt.setString(3, schedule.gettNo());
			
			pstmt.executeUpdate();
	
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 일정 정보를 삭제한다.
	public void deleteSchedule(Connection conn,String pNo) throws Exception{
		PreparedStatement pstmt=null;
		Statement stmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("delete from schedule    ");
			sql.append("where p_no=?     ");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, pNo);
			pstmt.executeUpdate();

			
		} finally {
			if(pstmt!=null)pstmt.close();
		}
		
		
	}
}
