package com.chihuo.food.domain.food.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.repository.po.FoodPO;

public interface FoodDao extends BaseMapper<FoodPO> {

	void save(@Param("foodPO") FoodPO foodPO);

	void update(@Param("foodPO") FoodPO foodPO);

	Optional<FoodPO> findById(Long uid);

	List<FoodPO> queryFoodList(Page<?> page, @Param("firstCategoryId") Integer firstCategoryId, @Param("secondCategoryId") Integer secondCategoryId, @Param("foodName") String foodName);

	List<FoodPO> findFoodListRandom(@Param("foodCount") Integer foodCount);

	List<FoodPO> findFoodList();

	List<FoodPO> findFoodListByIds(List<Long> ids);

}
