package com.chihuo.sharding.interfaces.facade.provider;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chihuo.api.component.OrderItemComponent;
import com.chihuo.sharding.application.OrderApplicationService;
import com.chihuo.sharding.domain.order.entity.OrderItem;

import cn.hutool.core.net.URLDecoder;

@RestController
public class OrderItemComponentProvider implements OrderItemComponent {

	@Autowired
	private OrderApplicationService service;

	@Override
	public Long save(String json) {
		OrderItem orderItem = JSONObject.parseObject(json, OrderItem.class);
		return this.service.saveOrderItem(orderItem);
	}

	@Override
	public String findOrderItemList(Integer current, Integer size) {
		Assert.notNull(current, "current is null");
		Assert.notNull(size, "size is null");
		List<OrderItem> itemList = this.service.findOrderItemList(current, size);
		if(CollectionUtils.isEmpty(itemList)) {
			return null;
		}
		return JSONObject.toJSONString(itemList);
	}

	@Override
	public Integer findOrderItemCount() {
		return this.service.findOrderItemCount();
	}

	@Override
	public Integer updateOrderItemBatch(String json) {
		Assert.hasLength(json, "param json is null");
		List<OrderItem> itemList = JSONArray.parseArray(URLDecoder.decode(json, Charset.defaultCharset()), OrderItem.class);
		this.service.updateOrderItemBatch(itemList);
		return itemList.size();
	}

}
