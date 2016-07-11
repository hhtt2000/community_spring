package com.demo.interceptor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demo.domain.BoardCategoryVO;
import com.demo.service.BoardService;

public class BoardCategoryInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(BoardCategoryInterceptor.class);
	
	@Inject
	private BoardService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("boardCategories") == null) {
			List<BoardCategoryVO> categoryList = service.getBoardCategories();
			
			session.setAttribute("boardCategories", categoryList);
		}
		
		return true;
	}

	
}
