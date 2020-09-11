package com.chihuo.sharding.domain.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.chihuo.sharding.domain.order.entity.Order;
import com.chihuo.sharding.domain.order.entity.OrderItem;
import com.chihuo.sharding.domain.order.repository.facade.OrderItemRepository;
import com.chihuo.sharding.domain.order.repository.facade.OrderRepository;
import com.chihuo.sharding.domain.order.repository.po.OrderItemPO;
import com.chihuo.sharding.domain.order.repository.po.OrderPO;
import com.chihuo.sharding.infrastructure.consumer.UidClientComponent;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.github.pagehelper.PageHelper;

@Service
public class OrderDomainService {

    @Resource
    private UidClientComponent uidClientComponent;
	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderFactory factory;

	@TxcTransaction(propagation = DTXPropagation.SUPPORTS)
	@Transactional(rollbackFor = Exception.class)
    public Long saveOrder(Order order) {
    	Long orderId = order.getUid();
    	if(null == orderId) {
    		orderId = this.uidClientComponent.getUID();
    		order.setUid(orderId);
    	}
    	this.orderRepository.save(this.factory.createOrderPO(order));
    	int i = 100/0;
    	return orderId;
    }

	@TxcTransaction(propagation = DTXPropagation.SUPPORTS)
	@Transactional(rollbackFor = Exception.class)
	public Long saveOrderItem(OrderItem orderItem) {
    	Long orderItemId = orderItem.getUid();
    	if(null == orderItemId) {
    		orderItemId = this.uidClientComponent.getUID();
    		orderItem.setUid(orderItemId);
    	}
		this.orderItemRepository.save(this.factory.createOrderItemPO(orderItem));
		return orderItemId;
	}

	public List<Order> findOrderList(Integer current, Integer size) {
		PageHelper.startPage(current, size);
		List<OrderPO> poList = this.orderRepository.findOrderList();
		List<Order> orderList = new ArrayList<>();
		for(OrderPO po : poList) {
			orderList.add(this.factory.createOrder(po));
		}
		return orderList;
	}
	
	public Integer countOrderList() {
		return this.orderRepository.countOrderList();
	}
	
	public List<OrderItem> findOrderItemList(Integer current, Integer size) {
		PageHelper.startPage(current, size);
		List<OrderItemPO> poList = this.orderItemRepository.findOrderItemList();
		List<OrderItem> itemList = new ArrayList<>();
		for(OrderItemPO po : poList) {
			itemList.add(this.factory.createOrderItem(po));
		}
		return itemList;
	}
	
	public Integer countOrderItemList() {
		return this.orderItemRepository.countOrderItemList();
	}

	public List<Order> findOrderListByUserId(Integer current, Integer size, Long userId) {
		PageHelper.startPage(current, size);
		List<OrderPO> poList = this.orderRepository.findOrderListByUserId(userId);
		List<Order> orderList = new ArrayList<>();
		for(OrderPO po : poList) {
			orderList.add(this.factory.createOrder(po));
		}
		return orderList;
	}
	
	public List<Order> findOrderListBySellerId(Integer current, Integer size, Long sellerId) {
		PageHelper.startPage(current, size);
		List<OrderPO> poList = this.orderRepository.findOrderListBySellerId(sellerId);
		List<Order> orderList = new ArrayList<>();
		for(OrderPO po : poList) {
			orderList.add(this.factory.createOrder(po));
		}
		return orderList;
	}

	public Order findOrderById(Long uid) {
		OrderPO orderPO = this.orderRepository.findOrderById(uid);
		return this.factory.createOrder(orderPO);
	}

	public List<Order> refreshSellerIdOrderList(Long userId) {
		List<OrderPO> poList = this.orderRepository.refreshSellerIdOrderList(userId);
		List<Order> orderList = new ArrayList<>();
		for(OrderPO po : poList) {
			orderList.add(this.factory.createOrder(po));
		}
		return orderList;
	}

	public Integer countRefreshSellerIdOrderList(Long userId) {
		return this.orderRepository.countRefreshSellerIdOrderList(userId);
	}

	public void updateOrderBatch(List<Order> list) {
		if(CollectionUtils.isEmpty(list)) {
			return;
		}
		List<OrderPO> poList = new ArrayList<>();
		for(Order order : list) {
			poList.add(this.factory.createOrderPO(order));
		}
		this.orderRepository.updateOrderBatch(poList);
	}

	public void updateOrderItemBatch(List<OrderItem> list) {
		if(CollectionUtils.isEmpty(list)) {
			return;
		}
		List<OrderItemPO> poList = new ArrayList<>();
		for(OrderItem item : list) {
			poList.add(this.factory.createOrderItemPO(item));
		}
		this.orderItemRepository.updateOrderItemBatch(poList);
	}
	
}
