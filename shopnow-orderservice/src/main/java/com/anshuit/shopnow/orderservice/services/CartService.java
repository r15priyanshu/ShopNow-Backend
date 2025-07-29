package com.anshuit.shopnow.orderservice.services;

import java.util.List;
import java.util.Optional;

import com.anshuit.shopnow.orderservice.entities.CartItem;

public interface CartService {
	CartItem addItemToCart(int productId, int customerId);

	Optional<CartItem> getItemByIdOptional(int cartItemId);

	CartItem getItemById(int cartItemId);

	CartItem deleteItemFromCart(int cartItemId);

	List<CartItem> getAllItemsInCartByCustomerId(int customerId);
}
