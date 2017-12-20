package model.dao.performance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import conn.DBConn;
import domain.performance.DetailFileVO;
import domain.performance.OrderVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import domain.performance.ScheduleVO;

public class PerformanceDAO {
	//�̱��� ����
	private static PerformanceDAO instance = new PerformanceDAO();
	
	public PerformanceDAO() {
		
	}
	
	public static PerformanceDAO getInstance() {
		return instance;
	}
	
	
	
	/*
	 *    month  :   null,   1
	 *    mode : image, text
	 *    genre :  ������, ����, �ܼ�Ʈ
	 *    keyword : null, '������'
	 *    startRow : 
	 *    endRow : 
	 */
	
	//��ȸ ���ǿ� �ش��ϴ� ���� ������ ����� ��ȸ�ϴ�.(�����)
	public List<PerformanceVO> selectPerformanceListByMember(HashMap<String, Object> map) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			
			String mode = (String)map.get("mod");
			
			if(mode.equals("image")) {
				sql.append("select perf.title, pos.SYSTEM_FILE_NAME, perf.start_date, perf.end_date		 		");
				sql.append("from (select rownum as rn, p.*  																	");
				sql.append(" from(select *   			");
				sql.append("from performance order by title asc) p) perf , poster pos															");
				sql.append("where perf.p_no = pos.P_NO 	");
				sql.append("and perf.rn>=1 and perf.rn<=9 		");
				sql.append("and to_char(perf.start_Date,'YYMM')<= ?  and to_char(perf.end_Date,'YYMM')>=?     ");
				sql.append("and pos.main_poster = 1     ");
			} else if(mode.equals("text")) {
				sql.append("select distinct performance.title,performance.start_Date,performance.end_Date,theater.t_Name 	");
				sql.append("from performance, poster, theater, schedule														");
				sql.append("where schedule.t_no = theater.t_no																");
				sql.append("and to_char(performance.start_Date,'MM') <= ? and to_char(performance.start_Date,'MM') >= ?		");
				sql.append("and to_char(performance.end_Date,'MM') <= ? and to_char(performance.end_Date,'MM') <= ?			");
				sql.append("and performance.p_no = schedule.p_no															");
			}
			
			String genre = (String)map.get("genre");
			
			if(genre.equals("������")) {
				sql.append("and perf.genre_no='G002"     );
				
			} else if(genre.equals("����")) {
				sql.append("and perf.genre_no='G001"     );	
				
			} else  if(genre.equals("�ܼ�Ʈ")) {				
				sql.append("and perf.genre_no='G003"     );
			}
			
			
			String keyword = (String)map.get("keyword");
			
			if(keyword != null) {
				sql.append("and perf.title Like'%'|| ? || '%'  ");
			} 
			
			pstmt=conn.prepareStatement(sql.toString());
			
			String month= (String)map.get("month");
			
			if(month != null) {
				pstmt.setString(1, month);
				
			} else {
				
				
			}
			

			rs = pstmt.executeQuery(sql.toString());
			while(rs.next()) {
				PerformanceVO performance = new PerformanceVO();
				performance.setTitle(rs.getString(1));
				performance.setStartDate(rs.getString(2));					
				performance.setEndDate(rs.getString(3));
				performance.settName(rs.getString(4));
					
				PosterVO poster = new PosterVO();
				poster.setPosterNo(rs.getString(5));
					
				performances.add(performance);
			}
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return performances;
	}
	
	//���� ��ȣ�� �ش��ϴ� ���� �� ������ ��ȸ�ϴ�.
	public PerformanceVO selectPerformance(String pNo) throws Exception {
		PerformanceVO performance = new PerformanceVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;				
		try {
			conn = DBConn.getConnection();
					
			StringBuffer sql = new StringBuffer();
			sql.append("select performance.title,performance.start_date,performance.end_date,theater.t_name,viewclass.view_class,performance.running_time,	");
			sql.append("performancegenre.genre,performance.price,poster.system_file_name,schedule.s_date,orders.o_time,detailfile.system_file_name	,performance.t_no		");
			sql.append("from poster,performance,schedule,orders,theater,viewclass,performancegenre,detailfile												");
			sql.append("where poster.p_no=performance.P_NO																									");
			sql.append("and performance.P_No=schedule.p_no(+)																								");
			sql.append("and theater.T_NO=schedule.T_NO																										");
			sql.append("and orders.s_no=schedule.s_no																										");
			sql.append("and viewclass.VIEW_NO=performance.VIEW_NO																							");
			sql.append("and performancegenre.GENRE_NO=performance.GENRE_NO																					");
			sql.append("and detailfile.p_no=performance.p_no																								");
			sql.append("and performance.p_no=?																												");
			sql.append(" order by performance.p_no asc,schedule.s_date asc, orders.o_time asc																");
			pstmt = conn.prepareStatement(sql.toString());
					
			pstmt.setString(1, pNo);
					
			rs = pstmt.executeQuery();
					
			int count = 1;
			String systemFileName = "";
			String posterName = "";
			String sDate = "";					
			while(rs.next()) {
				if(count == 1) {	//���������� �ϳ��� ���
					performance.setTitle(rs.getString(1));
					performance.setStartDate(rs.getString(2));
					performance.setEndDate(rs.getString(3));
					performance.settName(rs.getString(4));
					performance.setViewClass(rs.getString(5));
					performance.setRunningTime(rs.getInt(6));
					performance.setGenre(rs.getString(7));
					performance.setPrice(rs.getInt(8));
					performance.settNo(rs.getString(13));
				}
				
				//�� ����	
				if(!systemFileName.equals(rs.getString(12)))  {
					//���ε�� ������ �������� ��� DB�� �ѹ� �����ؼ� ��� ���ε�� ���� ������ �о������
					DetailFileVO detailfile = new DetailFileVO();
					detailfile.setSystemFileName(rs.getString(12));
					performance.addDetailFile(detailfile);
					systemFileName = rs.getString(12);										
				}
				
					
				//�����Ϳ� ���õ� ÷�������� �ִ� ���
				if(!posterName.equals(rs.getString(9))) {
					//���ε�� ������ �������� ��� DB�� �ѹ� �����ؼ� ��� ���ε�� ���� ������ �о������
					PosterVO poster = new PosterVO();
					poster.setSystemFileName(rs.getString(9));					
					performance.addPoster(poster);
					posterName = rs.getString(9);
				}
				
				//����
				ScheduleVO schedule = null;
				if(rs.getString(10) != null  && !sDate.equals(rs.getString(10))) {					
					schedule = new ScheduleVO();
					schedule.setsDate(rs.getString(10));					
					performance.addSchedule(schedule);
					sDate = rs.getString(10);
				}
				
				//ȸ��					
				//DB�� ����
				OrderVO order = new OrderVO();
				order.setoTime(rs.getString(11));
				schedule.addOrders(order);
				
				count++;
			}
			
		} finally {
			if(pstmt == null) pstmt.close();
			if(conn == null) conn.close();
		}
		
		return performance;			
	}

	

	//���� ������ ����� ��ȸ�ϴ�.(������)
	public List<PerformanceVO> selectPerformanceListByAdmin(int startRow, int endRow) throws Exception {
			ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBConn.getConnection();
				stmt = conn.createStatement();
				StringBuffer sql = new StringBuffer();
				sql.append("select select performance.p_no,performance.title,performance.start_Date,performance.end_Date,performancegenre.genre	");
				sql.append("from performance, genre																								");
				sql.append("where performance.genre_no=performancegenre.genre_no																");
				rs = stmt.executeQuery(sql.toString());
				while(rs.next()) {
					PerformanceVO performance = new PerformanceVO();
					performance.setpNo(rs.getString(1));
					performance.setTitle(rs.getString(2));
					performance.setStartDate(rs.getString(3));
					performance.setEndDate(rs.getString(4));
					performance.setGenre(rs.getString(5));
					performances.add(performance);
				}
			} finally {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}
			return performances;
		}
		
	//�˻� ���ǿ� �ش��ϴ� ������ ��ȸ�Ѵ�.(������)
	public List<PerformanceVO> searchPerformance(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct performance.title,performance.start_Date,performance.end_Date,theater.t_Name,poster.system_file_name 	");
			sql.append("from performance, poster, theater, schedule																				");
			sql.append("where performance.p_no = poster.p_no																					");
			sql.append("and schedule.t_no = theater.t_no																						");
			sql.append("and performance.p_no = schedule.p_no																					");
			if (keyfield.equals("title")) {
				sql.append("and title like '%' || ? || '%' 																						");
				sql.append("order by performance.title asc;																										");
			} else if (keyfield.equals("date")) {
				sql.append("and to_char(performance.start_Date,'MM') <= ? and to_char(performance.start_Date,'MM') >= ?							");
				sql.append("and to_char(performance.end_Date,'MM') <= ? and to_char(performance.end_Date,'MM') <= ?								");
				sql.append("order by performance.start_Date asc;																										");
			} else if (keyfield.equals("genre")) {
				sql.append("and genre like '%' || ? || '%' 																						");
				sql.append("order by 1 asc;																										");
			}
			
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				PerformanceVO performance = new PerformanceVO();
				performance.setTitle(rs.getString(1));
				performance.setStartDate(rs.getString(2));
				performance.setEndDate(rs.getString(3));
				performance.settName(rs.getString(4));
					
				PosterVO poster = new PosterVO();
				poster.setSystemFileName(rs.getString(5));
				
				performances.add(performance);
			}
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return performances;
	}

	//���� ������ ����ϴ�
	public String insertPerformance(PerformanceVO performance) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DBConn.getConnection();
			
			sql.append("insert into performance									");
			sql.append("values('p'||lpad(performance_seq.nextVal,5,0),?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, performance.getTitle());
			pstmt.setString(2, performance.getVideo());
			pstmt.setString(3, performance.getStartDate());
			pstmt.setString(4, performance.getEndDate());
			pstmt.setString(5, performance.getProduction());
			pstmt.setString(6, performance.getContactName());
			pstmt.setString(7, performance.getContactNumber());
			pstmt.setInt(8, performance.getRunningTime());
			pstmt.setString(9, performance.getNote());
			pstmt.setInt(10, performance.getPrice());
			pstmt.setString(11, performance.getViewNo());
			pstmt.setString(12, performance.getGenreNo());
			
			pstmt.executeUpdate();
			
			return performance.getpNo();
		
		} finally {
			if(pstmt != null) pstmt.close();	
		}
	}
	
	//������ ���� ���θ� Ȯ���Ѵ�.
	public boolean isReservaedPerformance(String pNo) throws Exception {
		
		boolean flag = false;	//���ŵǾ����� not null, ���ŵ��� �ʾ����� null
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			sql.append("select reservation.r_no							");
			sql.append("from performance,schedule,orders,reservation	");
			sql.append("where performance.p_no=schedule.p_no			");
			sql.append("and schedule.s_no=orders.s_no					");
			sql.append("and orders.o_no=reservation.o_no				");
			sql.append("and performance.p_no=?							");
			
			pstmt.setString(1, pNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(1) != null) {
					flag = true;
				} else if(rs.getString(1) == null) {
					flag = false;
				}
			} 
		} finally {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
		}
			
		return flag;
		 
			
	}
	
	//���� ������ �����Ѵ�.
	public void deletePerformance(String pNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			conn = DBConn.getConnection();
			sql.append("delete from performance ");
			sql.append("where pNo=? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, pNo);
			
			pstmt.executeUpdate();		
			
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
	
	//���� ������ �����Ѵ�.
	public void updatePerformance(PerformanceVO performance) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		try {
			conn = DBConn.getConnection();
			sql.append("update performance set title=?,video=?,start_date=?,end_date=?,production=?,													");
			sql.append("genre_no=(select genre_no from performancegenre where genre=?),view_no=(select view_no from viewclass where view_class=?),		");
			sql.append("contact_name=?,contact_number=?,note=?,running_time=?																			");
			sql.append("where pNo=?																														");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, performance.getpNo());
			pstmt.setString(2, performance.getTitle());
			pstmt.setString(3, performance.getVideo());
			pstmt.setString(4, performance.getStartDate());
			pstmt.setString(5, performance.getEndDate());
			pstmt.setString(6, performance.getProduction());
			pstmt.setString(7, performance.getGenreNo());
			pstmt.setString(8, performance.getViewNo());
			pstmt.setString(9, performance.getContactName());
			pstmt.setString(10, performance.getContactNumber());
			pstmt.setString(11, performance.getNote());
			pstmt.setInt(12, performance.getRunningTime());
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}	
}
