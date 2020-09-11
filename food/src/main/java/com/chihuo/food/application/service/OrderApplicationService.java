package com.chihuo.food.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.food.domain.order.entity.Order;
import com.chihuo.food.domain.order.entity.OrderItem;
import com.chihuo.food.domain.order.service.OrderDomainService;

@Service
public class OrderApplicationService {

	@Autowired
	private OrderDomainService orderDomainService;
	
	public Long saveOrder(Order order) {
		return this.orderDomainService.saveOrder(order);
	}

	public Long saveOrderItem(OrderItem orderItem) {
		return this.orderDomainService.saveOrderItem(orderItem);
	}
	
	public List<Order> findOrderList(Integer current, Integer size) {
		return this.orderDomainService.findOrderList(current, size);
	}

	public Integer findOrderCount() {
		return this.orderDomainService.findOrderCount();
	}

	public List<OrderItem> findOrderItemList(Integer current, Integer size) {
		return this.orderDomainService.findOrderItemList(current, size);
	}

	public Integer findOrderItemCount() {
		return this.orderDomainService.findOrderItemCount();
	}
	
	public List<Order> findUserOrderList(Integer current, Integer size, Long userId) {
		return this.orderDomainService.findUserOrderList(current, size, userId);
	}
	
	public List<Order> findSellerOrderList(Integer current, Integer size, Long sellerId) {
		return this.orderDomainService.findSellerOrderList(current, size, sellerId);
	}
	
	public Order findOrderById(Long uid) {
		return this.orderDomainService.findOrderById(uid);
	}
	
}
