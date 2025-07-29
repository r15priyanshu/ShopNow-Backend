package com.anshuit.shopnow.orderservice.entities;

import java.time.LocalDateTime;

import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int productId;
	private int customerId;
	private LocalDateTime orderDate;
	private String fullName;
	private String mobile;
	private String address;
	private String paymentType;
	private String email;

	@Transient
	private ProductDto product;
}
