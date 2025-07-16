package com.anshuit.shopnow.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfigurations {

	@Bean
	RouteLocator getCustomRouteLocator(RouteLocatorBuilder builder) {

		/*
		 * SUPPOSE WE HAVE /shopnow ALSO AS CUSTOM PREFIX BEFORE EUREKA APPLICATION NAME
		 * IN EVERY URL, THEN YOU HAVE TO MANUALLY WRITE THE ROUTE LOGIC TO TRANSFER IT
		 * TO DIFFERENT MS.
		 */

		/*
		 * USE .addRequestHeader() AND .addResponseHeader() ONLY FOR STATIC VALUES, AND
		 * USE CUSTOM FILTERS FOR DYNAMIC PER-REQUEST VALUES LIKE TRACE IDS.
		 */
		return builder.routes().route(p -> p.path("/shopnow/shopnow-customer-service/**")
				.filters(f -> f.rewritePath("/shopnow/shopnow-customer-service/?(?<remaining>.*)", "/${remaining}")
						.addResponseHeader("X-Application-Owner", "PRIYANSHU ANAND"))
				.uri("lb://SHOPNOW-CUSTOMER-SERVICE"))
				.route(p -> p.path("/shopnow/shopnow-product-service/**")
						.filters(f -> f
								.rewritePath("/shopnow/shopnow-product-service/?(?<remaining>.*)", "/${remaining}")
								.addResponseHeader("X-Application-Owner", "PRIYANSHU ANAND"))
						.uri("lb://SHOPNOW-PRODUCT-SERVICE"))
				.route(p -> p.path("/shopnow/shopnow-order-service/**")
						.filters(f -> f.rewritePath("/shopnow/shopnow-order-service/?(?<remaining>.*)", "/${remaining}")
								.addResponseHeader("X-Application-Owner", "PRIYANSHU ANAND"))
						.uri("lb://SHOPNOW-ORDER-SERVICE"))
				.build();
	}
}
