package domain.member;

public class AdminVO {
	
	String aId;
	String aPw;
	
	
	public AdminVO() {
		
		super();
	}
	
	public AdminVO(String aId, String aPw) {
		super();
		this.aId = aId;
		this.aPw = aPw;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getaPw() {
		return aPw;
	}

	public void setaPw(String aPw) {
		this.aPw = aPw;
	}

	@Override
	public String toString() {
		return "AdminVO [aId=" + aId + ", aPw=" + aPw + "]";
	}
		


}
