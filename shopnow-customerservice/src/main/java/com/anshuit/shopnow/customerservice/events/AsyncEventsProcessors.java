package com.anshuit.shopnow.customerservice.events;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AsyncEventsProcessors {

	@Bean
	Consumer<Integer> receiveRegistrationEmailResponse() {
		return (customerId) -> {
			log.info("Received Registration Email Response : {}", customerId);
		};
	}

	@Bean
	Consumer<Integer> receiveRegistrationSmsResponse() {
		return (customerId) -> {
			log.info("Received Registration Sms Response : {}", customerId);
		};
	}
}
