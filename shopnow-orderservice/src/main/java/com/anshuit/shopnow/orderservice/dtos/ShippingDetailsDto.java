package com.anshuit.shopnow.orderservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailsDto {
	private String fullName;
	private String mobile;
	private String address;
	private String paymentType;
	private String email;
}
