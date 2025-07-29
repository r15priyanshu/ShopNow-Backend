package com.anshuit.shopnow.customerservice.services;

import java.util.List;
import java.util.Optional;

import com.anshuit.shopnow.customerservice.entities.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Customer updateCustomerById(int customerId, Customer customer);

	Optional<Customer> getCustomerByIdOptional(int customerId);

	Customer getCustomerById(int customerId);

	List<Customer> getAllCustomers();

	Customer deleteCustomerById(int customerId);

	Customer doLogin(String email, String password);

}
