package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import controller.member.DBConn;
import domain.performance.DetailFileVO;

public class DetailFileDAO {
	
	private static DetailFileDAO instance=new DetailFileDAO();
	
	private DetailFileDAO() {
		
	}
	public static DetailFileDAO getInstance() {
		return instance;
	}
	
	//공연 상세정보를 일괄 등록한다.
	public void insertDetailFile(Connection conn,ArrayList<DetailFileVO> detailFiles) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			
			sql.append("insert into detailfile    ");
			sql.append("values('D'||lpad(detail_seq.nextVal,5,0),?,?,?,?)     ");
			pstmt=conn.prepareStatement(sql.toString());
			
			for(int i=0;i<detailFiles.size();i++) {
				DetailFileVO detailFile=detailFiles.get(i);
				pstmt.setString(1, detailFile.getSystemFileName());
				pstmt.setString(2, detailFile.getOriginalFileName());
				pstmt.setInt(3, detailFile.getFileSize());
				pstmt.setString(4, detailFile.getpNo());
				pstmt.addBatch();
			}
				pstmt.executeBatch();
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}		
	}
	
	//공연 상세 설명을 삭제한다.
	public void deleteDetailFile(Connection conn,String file_no)throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("delete from detailFile   ");
			sql.append("where file_no=?    ");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, file_no);
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 상세 설명을 일괄 삭제한다.
	public void deleteDetailFileList(Connection conn,String p_no) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuilder sql=new StringBuilder();
			sql.append("delete from detailFile   ");
			sql.append("where p_no=?    ");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, p_no);
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 상세 설명을 수정한다.-없어도 될것 같음.

}
