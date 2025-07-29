package com.anshuit.shopnow.asyncservice.events;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anshuit.shopnow.asyncservice.dtos.CustomerDto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AsyncEventProcessors {

	@Bean
	Function<CustomerDto, Integer> receiveRegistrationEmailRequest() {
		return (customerDto) -> {
			log.info("Received Registration Email Request : {}", customerDto);
			log.info("Sending Registration Email With Details : {}", customerDto);
			return customerDto.getCustomerId();
		};
	}
	
	@Bean
	Function<CustomerDto, Integer> receiveRegistrationSmsRequest() {
		return (customerDto) -> {
			log.info("Received Registration Sms Request : {}", customerDto);
			log.info("Sending Registration Sms With Details : {}", customerDto);
			return customerDto.getCustomerId();
		};
	}
}
