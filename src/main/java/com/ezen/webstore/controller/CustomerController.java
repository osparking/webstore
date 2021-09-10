package com.ezen.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.webstore.domain.Customer;
import com.ezen.webstore.domain.Product;
import com.ezen.webstore.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	

	@RequestMapping(value = "customers/add", method = RequestMethod.GET)
	public String addNewCustomer(
			@ModelAttribute("newCustomer") Customer newCustomer) {
		return "addCustomer";
	}	
	
	@RequestMapping(value = "customers/add", method = RequestMethod.POST)
	public String addNewCustomer(@ModelAttribute("newCustomer") 
			Customer newCustomer, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("엮어오려는 허용되지 않은 항목 : " + 
			StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		customerService.addCustomer(newCustomer);
		return "redirect:/customers";
	}	
	
	@RequestMapping("/customers") 
	public String list(Model model) {
		model.addAttribute("customers",
				customerService.getAllCustomers());
		return "customers";
	}

}
