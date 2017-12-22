package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.member.AdminVO;
import domain.member.MemberVO;

public class AdminDAO {
	
	
	private static AdminDAO instance = new AdminDAO();
	
	
	private AdminDAO( ) {
		
	}
	
	public static AdminDAO getInstance() {
	
		return instance;
	}
	
	
	
	
	//관리자 로그인 //관리자 아이디, 비밀번호에 해당하는 관리자 테이블 접속 후 일치 시 true를 반환함.
	public boolean adminLogin (String aId, String aPw) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		AdminVO admin = new AdminVO();
		
		
		try {
			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select admin_Id		 ");
			sql.append("from admin				");
			sql.append("where admin_Id = ? and admin_Pw = ? 	");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, aId);
			pstmt.setString(2, aPw);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String existAdminId = rs.getString(1);
				
				if(existAdminId != null) {
						return true;
					}
			}
			
			return false;
				
			
		} finally {
			
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
	}
	
	//관리자 로그아웃 // 
	
	
	
	
	
	public List<MemberVO> selectMemberList(String sortkey, int startRow, int endRow) throws Exception {
		List<MemberVO> members = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_no, m_id, m_pw, m_name, birthday, email, address,  		");
			sql.append("		score, withdrawal, wd_date, wd_reason, rank_no				");
			sql.append("from (select rownum as rn, member1.*								");
			sql.append("		from(select * from member									");
			if(sortkey.equals("m_no")) {
				sql.append("                order by m_no desc)member1)							");
			} else if (sortkey.equals("m_id")) {
				sql.append("                order by m_id desc)member1)							");
			} else if (sortkey.equals("m_name")) {
				sql.append("                order by m_name desc)member1)							");
			} else if (sortkey.equals("rank_no")) {
				sql.append("                order by rank_no desc)member1)							");
			} else if (sortkey.equals("withdrawal")) {
				sql.append("                order by withdrawal asc)member1)							");
			}
			sql.append("where rn>= ? and rn<= ?												");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setmNo(rs.getString(1));
				member.setmId(rs.getString(2));
				member.setmPw(rs.getString(3));
				member.setmName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setScore(rs.getInt(8));
				member.setWithdrawal(rs.getString(9));
				member.setWdDate(rs.getString(10));
				member.setWdReason(rs.getString(11));
				member.setRankNo(rs.getInt(12));
				members.add(member);
			}			
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return members;
	}
	
	public MemberVO selectMemberDetail(String mNo) throws Exception {
		MemberVO member = new MemberVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select m_no, m_id, m_pw, m_name, birthday, email, address,  		");
			sql.append("		score, withdrawal, wd_date, wd_reason, rank_no				");
			sql.append("from member															");
			sql.append("where m_no = ?														");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, mNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setmNo(rs.getString(1));
				member.setmId(rs.getString(2));
				member.setmPw(rs.getString(3));
				member.setmName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setScore(rs.getInt(8));
				member.setWithdrawal(rs.getString(9));
				member.setWdDate(rs.getString(10));
				member.setWdReason(rs.getString(11));
				member.setRankNo(rs.getInt(12));
			}
		} finally {
			if(pstmt!=null) conn.close();
			if(conn!=null) conn.close();
		}
		return member;
	}
	
	public List<MemberVO> searchByMember(String sortkey, String keyfield, 
											String keyword, int startRow, int endRow) throws Exception {
		List<MemberVO> members = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select m_no, m_id, m_pw, m_name, birthday, email, address,  		");
			sql.append("		score, withdrawal, wd_date, wd_reason, rank_no				");
			sql.append("from (select rownum as rn, member1.*								");
			sql.append("		from(select * from member									");
			if(sortkey.equals("m_no")) {
				sql.append("                order by m_no desc)member1)							");
			} else if (sortkey.equals("m_id")) {
				sql.append("                order by m_id desc)member1)							");
			} else if (sortkey.equals("m_name")) {
				sql.append("                order by m_name desc)member1)							");
			} else if (sortkey.equals("rank_no")) {
				sql.append("                order by rank_no desc)member1)							");
			} else if (sortkey.equals("withdrawal")) {
				sql.append("                order by withdrawal asc)member1)							");
			}
			
			if(keyfield.equals("m_no")) {
				sql.append("where m_no like '%' || ? || '%'									");
			} else if (keyfield.equals("m_id")) {
				sql.append("where m_id like '%' || ? || '%'									");
			} else if (keyfield.equals("m_name")) {
				sql.append("where m_name like '%' || ? || '%'								");
			} else if (keyfield.equals("birthday")) {
				sql.append("where m_id like '%' || ? || '%'									");
			} else if (keyfield.equals("rank_no")) {
				sql.append("where rank_no like '%' || ? || '%'								");
			} else if (keyfield.equals("withdrawal")) {
				sql.append("where withdrawal like '%' || ? || '%'							");
			}
			sql.append("	and rn >= ? and rn =< ?											");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, keyword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setmNo(rs.getString(1));
				member.setmId(rs.getString(2));
				member.setmPw(rs.getString(3));
				member.setmName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setScore(rs.getInt(8));
				member.setWithdrawal(rs.getString(9));
				member.setWdDate(rs.getString(10));
				member.setWdReason(rs.getString(11));
				member.setRankNo(rs.getInt(12));
				members.add(member);
			}
			
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return members;
		
		
	}

}
