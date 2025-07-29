package com.anshuit.shopnow.orderservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshuit.shopnow.orderservice.entities.CartItem;
import com.anshuit.shopnow.orderservice.services.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/carts/product/{productId}/customer/{customerId}")
	public ResponseEntity<CartItem> addToCart(@PathVariable("productId") int productId,
			@PathVariable("customerId") int customerId) {
		CartItem addedToCart = cartService.addItemToCart(productId, customerId);
		return new ResponseEntity<CartItem>(addedToCart, HttpStatus.CREATED);
	}

	@DeleteMapping("/carts/{cartItemId}")
	public ResponseEntity<CartItem> deleteFromCart(@PathVariable("cartItemId") int cartItemId) {
		CartItem deletedCartItem = cartService.deleteItemFromCart(cartItemId);
		return new ResponseEntity<CartItem>(deletedCartItem, HttpStatus.OK);
	}

	@GetMapping("/carts/customer/{customerId}")
	public ResponseEntity<List<CartItem>> getAllCartItemsByCustomerId(@PathVariable("customerId") int customerId) {
		List<CartItem> allItemsInCartByCustomerId = cartService.getAllItemsInCartByCustomerId(customerId);
		return new ResponseEntity<>(allItemsInCartByCustomerId, HttpStatus.OK);
	}
}
