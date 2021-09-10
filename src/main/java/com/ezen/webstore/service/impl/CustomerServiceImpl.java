package com.ezen.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.webstore.domain.Customer;
import com.ezen.webstore.domain.Product;
import com.ezen.webstore.domain.repository.CustomerRepository;
import com.ezen.webstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;	
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepository.addCustomer(customer);
	}
}
