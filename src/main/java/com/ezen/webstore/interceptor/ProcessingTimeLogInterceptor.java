package com.ezen.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProcessingTimeLogInterceptor 
	extends HandlerInterceptorAdapter {
//	implements HandlerInterceptor {
	private static final Logger LOGGER = 
			Logger.getLogger(ProcessingTimeLogInterceptor.class);
	static {
		LOGGER.setLevel(Level.INFO);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		String queryString = request.getQueryString() == null ?
				"" : "?" + request.getQueryString();
		String path = request.getRequestURL() + queryString;

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();

		LOGGER.info(String.format("%s 요청 처리에 소요된 시간(ms) : %s.",
				path, (endTime - startTime)));		
	}
}
