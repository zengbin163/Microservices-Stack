package com.chihuo.sharding.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.sharding.domain.order.entity.Order;
import com.chihuo.sharding.domain.order.entity.OrderItem;
import com.chihuo.sharding.domain.order.service.OrderDomainService;

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
		return this.orderDomainService.countOrderList();
	}
	
	public List<OrderItem> findOrderItemList(Integer current, Integer size) {
		return this.orderDomainService.findOrderItemList(current, size);
	}
	
	public Integer findOrderItemCount() {
		return this.orderDomainService.countOrderItemList();
	}
	
	public List<Order> findOrderListByUserId(Integer current, Integer size, Long userId) {
		return this.orderDomainService.findOrderListByUserId(current, size, userId);
	}
	
	public List<Order> findOrderListBySellerId(Integer current, Integer size, Long sellerId) {
		return this.orderDomainService.findOrderListBySellerId(current, size, sellerId);
	}

	public Order findOrderById(Long uid) {
		return this.orderDomainService.findOrderById(uid);
	}

	public List<Order> refreshSellerIdOrderList(Long userId) {
		return this.orderDomainService.refreshSellerIdOrderList(userId);
	}

	public Integer countRefreshSellerIdOrderList(Long userId) {
		return this.orderDomainService.countRefreshSellerIdOrderList(userId);
	}

	public void updateOrderBatch(List<Order> list) {
		this.orderDomainService.updateOrderBatch(list);
	}

	public void updateOrderItemBatch(List<OrderItem> list) {
		this.orderDomainService.updateOrderItemBatch(list);
	}

}
