package com.chihuo.food.domain.seller.repository.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.seller.repository.facade.SellerFoodRepository;
import com.chihuo.food.domain.seller.repository.mapper.SellerFoodDao;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;

@Repository
public class SellerFoodRepositoryImpl implements SellerFoodRepository {

	@Autowired
	private SellerFoodDao sellerFoodDao;
	
	@Override
	public void save(SellerFoodPO sellerFoodPO) {
		this.sellerFoodDao.save(sellerFoodPO);
	}
	
	@Override
	public SellerFoodPO findSellerIdByFoodId(Long foodId) {
		return this.sellerFoodDao.findSellerIdByFoodId(foodId);
	}

	@Override
	public List<FoodPO> findSellerFoodListRandom() {
		return this.sellerFoodDao.findSellerFoodListRandom();
	}

	@Override
	public List<SellerFoodPO> findSellerFoodList() {
		return this.sellerFoodDao.findSellerFoodList();
	}

	@Override
	public Optional<SellerFoodPO> findSellerFoodById(Long uid) {
		return this.sellerFoodDao.findSellerFoodById(uid);
	}

}
