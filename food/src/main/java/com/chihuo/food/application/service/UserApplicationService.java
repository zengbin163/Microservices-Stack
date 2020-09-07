package com.chihuo.food.application.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.food.domain.user.entity.ShoppingCat;
import com.chihuo.food.domain.user.service.UserDomainService;

@Service
public class UserApplicationService {
	
	@Autowired
	private UserDomainService userDomainService;
	
	public BigDecimal createSingleOrder(Long userId, Map<Long, Integer> shoppingFoodList) {
		ShoppingCat shoppingCat = new ShoppingCat();
		shoppingCat.setShoppingFoodList(shoppingFoodList);
		return this.userDomainService.createSingleOrder(userId, shoppingCat);
	}

}
