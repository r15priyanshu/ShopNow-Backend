package com.anshuit.shopnow.customerservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anshuit.shopnow.customerservice.entities.Customer;
import com.anshuit.shopnow.customerservice.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<Customer> doLogin(@RequestBody Customer customer) {
		Customer loggedinuser = customerService.doLogin(customer.getEmail(), customer.getPassword());
		return new ResponseEntity<>(loggedinuser, HttpStatus.OK);
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer addedcustomer = customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(addedcustomer, HttpStatus.CREATED);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{cid}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable("cid") Integer cid) {
		Customer foundCustomer = customerService.deleteCustomerById(cid);
		return new ResponseEntity<Customer>(foundCustomer, HttpStatus.OK);
	}

	@PutMapping("/customers/{cid}")
	public ResponseEntity<Customer> updateCustomerById(@PathVariable("cid") Integer cid,
			@RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomerById(cid, customer);
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}

	@GetMapping("/customers/{cid}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("cid") Integer cid) {
		Customer foundCustomer = customerService.getCustomerById(cid);
		return new ResponseEntity<Customer>(foundCustomer, HttpStatus.OK);
	}
}
