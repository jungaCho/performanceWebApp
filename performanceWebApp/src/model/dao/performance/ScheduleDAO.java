package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public String insertSchedule(Connection conn, ScheduleVO schedule)throws Exception{
		PreparedStatement pstmt=null;
		Statement stmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			
			sql.append("insert into schedule(s_no,s_date,p_no,t_no)   ");
			sql.append("values('S'||lpad(schedule_seq.nextVal,5,0), ?,?,?)  ");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, schedule.getsDate());
			pstmt.setString(2, schedule.getpNo());
			pstmt.setString(3, schedule.gettNo());
			
			pstmt.executeUpdate();
			pstmt.close();

			sql.delete(0, sql.length());

			stmt = conn.createStatement();

			sql.append("select 'S'||lpad(schedule_seq.currVal,5,0) from dual ");

			ResultSet rs = stmt.executeQuery(sql.toString());

			String sNo = "";
			if (rs.next()) {
				sNo = rs.getString(1);
			}
			return sNo;

		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 일정 정보를 삭제한다.
	public void deleteSchedule(Connection conn,String pNo) throws Exception{
		PreparedStatement pstmt=null;
	
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
	
	//공연번호에 해당하는 회차번호 조회
		public String[] selectSchedule(Connection conn, String pNo) throws Exception{
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				
				StringBuffer sql=new StringBuffer();
				
				sql.append("select s_no    ");
				sql.append("from schedule   ");
				sql.append("where p_no=?    ");
				
				pstmt=conn.prepareStatement(sql.toString());
				pstmt.setString(1,pNo);
				
				rs=pstmt.executeQuery();
				String[] sNos=null;
				int i=0;
				while(rs.next()) {
					String sNo=rs.getString(1);
					sNos[i]=sNo;
					i++;
				}
				return sNos;	
				
			} finally {
				if(pstmt!=null) pstmt.close();
			}
		}
}
