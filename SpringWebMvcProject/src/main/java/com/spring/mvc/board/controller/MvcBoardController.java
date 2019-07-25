package com.spring.mvc.board.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.board.model.BoardVO;
import com.spring.mvc.board.service.IMvcBoardService;
import com.spring.mvc.commons.paging.PageCreator;
import com.spring.mvc.commons.paging.SearchCriteria;

@Controller
@RequestMapping("/board")
public class MvcBoardController {

	@Autowired
	private IMvcBoardService service;

	//list.jsp를 열어주는 메서드 선언(URL: /board/list)
	//페이징 처리 전
	/*@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {

		System.out.println("URL=> /board/list : GET");		
		model.addAttribute("articles", service.listAll());		
		return "board/list";
	}
	 */

	//페이징 처리 후 목록 요청
	/*@RequestMapping(value="/list/{page}", method=RequestMethod.GET)
	public String list(@PathVariable("page") int page,
						Criteria cri ,Model model) throws Exception {

		cri.setPage(page);

		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countArticles());		

		System.out.println("URL=> /board/list/"+ page +" : GET");
		model.addAttribute("articles", service.listPaging(cri));
		model.addAttribute("pageCreator", pc);

		return "board/list";
	}*/

	//검색 처리 후 목록 요청
	@RequestMapping(value="/list/{page}", method=RequestMethod.GET)
	public String list(@PathVariable("page") int page,
			SearchCriteria cri ,Model model) throws Exception {

		System.out.println("URL=> /board/list/"+ page 
				+ "?keyword=" + cri.getKeyword() 
				+ "&condition=" + cri.getCondition() 
				+ " : GET");
		
		cri.setPage(page);

		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countSearchArticles(cri));		

		model.addAttribute("articles", service.listSearch(cri));
		
		model.addAttribute("pageCreator", pc);

		return "board/list";
	}



	//게시글 작성화면 요청(URL: /board/write : GET)
	//view: /board/write.jsp
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() throws Exception {
		System.out.println("URL=> /board/list : GET");				
		
		return "board/write";
	}

	//게시글 등록 요청(URL: /board/write : POST)
	//redirect: /board/list
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {

		System.out.println("URL=> /board/list : POST");	
		service.insert(article);
		redirectAttr.addFlashAttribute("message", "regSuccess");
		return "redirect:/board/list/1";
	}

	//게시물 상세 조회 페이지 요청
	@RequestMapping(value="/content/{boardNo}", method=RequestMethod.GET)
	public String content(@PathVariable int boardNo, Model model,
			@ModelAttribute("criteria") SearchCriteria cri) throws Exception {

		System.out.println("URL=> /board/content/"+ boardNo +" : GET");
		model.addAttribute("article", service.content(boardNo, true));

		return "board/read";
	}

	//게시물 수정 페이지 요청
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam int boardNo, 
			Model model) throws Exception {

		System.out.println("URL=> /board/modify : GET");
		model.addAttribute("article", service.content(boardNo, false));

		return "board/modify";
	}

	//게시물 수정 처리 요청
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVO article, 
			RedirectAttributes ra) throws Exception {
		System.out.println("URL=> /board/modify : POST");
		service.update(article);

		ra.addFlashAttribute("message", "modSuccess");
		return "redirect:/board/content/"+article.getBoardNo();
	}


	//게시물 삭제 처리 요청
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String remove(@RequestParam("boardNo") int boardNo,
			SearchCriteria cri,
			RedirectAttributes ra) throws Exception {

		System.out.println("URL=> /board/delete : POST");
		service.delete(boardNo);
		
		ra.addFlashAttribute("message", "delSuccess")
		  .addAttribute("countPerPage", cri.getCountPerPage())
		  .addAttribute("keyword", cri.getKeyword())
		  .addAttribute("condition", cri.getCondition());
		

		return "redirect:/board/list/" + cri.getPage();
	}

}










