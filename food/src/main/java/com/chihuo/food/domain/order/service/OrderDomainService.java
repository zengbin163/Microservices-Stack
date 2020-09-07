package com.chihuo.food.domain.order.service;

import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.order.entity.Order;
import com.chihuo.food.domain.order.entity.OrderItem;
import com.chihuo.food.domain.seller.repository.facade.SellerFoodRepository;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;
import com.chihuo.food.domain.user.repository.facade.UserRepository;
import com.chihuo.food.domain.user.repository.po.UserPO;
import com.chihuo.food.infrastructure.consumer.OrderClientComponent;
import com.chihuo.food.infrastructure.consumer.OrderItemClientComponent;

import cn.hutool.core.net.URLEncoder;

@Service
public class OrderDomainService {
	
	@Resource
	private OrderClientComponent orderClientComponent;
	@Resource
	private OrderItemClientComponent orderItemClientComponent;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SellerFoodRepository sellerFoodRepository;
	
	private static final Logger log = LoggerFactory.getLogger(OrderDomainService.class);

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
	
	public void refreshOrder() {
		List<UserPO> userList = this.userRepository.findUserList();
		log.error("一共{}个用户", userList.size());
		for(UserPO user : userList) {
			Long userId = user.getUid();
			String json = this.orderClientComponent.refreshSellerIdOrderList(userId);
			List<Order> orderList = JSONArray.parseArray(json, Order.class);
			if(CollectionUtils.isEmpty(orderList)) {
				continue;
			}
			Long sellerId = null;
			Integer itemSum = 0;
			log.error("begin-test");
			for(Order order : orderList) {
				List<OrderItem> itemList = order.getItemList();
				for(OrderItem item : itemList) {
					SellerFoodPO sellerFood = this.sellerFoodRepository.findSellerIdByFoodId(item.getFoodId());
					if(null == sellerFood) {
						log.error("the seller is null, orderId={},foodId={}", order.getUid(), item.getFoodId());
						continue;
					}
					sellerId = sellerFood.getSellerId();
					item.setSellerId(sellerId);
				}
				order.setSellerId(sellerId);
				
				this.orderItemClientComponent.updateOrderItemBatch(URLEncoder.createDefault().encode(JSONObject.toJSONString(itemList), Charset.defaultCharset()));
				itemSum = itemSum + itemList.size();
			}
			this.orderClientComponent.updateOrderBatch(URLEncoder.createDefault().encode(JSONObject.toJSONString(orderList), Charset.defaultCharset()));
			log.error("用户:{}订单刷新完成，订单量:{}，订单明细量:{}", user.getUid(), orderList.size(), itemSum);
		}
	}

}
