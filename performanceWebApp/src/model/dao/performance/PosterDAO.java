package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.performance.PosterVO;


public class PosterDAO {

	private static PosterDAO instance=new PosterDAO();
	
	private PosterDAO() {
		
	}
	public static PosterDAO getInstance() {
		return instance;
	}
	
	//공연 포스터를 일괄 등록한다.
	public void insertPoster(Connection conn,ArrayList<PosterVO> posters) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			
			sql.append("insert into poster(poster_no, system_file_name, original_file_name, file_size, main_poster,  p_no)   ");
			sql.append("values ('I'||lpad(image_seq.nextVal,5,0), ? , ? ,?, ? , ?)  ");
			
			System.out.println(sql.toString());
			
			pstmt=conn.prepareStatement(sql.toString());
			System.out.println("~~~~~~~~~~"+posters.get(0).getOriginalFileName());
			
			for(int i=0;i<posters.size();i++) {
				PosterVO poster=posters.get(i);
				System.out.println("~~~~~~~~~~"+poster.toString());
				pstmt.setString(1, poster.getSystemFileName());
				pstmt.setString(2, poster.getOriginalFileName());
				pstmt.setLong(3, poster.getFileSize());
				pstmt.setInt(4, poster.getMainPoster());
				pstmt.setString(5, poster.getpNo());
				pstmt.addBatch();
			}
			pstmt.executeBatch();			
							
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
		
	}
	
	//공연 포스터를 삭제한다.
	public void deletePoster(Connection conn,String poster_no) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("delete from poster     ");
			sql.append("where poster_no=?   " );
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, poster_no);
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 포스터를 일괄 삭제한다.
	public void deletePosterList(Connection conn, String p_no) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("delete from poster     ");
			sql.append("where p_no=?   " );
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, p_no);
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	/*//공연 포스터 정보를 수정한다.
	public void updatePoster(Connection conn,PosterVO poster) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("update poster    ");
			sql.append("set main_Poster=?   ");
			sql.append("where poster_no=?    ");
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1,poster.getMainPoster() );
			pstmt.setString(2, poster.getPosterNo());
			
			pstmt.executeUpdate();
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}*/
	
	//공연에 해당하는 업로드된 포스터 파일 목록을 조회하다.
		public List<PosterVO> selectPoster(String pNo) throws Exception {
			ArrayList<PosterVO> posters = new ArrayList<PosterVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBConn.getConnection();
				
				StringBuilder sql = new StringBuilder();
				sql.append("select poster_no, System_file_name, original_file_name, file_size, Main_poster, P_no		");
				sql.append("from poster																					");
				sql.append("where p_no = ?																				");
				sql.append("order by 1 asc																				");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, pNo);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					PosterVO poster = new PosterVO();
					poster.setPosterNo(rs.getString(1));
					poster.setSystemFileName(rs.getString(2));
					poster.setOriginalFileName(rs.getString(3));
					poster.setFileSize(rs.getLong(4));
					poster.setpNo(rs.getString(5));
					
					posters.add(poster);
				}	
			} finally {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}
		return posters;	
	}
}
	
