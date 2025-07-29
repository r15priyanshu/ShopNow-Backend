package com.anshuit.shopnow.customerservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.customerservice.dtos.CustomerDto;
import com.anshuit.shopnow.customerservice.entities.Customer;

@Service
public class DataTransferServiceImpl {

	@Autowired
	private ModelMapper modelMapper;

	CustomerDto mapCustomerToCustomerDto(Customer customer) {
		return this.modelMapper.map(customer, CustomerDto.class);
	}

	Customer mapCustomerDToToCustomer(CustomerDto customerDto) {
		return this.modelMapper.map(customerDto, Customer.class);
	}
}
