package com.anshuit.shopnow.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshuit.shopnow.orderservice.entities.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
	List<CartItem> findCartByCustomerId(int customerId);
}
