package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.performance.DetailFileVO;
import domain.performance.OrderVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import domain.performance.ScheduleVO;

public class PerformanceDAO {
	// 싱글톤 패턴
	private static PerformanceDAO instance = new PerformanceDAO();

	public PerformanceDAO() {

	}

	public static PerformanceDAO getInstance() {
		return instance;
	}

	// 조회 조건에 해당하는 공연 정보를 목록을 조회하다.(사용자)
	public List<PerformanceVO> selectPerformanceListByMember(String filter, String keyword, int startRow, int endRow)
			throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			if (filter.equals("image")) {
				sql.append(
						"select poster.system_file_name,performance.start_Date,performance.end_Date				 		");
				sql.append(
						"from performance,poster																			");
				sql.append(
						"where performance.p_no=poster.p_no																");
				sql.append(
						"and to_char(performance.start_Date,'MM') <= ? and to_char(performance.start_Date,'MM') >= ?		");
				sql.append(
						"and to_char(performance.end_Date,'MM') <= ? and to_char(performance.end_Date,'MM') <= ?			");
			} else if (filter.equals("text")) {
				sql.append(
						"select distinct performance.title,performance.start_Date,performance.end_Date,theater.t_Name 	");
				sql.append(
						"from performance, poster, theater, schedule														");
				sql.append(
						"where schedule.t_no = theater.t_no																");
				sql.append(
						"and to_char(performance.start_Date,'MM') <= ? and to_char(performance.start_Date,'MM') >= ?		");
				sql.append(
						"and to_char(performance.end_Date,'MM') <= ? and to_char(performance.end_Date,'MM') <= ?			");
				sql.append(
						"and performance.p_no = schedule.p_no															");
				if (filter.equals("genre")) {
					sql.append(
							"and genre like '%' || ? || '%' 																");
				}
				sql.append(
						"order by performance.title asc;																					");
			}
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}

	// 공연 번호에 해당하는 공연 상세 정보를 조회하다.
	public PerformanceVO selectPerformance(String pNo) throws Exception {
		PerformanceVO performance = new PerformanceVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select performance.title,performance.start_date,performance.end_date,theater.t_name,viewclass.view_class,performance.running_time,	");
			sql.append(
					"performancegenre.genre,performance.price,poster.system_file_name,schedule.s_date,orders.o_time,detailfile.system_file_name	,theater.t_no		");
			sql.append(
					"from poster,performance,schedule,orders,theater,viewclass,performancegenre,detailfile												");
			sql.append(
					"where poster.p_no=performance.P_NO																									");
			sql.append(
					"and performance.P_No=schedule.p_no(+)																								");
			sql.append(
					"and theater.T_NO=schedule.T_NO																										");
			sql.append(
					"and orders.s_no=schedule.s_no																										");
			sql.append(
					"and viewclass.VIEW_NO=performance.VIEW_NO																							");
			sql.append(
					"and performancegenre.GENRE_NO=performance.GENRE_NO																					");
			sql.append(
					"and detailfile.p_no=performance.p_no																								");
			sql.append(
					"and performance.p_no=?																												");
			sql.append(
					" order by performance.p_no asc,schedule.s_date asc, orders.o_time asc																");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, pNo);

			rs = pstmt.executeQuery();

			int count = 1;
			String systemFileName = "";
			String posterName = "";
			String sDate = "";
			while (rs.next()) {
				if (count == 1) { // 공연정보가 하나인 경우
					performance.setTitle(rs.getString(1));
					performance.setStartDate(rs.getString(2));
					performance.setEndDate(rs.getString(3));
					performance.settName(rs.getString(4));
					performance.setViewClass(rs.getString(5));
					performance.setRunningTime(rs.getInt(6));
					performance.setGenre(rs.getString(7));
					performance.setPrice(rs.getInt(8));
					//performance.settNo(rs.getString(13));
				}

				// 상세 설명
				if (!systemFileName.equals(rs.getString(12))) {
					// 업로드된 파일이 여러개인 경우 DB에 한번 접근해서 모든 업로드된 파일 정보를 읽어오도록
					DetailFileVO detailfile = new DetailFileVO();
					detailfile.setSystemFileName(rs.getString(12));
					performance.addDetailFile(detailfile);
					systemFileName = rs.getString(12);
				}

				// 포스터와 관련된 첨부파일이 있는 경우
				if (!posterName.equals(rs.getString(9))) {
					// 업로드된 파일이 여러개인 경우 DB에 한번 접근해서 모든 업로드된 파일 정보를 읽어오도록
					PosterVO poster = new PosterVO();
					poster.setSystemFileName(rs.getString(9));
					performance.addPoster(poster);
					posterName = rs.getString(9);
				}

				// 일정
				ScheduleVO schedule = null;
				if (rs.getString(10) != null) {
					schedule = new ScheduleVO();
					schedule.setsDate(rs.getString(10));
					performance.addSchedule(schedule);
					sDate = rs.getString(10);
				}

				// 회차
				// DB에 접근
				OrderVO order = new OrderVO();
				order.setoTime(rs.getString(11));
				schedule.addOrders(order);

				count++;
			}

		} finally {
			if (pstmt == null)
				pstmt.close();
			if (conn == null)
				conn.close();
		}

		return performance;
	}

	// 공연 정보를 목록을 조회하다.(관리자)
	public List<PerformanceVO> selectPerformanceListByAdmin(int startRow, int endRow) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select select performance.p_no,performance.title,performance.start_Date,performance.end_Date,performancegenre.genre	");
			sql.append(
					"from performance, genre																								");
			sql.append(
					"where performance.genre_no=performancegenre.genre_no																");
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				PerformanceVO performance = new PerformanceVO();
				performance.setpNo(rs.getString(1));
				performance.setTitle(rs.getString(2));
				performance.setStartDate(rs.getString(3));
				performance.setEndDate(rs.getString(4));
				performance.setGenre(rs.getString(5));
				performances.add(performance);
			}
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}

	// 검색 조건에 해당하는 정보를 조회한다.(관리자)
	public List<PerformanceVO> searchPerformance(String keyfield, String keyword, int startRow, int endRow)
			throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select distinct performance.title,performance.start_Date,performance.end_Date,theater.t_Name,poster.system_file_name 	");
			sql.append(
					"from performance, poster, theater, schedule																				");
			sql.append(
					"where performance.p_no = poster.p_no																					");
			sql.append(
					"and schedule.t_no = theater.t_no																						");
			sql.append(
					"and performance.p_no = schedule.p_no																					");
			if (keyfield.equals("title")) {
				sql.append(
						"and title like '%' || ? || '%' 																						");
				sql.append(
						"order by performance.title asc;																										");
			} else if (keyfield.equals("date")) {
				sql.append(
						"and to_char(performance.start_Date,'MM') <= ? and to_char(performance.start_Date,'MM') >= ?							");
				sql.append(
						"and to_char(performance.end_Date,'MM') <= ? and to_char(performance.end_Date,'MM') <= ?								");
				sql.append(
						"order by performance.start_Date asc;																										");
			} else if (keyfield.equals("genre")) {
				sql.append(
						"and genre like '%' || ? || '%' 																						");
				sql.append(
						"order by 1 asc;																										");
			}

			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}

	// 공연 정보를 등록하다
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
			if (pstmt != null)
				pstmt.close();
		}
	}

	// 공연의 예매 여부를 확인한다.
	public boolean isReservaedPerformance(String pNo) throws Exception {

		boolean flag = false; // 예매되었으면 not null, 예매되지 않았으면 null
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

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1) != null) {
					flag = true;
				} else if (rs.getString(1) == null) {
					flag = false;
				}
			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}

		return flag;

	}

	// 공연 정보를 삭제한다.
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
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	// 공연 정보를 수정한다.
	public void updatePerformance(PerformanceVO performance) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		try {
			conn = DBConn.getConnection();
			sql.append(
					"update performance set title=?,video=?,start_date=?,end_date=?,production=?,													");
			sql.append(
					"genre_no=(select genre_no from performancegenre where genre=?),view_no=(select view_no from viewclass where view_class=?),		");
			sql.append(
					"contact_name=?,contact_number=?,note=?,running_time=?																			");
			sql.append(
					"where pNo=?																														");

			pstmt = conn.prepareStatement(sql.toString());

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
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}
}
