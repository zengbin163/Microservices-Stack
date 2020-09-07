package com.chihuo.sharding.domain.order.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chihuo.sharding.domain.order.repository.po.OrderItemPO;

@Repository
@Mapper
public interface OrderItemDao {

	void save(@Param("orderItemPO") OrderItemPO orderItemPO);

	List<OrderItemPO> findOrderItemList();
	
	Integer countOrderItemList();
	
	void updateOrderItem(@Param("item") OrderItemPO orderItemPO);
	
}
