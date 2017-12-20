package domain.performance;

public class DetailFileVO {
	private String fileNo;	//���� ��ȣ
	private String systemFileName;	//�ý��� ���� �̸�
	private String originalFileName;	//���� ���� �̸�
	private long fileSize;	//���� ũ��
	private String pNo;	//������ȣ
	
	
	public DetailFileVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetailFileVO(String fileNo, String systemFileName, String originalFileName, int fileSize, String pNo) {
		super();
		this.fileNo = fileNo;
		this.systemFileName = systemFileName;
		this.originalFileName = originalFileName;
		this.fileSize = fileSize;
		this.pNo = pNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
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

	public void setFileSize(long size) {
		this.fileSize = size; 
	}

	public String getpNo() {
		return pNo;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	@Override
	public String toString() {
		return "DetailFileVO [fileNo=" + fileNo + ", systemFileName=" + systemFileName + ", originalFileName="
				+ originalFileName + ", fileSize=" + fileSize + ", pNo=" + pNo + "]";
	}
	
	
	
}
