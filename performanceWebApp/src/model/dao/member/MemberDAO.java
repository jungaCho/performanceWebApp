package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.member.MemberVO;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public void insertMember(MemberVO member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert into member(m_no,m_id,m_pw,m_name,birthday,email,address			");
			sql.append("values (to_char(sysdate,'yyyymmdd')||lpad(member_seq.nextval,4,0), ?,?,	");
			sql.append("			?, ?,?,?");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}

	public MemberVO selectMember(String mNo) throws Exception {
		MemberVO member = new MemberVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_id, m_pw, m_name, birthday, email, address, r_name 		");
			sql.append("from member m, rank r												");
			sql.append("where m.rank_no = r.rank_no											");
			sql.append("	and m_no = ?													");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member.setmId(rs.getString(1));
				member.setmPw(rs.getString(2));
				member.setmName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setEmail(rs.getString(5));
				member.setAddress(rs.getString(6));
				member.setrName(rs.getInt(7));
			}
			return member;

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public void updateMember(MemberVO member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("update member															");
			sql.append("set m_pw = ? , email = ? , address = ?									");
			sql.append("where m_no = ? 															");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getAddress());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	/*
	 * public void updateRank(int rankNo) {
	 * 
	 * }
	 */
	public void deleteMember(MemberVO member) throws Exception {

		//test 1
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("update member												");
			sql.append("set withdrawal = T, wd_date = sysdate, wd_reason = ?, r_no = null		");
			sql.append("where m_no = ?												");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, member.getWdReason());
			pstmt.setString(2, member.getmNo());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}


	public boolean loginMember(String mId, String mPw) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_no						");
			sql.append("from member						");
			sql.append("where m_id = ? and m_pw = ? 	");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			if (rs.getString(1) != null) {
				return true;
			}			
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return false;
	}

	public boolean checkOverLapId(String mId) throws Exception {

		// 1. DB에 접속해 회원 아이디를 조회한다.

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_id, withdrawal		");
			sql.append("from member     ");
			sql.append("where m_id = ?   ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, mId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String memberId = rs.getString(1);
				String wdCheck = rs.getString(2);

				// 똑같은 이름의 아이디가 존재하는 지 먼저 체크
				if (memberId.equals(mId)) {

					// 탈퇴여부가 F일때는
					if (wdCheck != "T") {
						return false; // false
					}
				}
			}

		} finally {

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return true;
	}

	public boolean checkOverLapEmail(String email) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select email ,withdrawal							");
			sql.append("from member										");
			sql.append("where email = ?									");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setEmail(rs.getString(1));
				member.setWithdrawal(rs.getString(2));

				if (member.getEmail().equals(email)) {
					if (member.getWithdrawal().equals('T')) {
						return false;
					}
				}
			}
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return true;
	}

	/*
	 * public String searchID(String mName, String email) throws Exception {
	 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * try { conn = DBConn.getConnection(); String mId = "";
	 * 
	 * StringBuffer sql = new StringBuffer();
	 * sql.append("select m_id						");
	 * sql.append("from member						");
	 * sql.append("where m_name = ? and email = ?	");
	 * 
	 * pstmt = conn.prepareStatement(sql.toString());
	 * 
	 * pstmt.setString(1, mName); pstmt.setString(2, email);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * mId = rs.getString(1);
	 * 
	 * return mId;
	 * 
	 * } finally { if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
	 * if(conn!=null) conn.close(); } }
	 */
	public boolean searchID(String mName, String email) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_id						");
			sql.append("from member						");
			sql.append("where m_name = ? and email = ?	");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mName);
			pstmt.setString(2, email);

			rs = pstmt.executeQuery();
			
			MemberVO member = new MemberVO();
			member.setmId(rs.getString(1));
			
			if(member.getmId() == null) {
				return false; 
			}			
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return true;
	}

	public String searchPwd(String mId, String mName, String email) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_id, m_email			");
			sql.append("from member    ");
			sql.append("where m_id = ? and m_name = ? and m_email = ? 		");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, email);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			String existId = "";
			String existEmail = "";

			while (rs.next()) {

				existId = rs.getString(1);
				existEmail = rs.getString(2);
			}

			if (existId == null) {
				existEmail = "";
			}

			return existEmail;

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public List<MemberVO> selectMemberList(int startRow, int endRow) throws Exception {
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
			sql.append("                order by m_no desc)member1)							");
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
	
	public List<MemberVO> searchByMember(String keyfield, String keyword, int startRow, int endRow) throws Exception {
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
			sql.append("                order by m_no desc)member1)							");
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
