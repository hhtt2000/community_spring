package com.demo.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.demo.domain.UserVO;
import com.demo.dto.LoginDTO;
import com.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Inject
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGet() {
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void loginPost(LoginDTO dto, Model model, HttpSession session) throws Exception {
		UserVO vo = service.login(dto);
		
		if(vo == null) {
			return;
		}
		
		model.addAttribute("userVO", vo);
		
		if(dto.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			
			Date sessionLimit = new Date(System.currentTimeMillis()+(amount*1000));
			
			service.keepLogin(vo.getUserid(), session.getId(), sessionLimit);
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			UserVO vo = (UserVO)obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUserid(), session.getId(), new Date());
			}
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegisterPage(Model model) {
		logger.info("user register get page...");
		
		model.addAttribute("userVO", new UserVO());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(@Valid UserVO vo, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("user register post...");
		
		if(bindingResult.hasErrors()) {
			logger.info("register post error...");
			
			return "user/register";
		}
		
		UserVO userVO = service.register(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("login", userVO);
		
		rttr.addFlashAttribute("msg", "회원가입을 축하합니다.");
		
		return "redirect:/";
	}
}
