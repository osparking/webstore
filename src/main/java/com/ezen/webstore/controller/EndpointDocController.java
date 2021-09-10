package com.ezen.webstore.controller;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//@formatter:off
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
		var list = handlers.entrySet().stream()
				.map(e->e.getKey().toString())
				.sorted()
				.map(ls->ls.substring(2, ls.length()-2))
				.collect(Collectors.toList());
		model.addAttribute("reqPaths", list);
	}
}