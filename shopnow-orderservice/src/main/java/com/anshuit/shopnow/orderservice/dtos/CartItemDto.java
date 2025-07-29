package com.anshuit.shopnow.orderservice.dtos;

import java.time.LocalDateTime;

import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	private int cartItemId;
	private int productId;
	private int customerId;
	private LocalDateTime addedToCartDate;
	private ProductDto product;
}
