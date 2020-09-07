package com.chihuo.food.domain.food.repository.facade;

import java.util.List;

import com.chihuo.food.domain.food.repository.po.FoodStockPO;

public interface FoodStockRepository {

	void save(FoodStockPO foodStockPO);

	void updateFoodStock(Long foodId, Integer stockNum, Integer version);

	void updateSellerId(Long foodId, Long sellerId);
	
	FoodStockPO findFoodStockByFoodId(Long foodId);

	List<FoodStockPO> findFoodStockList();
	
}
