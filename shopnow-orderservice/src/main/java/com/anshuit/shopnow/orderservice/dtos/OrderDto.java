package com.anshuit.shopnow.orderservice.dtos;

import java.time.LocalDateTime;

import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	private int orderId;
	private int productId;
	private int customerId;
	private LocalDateTime orderDate;
	private String fullName;
	private String mobile;
	private String address;
	private String paymentType;
	private String email;
	private ProductDto product;
}
