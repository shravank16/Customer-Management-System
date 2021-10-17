package com.essentialsfy.CustomerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.essentialsfy.CustomerManagement.Model.Customer;
import com.essentialsfy.CustomerManagement.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findCustomersByPage(1, model);
	}
	
	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerForm(Model model) {
		Customer cust = new Customer();
		model.addAttribute("customer", cust);
		return "newCustomer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.addNewCustomer(customer);
		return "redirect:/";
	}
	
	@GetMapping("/showCustomerUpdateForm/{id}")
	public String updateCustomer(@PathVariable(value="id") long id, Model model) {
		Customer cust = customerService.getCustomerById(id);
		model.addAttribute("customer", cust);
		return "updateCustomer";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteEmployee(@PathVariable(value="id") long id) {
		customerService.deleteCustomerById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findCustomersByPage(@PathVariable(value="pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Customer> page = customerService.findPaginated(pageNo, pageSize);
		List<Customer> listOfCustomers = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listCustomers", listOfCustomers);
		return "index";
	}
}
