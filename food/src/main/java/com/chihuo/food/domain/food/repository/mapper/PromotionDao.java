package com.chihuo.food.domain.food.repository.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chihuo.food.domain.food.repository.po.PromotionPO;

public interface PromotionDao extends BaseMapper<PromotionPO> {

	void save(@Param("promotionPO") PromotionPO promotionPO);
	
	PromotionPO findPromotionByRule(@Param("foodId") Long foodId, @Param("orderItemAmount") BigDecimal orderItemAmount);

	List<PromotionPO> findPromotionListByFoodId(@Param("foodId") Long foodId);
}
