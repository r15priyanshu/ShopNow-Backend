package com.anshuit.shopnow.orderservice.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;


@FeignClient(name = "PRODUCT-MICROSERVICE", path = "/pms")
public interface ProductServiceExternal {

	@GetMapping("/products/{pid}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("pid") Integer pid);
}
