package domain.performance;

public class PosterVO {
	private String posterNo;		//포스터 번호
	private String systemFileName;	//시스템 파일 이름
	private String originalFileName;	//원본 파일 이름
	private long fileSize;	//파일 크기
	private int mainPoster;	//메인포스터 여부
	private String pNo;	//공연번호
	
	
	public PosterVO() {
		super();
	}

	public PosterVO(String posterNo, String systemFileName, String originalFileName, int fileSize, int mainPoster,
			String pNo) {
		super();
		this.posterNo = posterNo;
		this.systemFileName = systemFileName;
		this.originalFileName = originalFileName;
		this.fileSize = fileSize;
		this.mainPoster = mainPoster;
		this.pNo = pNo;
	}
	
	public String getPosterNo() {
		return posterNo;
	}
	public void setPosterNo(String posterNo) {
		this.posterNo = posterNo;
	}
	public String getSystemFileName() {
		return systemFileName;
	}
	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getMainPoster() {
		return mainPoster;
	}
	public void setMainPoster(int mainPoster) {
		this.mainPoster = mainPoster;
	}
	public String getpNo() {
		return pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = pNo;
	}
	
	@Override
	public String toString() {
		return "PosterVO [posterNo=" + posterNo + ", systemFileName=" + systemFileName + ", originalFileName="
				+ originalFileName + ", fileSize=" + fileSize + ", mainPoster=" + mainPoster + ", pNo=" + pNo + "]";
	}
	
	
}
