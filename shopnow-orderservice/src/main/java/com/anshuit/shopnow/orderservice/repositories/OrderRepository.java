package com.anshuit.shopnow.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshuit.shopnow.orderservice.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findOrdersByCustomerId(int customerId);
}
