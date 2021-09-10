package com.ezen.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.webstore.domain.Product;
import com.ezen.webstore.domain.repository.ProductRepository;

//@formatter:off
@Repository
public class MariaProductRepository implements ProductRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllProducts() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM products", 
				params, new ProductMapper());
		return result;

	}

	@Override
	public List<Product> getAllProducts(String...args) {
		Map<String, Object> params = new HashMap<String, Object>();
		String query = "SELECT * FROM products";
		
		if (args.length == 1) {
			query += " WHERE LCASE(CATEGORY) = :category";
			params.put("category", args[0]);
		}
		List<Product> result = jdbcTemplate.query(query, 
				params, new ProductMapper());
		return result;
	}
	
	private static final class ProductMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("PROD_NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("PROD_CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
	}

	@Override
	public int updateStock(String productId, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET " +
				"UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productId);
		params.put("unitsInStock", noOfUnits);

		return jdbcTemplate.update(SQL, params);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		String SQL = "SELECT * FROM PRODUCTS " 
				+ "WHERE LCASE(CATEGORY) = :category";
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("category", category.toLowerCase());
		return jdbcTemplate.query(SQL, params, new ProductMapper());
	}

	@Override
	public List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {
		String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY "
				+ "IN (:categories) AND MANUFACTURER IN (:brands)";
		
		return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	}

	@Override
	public Product getProductById(String productID) {
		String SQL = "SELECT * FROM PRODUCTS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		
		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
	}

	@Override
	public List<Product> getProdsByMultiFilter(String productCategory, 
			Map<String, String> criteria, String brand) {
		var SQL = new StringBuilder("SELECT * FROM PRODUCTS");
		SQL.append(" WHERE CATEGORY = :category");
		SQL.append(" AND UNIT_PRICE >= :low And UNIT_PRICE <= :high");
		if (brand != null) {
			SQL.append(" AND MANUFACTURER = :brand ");
			criteria.put("brand", brand);
		}		
		criteria.put("category", productCategory); // **
		
		return jdbcTemplate.query(SQL.toString(), criteria, 
				new ProductMapper());
	}
	//@formatter:on
}
