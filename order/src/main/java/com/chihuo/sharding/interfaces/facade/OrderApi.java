package com.chihuo.sharding.interfaces.facade;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chihuo.sharding.application.OrderApplicationService;
import com.chihuo.sharding.domain.order.entity.Order;
import com.chihuo.sharding.domain.order.entity.OrderItem;
import com.chihuo.sharding.infrastructure.consumer.UidClientComponent;


@Controller
@RequestMapping("/order")
public class OrderApi {
	
    @Resource
    private OrderApplicationService service;
    @Resource
    private UidClientComponent uidClientComponent;

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam(value = "number") Integer number){
		for (int i = 0; i < number; i++) {
	        Order order = new Order();
	        Long orderId = uidClientComponent.getUID();
	        Long orderItemId = uidClientComponent.getUID();
	        Long userId = uidClientComponent.getUID();
	        order.setUid(orderId);
	        order.setUserId(userId);
	        order.setOrderAmount(BigDecimal.TEN);
	        order.setHongbaoAmount(BigDecimal.TEN);
	        this.service.saveOrder(order);
	        OrderItem orderItem = new OrderItem();
	        orderItem.setUid(orderItemId);
	        orderItem.setUserId(userId);
	        orderItem.setFoodId(1L);
			orderItem.setFoodName("f" + i);
	        orderItem.setCouponId(1L);
	        orderItem.setCouponAmount(BigDecimal.TEN);
	        orderItem.setPromotionId(1L);
	        orderItem.setPromotionAmount(BigDecimal.TEN);
	        orderItem.setOrderItemAmount(BigDecimal.TEN);
	        orderItem.setOrderItemSum(10);
	        orderItem.setOrderId(orderId);
	        this.service.saveOrderItem(orderItem);
    	}
        return "添加成功";
    }
    
    @RequestMapping("/orderList")
    @ResponseBody
    public List<Order> orderList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size) {
    	return this.service.findOrderList(current, size);
    }
    
    @RequestMapping("/userOrderList")
    @ResponseBody
    public List<Order> userOrderList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size, @RequestParam(value = "userId") Long userId) {
    	return this.service.findOrderListByUserId(current, size, userId);
    }
    
    @RequestMapping("/orderCount")
    @ResponseBody
    public Integer orderCount() {
    	return this.service.findOrderCount();
    }
    
    @RequestMapping("/itemList")
    @ResponseBody
    public List<OrderItem> itemList(@RequestParam(value = "current") Integer current, @RequestParam(value = "size") Integer size) {
    	return this.service.findOrderItemList(current, size);
    }
    
    @RequestMapping("/itemCount")
    @ResponseBody
    public Integer itemCount() {
    	return this.service.findOrderItemCount();
    }
    
    @RequestMapping("/countRefreshOrder")
    @ResponseBody
    public Integer countRefreshOrder() {
    	return this.service.countRefreshSellerIdOrderList(null);
    }

}
