package com.anshuit.shopnow.customerservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.customerservice.dtos.CustomerDto;
import com.anshuit.shopnow.customerservice.entities.Customer;

@Service
public class DataTransferServiceImpl implements DataTransferService {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDto mapCustomerToCustomerDto(Customer customer) {
		return this.modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	public Customer mapCustomerDToToCustomer(CustomerDto customerDto) {
		return this.modelMapper.map(customerDto, Customer.class);
	}
}
