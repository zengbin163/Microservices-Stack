package com.chihuo.sharding.domain.order.repository.facade;

import java.util.List;

import com.chihuo.sharding.domain.order.repository.po.OrderPO;

public interface OrderRepository {

	void save(OrderPO orderPO);

	List<OrderPO> findOrderList();
	
	Integer countOrderList();

	List<OrderPO> findOrderListByUserId(Long userId);
	
	List<OrderPO> findOrderListBySellerId(Long sellerId);

	OrderPO findOrderById(Long uid);

	List<OrderPO> refreshSellerIdOrderList(Long userId);
	
	Integer countRefreshSellerIdOrderList(Long userId);

	void updateOrderBatch(List<OrderPO> list);
	
}
