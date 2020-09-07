package com.chihuo.food.domain.food.repository.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.facade.FoodRepository;
import com.chihuo.food.domain.food.repository.mapper.FoodDao;
import com.chihuo.food.domain.food.repository.po.FoodPO;

@Repository
public class FoodRepositoryImpl implements FoodRepository {
	
    @Autowired
    private FoodDao foodDao;

	@Override
	public void save(FoodPO foodPO) {
		this.foodDao.save(foodPO);
	}

	@Override
	public void update(FoodPO foodPO) {
		this.foodDao.update(foodPO);
	}

	@Override
	public Optional<FoodPO> findById(Long uid) {
		return this.foodDao.findById(uid);
	}

	@Override
	public List<FoodPO> queryFoodList(Page<?> page, Integer firstCategoryId, Integer secondCategoryId, String foodName) {
		return this.foodDao.queryFoodList(page, firstCategoryId, secondCategoryId, foodName);
	}

	@Override
	public List<FoodPO> findFoodListRandom(Integer foodCount) {
		return this.foodDao.findFoodListRandom(foodCount);
	}

	@Override
	public List<FoodPO> findFoodList() {
		return this.foodDao.findFoodList();
	}

	@Override
	public List<FoodPO> findFoodListByIds(List<Long> ids) {
		return this.foodDao.findFoodListByIds(ids);
	}

}
