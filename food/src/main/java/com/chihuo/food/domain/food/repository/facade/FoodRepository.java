package com.chihuo.food.domain.food.repository.facade;

import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.po.FoodPO;

public interface FoodRepository {

	void save(FoodPO foodPO);

	void update(FoodPO foodPO);

	Optional<FoodPO> findById(Long uid);

	List<FoodPO> queryFoodList(Page<?> page, Integer firstCategoryId, Integer secondCategoryId, String foodName);

	List<FoodPO> findFoodListRandom(Integer foodCount);	
	
	List<FoodPO> findFoodList();
	
	List<FoodPO> findFoodListByIds(List<Long> ids);
	
}
