package com.ezen.webstore.domain.repository.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.webstore.domain.Product;
import com.ezen.webstore.domain.repository.ProductRepository;
import com.ezen.webstore.exception.ProductNotFoundException;

import lombok.NoArgsConstructor;

//@formatter:off
@Repository
public class MariaProductRepository implements ProductRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllProducts(String root) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM products", 
				params, new ProductMapper(root));
		return result;
	}

	@Override
	public List<Product> getAllProducts(String root, String...args) {
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
	
	@NoArgsConstructor
	private static final class ProductMapper implements RowMapper<Product> {
		String root;
		ProductMapper(String root) {
			this.root = root;
		}
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			String prodId = rs.getString("ID");
			product.setProductId(prodId);
			product.setName(rs.getString("PROD_NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("PROD_CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			
			var blob = rs.getBlob("IMAGE");
			if (blob != null) {
				writeBytesToFile(root, blob.getBinaryStream(),
						"resources\\images\\", prodId, ".png");
			}
			
			blob = rs.getBlob("MANUAL");
			if (blob != null) {
				writeBytesToFile(root, blob.getBinaryStream(), 
						"resources\\pdf\\", prodId, ".pdf");
			}

			return product;
		}
		private void writeBytesToFile(String root, InputStream in, 
				String resoPath, String prodID, String ext3) {
			String dirPath = root + resoPath; 
					
			File directory = new File(dirPath);
			if (! directory.exists()) {
				directory.mkdirs();
			}
			StringBuilder filePathSB = new StringBuilder(dirPath);
			filePathSB.append(prodID);
			filePathSB.append(ext3);
			
			try (var out = new FileOutputStream(filePathSB.toString())) {
				byte [] buff = new byte[4096];
				int len = 0;
				
				while ((len = in.read(buff)) != -1) {
					out.write(buff, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		try {
			return jdbcTemplate.queryForObject(SQL, params, 
				new ProductMapper());
		} catch (DataAccessException e) {
			throw new ProductNotFoundException(productID);
		}
	}

	@Override
	public List<Product> getProdsByMultiFilter(String productCategory, 
			Map<String, String> criteria, Optional<String> brand) {
		var SQL = new StringBuilder("SELECT * FROM PRODUCTS");
		SQL.append(" WHERE CATEGORY = :category");
		SQL.append(" AND UNIT_PRICE >= :low And UNIT_PRICE <= :high");
		if (brand.isPresent()) {
			SQL.append(" AND MANUFACTURER = :brand ");
			criteria.put("brand", brand.get());
		}
		criteria.put("category", productCategory);
		
		return jdbcTemplate.query(SQL.toString(), criteria, 
				new ProductMapper());
	}
	//@formatter:on

	@Override
	public void addProduct(Product product) {
		var SQL = new StringBuilder("INSERT INTO PRODUCTS");
		SQL.append(" (ID, PROD_NAME, DESCRIPTION, UNIT_PRICE,");
		SQL.append(" MANUFACTURER, CATEGORY, PROD_CONDITION,");
		SQL.append(" UNITS_IN_STOCK, UNITS_IN_ORDER, DISCONTINUED");
		
		if (product.getProductImage().getSize() > 0) {
			SQL.append(", IMAGE");
		}
		
		if (product.getProductManual().getSize() > 0) {
			SQL.append(", MANUAL");
		}

		SQL.append(") VALUES (:id, :name, :desc, :price,");
		SQL.append(" :manufacturer, :category, :condition,");
		SQL.append(" :inStock, :inOrder, :discontinued"); 
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", product.getProductId());  
		params.put("name", product.getName());  
		params.put("desc", product.getDescription());  
		params.put("price", product.getUnitPrice());  
		params.put("manufacturer", product.getManufacturer());  
		params.put("category", product.getCategory());  
		params.put("condition", product.getCondition());  
		params.put("inStock", product.getUnitsInStock());  
		params.put("inOrder", product.getUnitsInOrder());  
		params.put("discontinued", product.isDiscontinued());  	
		try {
			if (product.getProductImage().getSize() > 0) {
				SQL.append(", :image"); 
				params.put("image", 
						product.getProductImage().getBytes());
			}
			if (product.getProductManual().getSize() > 0) {
				SQL.append(", :manual"); 
				params.put("manual", 
						product.getProductManual().getBytes());
			}
			SQL.append(")"); 
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		jdbcTemplate.update(SQL.toString(), params); 
	}

	@Override
	public void updateProduct(Product updated, String root) {
		var SQL = new StringBuilder("update PRODUCTS set");
		SQL.append(" PROD_NAME = :PROD_NAME,");
		SQL.append(" DESCRIPTION = :DESCRIPTION,");
		SQL.append(" UNIT_PRICE = :UNIT_PRICE,");
		SQL.append(" MANUFACTURER = :MANUFACTURER,");
		SQL.append(" CATEGORY = :CATEGORY,");
		SQL.append(" PROD_CONDITION = :PROD_CONDITION,");
		SQL.append(" UNITS_IN_STOCK = :UNITS_IN_STOCK,");
		SQL.append(" UNITS_IN_ORDER = :UNITS_IN_ORDER,");
		SQL.append(" DISCONTINUED = :DISCONTINUED");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PROD_NAME", updated.getName()); 		
		params.put("DESCRIPTION", updated.getDescription()); 		
		params.put("UNIT_PRICE", updated.getUnitPrice()); 		
		params.put("MANUFACTURER", updated.getManufacturer()); 		
		params.put("CATEGORY", updated.getCategory()); 		
		params.put("PROD_CONDITION", updated.getCondition()); 		
		params.put("UNITS_IN_STOCK", updated.getUnitsInStock()); 		
		params.put("UNITS_IN_ORDER", updated.getUnitsInOrder()); 		
		params.put("DISCONTINUED", updated.isDiscontinued()); 		
 
		try {
			if (updated.getProductImage().getSize() > 0) {
				SQL.append(", image = :image");
				params.put("image", 
						updated.getProductImage().getBytes());
			}
			if (updated.getProductManual().getSize() > 0) {
				SQL.append(", manual = :manual");
				params.put("manual", 
						updated.getProductManual().getBytes());
			}
			SQL.append(" where id = :id");
			params.put("id", updated.getProductId()); 	
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
		jdbcTemplate.update(SQL.toString(), params);
	}
}
