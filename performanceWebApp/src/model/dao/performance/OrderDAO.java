package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import domain.performance.OrderVO;
import conn.DBConn;

public class OrderDAO {
	private static OrderDAO instance =new OrderDAO();
	
	private OrderDAO() {
		
	}
	
	public static OrderDAO getInstance() {
		return instance;
	}
	
	//공연 회차 정보 일괄 등록
	public void insertOrder(Connection conn, ArrayList<OrderVO> orders)throws Exception{
		PreparedStatement pstmt=null;
		try {
			conn=DBConn.getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("insert into orders(o_no, o_time,s_no)    ");
			sql.append("values('O'||lpad(order_seq.nextVal,5,0),? ,?)    ");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			for(int i=0;i<orders.size();i++) { 
				OrderVO order =orders.get(i);
				pstmt.setString(1,order.getoTime() );
				pstmt.setString(2, order.getsNo());
				pstmt.addBatch();
				
			} 
			pstmt.executeBatch();
			
		} finally {
			if(pstmt!=null)pstmt.close();
		}
	}
	
	//공연 회차 정보를 삭제한다.
		public void deleteSchedule(Connection conn,String sNo) throws Exception{
			PreparedStatement pstmt=null;
			Statement stmt=null;
			try {
				StringBuffer sql=new StringBuffer();
				sql.append("delete from orders    ");
				sql.append("where s_no=?     ");
				
				pstmt=conn.prepareStatement(sql.toString());
				pstmt.setString(1, sNo);
				pstmt.executeUpdate();

				
			} finally {
				if(pstmt!=null)pstmt.close();
			}
		}
}
