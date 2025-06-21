package com.anshuit.shopnow.orderservice.services;

import java.time.LocalDateTime;
import java.util.List;
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
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;

	@Autowired
	ProductServiceExternal productServiceExternal;

	// This method will fetch each items in the cart and create orders for each of
	// them and
	// ultimately remove items from the cart
	@Override
	public List<Order> createOrder(Integer cid, ShippingDetailsDto shippingDetailsDto) {
		List<CartItem> allItemsInCartByCustomerId = cartService.getAllItemsInCartByCustomerId(cid);
		if (allItemsInCartByCustomerId.isEmpty())
			throw new CustomException("No items in the cart for customer with id:" + cid, HttpStatus.NOT_FOUND);

		List<Order> createdOrders = allItemsInCartByCustomerId.stream().map((item) -> {

			// creating new order
			Order newOrder = Order.builder().productid(item.getProductid()).customerid(cid)
					.orderdate(LocalDateTime.now()).fullname(shippingDetailsDto.getFullname())
					.address(shippingDetailsDto.getAddress()).email(shippingDetailsDto.getEmail())
					.paymenttype(shippingDetailsDto.getPaymenttype()).mobile(shippingDetailsDto.getPaymenttype())
					.build();
			orderRepository.save(newOrder);
			// now delete the cart item after order is placed
			cartService.deleteFromCart(item.getCartitemid());
			return newOrder;
		}).collect(Collectors.toList());
		return createdOrders;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> allOrders = orderRepository.findAll();
		return allOrders;
	}

	@Override
	public Order getOrderById(Integer oid) {
		Order foundOrder = orderRepository.findById(oid)
				.orElseThrow(() -> new CustomException("Order not found with id:" + oid, HttpStatus.NOT_FOUND));
		return foundOrder;
	}

	@Override
	public List<Order> getAllOrdersByCustomerId(Integer cid) {
		List<Order> allOrders = orderRepository.findOrdersByCustomerid(cid);
		allOrders = allOrders.stream().map((order) -> {
			ProductDto product = productServiceExternal.getProductById(order.getProductid()).getBody();
			order.setProduct(product);
			return order;
		}).collect(Collectors.toList());
		return allOrders;
	}
}
