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
			for(PosterVO poster : posters) {
				StringBuffer sql=new StringBuffer();
				sql.append("insert into poster(poster_no,system_file_name,original_file_name,file_size,main_poster,p_no)   ");
				sql.append("values('I'||lpad('image_seq,5,0), ? , ? ,? ,?,?    ");
				
				pstmt=conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, poster.getSystemFileName());
				pstmt.setString(2, poster.getOriginalFileName());
				pstmt.setLong(3, poster.getFileSize());
				pstmt.setString(4, poster.getMainPoster());
				pstmt.setString(5, poster.getpNo());
				
				pstmt.executeUpdate();
				sql.delete(1, 5);
							
			}
		} finally {
			if(pstmt!=null) pstmt.close();
		}
		
	}
	
	
	
}
