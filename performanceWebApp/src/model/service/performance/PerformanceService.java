package model.service.performance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import conn.DBConn;
import domain.performance.DetailFileVO;
import domain.performance.OrderVO;
import domain.performance.PerformanceVO;
import domain.performance.PosterVO;
import domain.performance.ScheduleVO;
import model.dao.performance.DetailFileDAO;
import model.dao.performance.OrderDAO;
import model.dao.performance.PerformanceDAO;
import model.dao.performance.PosterDAO;
import model.dao.performance.ScheduleDAO;

public class PerformanceService {
	private static PerformanceService instance = new PerformanceService();

	private PerformanceService() {

	}

	public static PerformanceService getInstance() {
		return instance;
	}

	// 공연 목록 조회(사용자)
	public List<PerformanceVO> retrievePerformanceListByMember(HashMap<String,Object> map)
			throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.selectPerformanceListByMember(map);
	}

	// 전체 공연 목록 조회(관리자)
	public List<PerformanceVO> retrievePerformanceListByAdmin(int startRow, int endRow) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.selectPerformanceListByAdmin(startRow, endRow);
	}

	// 공연 상세정보 조회
	public PerformanceVO retirevePerformance(String pNo) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		PerformanceVO performance = performanceDao.selectPerformance(pNo);

		return performance;
	}

	// 검색 조건에 해당하는 공연 정보 검색
	public List<PerformanceVO> findPerformance(String keyfield, String keyword, int startRow, int endRow)
			throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.searchPerformance(keyfield, keyword, startRow, endRow);
	}

	// 공연 정보를 등록하다.
	public void createPerformance(PerformanceVO performance) throws Exception {
				
		Connection conn = null;
		try {
			conn = DBConn.getConnection();			
			System.out.println("conn : " + conn);

		
			 conn.setAutoCommit(false);

			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			String pNo=performanceDao.insertPerformance(performance);
			System.out.println("pNO : "+pNo);
				
			ArrayList<PosterVO> posters = (ArrayList<PosterVO>) performance.getPosters();
			for(PosterVO poster : posters) {
				poster.setpNo(pNo); 
			}
			PosterDAO posterDao = PosterDAO.getInstance();
			posterDao.insertPoster(conn, posters);
			

			ArrayList<DetailFileVO> detailFiles = (ArrayList<DetailFileVO>) performance.getDetaileFiles();
			for(DetailFileVO detialFile : detailFiles) {
				detialFile.setpNo(pNo);				
			}
			DetailFileDAO detailfileDao = DetailFileDAO.getInstance();
			detailfileDao.insertDetailFile(conn, detailFiles);

			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)		conn.close();
		}
	}

	// 공연 정보를 삭제하다.
	public void removePerformance(String pNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// 트랜잭션
			conn.setAutoCommit(false);

			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			PosterDAO posterDao = PosterDAO.getInstance();
			DetailFileDAO detailfileDao = DetailFileDAO.getInstance();
			ScheduleDAO scheduleDao = ScheduleDAO.getInstance();
			OrderDAO orderDao = OrderDAO.getInstance();

			posterDao.deletePosterList(conn, pNo);
			detailfileDao.deleteDetailFileList(conn, pNo);
			String[] sNOs = scheduleDao.selectSchedule(conn, pNo);
			for (String sNo : sNOs) {
				orderDao.deleteOrder(conn, sNo);
			}
			scheduleDao.deleteSchedule(conn, pNo);

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 공연 포스터를 삭제하다.
	public void removePoster(String posterNo) throws Exception {
		Connection conn = null;
		try {

			conn = DBConn.getConnection();

			PosterDAO posterDao = PosterDAO.getInstance();
			posterDao.deletePoster(conn, posterNo);

		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 공연 상세 설명을 삭제하다.
	public void removeDetailFile(String fileNo) throws Exception {
		Connection conn = null;
		try {

			conn = DBConn.getConnection();

			DetailFileDAO detailFileDao = DetailFileDAO.getInstance();
			detailFileDao.deleteDetailFile(conn, fileNo);

		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 공연 정보를 수정하다.
	public void modifyPerformance(PerformanceVO performance) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// 트랜잭션
			conn.setAutoCommit(false);

			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			performanceDao.updatePerformance(performance);

			PosterDAO posterDao = PosterDAO.getInstance();
			for (PosterVO poster : performance.getPosters()) {
				posterDao.updatePoster(conn, poster);
			}

			DetailFileDAO detailFileDao = DetailFileDAO.getInstance();
			detailFileDao.deleteDetailFileList(conn, performance.getpNo());
			detailFileDao.insertDetailFile(conn, (ArrayList<DetailFileVO>) performance.getDetaileFiles());

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 공연 일정을 등록하다.
	public void createSchedule(List<ScheduleVO> schedules, String pNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// 트랜잭션
			conn.setAutoCommit(false);

			for (ScheduleVO schedule : schedules) {
				ScheduleDAO dao = ScheduleDAO.getInstance();
				dao.insertSchedule(conn, schedule);

				ArrayList<OrderVO> orders = (ArrayList<OrderVO>) schedule.getOrders();
				OrderDAO dao1 = OrderDAO.getInstance();
				dao1.insertOrder(conn, orders);

			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	//특정 게시글의 상세정보를 모두 조회하다.
		public List<DetailFileVO> retrieveDetailFile(String pNo) throws Exception{
				
			DetailFileDAO detailFileDao = DetailFileDAO.getInstance();
			List<DetailFileVO> detailFileVO = detailFileDao.selectDetailFileList(pNo);
				
			return detailFileVO;
		}
		
		//특정 게시글의 포스터를 모두 조회하다.
		public List<PosterVO> retrievePoster(String pNo) throws Exception{
					
			PosterDAO posterDao = PosterDAO.getInstance();
			List<PosterVO> posterVO = posterDao.selectPoster(pNo);
					
			return posterVO;
		}
	
	
}
