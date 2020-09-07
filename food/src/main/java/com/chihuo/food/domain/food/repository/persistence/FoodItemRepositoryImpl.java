package com.chihuo.food.domain.food.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.food.repository.facade.FoodItemRepository;
import com.chihuo.food.domain.food.repository.mapper.FoodItemDao;
import com.chihuo.food.domain.food.repository.po.FoodItemPO;

@Repository
public class FoodItemRepositoryImpl implements FoodItemRepository {
	
    @Autowired
    private FoodItemDao foodItemDao;

	@Override
	public Long save(FoodItemPO foodItemPO) {
		this.foodItemDao.save(foodItemPO);
		return foodItemPO.getUid();
	}

	@Override
	public void delete(Long uid) {
		this.foodItemDao.delete(uid);
	}

	@Override
	public FoodItemPO findById(Long uid) {
		return this.foodItemDao.findById(uid);
	}

	@Override
	public List<FoodItemPO> queryFoodItemListByFoodId(Long foodId) {
		return this.foodItemDao.queryFoodItemListByFoodId(foodId);
	}

}
