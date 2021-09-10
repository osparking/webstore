package com.ezen.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class EndpointDocController {
	private final RequestMappingHandlerMapping handlerMapping;

	@Autowired
	public EndpointDocController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	@RequestMapping(value = "/endpointdoc", method = RequestMethod.GET)
	public void show(Model model) {
		var handlers = this.handlerMapping.getHandlerMethods();
		model.addAttribute("handlerMethods", handlers);
	}
}