package com.ezen.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.webstore.domain.repository.ProductRepository;
import com.ezen.webstore.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/update/stock") 
	public String updateAllStock() {
		int rc = productService.updateAllStock();
		System.out.println("updated row count: " + rc);
		return "redirect:/products"; 
	}
	
	@RequestMapping("/products") 
	public String list(Model model) { 
		model.addAttribute("products", productRepository.getAllProducts());
		return "products";
	} 
}

