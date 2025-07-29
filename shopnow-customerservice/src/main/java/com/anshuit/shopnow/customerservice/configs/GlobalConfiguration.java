package com.anshuit.shopnow.customerservice.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration {
	private ModelMapper modelMapper;

	@Bean
	ModelMapper getModelMapper() {
		this.modelMapper = new ModelMapper();
		return this.modelMapper;
	}
}
