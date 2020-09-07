package com.chihuo.food.domain.food.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.food.repository.po.FoodItemPO;

public interface FoodItemDao extends BaseMapper<FoodItemPO> {

	void save(@Param("foodItemPO") FoodItemPO foodItemPO);

	void delete(Long uid);

	FoodItemPO findById(Long uid);

	List<FoodItemPO> queryFoodItemListByFoodId(Long foodId);

}
