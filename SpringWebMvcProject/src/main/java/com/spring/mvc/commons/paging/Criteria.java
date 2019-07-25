package com.spring.mvc.commons.paging;

public class Criteria {
	
	private int page; //사용자가 요청한 페이지번호
	private int countPerPage;//한 페이지당 들어갈 게시물 수
	
	public Criteria() {
		this.page = 1;
		this.countPerPage = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		if(countPerPage <= 0 || countPerPage > 100) {
			this.countPerPage = 10;
			return;
		}
		this.countPerPage = countPerPage;
	}
	
	//SQL LIMIT절의 조회 시작 인덱스를 계산해서 리턴하는 메서드
	public int getLimitStart() {
		return (this.page - 1) * countPerPage;
	}
	
	

}







