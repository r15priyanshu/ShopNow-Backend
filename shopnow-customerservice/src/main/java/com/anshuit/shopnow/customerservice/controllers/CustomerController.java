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

import com.anshuit.shopnow.customerservice.dtos.CustomerDto;
import com.anshuit.shopnow.customerservice.entities.Customer;
import com.anshuit.shopnow.customerservice.services.CustomerService;
import com.anshuit.shopnow.customerservice.services.DataTransferService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DataTransferService dataTransferService;

	@PostMapping("/login")
	public ResponseEntity<CustomerDto> doLogin(@RequestBody CustomerDto customerDto) {
		Customer foundCustomer = customerService.doLogin(customerDto.getEmail(), customerDto.getPassword());
		CustomerDto foundCustomerDto = dataTransferService.mapCustomerToCustomerDto(foundCustomer);
		return new ResponseEntity<>(foundCustomerDto, HttpStatus.OK);
	}

	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		Customer customer = dataTransferService.mapCustomerDToToCustomer(customerDto);
		Customer createdCustomer = customerService.createCustomer(customer);
		CustomerDto createdCustomerDto = dataTransferService.mapCustomerToCustomerDto(createdCustomer);
		return new ResponseEntity<CustomerDto>(createdCustomerDto, HttpStatus.CREATED);
	}

	@PutMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable("customerId") int customerId,
			@RequestBody CustomerDto customerDto) {
		Customer customer = dataTransferService.mapCustomerDToToCustomer(customerDto);
		Customer updatedCustomer = customerService.updateCustomerById(customerId, customer);
		CustomerDto updatedCustomerDto = dataTransferService.mapCustomerToCustomerDto(updatedCustomer);
		return new ResponseEntity<CustomerDto>(updatedCustomerDto, HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		List<CustomerDto> allCustomersDto = allCustomers.stream()
				.map(customer -> dataTransferService.mapCustomerToCustomerDto(customer)).toList();
		return new ResponseEntity<List<CustomerDto>>(allCustomersDto, HttpStatus.OK);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") int customerId) {
		Customer foundCustomer = customerService.getCustomerById(customerId);
		CustomerDto foundCustomerDto = dataTransferService.mapCustomerToCustomerDto(foundCustomer);
		return new ResponseEntity<CustomerDto>(foundCustomerDto, HttpStatus.OK);
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable("customerId") int customerId) {
		Customer deletedCustomer = customerService.deleteCustomerById(customerId);
		CustomerDto deletedCustomerDto = dataTransferService.mapCustomerToCustomerDto(deletedCustomer);
		return new ResponseEntity<CustomerDto>(deletedCustomerDto, HttpStatus.OK);
	}

}
