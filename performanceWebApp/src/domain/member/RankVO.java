package domain.member;

public class RankVO {
	int rankNo;
	String rName;
	String updateScore;
	double discount;
	
	public RankVO() {
		super();
	}

	public int getRankNo() {
		return rankNo;
	}

	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getUpdateScore() {
		return updateScore;
	}

	public void setUpdateScore(String updateScore) {
		this.updateScore = updateScore;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
