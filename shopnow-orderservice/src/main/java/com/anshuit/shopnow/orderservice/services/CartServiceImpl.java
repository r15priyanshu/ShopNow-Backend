package com.anshuit.shopnow.orderservice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.orderservice.entities.CartItem;
import com.anshuit.shopnow.orderservice.exceptions.CustomException;
import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;
import com.anshuit.shopnow.orderservice.external.services.ProductServiceExternal;
import com.anshuit.shopnow.orderservice.repositories.CartRepository;

import feign.FeignException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductServiceExternal productServiceExternal;

	private CartItem saveOrUpdateCartItem(CartItem cartItem) {
		return this.cartRepository.save(cartItem);
	}

	@Override
	public CartItem addItemToCart(int productId, int customerId) {
		ResponseEntity<ProductDto> product = null;
		try {
			product = productServiceExternal.getProductById(productId);
		} catch (FeignException e) {
			if (e.status() == HttpStatus.NOT_FOUND.value())
				throw new CustomException("Product Service Returned 404 , Product Not Found With Id : " + productId,
						HttpStatus.NOT_FOUND);
		}

		CartItem newItemInCart = CartItem.builder().addedToCartDate(LocalDateTime.now()).customerId(customerId)
				.productId(productId).product(product.getBody()).build();
		CartItem productAddedToCart = saveOrUpdateCartItem(newItemInCart);
		return productAddedToCart;
	}

	@Override
	public Optional<CartItem> getItemByIdOptional(int cartItemId) {
		return cartRepository.findById(cartItemId);
	}

	@Override
	public CartItem getItemById(int cartItemId) {
		CartItem cartItem = getItemByIdOptional(cartItemId).orElseThrow(
				() -> new CustomException("Cart Item Not Found With Id : " + cartItemId, HttpStatus.NOT_FOUND));
		return cartItem;
	}

	@Override
	public CartItem deleteItemFromCart(int cartItemId) {
		CartItem cartItem = getItemById(cartItemId);
		cartRepository.delete(cartItem);
		return cartItem;
	}

	@Override
	public List<CartItem> getAllItemsInCartByCustomerId(int customerId) {
		List<CartItem> allItemsInCart = cartRepository.findCartByCustomerId(customerId);
		// FETCH PRODUCT DETAILS FROM PRODUCT EXTERNAL MICROSERVICE FOR EACH OF THE
		// ITEMS IN THE CART

		List<CartItem> allItemsInCartWithProductDetails = allItemsInCart.stream().map((item) -> {
			item.setProduct(productServiceExternal.getProductById(item.getProductId()).getBody());
			return item;
		}).collect(Collectors.toList());
		return allItemsInCartWithProductDetails;
	}
}
