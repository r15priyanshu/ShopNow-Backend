package com.anshuit.shopnow.orderservice.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.anshuit.shopnow.orderservice.dtos.ShippingDetailsDto;
import com.anshuit.shopnow.orderservice.entities.CartItem;
import com.anshuit.shopnow.orderservice.entities.Order;
import com.anshuit.shopnow.orderservice.exceptions.CustomException;
import com.anshuit.shopnow.orderservice.external.dtos.ProductDto;
import com.anshuit.shopnow.orderservice.external.services.ProductServiceExternal;
import com.anshuit.shopnow.orderservice.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductServiceExternal productServiceExternal;

	// THIS METHOD WILL FETCH EACH ITEMS IN THE CART AND CREATE ORDERS FOR EACH OF
	// THEM AND ULTIMATELY REMOVE ITEMS FROM THE CART
	@Override
	public List<Order> createOrder(int customerId, ShippingDetailsDto shippingDetailsDto) {
		List<CartItem> allItemsInCartByCustomerId = cartService.getAllItemsInCartByCustomerId(customerId);
		if (allItemsInCartByCustomerId.isEmpty())
			throw new CustomException("No Items In The Cart For Customer With Id : " + customerId,
					HttpStatus.NOT_FOUND);

		List<Order> createdOrders = allItemsInCartByCustomerId.stream().map((item) -> {
			// CREATING NEW ORDER
			Order newOrder = Order.builder().productId(item.getProductId()).customerId(customerId)
					.orderDate(LocalDateTime.now()).fullName(shippingDetailsDto.getFullName())
					.address(shippingDetailsDto.getAddress()).email(shippingDetailsDto.getEmail())
					.paymentType(shippingDetailsDto.getPaymentType()).mobile(shippingDetailsDto.getPaymentType())
					.build();
			orderRepository.save(newOrder);
			// NOW DELETE THE CART ITEM AFTER ORDER IS PLACED
			cartService.deleteItemFromCart(item.getCartItemId());
			return newOrder;
		}).collect(Collectors.toList());
		return createdOrders;
	}

	@Override
	public Optional<Order> getOrderByIdOptional(int orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	public Order getOrderById(int orderId) {
		Order foundOrder = getOrderByIdOptional(orderId)
				.orElseThrow(() -> new CustomException("Order Not Found With Id : " + orderId, HttpStatus.NOT_FOUND));
		return foundOrder;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> allOrders = orderRepository.findAll();
		return allOrders;
	}

	@Override
	public List<Order> getAllOrdersByCustomerId(int customerId) {
		List<Order> allOrders = orderRepository.findOrdersByCustomerId(customerId);
		allOrders = allOrders.stream().map((order) -> {
			ProductDto product = productServiceExternal.getProductById(order.getProductId()).getBody();
			order.setProduct(product);
			return order;
		}).collect(Collectors.toList());
		return allOrders;
	}
}
