package model.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
/*
	public void updateRank(int rankNo) {
		
	}
*/
	public boolean deleteMember(MemberVO member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("update member												");
			sql.append("set withdrawal = T, wd_date = sysdate, wd_reason = ?		");
			sql.append("where m_no = ?												");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, member.getWdReason());			
			pstmt.setString(2, member.getmNo());

			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return true;
	}

	public boolean loginMember(String mId, String mPw) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select 						");
			sql.append("from member					");
			sql.append("where m_id = ?, m_pw = ? 	");
			
			pstmt = conn.prepareStatement(sql.toString());
			
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} 
		return true;
	}

	public boolean checkOverLapId(String mId) {
		return true;
	}

	public boolean checkOverLapEmail(String email) {
		return true;
	}
}
