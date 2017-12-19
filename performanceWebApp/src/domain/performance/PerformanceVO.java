package domain.performance;

import java.util.ArrayList;
import java.util.List;

public class PerformanceVO {
	private String pNo;	//������ȣ
	private String title; //������
	private String video;//������
	private String startDate; //������
	private String endDate;//������
	private String production; //���ۻ�
	private String contactName; //���� �����
	private String contactNumber; //���� ����� ����ó
	private int runningTime; //����Ÿ��
	private String note; //���
	private int price; //����
	private String viewNo;//������޹�ȣ 
	private String viewClass;//�������
	private String genreNo;//�帣��ȣ
	private String genre;//�帣
	private String tName;//������ �̸�
	private List<ScheduleVO> schedules = new ArrayList<ScheduleVO>();//���� ���� ���
	private List<PosterVO> posters = new ArrayList<PosterVO>();//������ ���� ���
	private List<DetailFileVO> detailFiles = new ArrayList<DetailFileVO>();//�󼼼��� �̹��� ���
	
	public PerformanceVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerformanceVO(String pNo, String title, String video, String startDate, String endDate, String production,
			String contackName, String contactNumber, int runningTime, String note, int price, String viewNo,
			String viewClass, String genreNo, String genre, String tName, List<ScheduleVO> schedules,
			List<PosterVO> posters, List<DetailFileVO> detailFiles) {
		super();
		this.pNo = pNo;
		this.title = title;
		this.video = video;
		this.startDate = startDate;
		this.endDate = endDate;
		this.production = production;
		this.contactName = contackName;
		this.contactNumber = contactNumber;
		this.runningTime = runningTime;
		this.note = note;
		this.price = price;
		this.viewNo = viewNo;
		this.viewClass = viewClass;
		this.genreNo = genreNo;
		this.genre = genre;
		this.tName = tName;
		this.schedules = schedules;
		this.posters = posters;
		this.detailFiles = detailFiles;
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getViewNo() {
		return viewNo;
	}

	public void setViewNo(String viewNo) {
		this.viewNo = viewNo;
	}

	public String getViewClass() {
		return viewClass;
	}

	public void setViewClass(String viewClass) {
		this.viewClass = viewClass;
	}

	public String getGenreNo() {
		return genreNo;
	}

	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public List<ScheduleVO> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<ScheduleVO> schedules) {
		this.schedules = schedules;
	}

	public List<PosterVO> getPosters() {
		return posters;
	}

	public void setPosters(List<PosterVO> posters) {
		this.posters = posters;
	}

	public List<DetailFileVO> getDetaileFiles() {
		return detailFiles;
	}

	public void setDetaileFiles(List<DetailFileVO> detaileFiles) {
		this.detailFiles = detailFiles;
	}

	@Override
	public String toString() {
		return "PerformanceVO [pNo=" + pNo + ", title=" + title + ", video=" + video + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", production=" + production + ", contactName=" + contactName
				+ ", contactNumber=" + contactNumber + ", runningTime=" + runningTime + ", note=" + note + ", price="
				+ price + ", viewNo=" + viewNo + ", viewClass=" + viewClass + ", genreNo=" + genreNo + ", genre="
				+ genre + ", tName=" + tName + ", schedules=" + schedules + ", posters=" + posters + ", detailFiles="
				+ detailFiles + "]";
	}
	
	public void addPoster(PosterVO poster) {
		this.posters.add(poster);
	}
	
	public void addSchedule(ScheduleVO schedule) {
		this.schedules.add(schedule);
	}
	
	public void addDetailFile(DetailFileVO detailFile) {
		this.detailFiles.add(detailFile);
	}
}














