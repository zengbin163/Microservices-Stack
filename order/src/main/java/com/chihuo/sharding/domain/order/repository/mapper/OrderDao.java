package com.chihuo.sharding.domain.order.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chihuo.sharding.domain.order.repository.po.OrderPO;

@Repository
@Mapper
public interface OrderDao {
	
	void save(@Param("orderPO") OrderPO orderPO);
	
	List<OrderPO> findOrderList();
	
	Integer countOrderList();
	
	List<OrderPO> findOrderListByUserId(@Param("userId") Long userId);

	List<OrderPO> findOrderListBySellerId(@Param("sellerId") Long sellerId);

	OrderPO findOrderById(@Param("uid") Long uid);
	
	List<OrderPO> refreshSellerIdOrderList(@Param("userId") Long userId);
	
	Integer countRefreshSellerIdOrderList(@Param("userId") Long userId);

	void updateOrder(@Param("item") OrderPO orderPO);
	
}