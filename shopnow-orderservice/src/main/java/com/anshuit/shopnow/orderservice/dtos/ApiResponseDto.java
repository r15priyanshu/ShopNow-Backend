package com.anshuit.shopnow.orderservice.dtos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto {
	private String message;
	private LocalDateTime timestamp;
	private HttpStatus status;
	private int statuscode;

	public ApiResponseDto(String message, LocalDateTime timestamp, HttpStatus status, int statuscode) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.status = status;
		this.statuscode = statuscode;
	}
}