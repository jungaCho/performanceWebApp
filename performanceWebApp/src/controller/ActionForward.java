package controller;

public class ActionForward {
	private String path; //이동할 경로
	private boolean isRedirect; //리다이렉트 여부
	
	//default 생성자
	public ActionForward() {
		super();
	}
	
	//getter,setter
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() { //정해진 명령 규칙, getIsRedirect 아님
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	
}
