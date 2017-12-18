package domain.performance;

import java.util.List;

public class PerformanceVO {
	private String pNo;	//공연번호
	private String title; //공연명
	private String video;//동영상
	private String startDate; //시작일
	private String endDate;//종료일
	private String production; //제작사
	private String contackName; //공연 담당자
	private String contactNumber; //공연 담당자 연락처
	private int runningTime; //러닝타임
	private String note; //비고
	private int price; //가격
	private String viewNo;//관람등급번호 
	private String viewClass;//관람등급
	private String genreNo;//장르번호
	private String genre;//장르
	private String tName;//공연장 이름
	private List<ScheduleVO> schedules;//공연 일정 목록
	private List<PosterVO> posters;//포스터 파일 목록
	private List<DetailFileVO> detaileFiles;//상세설명 이미지 목록
	
	
}
