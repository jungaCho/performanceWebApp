package domain.pub;

public class PagingVO {
	private int postPerpage; // 한 페이지에 보여줄 게시글 수
	private int pageBlock; // 한 페이지에 보여줄 페이지 목록수
	private int totalPost; // 총 게시글 수
	private int totalPage; // 총 페이지 수
	private int currentPage; // 현재 페이지 번호
	private int startPage; // 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호
	private int endPage; // 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호
	private int prevPage; // 이전 페이지
	private int nextPage; // 다음 페이지
	private int startRow;
	private int endRow;
	private int num; // 현재 페이지번호가 속한 게시글의 시작번호

	public PagingVO() {

	}

	public void setPostPerpage(int postPerpage) {
		this.postPerpage = postPerpage;
	}
	
	public int getPostPerpage() {
		return postPerpage;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	
	public int getPageBlock() {
		return pageBlock;
	}

	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}

	public int getTotalPost() {
		return totalPost;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		this.totalPage = (totalPost / postPerpage) + (totalPost % postPerpage > 0 ? 1 : 0);//
		return totalPage;
	}

	// 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 구한다.
	public int getStartPage() {
		// 현재 페이지 번호가 속한 페이지 그룹을구한다.
		int pageGroup = (currentPage / pageBlock) + (currentPage % pageBlock > 0 ? 1 : 0);
		this.startPage = (pageGroup * pageBlock) - (pageBlock - 1);
		return startPage;
	}

	// 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 구한다.
	public int getEndPage() {
		// 현재 페이지 번호가 속한 페이지 그룹을 구한다.
		int pageGroup = (currentPage / pageBlock) + (currentPage % pageBlock > 0 ? 1 : 0);
		this.endPage = pageGroup * pageBlock;
		if (endPage > getTotalPage()) {
			this.endPage = getTotalPage();
		}
		return endPage;
	}

	// [이전] 클릭시 이전 페이지 그룹의 시작 페이지 번호를 구한다.
	public int getPrevPage() {
		this.prevPage = getStartPage() - getPageBlock();
		return prevPage;
	}

	// [다음] 클릭시 다음 페이지 그룹의 시작 페이지 번호를 구한다.
	public int getNextPage() {
		this.nextPage = endPage + 1;
		return nextPage;
	}

	public int getStartRow() {
		this.startRow = (currentPage - 1) * postPerpage + 1;
		return startRow;
	}

	public int getEndRow() {
		this.endRow = currentPage * postPerpage;
		if (this.endRow > totalPost) {
			this.endRow = totalPost;
		}
		return endRow;
	}
	
	public int getNum() {
		return totalPost - ((currentPage - 1) * postPerpage);
	}

	@Override
	public String toString() {
		return "PagingVO [postPerpage=" + postPerpage + ", pageBlock=" + pageBlock + ", totalPost=" + totalPost
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", startRow="
				+ startRow + ", endRow=" + endRow + ", num=" + num + "]";
	}

}
