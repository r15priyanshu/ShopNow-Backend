package com.anshuit.shopnow.customerservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {
	private int customerId;
	private String fullName;
	private String email;
	private String password;
	private String mobile;
	private String address;
	private boolean admin;
	private boolean registrationMailSent;
	private boolean registrationSmsSent;
}
