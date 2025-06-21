package com.anshuit.shopnow.orderservice.services;

import java.util.List;

import com.anshuit.shopnow.orderservice.dtos.ShippingDetailsDto;
import com.anshuit.shopnow.orderservice.entities.Order;

public interface OrderService {
	
	List<Order> createOrder(Integer cid,ShippingDetailsDto shippingDetailsDto);
	Order getOrderById(Integer oid);
	List<Order> getAllOrders();
	List<Order> getAllOrdersByCustomerId(Integer cid);
}
