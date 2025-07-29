package com.anshuit.shopnow.customerservice.services;

import java.util.List;
import java.util.Optional;

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

	private Customer saveOrUpdateCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		boolean isCustomerPresent = customerRepository.findCustomerByEmail(customer.getEmail()).isPresent();

		if (isCustomerPresent) {
			throw new CustomException("Email Already Registered : " + customer.getEmail(), HttpStatus.BAD_REQUEST);
		}
		Customer createdCustomer = saveOrUpdateCustomer(customer);

		// NAME OF THE OUTPUT BINDING WILL BE USED TO SEND EVENTS
		boolean sendRegistrationEmailCommunication = streamBridge.send("sendRegistrationEmailRequest-out-0",
				createdCustomer);
		log.info("Registration Email Communication Sent : {}", sendRegistrationEmailCommunication);

		boolean sendRegistrationSmsCommunication = streamBridge.send("sendRegistrationSmsRequest-out-0", createdCustomer);
		log.info("Registration Sms Communication Sent : {}", sendRegistrationSmsCommunication);

		return createdCustomer;
	}

	@Override
	public Customer updateCustomerById(int customerId, Customer customer) {
		Customer foundCustomer = getCustomerById(customerId);
		foundCustomer.setFullName(customer.getFullName());
		foundCustomer.setAddress(customer.getAddress());
		foundCustomer.setMobile(customer.getMobile());
		foundCustomer.setPassword(customer.getPassword());
		Customer updatedCustomer = saveOrUpdateCustomer(foundCustomer);
		return updatedCustomer;
	}

	@Override
	public Optional<Customer> getCustomerByIdOptional(int customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer foundCustomer = getCustomerByIdOptional(customerId).orElseThrow(
				() -> new CustomException("Customer Not Found With Id:" + customerId, HttpStatus.NOT_FOUND));
		return foundCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer deleteCustomerById(int customerId) {
		Customer foundCustomer = getCustomerById(customerId);
		customerRepository.delete(foundCustomer);
		return foundCustomer;
	}

	@Override
	public Customer doLogin(String email, String password) {
		Customer foundCustomer = customerRepository.findCustomerByEmailAndPassword(email, password)
				.orElseThrow(() -> new CustomException("Invalid Credentials !!", HttpStatus.BAD_REQUEST));
		return foundCustomer;
	}
}
