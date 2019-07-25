package com.spring.mvc.commons.paging;

public class SearchCriteria extends Criteria {
	
	private String keyword;
	private String condition;
	
	public SearchCriteria() {
		super();
		this.keyword = "";
		this.condition = "";
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	
}
