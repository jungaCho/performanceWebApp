package domain.performance;

public class PosterVO {
	private String posterNo;		//������ ��ȣ
	private String systemFileName;	//�ý��� ���� �̸�
	private String originalFileName;	//���� ���� �̸�
	private int fileSize;	//���� ũ��
	private String mainPoster;	//���������� ����
	private String pNo;	//������ȣ
	
	
	public PosterVO() {
		super();
	}

	public PosterVO(String posterNo, String systemFileName, String originalFileName, int fileSize, String mainPoster,
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
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getMainPoster() {
		return mainPoster;
	}
	public void setMainPoster(String mainPoster) {
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
