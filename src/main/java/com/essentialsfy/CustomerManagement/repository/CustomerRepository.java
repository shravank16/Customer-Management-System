package com.essentialsfy.CustomerManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.essentialsfy.CustomerManagement.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
