package com.spring.mvc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IMvcBoardDAO;
import com.spring.mvc.commons.paging.Criteria;
import com.spring.mvc.commons.paging.SearchCriteria;

@Service
public class MvcBoardService implements IMvcBoardService {

	@Autowired
	private IMvcBoardDAO dao;

	@Override
	public void insert(BoardVO article) throws Exception {
		dao.insert(article);
	}

	@Override
	public BoardVO content(int boardNo, boolean trigger) throws Exception {

		BoardVO article = dao.content(boardNo);

		if(trigger) {
			String content = article.getContent();
			article.setContent(content.replace("\n", "<br>"));
		}		
		return article;
	}

	@Override
	public void update(BoardVO article) throws Exception {
		dao.update(article);
	}

	@Override
	public void delete(int boardNo) throws Exception {
		dao.delete(boardNo);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {

		List<BoardVO> articles = dao.listAll();

		//3일 이내 신규 글 new마크처리
		for(BoardVO article : articles) {
			//각 게시물들의 작성 시간 읽어오기
			long regTime = article.getRegDate().getTime();
			//현재시간 읽어오기
			long now = System.currentTimeMillis();

			if(now - regTime < (60 * 60 * 24 * 1000)) {
				article.setNewMark(true);
			}
		}

		return articles;
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {

		return dao.listPaging(cri);
	}

	@Override
	public int countArticles() throws Exception {
		return dao.countArticles();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {

		
		//Mapper에게 전달할 SQL에 맞는 검색 키워드로 설정.		
		//cri.setKeyword("%"+cri.getKeyword()+"%");
		
		List<BoardVO> articles = dao.listSearch(cri);

		//3일 이내 신규 글 new마크처리
		for(BoardVO article : articles) {
			//각 게시물들의 작성 시간 읽어오기
			long regTime = article.getRegDate().getTime();
			//현재시간 읽어오기
			long now = System.currentTimeMillis();

			if(now - regTime < (60 * 60 * 24 * 1000)) {
				article.setNewMark(true);
			}
		}

		return articles;
	}

	@Override
	public int countSearchArticles(SearchCriteria cri) throws Exception {
		//cri.setKeyword("%"+cri.getKeyword()+"%");
		int cnt = dao.countSearchArticles(cri);
		System.out.println("검색 후 게시물 수: " + cnt);
		
		return cnt;
	}

}






