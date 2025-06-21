package com.anshuit.shopnow.orderservice.services;

import java.util.List;

import com.anshuit.shopnow.orderservice.entities.CartItem;

public interface CartService {
	CartItem addToCart(Integer productid,Integer customerid);
	CartItem deleteFromCart(Integer cartitemid);
	List<CartItem> getAllItemsInCartByCustomerId(Integer customerid);
}
