package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

	/*
	 * month : null, 1 mode : image, text genre : 뮤지컬, 연극, 콘서트 keyword : null, '공연명'
	 * startRow : endRow :
	 */

	// 조회 조건에 해당하는 공연 정보를 목록을 조회하다.(사용자)
	public List<PerformanceVO> selectPerformanceListByMember(HashMap<String, Object> map) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();

			String mode = (String) map.get("mod");

			// 이미지 보기 텍스트 보기
			if (mode.equals("image")) {
				sql.append(
						"select perf.title, pos.SYSTEM_FILE_NAME, perf.start_date, perf.end_date		 				");
				sql.append(
						"from (select rownum as rn, p.*  																	");
				sql.append(
						" from(select *   																						");
				sql.append(
						"from performance order by title asc) p) perf , poster pos															");
				sql.append(
						"where perf.p_no = pos.P_NO																						 	");
				sql.append(
						"and to_char(perf.start_Date,'YYMM')<=to_char(sysdate,'YY')||?  and to_char(perf.end_Date,'YYMM')>=to_char(sysdate,'YY')||?    ");
				sql.append(
						"and pos.main_poster = 1    																														 ");
				sql.append(
						"and perf.rn>=? and perf.rn<=? 																							");
			} else if (mode.equals("text")) {
				sql.append(
						"select distinct perf.title,perf.start_Date,perf.end_Date,t.t_Name										");
				sql.append("from (select rownum as rn, p.*   													");
				sql.append("from(select *  																");
				sql.append("from performance order by title asc) p) perf  , theater t , schedule s		");
				sql.append("where perf.p_no=s.p_no									");
				sql.append("and s.t_no=t.t_no															");
				sql.append(
						"and to_char(perf.start_Date,'YYMM')<=to_char(sysdate,'YY')||? and to_char(perf.end_Date,'YYMM')>=to_char(sysdate,'YY')||?  ");
				sql.append(
						"and perf.rn>=? and perf.rn<=? 																							");
			}

			// 장르 선택시
			String genre = (String) map.get("genre");
			if (genre.equals("뮤지컬")) {
				sql.append("and perf.genre_no='G002");

			} else if (genre.equals("연극")) {
				sql.append("and perf.genre_no='G001");

			} else if (genre.equals("콘서트")) {
				sql.append("and perf.genre_no='G003");
			}

			// 키워드 검색시
			String keyword = (String) map.get("keyword");
			if (keyword != null) {
				sql.append("and perf.title Like'%'|| ? || '%'  ");
			}

			pstmt = conn.prepareStatement(sql.toString());

			// 월 선택
			String month = (String) map.get("month");
			if (month != null) {
				pstmt.setString(1, month);
				pstmt.setString(2, month);

			} else {
				GregorianCalendar today = new GregorianCalendar();

				month = String.valueOf(today.get(today.MONTH) + 1);
				pstmt.setString(1, month);
				pstmt.setString(2, month);
			}

			int startRow = (Integer) map.get("startRow");
			int endRow = (Integer) map.get("endRow");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rs = pstmt.executeQuery(sql.toString());
			if (mode.equals("image")) {
				while (rs.next()) {
					PerformanceVO performance = new PerformanceVO();
					performance.setTitle(rs.getString(1));
					performance.setStartDate(rs.getString(3));
					performance.setEndDate(rs.getString(4));

					PosterVO poster = new PosterVO();
					ArrayList<PosterVO> posters = new ArrayList<PosterVO>();
					poster.setSystemFileName(rs.getString(2));
					posters.add(poster);
					performance.setPosters(posters);

					performances.add(performance);
				}
			} else if (mode.equals("text")) {
				while (rs.next()) {
					PerformanceVO performance = new PerformanceVO();
					performance.setTitle(rs.getString(1));
					performance.setStartDate(rs.getString(2));
					performance.setEndDate(rs.getString(3));
					performance.settName(rs.getString(4));

					performances.add(performance);
				}
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}

	// 공연 번호에 해당하는 공연 상세 정보를 조회하다.(파일들 ㅃㅐ고)
	public PerformanceVO selectPerformance(String pNo) throws Exception {
		PerformanceVO performance = new PerformanceVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sDate = "";
		String oTime = "";
		ScheduleVO schedule = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select performance.title, to_char(performance.start_date,'YYYY-MM-DD'), 		");
			sql.append(
					"to_char(performance.end_date,'YYYY-MM-DD'), theater.t_name, viewclass.view_class, performance.running_time,	");
			sql.append("performancegenre.genre, performance.price, to_char(schedule.s_date,'YYYY/MM/DD'), 	");
			sql.append(
					"to_char(orders.o_time,'HH24:MI') ,theater.t_no, 												 ");
			sql.append(
					"performance.contact_name, performance.CONTACT_NUMBER , performance.video, performance.production, performance.note	, performance.p_no, schedule.s_no, orders.o_no		");
			sql.append(
					"from poster,performance,schedule,orders,theater,viewclass,performancegenre,detailfile										");
			sql.append(
					"where poster.p_no=performance.P_NO																										");
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
					" order by performance.p_no asc, schedule.s_date asc, orders.o_no asc																");
			System.out.println(sql.toString());

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, pNo);

			rs = pstmt.executeQuery();

			int count = 1;
			while (rs.next()) {

				// 공연정보
				if (count == 1) {
					System.out.println("call 공연정보 등록");
					performance.setTitle(rs.getString(1));
					performance.setStartDate(rs.getString(2));
					performance.setEndDate(rs.getString(3));
					performance.settName(rs.getString(4));
					performance.setViewClass(rs.getString(5));
					performance.setRunningTime(rs.getInt(6));
					performance.setGenre(rs.getString(7));
					performance.setPrice(rs.getInt(8));
					performance.settNo(rs.getString(11));
					performance.setContactName(rs.getString(12));
					performance.setContactNumber(rs.getString(13));
					performance.setVideo(rs.getString(14));
					performance.setProduction(rs.getString(15));
					performance.setNote(rs.getString(16));
					performance.setpNo(rs.getString(17));

				}

				// 공연 일정
				if (rs.getString(10) != null) {
					if (schedule == null || !sDate.equals(rs.getString(9))) {
						schedule = new ScheduleVO();
						schedule.setsDate(rs.getString(9));
						schedule.setsNo(rs.getString(18));
						performance.addSchedule(schedule);
						sDate = rs.getString(9);
						System.out.println(rs.getString(9));
					}

					// 회차
					if (!oTime.equals(rs.getString(19))) {
						OrderVO order = new OrderVO();
						order.setoTime(rs.getString(10));
						schedule.addOrders(order);
						oTime = rs.getString(19);
						order.setoNo(rs.getString(19));
						System.out.println(rs.getString(19));
					}
				}

				count++;

				/*
				 * //상세 설명 if(!systemFileName.equals(rs.getString(12))) { //업로드된 파일이 여러개인 경우 DB에
				 * 한번 접근해서 모든 업로드된 파일 정보를 읽어오도록 DetailFileVO detailfile = new DetailFileVO();
				 * detailfile.setSystemFileName(rs.getString(12));
				 * performance.addDetailFile(detailfile); systemFileName = rs.getString(12); }
				 * 
				 * 
				 * 
				 * //포스터와 관련된 첨부파일이 있는 경우 if(!posterName.equals(rs.getString(9))) { //업로드된 파일이
				 * 여러개인 경우 DB에 한번 접근해서 모든 업로드된 파일 정보를 읽어오도록 System.out.println("call 포스터정보 등록");
				 * PosterVO poster = new PosterVO(); poster.setSystemFileName(rs.getString(9));
				 * performance.addPoster(poster); posterName = rs.getString(9); }
				 */
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return performance;
	}




	// 공연 번호에 해당하는 공연 상세 정보를 조회하다.(파일들만)
	public PerformanceVO selectFiles(String pNo) throws Exception {
		PerformanceVO performance = new PerformanceVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		int num = 0;
		String posterName = "";
		String[] files = { "", "", "", "" };
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select poster.system_file_Name, detailFile.system_File_Name				");
			sql.append("from poster,performance,detailFile											");
			sql.append(
					"where poster.p_no=performance.P_NO																		");
			sql.append(
					"and detailfile.p_no=performance.p_no																			");
			sql.append(
					"and performance.p_no=?																				");
			sql.append("order by 1 asc, 2 asc																");
			System.out.println(sql.toString());

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, pNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 상세 설명
				if (rs.getString(2) != null) {
					if (files != null) {
						for (int i = 0; i < files.length; i++) {
							String file = files[i];
							if (file.equals(rs.getString(2))) {
								count++;
							}
						}
					}

					if (count < 1 || files[0] == "") {
						System.out.println("~~~" + rs.getString(2));
						DetailFileVO detailfile = new DetailFileVO();
						detailfile.setSystemFileName(rs.getString(2));
						files[num] = rs.getString(2);
						num++;
						performance.addDetailFile(detailfile);
					}
					count = 0;
				}

				// 포스터와 관련된 첨부파일이 있는 경우
				if (!posterName.equals(rs.getString(1))) {

					System.out.println("call 포스터정보 등록");
					System.out.println("~~~" + rs.getString(1));
					PosterVO poster = new PosterVO();
					poster.setSystemFileName(rs.getString(1));
					performance.addPoster(poster);
					posterName = rs.getString(1);
				}

			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return performance;
	}

	// 공연 정보를 목록을 조회하다.(관리자)
	public List<PerformanceVO> selectPerformanceListByAdmin(int startRow, int endRow) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select p.p_no,p.title,to_char(p.start_Date,'YYYY/MM/DD'),to_char(p.end_Date,'YYYY/MM/DD'), pg.GENRE		");
			sql.append(
					"from (select rownum as rn, perf.*																		");
			sql.append(
					"from (select *																							");
			sql.append(
					"from performance order by p_no desc) perf) p, PERFORMANCEGENRE pg 										");
			sql.append(
					"where p.genre_no = pg.genre_no																			");
			sql.append(
					"and p.rn>=? and p.rn<=? 																				");
			pstmt = conn.prepareStatement(sql.toString());

			System.out.printf("startRow : %d, endRow : %d%n", startRow, endRow);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();
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
			if (pstmt != null)
				pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select distinct perf.p_no, perf.title,to_char(perf.start_Date, 'YY/MM/DD'),to_char(perf.end_Date, 'YY/MM/DD'),g.genre 	");
			sql.append("from (select rownum as rn, p.*													");
			sql.append("from(select *																	");
			sql.append("from performance order by title asc) p) perf, schedule s, performancegenre g	");
			sql.append("where perf.p_no=s.p_no															");
			sql.append("and perf.genre_no=g.genre_no													");
			// 제목 선택시
			if (keyfield.equals("title")) {
				sql.append("and title like '%' || ? || '%' 												");
				sql.append("order by 1 asc																");
				// 월 선택시
			} else if (keyfield.equals("date")) {
				sql.append("and to_char(perf.start_Date,'YYMM')<=to_char(sysdate,'YY')||? 				");
				sql.append("and to_char(perf.end_Date,'YYMM')>=to_char(sysdate,'YY')||?					");
				// sql.append("and perf.rn>=? and perf.rn<=? ");
				sql.append("order by 1 asc																");
				// 장르 선택시
			} else if (keyfield.equals("genre")) {
				sql.append("and genre like '%' || ? || '%' 												");
				sql.append("order by 1 asc																");
			}

			System.out.println(sql.toString());

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, keyword);
			if (keyfield.equals("date")) {
				pstmt.setString(2, keyword);
			}

			rs = pstmt.executeQuery();

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
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}

	// 공연 정보를 등록하다
	public String insertPerformance(PerformanceVO performance) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = DBConn.getConnection();

			sql.append(
					"insert into performance	(p_no,title,video,start_date,end_date,production,contact_name,contact_number, running_time,note,price,view_no,genre_no)								");
			sql.append("values('P'||lpad(performance_seq.nextVal,5,0),?,?,?,?,?,?,?,?,?,?,?,?) ");

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

			System.out.println("!!!!!!!!!!!!1" + performance.getGenreNo() + "~!!!!" + performance.getViewNo());
			pstmt.executeUpdate();

			pstmt.close();

			sql.delete(0, sql.length());

			stmt = conn.createStatement();

			sql.append("select 'P'||lpad(performance_seq.currVal,5,0) from dual ");

			ResultSet rs = stmt.executeQuery(sql.toString());

			String pNo = "";
			if (rs.next()) {
				pNo = rs.getString(1);
			}
			return pNo;

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

			rs = pstmt.executeQuery(sql.toString());

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

	// 총 게시글 수를 구하다.
	public int selectTotalPost() throws Exception {

		int totalPost = 0;
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from performance                       ");

			rs = stmt.executeQuery(sql.toString());

			if (rs.next()) {
				totalPost = rs.getInt(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return totalPost;
	}
/*
	//모든 공연의 제목 구하기
	public List<String> selectTitles() throws Exception{
		PreparedStatement pstmt=null;
		Connection conn=null;
		ResultSet rs=null;
		List<String> titles=new ArrayList<String>();
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select title ,     ");
			sql.append("from performance  ");
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				titles.add(title);
			}
			return titles;

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}
*/
	
	public List<PerformanceVO> selectPerformance() throws Exception {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select p_no, title ");
			sql.append("from performance ");
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PerformanceVO performance = new PerformanceVO();
				performance.setpNo(rs.getString(1));
				performance.setTitle(rs.getString(2));
				performances.add(performance);
			}
			return performances;

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}
	

	//  공연 정보 리스트를 조회하다(for 예매 페이지).
	public List<PerformanceVO> selectPerformanceList(int startRow, int endRow) throws Exception {
		ArrayList<PerformanceVO> performances = new ArrayList<PerformanceVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select pos.system_file_name, perf.title, perf.start_date, perf.end_date, perf.price, perf.pno	");
			sql.append(
					"from (select rownum as rn, p.* 									");
			sql.append(
					" from(select *      																					");
			sql.append(
					" from performance order by p_no desc) p) perf,																					");
			sql.append(
					" (select system_file_name,p_no,main_poster from poster where main_poster=1) pos																						");
			sql.append(
					"where perf.p_no=pos.p_no																							");
			sql.append("and rn >= ? and rn <= ?                                                       ");
			

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PosterVO poster = new PosterVO();
				poster.setSystemFileName(rs.getString(1));

				PerformanceVO performance = new PerformanceVO();
				performance.setTitle(rs.getString(2));
				performance.setStartDate(rs.getString(3));
				performance.setEndDate(rs.getString(4));
				performance.setPrice(rs.getInt(5));
				performance.setpNo(rs.getString(6));
				performances.add(performance);
			}
		} finally {
			if(rs!=null) rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return performances;
	}


}
