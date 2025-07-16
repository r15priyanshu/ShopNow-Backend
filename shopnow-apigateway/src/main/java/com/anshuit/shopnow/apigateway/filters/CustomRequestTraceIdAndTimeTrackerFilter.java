package com.anshuit.shopnow.apigateway.filters;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.anshuit.shopnow.apigateway.constants.GlobalConstants;

import reactor.core.publisher.Mono;

/**
 * THIS IS A GLOBAL FILTER AND HENCE IT WILL EXECUTE FOR EACH AND EVERY REQUEST
 * RECEIVED IN API GATEWAY.
 */
@Component
public class CustomRequestTraceIdAndTimeTrackerFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// GENERATE UNIQUE TRACE ID
		String traceId = UUID.randomUUID().toString();

		// RECORD REQUEST START TIME
		String startTime = LocalDateTime.now().toString();

		// SET TRACE ID AND START TIME IN RESPONSE HEADERS IMMEDIATELY
		exchange.getResponse().getHeaders().add(GlobalConstants.CUSTOM_TRACE_ID_HEADER, traceId);
		exchange.getResponse().getHeaders().add(GlobalConstants.CUSTOM_REQUEST_START_TIME_HEADER, startTime);

		// SAFELY ADD END TIME BEFORE RESPONSE IS COMMITTED
		exchange.getResponse().beforeCommit(() -> {
			String endTime = LocalDateTime.now().toString();
			exchange.getResponse().getHeaders().add(GlobalConstants.CUSTOM_REQUEST_END_TIME_HEADER, endTime);
			return Mono.empty();
		});

		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		// RUN THIS EARLY IN THE FILTER CHAIN
		return -100;
	}
}
