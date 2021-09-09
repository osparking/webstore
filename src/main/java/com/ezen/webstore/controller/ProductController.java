package com.ezen.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.webstore.domain.repository.ProductRepository;
import com.ezen.webstore.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/update/stock") 
	public String updateAllStock() {
		productService.updateAllStock();
		return "redirect:/market/products"; 
	}
	
	@RequestMapping("/products") 
	public String list(Model model) { 
		model.addAttribute("products", 
				productService.getAllProducts());
		return "products";
	} 
	
	@RequestMapping("/products/{category}") 
	public String getProductsByCategory(Model model,
			@PathVariable String category) { 
		model.addAttribute("products", 
				productService.getProductsByCategory(category));
		
		return "products";
	} 
	
	@RequestMapping("/products/laptop")
	public String laptop(Model model) {
		model.addAttribute("products",
			productService.getAllProducts("laptop"));
		return "products";
	}
}

