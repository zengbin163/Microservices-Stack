package com.chihuo.food.domain.food.repository.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.food.repository.facade.FoodStockRepository;
import com.chihuo.food.domain.food.repository.mapper.FoodStockDao;
import com.chihuo.food.domain.food.repository.po.FoodStockPO;

@Repository
public class FoodStockRepositoryImpl implements FoodStockRepository {
	
	@Autowired
	private FoodStockDao foodStockDao;

	@Override
	public void save(FoodStockPO foodStockPO) {
		this.foodStockDao.save(foodStockPO);
	}

	@Override
	public void updateFoodStock(Long foodId, Integer stockNum, Integer version) {
		this.foodStockDao.updateFoodStock(foodId, stockNum, version);
	}

	@Override
	public void updateSellerId(Long foodId, Long sellerId) {
		this.foodStockDao.updateSellerId(foodId, sellerId);
	}
	
	@Override
	public FoodStockPO findFoodStockByFoodId(Long foodId) {
		return this.foodStockDao.findFoodStockByFoodId(foodId);
	}

	@Override
	public List<FoodStockPO> findFoodStockList() {
		return this.foodStockDao.findFoodStockList();
	}

}
