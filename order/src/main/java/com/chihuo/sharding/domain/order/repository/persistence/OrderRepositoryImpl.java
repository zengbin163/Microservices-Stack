package com.chihuo.sharding.domain.order.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.sharding.domain.order.repository.facade.OrderRepository;
import com.chihuo.sharding.domain.order.repository.mapper.OrderDao;
import com.chihuo.sharding.domain.order.repository.po.OrderPO;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void save(OrderPO orderPO) {
		this.orderDao.save(orderPO);
	}

	@Override
	public List<OrderPO> findOrderList() {
		return this.orderDao.findOrderList();
	}

	@Override
	public Integer countOrderList() {
		return this.orderDao.countOrderList();
	}

	@Override
	public List<OrderPO> findOrderListByUserId(Long userId) {
		return this.orderDao.findOrderListByUserId(userId);
	}

	@Override
	public List<OrderPO> findOrderListBySellerId(Long sellerId) {
		return this.orderDao.findOrderListBySellerId(sellerId);
	}

	@Override
	public OrderPO findOrderById(Long uid) {
		return this.orderDao.findOrderById(uid);
	}

	@Override
	public List<OrderPO> refreshSellerIdOrderList(Long userId) {
		return this.orderDao.refreshSellerIdOrderList(userId);
	}

	@Override
	public Integer countRefreshSellerIdOrderList(Long userId) {
		return this.orderDao.countRefreshSellerIdOrderList(userId);
	}

	@Override
	public void updateOrderBatch(List<OrderPO> list) {
		for(OrderPO item : list) {
			this.orderDao.updateOrder(item);
		}
	}

}
