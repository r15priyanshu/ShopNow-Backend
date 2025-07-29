package com.anshuit.shopnow.customerservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String fullName;
	private String email;
	private String password;
	private String mobile;
	private String address;
	@Builder.Default
	private boolean admin = false;
	@Builder.Default
	private boolean registrationMailSent = false;
	@Builder.Default
	private boolean registrationSmsSent = false;
}
