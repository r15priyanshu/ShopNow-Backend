package com.anshuit.shopnow.asyncservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {
	private int customerId;
	private String fullName;
	private String email;
	private String mobile;
}
