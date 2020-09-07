package com.chihuo.food.domain.seller.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;

public interface SellerFoodDao extends BaseMapper<SellerFoodPO> {

	void save(@Param("sellerFoodPO") SellerFoodPO sellerFoodPO);

	SellerFoodPO findSellerIdByFoodId(@Param("foodId") Long foodId);
	
	List<FoodPO> findSellerFoodListRandom();

	List<SellerFoodPO> findSellerFoodList();
	
	Optional<SellerFoodPO> findSellerFoodById(@Param("uid") Long uid);
	
}
