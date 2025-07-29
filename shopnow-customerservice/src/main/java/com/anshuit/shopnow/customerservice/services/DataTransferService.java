package com.anshuit.shopnow.customerservice.services;

import com.anshuit.shopnow.customerservice.dtos.CustomerDto;
import com.anshuit.shopnow.customerservice.entities.Customer;

public interface DataTransferService {

	CustomerDto mapCustomerToCustomerDto(Customer customer);

	Customer mapCustomerDToToCustomer(CustomerDto customerDto);

}