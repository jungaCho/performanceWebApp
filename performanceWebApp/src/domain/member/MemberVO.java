package domain.member;

public class MemberVO {
	String mNo;
	String mId;
	String mPw;
	String mName;
	String birthday;
	String email;
	String address;
	int score;
	int rankNo;
	String withdrawal;
	String wdDate;
	String wdReason;
	
	RankVO rank = new RankVO();
	String rName;
	

	public MemberVO() {
		super();
	}
	
	

	public MemberVO(String mNo, String wdReason) {
		super();
		this.mNo = mNo;
		this.wdReason = wdReason;
	}

	public MemberVO(String mNo, String mPw, String mName, String email, String address) {
		super();
		this.mNo = mNo;
		this.mPw = mPw;
		this.mName = mName;
		this.email = email;
		this.address = address;
	}

	public MemberVO(String mId, String mPw, String mName, String email, String birthday, String address) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
	}

	public MemberVO(String mNo, String mId, String mPw, String mName, String email, String birthday, String address,
			int score, int rankNo, String withdrawal, String wdDate, String wdReason) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.score = score;
		this.rankNo = rankNo;
		this.withdrawal = withdrawal;
		this.wdDate = wdDate;
		this.wdReason = wdReason;
	}

	public String getmNo() {
		return mNo;
	}

	public void setmNo(String mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRankNo() {
		return rankNo;
	}

	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}

	public void setRName(int rNo ,String rName) {
		

		if(rNo == 1) {
			
		this.rName = "ÀÏ¹Ý";
		
		}else if(rNo == 2){
			this.rName = "Silver";
		}else if(rNo == 3) {
			this.rName = "Gold";
		}else if(rNo == 4){
			this.rName = "Diamond";
		} else {
			this.rName = "Å»ÅðÈ¸¿ø";
		}
		
	}

	public String getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(String withdrawal) {
		this.withdrawal = withdrawal;
	}

	public String getWdDate() {
		return wdDate;
	}

	public void setWdDate(String wdDate) {
		this.wdDate = wdDate;
	}

	public String getWdReason() {
		return wdReason;
	}

	public void setWdReason(String wdReason) {
		this.wdReason = wdReason;
	}

	public RankVO getRank() {
		return rank;
	}

	public void setRank(RankVO rank) {
		this.rank = rank;
	}
	
	public String getrName() {
		

		/*if(rNo == 1) {
			
		this.rName = "ÀÏ¹Ý";
		
		}else if(rNo == 2){
			this.rName = "Silver";
		}else if(rNo == 3) {
			this.rName = "Gold";
		}else {
			this.rName = "Diamond";
		}
		
		return rName;
		*/
		
		return rName;
	}

	@Override
	public String toString() {
		return "MemberVO [mNo=" + mNo + ", mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", birthday=" + birthday
				+ ", email=" + email + ", address=" + address + ", score=" + score + ", rankNo=" + rankNo
				+ ", withdrawal=" + withdrawal + ", wdDate=" + wdDate + ", wdReason=" + wdReason + "]";
	}

}
