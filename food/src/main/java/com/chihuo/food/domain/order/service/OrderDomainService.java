package com.chihuo.food.domain.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.order.entity.Order;
import com.chihuo.food.domain.order.entity.OrderItem;
import com.chihuo.food.infrastructure.consumer.OrderClientComponent;
import com.chihuo.food.infrastructure.consumer.OrderItemClientComponent;

@Service
public class OrderDomainService {
	
	@Resource
	private OrderClientComponent orderClientComponent;
	@Resource
	private OrderItemClientComponent orderItemClientComponent;
	
	public Long saveOrder(Order order) {
		return this.orderClientComponent.save(JSONObject.toJSONString(order));
	}

	public List<Order> findOrderList(Integer current, Integer size) {
		String json = this.orderClientComponent.findOrderList(current, size);
		List<Order> orderList = JSONObject.parseArray(json, Order.class);
		return orderList;
	}

	public Integer findOrderCount() {
		return this.orderClientComponent.findOrderCount();
	}

	public Long saveOrderItem(OrderItem orderItem) {
		return this.orderItemClientComponent.save(JSONObject.toJSONString(orderItem));
	}

	public List<OrderItem> findOrderItemList(Integer current, Integer size) {
		String json = this.orderItemClientComponent.findOrderItemList(current, size);
		List<OrderItem> itemList = JSONObject.parseArray(json, OrderItem.class);
		return itemList;
	}

	public Integer findOrderItemCount() {
		return this.orderItemClientComponent.findOrderItemCount();
	}
	
	public List<Order> findUserOrderList(Integer current, Integer size, Long userId) {
		String json = this.orderClientComponent.findUserOrderList(current, size, userId);
		List<Order> orderList = JSONObject.parseArray(json, Order.class);
		return orderList;
	}
	
	public List<Order> findSellerOrderList(Integer current, Integer size, Long sellerId) {
		String json = this.orderClientComponent.findSellerOrderList(current, size, sellerId);
		List<Order> orderList = JSONObject.parseArray(json, Order.class);
		return orderList;
	}
	
	public Order findOrderById(Long uid) {
		String json = this.orderClientComponent.findOrderById(uid);
		Order order = JSONObject.parseObject(json, Order.class);
		return order;
	}
	
}
