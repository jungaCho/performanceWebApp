package domain.member;

public class RankVO {
	int rankNo;
	String rName;
	String updateScore;
	String discount;
	
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

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
