package com.ezen.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("greeting", "환영합니다!");
		model.addAttribute("tagline", "세상에서 하나 뿐인 웹 가게");
		return "welcome";
	}
}
