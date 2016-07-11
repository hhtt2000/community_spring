package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.BoardVO;
import com.demo.domain.PageMaker;
import com.demo.domain.SearchCriteria;
import com.demo.domain.UserVO;
import com.demo.service.BoardService;
import com.demo.util.InterceptorUtils;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGet(String boardType, Model model) throws Exception {
		logger.info("register get...");
		logger.info("boardType: "+boardType);
		
		BoardCategoryVO boardCategory = service.getBoardCategory(boardType);
		
		model.addAttribute("boardCategory", boardCategory);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(BoardVO board, HttpSession session,
			RedirectAttributes rttr) throws Exception {
		logger.info("register post...");
		logger.info(board.toString());
		
		UserVO user = (UserVO)session.getAttribute("login");
		
		if(user != null) {
			service.register(board, user.getUserid());
			
			rttr.addFlashAttribute("msg", "success");
		}
		rttr.addAttribute("boardType", board.getBoardType());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("get list...");
		logger.info(cri.toString());
		
		// 공지 포함
		model.addAttribute("list", service.list(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countList(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			HttpSession session,
			SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		UserVO user = (UserVO)session.getAttribute("login");
		
		if(user != null) {
			service.remove(bno, user.getUserid());
			
			rttr.addFlashAttribute("msg", "success");
		}
		
		rttr.addAttribute("boardType", cri.getBoardType());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGet(int bno, @ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("modify get...");
		logger.info(cri.toString());
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO board, SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("modify post...");
		logger.info(cri.toString());
		logger.info(board.toString());
		
		service.modify(board);
		
		rttr.addAttribute("boardType", cri.getBoardType());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception {
		return service.getAttach(bno);
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	@ResponseBody
	public List<BoardCategoryVO> getBoardCategoryList() throws Exception {
		return service.getBoardCategories();
	}
}
