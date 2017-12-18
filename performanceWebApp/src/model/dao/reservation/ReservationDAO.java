package model.dao.reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.reservation.TotalInfoVO;

public class ReservationDAO {

	//�̱��� ����
	private static ReservationDAO instance = new ReservationDAO();
	
	private ReservationDAO() {
		
	}
	
	public static ReservationDAO getInstance() {
		return instance;
	}
	
	//���ŵ� �¼� ������ ����ϴ�
		public void insertReservedSeat(Connection conn, String rNo, String seat) throws Exception {
			PreparedStatement pstmt = null;
			
			try {
				
				StringBuffer sql = new StringBuffer();
				
				
				sql.append("insert into reserved_seat(seat_no, r_no)  ");
				sql.append("values(?, ?)                              ");
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, rNo);
				pstmt.setString(2, seat);
				pstmt.executeUpdate();
				
				
			} finally {
				if(pstmt != null) pstmt.close();
			}
			
		}
		
		//���Ż����� ��� ���θ� Ȯ���ϴ�
		public boolean checkCanceledReservation(Connection conn, String rNo) throws Exception {
			
			boolean flag = false;	//�̹� ��ҵǾ����� 0, ��� �ȵ����� 1
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("select r_status from reservation      ");
				sql.append("where r_no = ?                        ");
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, rNo);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getString(1).equals('0')) {
						flag = true;
					} else if(rs.getString(1).equals('1')) {
						flag = false;
					}
				}
			} finally {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}
			
			return flag;
		} 
		
		
		//ȯ�� ������ ����ϴ�.
		public void insertRefund(Connection conn, String rNo) throws Exception {
			
			PreparedStatement pstmt = null;
			try {
			
				StringBuffer sql = new StringBuffer();
				sql.append("insert into refund(refund_no, refund_date, refund_value, r_no)            ");
				sql.append("select to_char(sysdate, 'YYYYMMDD') || lpad(refund_seq.nextval, 6, '0'),  ");
				sql.append("to_char(sysdate, 'YYYY/MM/DD'), total_price, r_no                         ");
				sql.append("from reservation                                                          ");
				sql.append("where r_no = ?                                                            ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, rNo);
				pstmt.executeUpdate();
				
			
			} finally {
				if(pstmt != null) pstmt.close();
			}
		}
		
		
	
		//�˻����ǿ� �ش��ϴ� ��ü ȸ���� ���ų����� ��ȸ�Ѵ�. (������)
		public List<TotalInfoVO> selectReservationListByAdmin(String keyfield, String keyword, int startRow, int endRow) throws Exception {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<TotalInfoVO> totalInfos = new ArrayList<TotalInfoVO>();
			try {
				conn = DBConn.getConnection();
				StringBuffer sql = new StringBuffer();
				
				sql.append("select res.r_no, res.r_status, to_char(res.r_date, 'YYYY/MM/DD'), res.card_number,            "); 
				sql.append("res.approve_number, res.total_price, card.cardco_name, mem.m_id, perf.title,                  ");
				sql.append("to_char(sch.s_date, 'YYYY/MM/DD'), to_char(ord.o_time, 'HH24:MI'), t.t_name                   ");
				sql.append("from reservation res, card_company card, member mem, performance perf, schedule sch,          ");
				sql.append("orders ord, theater t, reserved_seat rseat, seat                                              ");
				sql.append("where res.o_no = ord.o_no                                                                     ");
				sql.append("and ord.s_no = sch.s_no                                                                       ");
				sql.append("and sch.p_no = perf.p_no                                                                      ");
				sql.append("and sch.t_no = t.t_no                                                                         ");
				sql.append("and res.r_no = rseat.r_no                                                                     ");
				sql.append("and seat.seat_no = rseat.seat_no                                                              ");
				sql.append("and seat.t_no = t.t_no                                                                        ");
				sql.append("and res.m_no = mem.m_no                                                                       ");
				sql.append("and res.cardco_no = card.cardco_no;                                                           ");
				

				if(keyfield.equals("mId")) {
					sql.append("and mem.m_id LIKE '%' || ? || '%'     ");
				} else if(keyfield.equals("rDate")) {
					sql.append("and res.r_date = ?                    ");
				} else if(keyfield.equals("title")) {
					sql.append("and perf.title LIKE '%' || ? || '%'   ");
				} else if(keyfield.equals("sDate")) {
					sql.append("and sch.s_date = ?                    ");
				} else if(keyfield.equals("rStatus")) {
					sql.append("and res.r_status = ?                  ");
				}
				
				sql.append("order by r_no desc                        ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, keyword);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					TotalInfoVO totalInfo = new TotalInfoVO();
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


			} finally {
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}
			return totalInfos;
		}
	

}
