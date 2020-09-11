package com.chihuo.food.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.food.service.FoodDomainService;

@Service
public class FoodApplicationService {
	
    @Autowired
    private FoodDomainService foodDomainService;

	public void createFood(Food food) throws Exception {
		this.foodDomainService.create(food);
	}

	public void updateFood(Food food) {
		this.foodDomainService.update(food);
	}

	public Food findById(Long uid) {
		return this.foodDomainService.findById(uid);
	}

	public IPage<Food> queryFoodList(Integer current, Integer size, Integer firstCategoryId, Integer secondCategoryId, String foodName) {
		return this.foodDomainService.queryFoodList(current, size, firstCategoryId, secondCategoryId, foodName);
	}
	
	public FoodStock findFoodStockByFoodSellerId(Long foodId, Long sellerId) {
		return this.foodDomainService.findFoodStockByFoodSellerId(foodId, sellerId);
	}
	
	public boolean updateFoodStock(Long foodId, Long sellerId, Integer stockNum) {
		return this.foodDomainService.updateFoodStock(foodId, sellerId, stockNum);
	}
}
