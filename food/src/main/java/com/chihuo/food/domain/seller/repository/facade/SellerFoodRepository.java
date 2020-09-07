package com.chihuo.food.domain.seller.repository.facade;

import java.util.List;
import java.util.Optional;

import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;

public interface SellerFoodRepository {

	void save(SellerFoodPO sellerFoodPO);
	
	SellerFoodPO findSellerIdByFoodId(Long foodId);

	List<FoodPO> findSellerFoodListRandom();	
	
	List<SellerFoodPO> findSellerFoodList();
	
	Optional<SellerFoodPO> findSellerFoodById(Long uid);

}
