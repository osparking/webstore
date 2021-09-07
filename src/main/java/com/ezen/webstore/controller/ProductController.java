package com.ezen.webstore.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.webstore.domain.Product;

@Controller
public class ProductController {
	@RequestMapping("/products") 
	public String list(Model model) { 
		Product iphone = new Product("P1234", "아이폰 6s", 
				new BigDecimal(500)); 
		iphone.setDescription("애플 아이폰 6s 스마트폰 "
				+ "디스플레이 규격: 4.00-inch 640x1136, "
				+ "후방 카메라: 8-megapixel");
		iphone.setCategory("Smartphone"); 
		iphone.setManufacturer("Apple"); 
		iphone.setUnitsInStock(1000); 
		model.addAttribute("product", iphone); // 모델에 추가
		return "products"; // 뷰 이름 반환
	} 
}

