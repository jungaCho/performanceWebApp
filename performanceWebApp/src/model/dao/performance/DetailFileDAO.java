package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.performance.DetailFileVO;
import domain.performance.PerformanceVO;

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
			
			sql.append("insert into detailfile(file_no,system_file_name,original_file_name , file_size, p_no )      ");
			sql.append("values('D'||lpad(detail_seq.nextVal,5,0),?,?,?,?)     ");
			pstmt=conn.prepareStatement(sql.toString());
			
			for(int i=0;i<detailFiles.size();i++) {
				DetailFileVO detailFile=detailFiles.get(i);
				pstmt.setString(1, detailFile.getSystemFileName());
				pstmt.setString(2, detailFile.getOriginalFileName());
				pstmt.setLong(3, detailFile.getFileSize());
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
	
	//공연 상세 설명을 일괄 삭제한다.(공연번호에 해당하는)
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
	
	//공연 상세설명 일괄 삭제(리스트로 받기)
	public void deleteDetailFileList(Connection conn,List<String> detailFiles) throws Exception{
		PreparedStatement pstmt=null;
		try {
			StringBuffer sql=new StringBuffer();
			sql.append("delete from detailFile     ");
			sql.append("where file_no= ?			");
			
			pstmt=conn.prepareStatement(sql.toString());
			for(String fileNo : detailFiles) {
				pstmt.setString(1, fileNo);
				pstmt.addBatch();
			}
			pstmt.executeBatch();			
		
			
		}finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	//공연 상세 설명을 수정한다.-없어도 될것 같음.
	
	//공연에 해당하는 업로드된 상세설명 파일 목록을 조회하다.
	public List<DetailFileVO> selectDetailFileList(String pNo) throws Exception {
		ArrayList<DetailFileVO> detailFiles = new ArrayList<DetailFileVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select file_no, System_file_name, original_file_name, file_size, p_no		");
			sql.append("from detailFile																");
			sql.append("where p_no = ?															");
			sql.append("order by 1 asc																");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, pNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DetailFileVO detailFile = new DetailFileVO();
				detailFile.setFileNo(rs.getString(1));
				detailFile.setSystemFileName(rs.getString(2));
				detailFile.setOriginalFileName(rs.getString(3));
				detailFile.setFileSize(rs.getLong(4));
				detailFile.setpNo(rs.getString(5));
				
				detailFiles.add(detailFile);
			}	
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return detailFiles;	
	}
}
