package com.chihuo.sharding.domain.order.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.sharding.domain.order.repository.facade.OrderItemRepository;
import com.chihuo.sharding.domain.order.repository.mapper.OrderItemDao;
import com.chihuo.sharding.domain.order.repository.po.OrderItemPO;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public void save(OrderItemPO orderItemPO) {
		this.orderItemDao.save(orderItemPO);
	}

	@Override
	public List<OrderItemPO> findOrderItemList() {
		return this.orderItemDao.findOrderItemList();
	}

	@Override
	public Integer countOrderItemList() {
		return this.orderItemDao.countOrderItemList();
	}

	@Override
	public void updateOrderItemBatch(List<OrderItemPO> list) {
		for(OrderItemPO item : list) {
			this.orderItemDao.updateOrderItem(item);
		}
	}

}
