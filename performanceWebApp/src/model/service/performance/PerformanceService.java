package model.service.performance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.performance.DetailFileVO;
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
	
	//���� ��� ��ȸ(�����)
	public List<PerformanceVO> retrievePerformanceListByMember(String filter, String keyword, int startRow, int endRow) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.selectPerformanceListByMember(filter, keyword, startRow, endRow);
	}
	
	//��ü ���� ��� ��ȸ(������)
	public List<PerformanceVO> retrievePErformanceListByAdmin(int startRow, int endRow) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.selectPerformanceListByAdmin(startRow, endRow);
	}
	
	//���� ������ ��ȸ
	public PerformanceVO retirevePerformance(String pNo) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		PerformanceVO performance = performanceDao.selectPerformance(pNo);
		
		return performance;
	}
	
	//�˻� ���ǿ� �ش��ϴ� ���� ���� �˻�
	public List<PerformanceVO> findPerformance(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		PerformanceDAO performanceDao = PerformanceDAO.getInstance();
		return performanceDao.searchPerformance(keyfield, keyword, startRow, endRow);
	}
	
	//���� ������ ����ϴ�.
	public void createPerformance(PerformanceVO performance) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			//Ʈ�����
			conn.setAutoCommit(false);
			
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			PosterDAO posterDao = PosterDAO.getInstance();
			DetailFileDAO detailfileDao = DetailFileDAO.getInstance();
			
			performanceDao.insertPerformance(performance);
			
			ArrayList<PosterVO> posters=(ArrayList<PosterVO>)performance.getPosters();
			posterDao.insertPoster(conn, posters);

			ArrayList<DetailFileVO> detailFiles = (ArrayList<DetailFileVO>)performance.getDetaileFiles();
			detailfileDao.insertDetailFile(conn, detailFiles);
			
			conn.commit();
			
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(conn!=null)
				conn.close();
		}	
	}
	
	//���� ������ �����ϴ�.
	public void removePerformance(String pNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			//Ʈ�����
			conn.setAutoCommit(false);
			
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			PosterDAO posterDao = PosterDAO.getInstance();
			DetailFileDAO detailfileDao = DetailFileDAO.getInstance();
			ScheduleDAO scheduleDao=ScheduleDAO.getInstance();
			OrderDAO orderDao=OrderDAO.getInstance();
			
			posterDao.deletePosterList(conn, pNo);
			detailfileDao.deleteDetailFileList(conn, pNo);
			String[] sNOs=scheduleDao.selectSchedule(conn,pNo);
			for(String sNo: sNOs) {
				orderDao.deleteOrder(conn, sNo);
			}
			scheduleDao.deleteSchedule(conn, pNo);
			
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if(conn!=null)
				conn.close();
		}
	}
	
	//���� �����͸� �����ϴ�.
	public void removePoster(String posterNo) throws Exception {
		Connection conn = null;
		try {
			
			conn = DBConn.getConnection();
			
			PosterDAO posterDao = PosterDAO.getInstance();
			posterDao.deletePoster(conn, posterNo);
		
		} finally {
			if(conn!=null)
				conn.close();
		}	
	}
	
	//���� �� ������ �����ϴ�.
	public void removeDetailFile(String fileNo) throws Exception {
		Connection conn = null;
		try {
			
			conn = DBConn.getConnection();
			
			DetailFileDAO detailFileDao = DetailFileDAO.getInstance();
			detailFileDao.deleteDetailFile(conn, fileNo);
		
		} finally {
			if(conn!=null)
				conn.close();
		}				
	}
	
	//���� ������ �����ϴ�.
	public void modifyPerformance(PerformanceVO performance) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			//Ʈ�����
			conn.setAutoCommit(false);
			
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			performanceDao.updatePerformance(performance);
			
			PosterDAO posterDao = PosterDAO.getInstance();
			for(PosterVO poster : performance.getPosters() ) {
				posterDao.updatePoster(conn, poster);
			}
			
			DetailFileDAO detailFileDao = DetailFileDAO.getInstance();
			detailFileDao.deleteDetailFile(conn, fileNo);
			detailFileDao.insertDetailFile(conn, detailFiles);
			
		} catch(Exception e) {
			conn.rollback();
			throw e;	
		} finally {
			if(conn!=null)
				conn.close();
		}				
	}
	
	//���� ������ ����ϴ�.
	public void createSchedule(List<ScheduleVO> schedules) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			
			//Ʈ�����
			conn.setAutoCommit(false);
			
			PerformanceDAO performanceDao = PerformanceDAO.getInstance();
			performanceDao.insertPerformance(performance)
			
		} catch(Exception e) {
			conn.rollback();
			throw e;	
		} finally {
			if(conn!=null)
				conn.close();
		}				
	}
}
