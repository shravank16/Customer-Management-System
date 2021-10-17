package com.essentialsfy.CustomerManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.essentialsfy.CustomerManagement.Model.Customer;
import com.essentialsfy.CustomerManagement.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void addNewCustomer(Customer customer) {
		this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer cust = null;
		if(optional.isPresent()) {
			cust = optional.get();
		}else {
			throw new RuntimeException("Customer not found for id : " + id);
		}
		return cust;
	}

	@Override
	public void deleteCustomerById(long id) {
		this.customerRepository.deleteById(id);
	}

	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize) {
		Pageable pagable = PageRequest.of(pageNo - 1, pageSize);
		return this.customerRepository.findAll(pagable);
	}

}
