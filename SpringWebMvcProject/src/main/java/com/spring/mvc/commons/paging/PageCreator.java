package com.spring.mvc.commons.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageCreator {
	
	//페이지 번호와 한 페이지당 들어갈 게시물 수
	private Criteria criteria;
	private int articleTotalCount; //DB에저장된 총 게시물 수
	private int beginPage; //시작 페이지 번호
	private int endPage; //끝 페이지 번호
	private boolean prev; //이전 버튼 활성화 여부
	private boolean next; //다음 버튼 활성화 여부
	
	//한 화면에 보여질 페이지 수
	private final int displayNum = 10;
	
	
	//URI를 쉽게 작성하기 위한 유틸메서드 작성
	public String makeSearchURI() {
		
		return UriComponentsBuilder.newInstance()
				.queryParam("countPerPage", criteria.getCountPerPage())
				.queryParam("keyword", ((SearchCriteria)criteria).getKeyword())
				.queryParam("condition", ((SearchCriteria)criteria).getCondition())
				.build().toUriString();
	}
	
	//페이지 넘버 계산 메서드 선언.
	private void calcDataOfPage() {
		
		//보정 전 끝 페이지 구하기
		this.endPage = (int)Math.ceil(criteria.getPage() 
				/ (double)displayNum) * displayNum;
		
		//시작 페이지 번호 구하기
		this.beginPage = (this.endPage - displayNum) + 1;
		
		//끝 페이지 재보정하기
		int temp = (int)Math.ceil((double)articleTotalCount 
				/ criteria.getCountPerPage());
		
		//재보정 여부 판단하기
		if(endPage > temp) {
			this.endPage = temp;
		}
		
		//현재 시작페이지가 1이라면 이전버튼 활성화 여부를 false로 지정
		this.prev = (beginPage == 1) ? false : true;
		
		//마지막 페이지인지 여부 확인 후 다음 버튼 비활성.
		this.next = (articleTotalCount <= (endPage * criteria.getCountPerPage())) ? false : true;
		
	}
	

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public int getArticleTotalCount() {
		return articleTotalCount;
	}
	
	
	public void setArticleTotalCount(int articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayNum() {
		return displayNum;
	}
	
	
	
	
}







