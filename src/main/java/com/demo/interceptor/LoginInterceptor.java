package com.demo.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String requestMethod = request.getMethod();
		
		if(requestMethod.equals("POST")) {
			HttpSession session = request.getSession();

			ModelMap modelMap = modelAndView.getModelMap();
			Object userVO = modelMap.get("userVO");
			
			if(userVO != null) {
				logger.info("login success...");
				
				session.setAttribute(LOGIN, userVO);
				
				if(request.getParameter("useCookie") != null) {
					logger.info("remember me...");
					
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					loginCookie.setPath("/");
					loginCookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(loginCookie);
				}
				Object dest = session.getAttribute("dest");
				
				response.sendRedirect(dest != null ? (String)dest : "/");
			}
		}
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		String requestMethod = request.getMethod();
		
		if(requestMethod.equals("GET")) {
			Object dest = session.getAttribute("dest");
			
			if(dest == null) {
				String referer = request.getHeader("referer");
				String[] splitValue = referer.split("/", 4);
				String refererURI = "/" + splitValue[splitValue.length-1];
				
				session.setAttribute("dest", refererURI);
				logger.info("refererURI: "+refererURI);
			}
			
			return true;
		}
		
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before login...");
			
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
}
