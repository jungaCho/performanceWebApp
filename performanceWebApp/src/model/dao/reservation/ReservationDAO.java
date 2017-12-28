package model.dao.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.reservation.ReservationVO;
import domain.reservation.ReservedSeatVO;
import domain.reservation.SeatVO;
import domain.reservation.TotalInfoVO;

public class ReservationDAO {

	// 싱글톤 패턴
	private static ReservationDAO instance = new ReservationDAO();

	private ReservationDAO() {

	}

	public static ReservationDAO getInstance() {
		return instance;
	}

	// 예매된 좌석 정보를 일괄등록하다
	public void insertReservedSeat(Connection conn, List<ReservedSeatVO> seats) throws Exception {
		PreparedStatement pstmt = null;

		try {

			StringBuffer sql = new StringBuffer();

			sql.append("insert into reserved_seat(seat_no, r_no)  ");
			sql.append("values(?, (select max(r_no) from reservation)) ");
			pstmt = conn.prepareStatement(sql.toString());

			for (ReservedSeatVO seat : seats) {
				pstmt.setString(1, seat.getSeatNo());
				pstmt.addBatch();
			}

			pstmt.executeBatch();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
	}

	// 예매상태의 취소 여부를 확인하다
	public boolean checkCanceledReservation(Connection conn, String rNo) throws Exception {

		boolean flag = false; // 이미 취소되었으면 0, 취소 안됐으면 1
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select r_status from reservation      ");
			sql.append("where r_no = ?                        ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, rNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals('0')) {
					flag = true;
				} else if (rs.getString(1).equals('1')) {
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

	// 환불 정보를 등록하다.
	public void insertRefund(Connection conn, String rNo) throws Exception {

		PreparedStatement pstmt = null;
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("insert into refund(refund_no, refund_date, refund_value, r_no)                 ");
			sql.append("select to_char(sysdate, 'YYYYMMDD') || lpad(refund_seq.nextval, 6, '0'),   ");
			sql.append("to_char(sysdate, 'YYYY/MM/DD'), total_price, r_no                                 ");
			sql.append("from reservation                                                                               ");
			sql.append("where r_no = ?                                                                                 ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, rNo);
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	// 검색조건에 해당하는 전체 회원의 예매내역을 조회한다. (관리자)
	public List<TotalInfoVO> selectReservationListByAdmin(int startRow, int endRow)
			throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TotalInfoVO> totalInfos = new ArrayList<TotalInfoVO>();
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("select r_no, r_status, r_date, card_number, approve_number, total_price, cardco_name,                    ");
			sql.append(" m_id, title, s_date, o_time, t_name, seat_no                    ");
			sql.append("from ( select ROWNUM as rn, rs.*                                       ");
			sql.append("from (select res.r_no, res.r_status, to_char(res.r_date, 'YYYY/MM/DD') as r_date, res.card_number,             ");
			sql.append("res.approve_number, res.total_price, card.cardco_name, mem.m_id, perf.title,                  ");
			sql.append("to_char(sch.s_date, 'YYYY/MM/DD') as s_date, to_char(ord.o_time, 'HH24:MI') as o_time, t.t_name,               ");
			sql.append("(select listagg(rseat.seat_no, ',') within group (order by rseat.seat_no ) from reserved_seat rseat where  rseat.r_no = res.r_no) as seat_no             ");
			sql.append("from reservation res, card_company card, member mem,                           ");
			sql.append("orders ord, schedule sch,  performance perf,  theater t                            ");
			sql.append("   where res.o_no = ord.o_no  and ord.s_no = sch.s_no  and sch.t_no = t.t_no  and sch.p_no = perf.p_no                                                      ");
			sql.append("  and res.cardco_no = card.cardco_no  and res.m_no = mem.m_no                        ");
			sql.append("order by r_no desc ) rs ) res                                          ");
			sql.append("where res.rn >= ? and res.rn <= ?                                   ");
			pstmt = conn.prepareStatement(sql.toString());
			/*pstmt.setString(1, keyword);*/
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TotalInfoVO totalInfo = new TotalInfoVO();

				if (totalInfo != null) {

					totalInfo.setrNo(rs.getString(1));
					totalInfo.setrStatus(rs.getString(2));
					totalInfo.setrDate(rs.getString(3));
					totalInfo.setCardNumber(rs.getString(4));
					totalInfo.setApproveNumber(rs.getString(5));
					totalInfo.setTotalPrice(rs.getInt(6));
					totalInfo.setCardCoName(rs.getString(7));
					totalInfo.setmId(rs.getString(8));
					totalInfo.setTitle(rs.getString(9));
					totalInfo.setsDate(rs.getString(10));
					totalInfo.setoTime(rs.getString(11));
					totalInfo.settName(rs.getString(12));
				}

				if (rs.getString(13) != null) {
					ReservedSeatVO seat = new ReservedSeatVO();
					seat.setrNo(rs.getString(1));
					seat.setSeatNo(rs.getString(2));

					totalInfo.addSeats(seat);
				}

				totalInfos.add(totalInfo);
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}
		return totalInfos;
	}

	// 예매정보를 등록한다.
	public ReservationVO insertReservation(Connection conn, ReservationVO reservation) throws Exception {

		PreparedStatement pstmt = null;	
		Statement stmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into reservation(r_no , r_date , card_number , total_price , approve_number , m_no , o_no , cardco_no)                           ");
			sql.append("values('R'||to_char(sysdate,'YYMMDD') || lpad(reservation_seq.nextval,6,'0'), to_char(sysdate,'YYYY/MM/DD'),                    ");
			sql.append("? , ? , lpad(round(dbms_random.value(0,999999),0),6,'0'), ? , ?, ?)                                                                                       ");
		
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, reservation.getCardNumber());
			pstmt.setInt(2, reservation.getTotalPrice());
			pstmt.setString(3, reservation.getmNo());
			pstmt.setString(4, reservation.getoNo());
			pstmt.setInt(5,reservation.getCardCoNo());

			pstmt.executeUpdate();
			pstmt.close();

			stmt = conn.createStatement();

			sql.delete(0, sql.length());
			sql.append("select ('R'||to_char(sysdate,'YYMMDD') || lpad(reservation_seq.currval,6,'0')),lpad(round(dbms_random.value(0,999999),0),6,'0') from dual                                                      ");

			ResultSet rs = stmt.executeQuery(sql.toString());
			if (rs.next()) {
				reservation.setrNo(rs.getString(1));
				reservation.setApproveNumber(rs.getString(2));				
			}

		} finally {
			if (stmt != null) stmt.close();
		}

		return reservation;
	}

	// 환불 정보를 등록한다. (취소일자 등록, 예매상태 변경)
	public void updateReservation(String rNo, Connection conn) throws Exception {

		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"update reservation                                                                                                    ");
			sql.append(
					"set r_Status = '0'  ,  cancel_date = to_char(sysdate,'YYYY/MM/DD')                                  ");
			sql.append(
					"where r_no = ?                                                                                                         ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, rNo);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}

	}

	// 예매된 좌석 정보를 삭제한다.
	public void deleteReservedSeat(String rNo, Connection conn) throws Exception {

		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from reserved_seat                                            ");
			sql.append("where r_No = ?                                                           ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, rNo);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}

	}

	// 검색조건에 해당하는 회원의 예매 내역을 조회한다. (특정회원)

	public List<TotalInfoVO> selectReservationListByMember(Connection conn, String keyfield,
															String keyword, String mNo,	int startRow, int endRow) throws Exception {

		List<TotalInfoVO> totalInfos = new ArrayList<TotalInfoVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select r.r_no , r.r_status, to_char(r.r_date,'YYYY/MM/DD'), r.card_number, r.approve_number, r.total_price,                             ");
			sql.append(
					"c.cardCo_name, m.m_id, p.title, to_char(s.s_date,'YYYY/MM/DD'), to_char(o.o_time,'HH24:MI') , t.t_name , rs.seat_no        			");
			sql.append(
					"from reservation r, card_company c, member m , performance p, schedule s, orders o , theater t,  reserved_seat rs                 			");
			sql.append(
					"where r.o_no = o.o_no                                                                                                        ");
			sql.append(
					"and r.cardCo_no = c.cardCo_no                                                                                                 ");
			sql.append(
					"and r.m_no = m.m_no                                                                                                                ");
			sql.append(
					"and o.s_no = s.s_no                                                                                                                        ");
			sql.append(
					"and s.p_no = p.p_no                                                                                                                       ");
			sql.append(
					"and s.t_no = t.t_no                                                                                                                         ");
			sql.append(
					"and r.r_no = rs.r_no                                                                                                                       ");
			if (keyfield.equals("title")) {
				sql.append("and p.title like '%' || ? || '%'                                                                                                ");
			} else if (keyfield.equals("sDate")) {
				sql.append("and s.s_date like '%' || ? ||'%'                                                                                                     ");
			} else if (keyfield.equals("rStatus")) {
				sql.append("and r.r_status like '%' || ? || '%'                                                                                             ");
			}
			sql.append(
					"m.m_no = ?																														");
			sql.append(
					"order by r.r_no desc                                                                                                                ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, keyword);
			pstmt.setString(2, mNo);

			rs = pstmt.executeQuery();

			String rNo = "";
			while (rs.next()) {
				TotalInfoVO totalInfo = null;
				if (!rNo.equals(rs.getString(1))) {
					totalInfo = new TotalInfoVO();

					totalInfo.setrNo(rs.getString(1));
					totalInfo.setrStatus(rs.getString(2));
					totalInfo.setrDate(rs.getString(3));
					totalInfo.setCardNumber(rs.getString(4));
					totalInfo.setApproveNumber(rs.getString(5));
					totalInfo.setTotalPrice(rs.getInt(6));
					totalInfo.setCardCoName(rs.getString(7));
					totalInfo.setmId(rs.getString(8));
					totalInfo.setTitle(rs.getString(9));
					totalInfo.setsDate(rs.getString(10));
					totalInfo.setoTime(rs.getString(11));
					totalInfo.settName(rs.getString(12));

					totalInfos.add(totalInfo);

				}

				if (rs.getString(13) != null) {
					ReservedSeatVO seat = new ReservedSeatVO();
					seat.setrNo(rs.getString(1));
					seat.setSeatNo(rs.getString(2));
					totalInfo.addSeats(seat);
				}

			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}
		return totalInfos;
	}

	// 특정 공연회차의 예매된 좌석을 조회한다.
	public List<ReservedSeatVO> selectReservedSeat(String oNo) throws Exception {
		System.out.println("회차번호 : " + oNo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservedSeatVO> rSeats = new ArrayList<ReservedSeatVO>();

		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select s.seat_no, s.seat_number 	 					                                                             ");
			sql.append("from reserved_seat  rs , reservation r , seat s  			                                    ");
			sql.append("where rs.r_no = r.r_no                                                                               ");
			sql.append("and rs.seat_no = s.seat_no                                                                               ");
			sql.append("and r.o_no = ?                                                                                          ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, oNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReservedSeatVO rSeat = new ReservedSeatVO();
				rSeat.setSeatNo(rs.getString(1));
				rSeat.setSeatNumber(rs.getString(2));
				rSeats.add(rSeat);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		
		}

		return rSeats;

	}

	// 특정 공연장의 모든 좌석을 조회한다.
	public List<SeatVO> selectAllSeat(String tNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<SeatVO> seats = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("select seat_no, seat_number          ");
			sql.append("from seat                                    ");
			sql.append("where t_no = ?                              ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				SeatVO seat = new SeatVO();
				seat.setSeatNo(rs.getString(1));
				seat.setSeatNumber(rs.getString(2));
				seats.add(seat);

			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return seats;
	}
	
	
	//총 게시글 수를 구하다.
	public int selectTotalPost() throws Exception {
		int totalPost = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append("select count(*)	from reservation");
			
			
			rs = stmt.executeQuery(sql.toString());

			if(rs.next()) {
				totalPost = rs.getInt(1);
			}
		} finally {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}
		return totalPost;
	}

	public List<TotalInfoVO> selectReservationListByMember(Connection conn, String mNo,	int startRow, int endRow) throws Exception {
		List<TotalInfoVO> totalInfos = new ArrayList<TotalInfoVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select r.r_no, to_char(r.r_date,'yyyy/mm/dd'), p.title , to_char(s.s_date,'yyyy/mm/dd'), r.r_status		");
		/*	sql.append("	  ,r.card_number, r.approve_number, r.total_price,	c.cardCo_name, m.m_id, t.t_name, rs.seat_no		");
			sql.append("	   to_char(o.o_time,'HH24:MI')																		");*/
			sql.append("from (select rownum as rn, totalinfo1.*																	");
			sql.append("	 from (select *																						");
			sql.append("		   from reservation																				");
			sql.append("		   order by r_no desc) totalinfo1) r,									 							");
			sql.append("	 ,card_company c, member m , performance p,															");
			sql.append("	 schedule s, orders o , theater t,  reserved_seat rs 												");
			sql.append("where r.o_no = o.o_no																					");
			sql.append("and r.cardCo_no = c.cardCo_no																			");
			sql.append("and r.m_no = m.m_no																						");
			sql.append("and o.s_no = s.s_no																						");
			sql.append("and s.p_no = p.p_no																						");
			sql.append("and s.t_no = t.t_no																						");
			sql.append("and r.r_no = rs.r_no																					");
			/*if(keyfield.equals("title")) {
				sql.append("and p.title like '%' || ? || '%'																	");
			} else if (keyfield.equals("sDate")) {
				sql.append("and s.s_date like '%' || ? || '%'																	");
			} else if (keyfield.equals("rStatus")) {
				sql.append("and r.r_status like '%' || ? || '%'																	");
			}*/
			sql.append("and m.m_no = ?																							");
			sql.append("and rn >= ? and rn <= ?																					");
			sql.append("order by r.r_no desc																					");
			
			pstmt = conn.prepareStatement(sql.toString());
			/*pstmt.setString(1, keyword);*/
			pstmt.setString(1, mNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TotalInfoVO	totalInfo = new TotalInfoVO();
				totalInfo.setrNo(rs.getString(1));
				totalInfo.setrDate(rs.getString(2));
				totalInfo.setTitle(rs.getString(3));
				totalInfo.setsDate(rs.getString(4));
				totalInfo.setrStatus(rs.getString(5));
				/*
				totalInfo.setCardNumber(rs.getString(6));
				totalInfo.setApproveNumber(rs.getString(7));
				totalInfo.setTotalPrice(rs.getInt(8));
				totalInfo.setCardCoName(rs.getString(9));
				totalInfo.setmId(rs.getString(10));
				totalInfo.settName(rs.getString(11));
				totalInfo.setoTime(rs.getString(13));
				
				if (rs.getString(12) != null) {
					ReservedSeatVO seat = new ReservedSeatVO();
					seat.setrNo(rs.getString(1));
					seat.setSeatNo(rs.getString(12));
					totalInfo.addSeats(seat);
				}*/
				totalInfos.add(totalInfo);
			}
			return totalInfos;		
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
		}
		
	}
	


}
