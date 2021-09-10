package com.ezen.webstore.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ezen.webstore.domain.Product;

//@formatter:off
public interface ProductRepository {
	List<Product> getAllProducts();
	int updateStock(String productId, long noOfUnits);
	List<Product> getAllProducts(String... string);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams);
	Product getProductById(String productID);
	List<Product> getProdsByMultiFilter(String productCategory,
			Map<String, String> price, Optional<String> brand);
}
