package com.ezen.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		List<Product> allProducts = productRepository.getAllProducts("");

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
	public List<Product> getAllProducts(String root, String string) {
		return productRepository.getAllProducts(root, string);
	}

	@Override
	public List<Product> getAllProducts(String root) {
		return productRepository.getAllProducts(root);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	@Override
	public List<Product> getProdsByMultiFilter(String productCategory,
			Map<String, String> price, Optional<String> brand) {
		return productRepository.getProdsByMultiFilter(
				productCategory, price, brand);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

	@Override
	public void updateProduct(Product updatedProduct, String rootDirectory) {
		productRepository.updateProduct(updatedProduct, rootDirectory);
	}
}
