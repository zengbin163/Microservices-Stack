package com.chihuo.food.domain.seller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.food.repository.facade.FoodStockRepository;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.seller.entity.Seller;
import com.chihuo.food.domain.seller.entity.SellerFood;
import com.chihuo.food.domain.seller.repository.facade.SellerFoodRepository;
import com.chihuo.food.domain.seller.repository.facade.SellerRepository;
import com.chihuo.food.domain.seller.repository.po.SellerFoodPO;
import com.chihuo.food.domain.seller.repository.po.SellerPO;
import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.api.value.Unit;
import com.chihuo.food.infrastructure.common.exception.BusinessException;
import com.chihuo.food.infrastructure.consumer.UidClientComponent;

@Service
public class SellerDomainService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private UidClientComponent uidClientComponent;
	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private SellerFoodRepository sellerFoodRepository;
	@Autowired
	private FoodStockRepository foodStockRepository;
	@Autowired
	private SellerFactory factory;
	
	private static final Logger log = LoggerFactory.getLogger(SellerDomainService.class);
	
	public void createSellerData() {
		for (int i = 0; i < 650; i++) {
			String s = "";
			if (String.valueOf(i).length() == 1) {
				s = "00" + i;
			} else if (String.valueOf(i).length() == 2) {
				s = "0" + i;
			} else {
				s = "" + i;
			}
			
			Long sellerId = this.uidClientComponent.getUID();
			SellerPO sellerPO = new SellerPO();
			sellerPO.setUid(sellerId);
			sellerPO.setMobile("19999999" + s);
			sellerPO.setPassword("222222" + s);
			sellerPO.setSellerName("seller-lisi" + s);
			this.sellerRepository.save(sellerPO);
			
			List<FoodPO> foodList = this.sellerFoodRepository.findSellerFoodListRandom();
			for(FoodPO foodPO : foodList) {
				Long foodId = foodPO.getUid();
				SellerFoodPO sellerFoodPO = new SellerFoodPO();
				sellerFoodPO.setUid(this.uidClientComponent.getUID());
				sellerFoodPO.setFoodId(foodId);
				sellerFoodPO.setSellerId(sellerId);
				this.sellerFoodRepository.save(sellerFoodPO);
				
				this.foodStockRepository.updateSellerId(foodId, sellerId);
			}
			
			log.error("seller info 第{}已处理", i);
		}
	}
	
	public List<Seller> findSellerList() {
		List<SellerPO> poList = this.sellerRepository.findSellerList();
		List<Seller> sellerList = new ArrayList<>();
		for(SellerPO po : poList) {
			sellerList.add(this.factory.createSeller(po));
		}
		return sellerList;
	}
	
	public List<SellerFood> findSellerFoodList() {
		List<SellerFoodPO> poList = this.sellerFoodRepository.findSellerFoodList();
		List<SellerFood> sellerFoodList = new ArrayList<>();
		for(SellerFoodPO po : poList) {
			sellerFoodList.add(this.factory.createSellerFood(po));
		}
		return sellerFoodList;
	}
	
	public Seller findSellerById(Long uid) {
		String key = RedisKey.KEY_SELLER.getKeyPrefix() + uid;
		String json = this.redisTemplate.opsForValue().get(key);
		Seller seller = null;
		if(StringUtils.isEmpty(json)) {
			SellerPO sellerPO = this.sellerRepository.findSellerById(uid).orElseThrow(() -> new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "seller not exists!!!"));
			Long timeout = RedisKey.KEY_SELLER.getTimeout();
			TimeUnit unit = Unit.of(RedisKey.KEY_SELLER.getUnit());
			seller = this.factory.createSeller(sellerPO);
			this.redisTemplate.opsForValue().set(key, JSONObject.toJSONString(seller), timeout, unit);
		} else {
			seller = JSONObject.parseObject(json, Seller.class);
		}
		return seller;
	}
	
	public SellerFood findSellerFoodById(Long uid) {
		String key = RedisKey.KEY_SELLER_FOOD.getKeyPrefix() + uid;
		String json = this.redisTemplate.opsForValue().get(key);
		SellerFood sellerFood = null;
		if(StringUtils.isEmpty(json)) {
			SellerFoodPO sellerFoodPO = this.sellerFoodRepository.findSellerFoodById(uid).orElseThrow(() -> new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "sellerFood not exists!!!"));
			sellerFood = this.factory.createSellerFood(sellerFoodPO);
			this.redisTemplate.opsForValue().set(key, JSONObject.toJSONString(sellerFood), RedisKey.KEY_SELLER_FOOD.getTimeout(), Unit.of(RedisKey.KEY_SELLER_FOOD.getUnit()));
		} else {
			if(log.isInfoEnabled()) {
				log.info("findSellerFoodById from cache!!!");
			}
			sellerFood = JSONObject.parseObject(json, SellerFood.class);
		}
		return sellerFood;
	}
	
}
