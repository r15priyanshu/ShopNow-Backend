package com.anshuit.shopnow.customerservice.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anshuit.shopnow.customerservice.external.dtos.Order;

@FeignClient(name = "SHOPNOW-ORDER-SERVICE", path = "/oms")
public interface OrderServiceExternal {

	@GetMapping("/orders/customer/{cid}")
	public List<Order> getAllOrdersByCustomerId(@PathVariable("cid") int cid);
}
