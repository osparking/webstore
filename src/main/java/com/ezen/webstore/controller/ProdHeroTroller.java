package com.ezen.webstore.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.webstore.service.ProductService;

//@formatter:off
@Controller
@RequestMapping("market")
public class ProdHeroTroller {
	@Autowired
	private ProductService productService;

	@RequestMapping("/products/{category}/{price}")
	public String getProdsByMultiFilter(Model model, 
			@PathVariable("category") String productCategory,
			@MatrixVariable(pathVar = "price") Map<String, String> price,
			@RequestParam(value = "brand") Optional<String> brand) {
		model.addAttribute("products", productService.getProdsByMultiFilter(
				productCategory, price, brand));
		return "products";
	}
}
