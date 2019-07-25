package com.spring.mvc.board.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	
	private boolean newMark;//신규 게시물에 new마크를 붙일지 결정하는 논리변수
	
	
	
	
	
}
