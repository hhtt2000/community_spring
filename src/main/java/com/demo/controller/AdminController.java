package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.domain.BoardCategoryVO;
import com.demo.domain.PageMaker;
import com.demo.domain.SearchCriteria;
import com.demo.dto.ClaimDTO;
import com.demo.service.AdminService;
import com.demo.service.BoardService;
import com.demo.util.InterceptorUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Inject
	private AdminService adminService;
	
	@Inject
	private BoardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getAdminMainPage() {
		return "admin/main";
	}
	
	@RequestMapping(value="/boardcategory", method=RequestMethod.GET)
	public void getBoardCategoryPage() {
		
	}
	
	@RequestMapping(value="/boardcategory", method=RequestMethod.POST)
	public String addBoardCategory(BoardCategoryVO vo, HttpServletRequest request,
			RedirectAttributes rttr) throws Exception {
		adminService.createBoardCategory(vo);
		
		// 사이드바 게시판 목록 업데이트 
		HttpSession session = request.getSession();
		List<BoardCategoryVO> categoryList = boardService.getBoardCategories();
		session.setAttribute("boardCategories", categoryList);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/boardcategory";
	}
	
	@RequestMapping(value="/claim", method=RequestMethod.GET)
	public void getClaimList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("claim list searchCri: "+cri);
		
		model.addAttribute("list", adminService.getClaimList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(adminService.countClaimList(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/readClaim", method=RequestMethod.GET)
	public void readClaim(@RequestParam("cno") int cno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		model.addAttribute(adminService.readClaim(cno));
	}
	
	@RequestMapping(value="/updateStatus", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> updateStatus(@RequestBody ClaimDTO dto) {
		logger.info("admin update status put method...");
		logger.info("dto: "+dto);
		
		ResponseEntity<String> entity = null;
		
		try {
			adminService.updateClaimStatus(dto);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/board/{bno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoardByAdmin(@PathVariable("bno") Integer bno) {
		logger.info("delete board by admin...");
		
		ResponseEntity<String> entity = null;
		
		try {
			adminService.deleteBoardByAdmin(bno);

			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/reply/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteReplyByAdmin(@PathVariable("rno") Integer rno) {
		logger.info("delete reply by admin...");
		
		ResponseEntity<String> entity = null;
		
		try {
			adminService.deleteReplyByAdmin(rno);
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
