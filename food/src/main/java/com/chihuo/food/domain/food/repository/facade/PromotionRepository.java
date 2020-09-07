package com.chihuo.food.domain.food.repository.facade;

import java.math.BigDecimal;
import java.util.List;

import com.chihuo.food.domain.food.repository.po.PromotionPO;

public interface PromotionRepository {

	void save(PromotionPO promotionPO);

	PromotionPO findPromotionByRule(Long foodId, BigDecimal orderItemAmount);
	
	List<PromotionPO> findPromotionListByFoodId(Long foodId);
}
