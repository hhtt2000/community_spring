package com.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;

public class InterceptorUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(InterceptorUtils.class);

	public static void makeFlashMessage(HttpServletRequest request, HttpServletResponse response, String message) {
		FlashMap flashMap = new FlashMap();
		
		flashMap.put("msg", message);
		
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}
}
