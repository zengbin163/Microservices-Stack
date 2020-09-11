package com.chihuo.food.application.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.user.entity.ShoppingCat;
import com.chihuo.food.domain.user.service.UserDomainService;
import com.chihuo.food.infrastructure.consumer.CouponClientComponent;

@Service
public class UserApplicationService {
	
	@Autowired
	private UserDomainService userDomainService;
	@Autowired
	private CouponClientComponent couponClientComponent;
	
	public BigDecimal createSingleOrder(Long userId, Long couponId, Map<Long, Integer> shoppingFoodList) {
		ShoppingCat shoppingCat = new ShoppingCat();
		shoppingCat.setShoppingFoodList(shoppingFoodList);
		String coupon = this.couponClientComponent.findUserCouponByUid(couponId);
		shoppingCat.setCoupon(JSONObject.parseObject(coupon));
		return this.userDomainService.createSingleOrder(userId, shoppingCat);
	}

}
