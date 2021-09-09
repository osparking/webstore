package com.ezen.webstore.service;

import java.util.List;

import com.ezen.webstore.domain.Product;

public interface ProductService {
	int updateAllStock();
	List<Product> getAllProducts();
	List<Product> getAllProducts(String string);
}
