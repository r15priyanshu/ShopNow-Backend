package com.anshuit.shopnow.orderservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anshuit.shopnow.orderservice.dtos.ShippingDetailsDto;
import com.anshuit.shopnow.orderservice.entities.Order;
import com.anshuit.shopnow.orderservice.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders/customer/{customerId}")
	public ResponseEntity<List<Order>> createOrder(@PathVariable("customerId") int customerId,
			@RequestBody ShippingDetailsDto shippingDetailsDto) {
		List<Order> createdOrders = orderService.createOrder(customerId, shippingDetailsDto);
		return new ResponseEntity<>(createdOrders, HttpStatus.CREATED);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> allOrders = orderService.getAllOrders();
		return new ResponseEntity<>(allOrders, HttpStatus.OK);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId") int orderId) {
		Order foundOrder = orderService.getOrderById(orderId);
		return new ResponseEntity<>(foundOrder, HttpStatus.OK);
	}

	@GetMapping("/orders/customer/{customerId}")
	public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@PathVariable("customerId") int customerId) {
		List<Order> allOrdersByCustomerId = orderService.getAllOrdersByCustomerId(customerId);
		return new ResponseEntity<>(allOrdersByCustomerId, HttpStatus.OK);
	}
}
