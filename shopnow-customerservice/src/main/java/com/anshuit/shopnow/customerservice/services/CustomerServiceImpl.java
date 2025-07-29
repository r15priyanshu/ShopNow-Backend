package com.anshuit.shopnow.customerservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.customerservice.entities.Customer;
import com.anshuit.shopnow.customerservice.exceptions.CustomException;
import com.anshuit.shopnow.customerservice.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private StreamBridge streamBridge;

	@Override
	public Customer addCustomer(Customer customer) {
		if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent())
			throw new CustomException("Email already registered : " + customer.getEmail(), HttpStatus.BAD_REQUEST);
		Customer savedCustomer = customerRepository.save(customer);
		
		//NAME OF THE OUTPUT BINDING WILL BE USED TO SEND EVENTS
		boolean sendRegistrationEmailCommunication = streamBridge.send("sendRegistrationEmailRequest-out-0", savedCustomer);
		log.info("Registration Email Communication Sent : {}", sendRegistrationEmailCommunication);
		
		boolean sendRegistrationSmsCommunication = streamBridge.send("sendRegistrationSmsRequest-out-0", savedCustomer);
		log.info("Registration Sms Communication Sent : {}", sendRegistrationSmsCommunication);
		
		return savedCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer deleteCustomerById(Integer id) {
		Customer foundCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomException("Customer not found with id:" + id, HttpStatus.NOT_FOUND));
		customerRepository.delete(foundCustomer);
		return foundCustomer;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Customer foundCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomException("Customer not found with id:" + id, HttpStatus.NOT_FOUND));
		return foundCustomer;
	}

	@Override
	public Customer doLogin(String email, String password) {
		Customer foundCustomer = customerRepository.findCustomerByEmailAndPassword(email, password)
				.orElseThrow(() -> new CustomException("Invalid Credentials !!", HttpStatus.BAD_REQUEST));
		return foundCustomer;
	}

	@Override
	public Customer updateCustomerById(Integer cid, Customer customer) {
		Customer foundCustomer = customerRepository.findById(cid)
				.orElseThrow(() -> new CustomException("Customer not found with id:" + cid, HttpStatus.NOT_FOUND));

		foundCustomer.setFullName(customer.getFullName());
		foundCustomer.setAddress(customer.getAddress());
		foundCustomer.setMobile(customer.getMobile());
		foundCustomer.setPassword(customer.getPassword());

		Customer updatedCustomer = customerRepository.save(foundCustomer);
		return updatedCustomer;
	}
}
