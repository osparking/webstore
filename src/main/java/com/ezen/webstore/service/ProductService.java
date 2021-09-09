package com.ezen.webstore.service;

import java.util.List;
import java.util.Map;

import com.ezen.webstore.domain.Product;

public interface ProductService {
	int updateAllStock();
	List<Product> getAllProducts();
	List<Product> getAllProducts(String string);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filter);
	Product getProductById(String productID);
	List<Product> getProdsByMultiFilter(String productCategory, 
			Map<String, String> price, String brand);
}
