package domain.pub;

public class PagingVO {
	private int postPerpage; // �� �������� ������ �Խñ� ��
	private int pageBlock; // �� �������� ������ ������ ��ϼ�
	private int totalPost; // �� �Խñ� ��
	private int totalPage; // �� ������ ��
	private int currentPage; // ���� ������ ��ȣ
	private int startPage; // ���� �������� ���� ������ �׷��� ���� ������ ��ȣ
	private int endPage; // ���� �������� ���� ������ �׷��� ������ ������ ��ȣ
	private int prevPage; // ���� ������
	private int nextPage; // ���� ������
	private int startRow;
	private int endRow;
	private int num; // ���� ��������ȣ�� ���� �Խñ��� ���۹�ȣ

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

	// ���� �������� ���� ������ �׷��� ���� ������ ��ȣ�� ���Ѵ�.
	public int getStartPage() {
		// ���� ������ ��ȣ�� ���� ������ �׷������Ѵ�.
		int pageGroup = (currentPage / pageBlock) + (currentPage % pageBlock > 0 ? 1 : 0);
		this.startPage = (pageGroup * pageBlock) - (pageBlock - 1);
		return startPage;
	}

	// ���� �������� ���� ������ �׷��� ������ ������ ��ȣ�� ���Ѵ�.
	public int getEndPage() {
		// ���� ������ ��ȣ�� ���� ������ �׷��� ���Ѵ�.
		int pageGroup = (currentPage / pageBlock) + (currentPage % pageBlock > 0 ? 1 : 0);
		this.endPage = pageGroup * pageBlock;
		if (endPage > getTotalPage()) {
			this.endPage = getTotalPage();
		}
		return endPage;
	}

	// [����] Ŭ���� ���� ������ �׷��� ���� ������ ��ȣ�� ���Ѵ�.
	public int getPrevPage() {
		this.prevPage = getStartPage() - getPageBlock();
		return prevPage;
	}

	// [����] Ŭ���� ���� ������ �׷��� ���� ������ ��ȣ�� ���Ѵ�.
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
