package com.ezen.webstore.domain.repository;

import java.util.List;

import com.ezen.webstore.domain.Customer;

public interface CustomerRepository {
	List<Customer> getAllCustomers();
}
