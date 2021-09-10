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

import com.ezen.webstore.domain.Customer;
import com.ezen.webstore.domain.Product;
import com.ezen.webstore.domain.repository.CustomerRepository;

//@formatter:off
@Repository
public class MariaCustomerRepository implements CustomerRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Customer> getAllCustomers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> result = jdbcTemplate.query(
				"SELECT * FROM customers", params, new CustomerMapper());
		return result;
	}
	
	private static final class CustomerMapper 
	implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) 
				throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("address"));
			customer.setNoOfOrdersMade(rs.getInt("noOfOrdersMade"));
			return customer;
		}
	}

	//@formatter:on
	@Override
	public void addCustomer(Customer customer) {
		var SQL = new StringBuilder("INSERT INTO customers");
		SQL.append(" (ID, NAME, ADDRESS, noOfOrdersMade)");
		SQL.append(" VALUES (:id, :name, :address, :noOfOrdersMade)");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getAddress());
		params.put("address", customer.getName());
		params.put("noOfOrdersMade", customer.getNoOfOrdersMade());

		jdbcTemplate.update(SQL.toString(), params);
	}
}
