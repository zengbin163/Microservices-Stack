package com.chihuo.food.domain.food.repository.facade;

import java.util.List;

import com.chihuo.food.domain.food.repository.po.FoodItemPO;

public interface FoodItemRepository {

	Long save(FoodItemPO foodItemPO);

	void delete(Long uid);

	FoodItemPO findById(Long uid);

	List<FoodItemPO> queryFoodItemListByFoodId(Long foodId);

}
