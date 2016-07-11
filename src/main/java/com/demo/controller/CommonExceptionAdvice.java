package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView illegalArgException(IllegalArgumentException e) {
		logger.info("잘못된 요청...");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/page_temp");
		mav.addObject("exception", "잘못된 요청입니다.");
		
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		logger.info(e.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/page_temp");
		mav.addObject("exception", e.getMessage());
		
		return mav;
	}
}
