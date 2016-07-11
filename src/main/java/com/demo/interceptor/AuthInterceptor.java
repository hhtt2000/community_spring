package com.demo.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.demo.domain.UserVO;
import com.demo.service.BoardService;
import com.demo.service.UserService;
import com.demo.util.InterceptorUtils;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private UserService userService;
	
	@Inject
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			logger.info("current user not logged in...");
			
			saveDest(request);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				UserVO userVO = userService.checkLoginBefore(loginCookie.getValue());
				
				logger.info("userVO: "+userVO);
				
				if(userVO != null) {
					session.setAttribute("login", userVO);
					return checkModuleAuth(request, response, session);
				}
			}
			
			response.sendRedirect("/user/login");
			return false;
		}
		
		return checkModuleAuth(request, response, session);
	}
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("destination: " + (uri+query));
			
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	
	/**
	 * 모듈 경로에 따른 권한 확인
	 */
	private boolean checkModuleAuth(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String uri = request.getRequestURI();
		logger.info("in authInterceptor request uri: "+uri); // /board/list
		
		String module = uri.split("/")[1]; // board
		
		if("board".equals(module)) {
			return checkBoardAuth(request, response, session);
		}
		if("admin".equals(module)) {
			UserVO loginInfo = (UserVO)session.getAttribute("login");
			String userAuth = loginInfo.getRole();
			
			if(userAuth.equals("admin")) {
				return true;
			} else {
				InterceptorUtils.makeFlashMessage(request, response, "일반 사용자는 접근할 수 없습니다.");
				
				response.sendRedirect("/");
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 게시판에 따른 권한을 체크하여 사용자가 접근 가능한지 판별
	 */
	private boolean checkBoardAuth(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String boardType = request.getParameter("boardType");
		
		logger.info("boardType in authInterceptor: "+boardType);
		
		String boardAuth = boardService.getBoardMinAuthority(boardType);
		
		UserVO loginInfo = (UserVO)session.getAttribute("login");
		String userAuth = loginInfo.getRole();
		
		if(userAuth.equals("admin")) {
			return true;
		}
		
		if(userAuth.compareTo(boardAuth) >= 0) {
			return true;
		} else {
			String level = boardAuth.split("_")[1];
			InterceptorUtils.makeFlashMessage(request, response, "레벨 "+level+"이상 입장 가능합니다.");
			
			response.sendRedirect("/");
			return false;
		}
	}
	
}
