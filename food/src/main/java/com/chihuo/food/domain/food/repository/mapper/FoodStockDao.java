package com.chihuo.food.domain.food.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.food.repository.po.FoodStockPO;

public interface FoodStockDao extends BaseMapper<FoodStockPO> {

	void save(@Param("foodStockPO") FoodStockPO foodStockPO);

	void updateFoodStock(@Param("foodId") Long foodId, @Param("stockNum") Integer stockNum, @Param("version") Integer version);

	void updateSellerId(@Param("foodId") Long foodId, @Param("sellerId") Long sellerId);

	FoodStockPO findFoodStockByFoodId(Long foodId);

	List<FoodStockPO> findFoodStockList();
	
}
