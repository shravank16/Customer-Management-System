package com.essentialsfy.CustomerManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.essentialsfy.CustomerManagement.Model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void addNewCustomer(Customer customer);
	Customer getCustomerById(long id) throws RuntimeException;
	void deleteCustomerById(long id);
	Page<Customer> findPaginated(int pageNo, int pageSize);
}
