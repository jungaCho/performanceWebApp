package controller;

public class ActionForward {
	private String path; //�̵��� ���
	private boolean isRedirect; //�����̷�Ʈ ����
	
	//default ������
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
	public boolean isRedirect() { //������ ��� ��Ģ, getIsRedirect �ƴ�
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	
}
