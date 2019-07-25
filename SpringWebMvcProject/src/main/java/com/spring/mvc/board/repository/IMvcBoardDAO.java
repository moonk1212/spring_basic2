package com.spring.mvc.board.repository;

import java.util.List;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.commons.paging.Criteria;
import com.spring.mvc.commons.paging.SearchCriteria;

public interface IMvcBoardDAO {
	
	//게시글 등록 기능
	void insert(BoardVO article) throws Exception;
	
	//게시글 상세 조회 기능
	BoardVO content(int boardNo) throws Exception;
	
	//게시글 수정 기능
	void update(BoardVO article) throws Exception;
	
	//게시글 삭제 기능
	void delete(int boardNo) throws Exception;
	
	//게시글 전체 목록조회 기능
	List<BoardVO> listAll() throws Exception;
	
	//페이징 처리된 목록 조회 기능
	List<BoardVO> listPaging(Criteria cri) throws Exception;
	
	//총 페이지 수 조회 기능
	int countArticles() throws Exception;
	
	//작성자 이름으로 검색 처리 기능
	List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	//검색 후 게시물 수 구하기
	int countSearchArticles(SearchCriteria cri) throws Exception;
	
}








