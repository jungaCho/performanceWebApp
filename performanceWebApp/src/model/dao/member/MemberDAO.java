package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import conn.DBConn;
import domain.member.MemberVO;
import domain.member.RankVO;

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

			sql.append("insert into member(m_no,m_id,m_pw,m_name,birthday,email,address)				");
			sql.append("values (to_char(sysdate,'yymmdd')||lpad(member_seq.nextval,4,0), ?,?,?,?,?,?)	");

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
			sql.append("select m_no, m_id, m_pw, m_name, to_char(birthday,'YYYY/MM/DD'), email, address, r_name 		");
			sql.append("from member m, rank r																	");
			sql.append("where m.rank_no = r.rank_no																");
			sql.append("	and m_no = ?																		");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setmNo(rs.getString(1));
				member.setmId(rs.getString(2));
				member.setmPw(rs.getString(3));
				member.setmName(rs.getString(4));
				member.setBirthday(rs.getString(5));
				member.setEmail(rs.getString(6));
				member.setAddress(rs.getString(7));
				if (rs.getString(8) != null) {
					RankVO rank = new RankVO();
					rank.setrName(rs.getString(8));
					member.setRank(rank);
				}
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
			sql.append("set m_pw = ? , m_name = ?, email = ? , address = ?									");
			sql.append("where m_no = ? 															");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getmPw());
			pstmt.setString(2, member.getmName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getmNo());

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
	public void deleteMember(String mNo, String wdReason) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("update member															");
			sql.append("set withdrawal = 'T', wd_date = sysdate, wd_reason = ?, rank_no = null	");
			sql.append("where m_no = ?															");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, wdReason);
			pstmt.setString(2, mNo);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	/*
	 * public String loginMember(String mId, String mPw) throws Exception { String
	 * mNo = ""; Connection conn = null; PreparedStatement pstmt = null; ResultSet
	 * rs = null;
	 * 
	 * try { conn = DBConn.getConnection();
	 * 
	 * StringBuffer sql = new StringBuffer();
	 * sql.append("select m_no						");
	 * sql.append("from member						");
	 * sql.append("where m_id = ? and m_pw = ? 	");
	 * 
	 * pstmt = conn.prepareStatement(sql.toString());
	 * 
	 * pstmt.setString(1, mId); pstmt.setString(2, mPw);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while(rs.next()) { mNo = rs.getString(1); }
	 * 
	 * } finally { if (rs!=null) rs.close(); if (pstmt != null) pstmt.close(); if
	 * (conn != null) conn.close(); } return mNo; }
	 */
	public MemberVO loginMember(String mId, String mPw) throws Exception {
		MemberVO member = new MemberVO();
		String mNo = "";
		String withdrawal = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_no,m_id,m_pw,m_name,email,birthday,address, withdrawal	");
			sql.append("from member						");
			sql.append("where m_id = ? and m_pw = ? 	");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mId);
			pstmt.setString(2, mPw);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				member.setmNo(rs.getString(1));
				member.setmId(rs.getString(2));
				member.setmPw(rs.getString(3));
				member.setmName(rs.getString(4));
				member.setEmail(rs.getString(5));
				member.setBirthday(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setWithdrawal(rs.getString(8));
			}
/*
			while (rs.next()) {
				mNo = rs.getString(1);
				if (mNo != null) {
					member.setmId(mId);
				}
				member.setmNo(mNo);
				withdrawal = rs.getString(2);
				member.setWithdrawal(withdrawal);
			}
*/
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

	public boolean checkOverLapId(String mId) throws Exception {
		// 1. DB에 접속해 회원 아이디를 조회한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_no, m_id, withdrawal		");
			sql.append("from member     ");
			sql.append("where m_id = ?   ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, mId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String memberId = rs.getString(2);
				String wdCheck = rs.getString(3);
				System.out.println("wdCheck : " + wdCheck);
				// 똑같은 이름의 아이디가 존재하는 지 먼저 체크
				if (memberId.equals(mId)) {
					if (wdCheck.equals("T")) {
						return false; // false
					}
					return true;
				}
			}
			return false;

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

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
	public MemberVO searchID(String mName, String email) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mId = "";
		MemberVO member = new MemberVO();
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_id	,withdrawal			");
			sql.append("from member						");
			sql.append("where m_name = ? and email = ?	");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mName);
			pstmt.setString(2, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1) != null) {
					if(rs.getString(2).equals("F")) {
						mId = rs.getString(1);
						member.setmId(mId);	
					}
				}
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

	public boolean searchPwd(Connection conn, String mId, String mName, String email) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select m_no			");
			sql.append("from member    ");
			sql.append("where m_id = ? and m_name = ? and email = ? 		");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, email);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(1) != null) {
					return true;
				}
			}
			return false;

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
	}

	public void sendPwd(Connection conn, String tempPwd, String mId, String mName, String email) throws Exception {

		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("update member												");
			sql.append("set m_pw = ? 												");
			sql.append("where m_id = ? and m_name = ? and email = ?					");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tempPwd);
			pstmt.setString(2, mId);
			pstmt.setString(3, mName);
			pstmt.setString(4, email);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

}
