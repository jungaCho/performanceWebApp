package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
			
			sql.append("insert into poster(poster_no,system_file_name,original_file_name,file_size,main_poster,p_no)   ");
			sql.append("values ('I'||lpad('image_seq.nextVal,5,0), ? , ? ,? ,?,?    ");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			for(int i=1;i<posters.size();i++) {
				PosterVO poster=posters.get(i);
				pstmt.setString(1, poster.getSystemFileName());
				pstmt.setString(2, poster.getOriginalFileName());
				pstmt.setLong(3, poster.getFileSize());
				pstmt.setString(4, poster.getMainPoster());
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
	
	//공연 포스터 정보를 수정한다.
	public void updatePoster(Connection conn,PosterVO poster) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("update poster    ");
			sql.append("set main_Poster=?   ");
			sql.append("where poster_no=?    ");
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1,poster.getMainPoster() );
			pstmt.setString(2, poster.getPosterNo());
			
			pstmt.executeUpdate();
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
}
