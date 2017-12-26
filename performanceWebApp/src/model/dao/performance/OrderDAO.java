package model.dao.performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		public void deleteOrder(Connection conn,String sNo) throws Exception{
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
		
		//일정에 해당하는 회차 조회
				public List<OrderVO> selectOrders(String sNo) throws Exception {

					List<OrderVO> orders=null;
					PreparedStatement pstmt = null;
					Connection conn = null;
					ResultSet rs = null;

					try {
						conn = DBConn.getConnection();
					

						StringBuffer sql = new StringBuffer();
						sql.append("select to_char(o_time,'HH24:MI') ,O_no                    ");
						sql.append("from orders   										 ");
						sql.append("where S_NO=?  								 ");
						
						pstmt.setString(1, sNo);
						
						rs = pstmt.executeQuery(sql.toString());
						
						if (rs.next()) {
							String oTime=rs.getString(1);
							String oNo=rs.getString(2);
							OrderVO order=new OrderVO(oNo,oTime,sNo);
							orders.add(order);
						}
						return orders;
					} finally {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					}
				}	
	
}
