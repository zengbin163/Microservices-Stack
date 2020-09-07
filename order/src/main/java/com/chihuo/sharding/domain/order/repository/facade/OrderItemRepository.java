package com.chihuo.sharding.domain.order.repository.facade;

import java.util.List;

import com.chihuo.sharding.domain.order.repository.po.OrderItemPO;

public interface OrderItemRepository{

	void save(OrderItemPO orderItemPO);
	
	List<OrderItemPO> findOrderItemList();
	
	Integer countOrderItemList();

	void updateOrderItemBatch(List<OrderItemPO> list);
	
}
