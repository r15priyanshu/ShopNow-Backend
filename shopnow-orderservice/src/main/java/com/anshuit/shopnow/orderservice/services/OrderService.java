package com.anshuit.shopnow.orderservice.services;

import java.util.List;
import java.util.Optional;

import com.anshuit.shopnow.orderservice.dtos.ShippingDetailsDto;
import com.anshuit.shopnow.orderservice.entities.Order;

public interface OrderService {

	List<Order> createOrder(int customerId, ShippingDetailsDto shippingDetailsDto);

	Optional<Order> getOrderByIdOptional(int orderId);

	Order getOrderById(int orderId);

	List<Order> getAllOrders();

	List<Order> getAllOrdersByCustomerId(int customerId);
}
