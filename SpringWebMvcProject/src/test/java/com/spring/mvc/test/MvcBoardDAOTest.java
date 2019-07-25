package com.spring.mvc.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.repository.IMvcBoardDAO;
import com.spring.mvc.board.service.IMvcBoardService;
import com.spring.mvc.commons.paging.Criteria;
import com.spring.mvc.commons.paging.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MvcBoardDAOTest {

	@Autowired
	private IMvcBoardDAO dao;
	
	@Autowired
	private IMvcBoardService serv;

	/*@Test
	public void insertTest() throws Exception {		

		for (int i = 2; i <= 1000; i++) {			

			BoardVO article = new BoardVO();
			article.setWriter("김빠빠" + i);
			article.setTitle("테스트 게시물 " + i);
			article.setContent("테스트 중이랍니다. " + i);

			dao.insert(article);
		}

	}*/

	//게시물 단일 조회 테스트
	/*@Test
	public void selectOneTest() throws Exception {

		BoardVO article = dao.content(44);

		System.out.println(article);
	}*/

	//게시물 수정 테스트 : VO의 세터를 사용하여 수정 내용(글제목, 글내용)을 입력하고
	/*@Test
	public void updateTest() throws Exception {
		BoardVO article = new BoardVO();
		article.setBoardNo(1);
		article.setTitle("글 수정 테스트 제목~~");
		article.setContent("글 수정 테스트 내용~~");
		dao.update(article);
		System.out.println(dao.content(1));
	}*/


	//게시물 삭제 테스트 : 게시글 번호를 통한 삭제를 확인하세요
	/*@Test
	public void deleteTest() throws Exception {
		dao.delete(999);
	}
	*/
	
	/*@Test
	public void pagingTest() throws Exception {
		
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setCountPerPage(20);
		List<BoardVO> list = dao.listPaging(cri);
		
		for(BoardVO article : list) 
			System.out.println(article);
		
	}*/
	
	/*
	 
	 # 1. 페이지 화면 모습
	 - 한 화면에서 페이지를 10개씩 보여줘야 한다면???
	 ex) 1 2 3 4 ... 9 10 [다음] // [이전] 31 32 33 ... 39 40 [다음]
	 
	 - 만약에 총 게시물이 67개라면??
	 ex) 1 2 3 4 5 6 7
	 
	 - 총 게시물이 142개이고 현재 12페이지에 위치해 있다면??
	 ex) [이전] 11 12 13 14 15 
	 
	 # 2. 우선 총 게시물의 수를 조회해야 합니다.
	 - 총 게시물 수를 DB로부터 조회하는 SQL 작성
	 
	 # 3. 사용자가 현재 보고 있는 페이지를 기준으로 
	      끝 페이지 번호 계산하는 로직 작성
	      
	 - 만약 현재 보고 있는 페이지가 3번이고 한 화면에 보여줄 페이지가
	    10개씩이라면 ??
	    -> 끝 페이지번호는? 10번
	 - 현재 위치한 페이지가 37페이지이고 한 화면에 보여줄 페이지가 20개라면
	    -> 끝 페이지번호는? 40번
	    
	 - 공식: Math.ceil((현재 위치한 페이지 번호 / 한 화면당 보여질 페이지의 수))
	        * 한 화면당 보여질 페이지의 수;
	  
	  # 4. 시작 페이지 번호 계산하기
	  - 현재 보고 있는 페이지가 15페이지이고, 한번에 페이지를 10개씩 보여준다면?
	    -> 시작페이지 번호: 11번
	  - 현재 73페이지에 있고, 한번에 20개씩 보여준다면??
	    -> 시작페이지 번호: 61번
	    
	  - 공식: (끝 페이지 번호 - 한 화면에 보여줄 페이지 수) + 1
	  
	  # 5. 끝 페이지 번호 재보정
	  
	  - 만약 총 게시물이 138개이고 한 화면당 10페이지씩을 보여준다면
	    현재 사용자가 12페이지를 보고 있다면 실제 끝 페이지는 20페이지가 
	    아닌 14페이지여야 합니다.
	    
	  - 하지만 위 공식들을 적용하면 끝페이지는 20번으로 계산되므로
	    20이라는 숫자는 시작 페이지를 구할 때까지만 활용하고
	    다시 14로 재보정해야 합니다.
	  
	  - 재보정 공식: Math.ceil(총 게시물 수 / 한 화면에 보여줄 게시물 수)
	 */
	
	/*@Test
	public void pageAlgorithmTest() throws Exception {
		
		//총 게시물 수 구하는 테스트
		System.out.println("==============================");
		System.out.println("총 게시물 수: " 
					+ dao.countArticles() + "개");
		System.out.println("==============================");
		
		//끝 페이지 번호 계산 테스트
		Criteria c = new Criteria();
		c.setPage(54);
		int displayPage = 20;
		
		int endPage = (int)Math.ceil(c.getPage() / (double)displayPage) * displayPage;
		
		System.out.println("끝 페이지 번호: " + endPage);
		
		//시작 페이지 번호 계산 테스트
		int beginPage = (endPage - displayPage) + 1;
		System.out.println("시작페이지 번호: " + beginPage);
		System.out.println("=================================");
		
		//끝 페이지 보정 테스트
		int total = 388;
		endPage = (int)Math.ceil((double)total / c.getCountPerPage());
		System.out.println("보정 후 끝페이지 번호: " + endPage);
		
	}*/
	
	//작성자 이름으로 검색 테스트
	@Test
	public void searchTest() throws Exception {
		
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword("%멍멍%");
		cri.setPage(1);
		cri.setCondition("titleContent");
		
		List<BoardVO> list = dao.listSearch(cri);
		
		System.out.println("=============================");
		
		for (BoardVO boardVO : list) {
			System.out.println(boardVO);
		}
		System.out.println("검색된 게시물 수 : " + dao.countSearchArticles(cri) + "개");
		System.out.println("=============================");
		
	}


}









