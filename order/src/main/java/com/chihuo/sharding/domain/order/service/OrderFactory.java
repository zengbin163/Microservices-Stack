package com.chihuo.sharding.domain.order.service;

import org.springframework.stereotype.Service;

import com.chihuo.sharding.domain.order.entity.Order;
import com.chihuo.sharding.domain.order.entity.OrderItem;
import com.chihuo.sharding.domain.order.repository.po.OrderItemPO;
import com.chihuo.sharding.domain.order.repository.po.OrderPO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class OrderFactory {

	public Order createOrder(OrderPO po) {
		if (null == po) {
			return null;
		}
		BeanCopier<Order> beanCopier = BeanCopier.create(po, new Order(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public OrderPO createOrderPO(Order order) {
		if (null == order) {
			return null;
		}
		BeanCopier<OrderPO> beanCopier = BeanCopier.create(order, new OrderPO(), CopyOptions.create());
		return beanCopier.copy();
	}

	public OrderItem createOrderItem(OrderItemPO po) {
		if (null == po) {
			return null;
		}
		BeanCopier<OrderItem> beanCopier = BeanCopier.create(po, new OrderItem(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public OrderItemPO createOrderItemPO(OrderItem orderItem) {
		if (null == orderItem) {
			return null;
		}
		BeanCopier<OrderItemPO> beanCopier = BeanCopier.create(orderItem, new OrderItemPO(), CopyOptions.create());
		return beanCopier.copy();
	}

}
