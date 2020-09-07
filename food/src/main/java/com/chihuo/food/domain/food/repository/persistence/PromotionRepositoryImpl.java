package com.chihuo.food.domain.food.repository.persistence;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chihuo.food.domain.food.repository.facade.PromotionRepository;
import com.chihuo.food.domain.food.repository.mapper.PromotionDao;
import com.chihuo.food.domain.food.repository.po.PromotionPO;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {

	@Autowired
	private PromotionDao promotionDao;
	
	@Override
	public void save(PromotionPO promotionPO) {
		this.promotionDao.save(promotionPO);
	}
	
	@Override
	public PromotionPO findPromotionByRule(Long foodId, BigDecimal orderItemAmount) {
		return this.promotionDao.findPromotionByRule(foodId, orderItemAmount);
	}

	@Override
	public List<PromotionPO> findPromotionListByFoodId(Long foodId) {
		return this.promotionDao.findPromotionListByFoodId(foodId);
	}

}
