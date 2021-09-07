package com.ezen.webstore.config;

import org.springframework.web.servlet.support.
	AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer 
		extends AbstractAnnotationConfigDispatcherServletInitializer {
	// AACDSInitializer
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// 콘트롤러 클래스와 뷰 파일을 알려줌
		return new Class[] { WebApplicationContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/app/*" };
	}
}
