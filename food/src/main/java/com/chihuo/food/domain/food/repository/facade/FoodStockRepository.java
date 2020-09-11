package com.chihuo.food.domain.food.repository.facade;

import java.util.List;
import java.util.Optional;

import com.chihuo.food.domain.food.repository.po.FoodStockPO;

public interface FoodStockRepository {

	void save(FoodStockPO foodStockPO);

	void updateFoodStock(Long foodId, Long sellerId, Integer stockNum);

	void updateSellerId(Long foodId, Long sellerId);
	
	Optional<FoodStockPO> findFoodStockByFoodSellerId(Long foodId, Long sellerId);

	List<FoodStockPO> findFoodStockList();
	
}
