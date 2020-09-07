package com.chihuo.sharding.interfaces.facade.provider;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chihuo.api.component.OrderComponent;
import com.chihuo.sharding.application.OrderApplicationService;
import com.chihuo.sharding.domain.order.entity.Order;

import cn.hutool.core.net.URLDecoder;

@RestController
public class OrderComponentProvider implements OrderComponent {

	@Autowired
	private OrderApplicationService service;

	@Override
	public Long save(String json) {
		Order order = JSONObject.parseObject(json, Order.class);
		return this.service.saveOrder(order);
	}

	@Override
	public String findOrderList(Integer current, Integer size) {
		Assert.notNull(current, "current is null");
		Assert.notNull(size, "size is null");
		List<Order> orderList = this.service.findOrderList(current, size);
		if(CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		return JSONObject.toJSONString(orderList);
	}

	@Override
	public Integer findOrderCount() {
		return this.service.findOrderCount();
	}

	@Override
	public String findUserOrderList(Integer current, Integer size, Long userId) {
		Assert.notNull(current, "current is null");
		Assert.notNull(size, "size is null");
		Assert.notNull(userId, "userId is null");
		List<Order> orderList = this.service.findOrderListByUserId(current, size, userId);
		if(CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		return JSONObject.toJSONString(orderList);
	}

	@Override
	public String findSellerOrderList(Integer current, Integer size, Long sellerId) {
		Assert.notNull(current, "current is null");
		Assert.notNull(size, "size is null");
		Assert.notNull(sellerId, "sellerId is null");
		List<Order> orderList = this.service.findOrderListBySellerId(current, size, sellerId);
		if(CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		return JSONObject.toJSONString(orderList);	
	}

	@Override
	public String findOrderById(Long uid) {
		Assert.notNull(uid, "uid is null");
		Order order = this.service.findOrderById(uid);
		if(null == order) {
			return null;
		}
		return JSONObject.toJSONString(order);	
	}

	@Override
	public String refreshSellerIdOrderList(Long userId) {
		Assert.notNull(userId, "userId is null");
		List<Order> orderList = this.service.refreshSellerIdOrderList(userId);
		if(CollectionUtils.isEmpty(orderList)) {
			return null;
		}
		return JSONObject.toJSONString(orderList);	
	}

	@Override
	public Integer countRefreshSellerIdOrderList(Long userId) {
		Assert.notNull(userId, "userId is null");
		return this.service.countRefreshSellerIdOrderList(userId);
	}

	@Override
	public Integer updateOrderBatch(String json) {
		Assert.hasLength(json, "param json is null");
		List<Order> orderList = JSONArray.parseArray(URLDecoder.decode(json, Charset.defaultCharset()), Order.class);
		this.service.updateOrderBatch(orderList);
		return orderList.size();
	}


}
