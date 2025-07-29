package com.anshuit.shopnow.customerservice.events;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anshuit.shopnow.customerservice.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AsyncEventsProcessors {

	@Autowired
	private CustomerService customerService;

	@Bean
	Consumer<Integer> receiveRegistrationEmailResponse() {
		return (customerId) -> {
			log.info("Received Registration Email Response : {}", customerId);
			customerService.updateRegistrationMailSent(customerId);
			log.info("Successfully Updated 'RegistrationMailSent' Column For Customer With Id : {}", customerId);
		};
	}

	@Bean
	Consumer<Integer> receiveRegistrationSmsResponse() {
		return (customerId) -> {
			log.info("Received Registration Sms Response : {}", customerId);
			customerService.updateRegistrationSmsSent(customerId);
			log.info("Successfully Updated 'RegistrationSmsSent' Column For Customer With Id : {}", customerId);
		};
	}
}
