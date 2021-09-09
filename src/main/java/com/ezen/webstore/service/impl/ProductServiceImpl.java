package com.ezen.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.webstore.domain.Product;
import com.ezen.webstore.domain.repository.ProductRepository;
import com.ezen.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public int updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();

		int count = 0;
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) {
				count += productRepository.updateStock(
						product.getProductId(), 
						product.getUnitsInStock() + 1000);
			}
		}
		return count;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	@Override
	public List<Product> getAllProducts(String string) {
		return productRepository.getAllProducts(string);
	}
}
