package com.anshuit.shopnow.customerservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.anshuit.shopnow.customerservice.dtos.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponseDto> handleCustomException(CustomException e) {
		ApiResponseDto response = ApiResponseDto.builder().message(e.getMessage()).status(e.getStatus())
				.statuscode(e.getStatus().value()).timestamp(e.getTimestamp()).build();

		return new ResponseEntity<ApiResponseDto>(response, e.getStatus());
	}
}
