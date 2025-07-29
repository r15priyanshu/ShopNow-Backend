package com.anshuit.shopnow.customerservice.external.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDto {
	private long orderId;
	private long productId;
	private long customerId;
	private LocalDateTime orderDate;
}
