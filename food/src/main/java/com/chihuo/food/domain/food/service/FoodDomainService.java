package com.chihuo.food.domain.food.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodItem;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.food.entity.Promotion;
import com.chihuo.food.domain.food.event.FoodEvent;
import com.chihuo.food.domain.food.event.FoodEventType;
import com.chihuo.food.domain.food.repository.facade.FoodItemRepository;
import com.chihuo.food.domain.food.repository.facade.FoodRepository;
import com.chihuo.food.domain.food.repository.facade.FoodStockRepository;
import com.chihuo.food.domain.food.repository.facade.PromotionRepository;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.food.repository.po.FoodStockPO;
import com.chihuo.food.domain.food.repository.po.PromotionPO;
import com.chihuo.food.domain.seller.service.SellerDomainService;
import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.api.value.Unit;
import com.chihuo.food.infrastructure.common.event.EventPublisher;
import com.chihuo.food.infrastructure.common.exception.BusinessException;
import com.chihuo.food.infrastructure.consumer.UidClientComponent;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.EscapeUtil;

@Service
public class FoodDomainService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EventPublisher eventPublisher;
	@Resource
    private UidClientComponent uidClientComponent;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private FoodStockRepository foodStockRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private FoodFactory foodFactory;

	private static final Logger log = LoggerFactory.getLogger(SellerDomainService.class);

	@Transactional(rollbackFor = Exception.class)
    public void create(Food food) throws Exception {
    	if(StringUtils.isBlank(food.getFoodName())) {
    		throw new IllegalArgumentException("foodName is null");
    	}
    	
    	if(StringUtils.isBlank(food.getFoodPic())) {
    		throw new IllegalArgumentException("foodPic is null");
    	}
    	
    	if(StringUtils.isBlank(food.getFoodInfo())) {
    		throw new IllegalArgumentException("foodInfo is null");
    	} else {
    		String foodInfo = food.getFoodInfo();
    		food.setFoodInfo(foodInfo);
    	}
    	
    	if(null == food.getCategory().getId()) {
    		throw new IllegalArgumentException("category id is null");
    	}
    	
    	if(CollectionUtil.isEmpty(food.getFoodItemList())) {
    		throw new IllegalArgumentException("foodItemList is empty");
    	}
	    
    	Long foodId = this.uidClientComponent.getUID();
    	food.setUid(foodId);
    	this.foodRepository.save(foodFactory.createFoodPO(food));
        List<FoodItem> foodItemList = food.getFoodItemList();
        for(FoodItem foodItem : foodItemList) {
        	foodItem.setUid(this.uidClientComponent.getUID());
        	foodItem.setFoodId(foodId);
        	this.foodItemRepository.save(foodFactory.createFoodItemPO(foodItem));
        }
		String key = RedisKey.KEY_FOOD.getKeyPrefix() + foodId;
		this.redisTemplate.opsForValue().set(key, JSONObject.toJSONString(food), RedisKey.KEY_FOOD.getTimeout(), Unit.of(RedisKey.KEY_FOOD.getUnit()));
    	this.eventPublisher.publish(FoodEvent.create(FoodEventType.PUBLISH_EVENT, food));
    }

    public void update(Food food) {
    	if(StringUtils.isNotBlank(food.getFoodInfo())) {
    		String foodInfo = food.getFoodInfo();
    		food.setFoodInfo(EscapeUtil.unescape(foodInfo));
    	}
    	this.foodRepository.update(foodFactory.createFoodPO(food));
		String key = RedisKey.KEY_FOOD.getKeyPrefix() + food.getUid();
		this.redisTemplate.opsForValue().set(key, JSONObject.toJSONString(food), RedisKey.KEY_FOOD.getTimeout(), Unit.of(RedisKey.KEY_FOOD.getUnit()));
    }

    public Food findById(Long uid) {
		String key = RedisKey.KEY_FOOD.getKeyPrefix() + uid;
		String json = this.redisTemplate.opsForValue().get(key);
		Food food = null;
		if(StringUtils.isEmpty(json)) {
			FoodPO po = this.foodRepository.findById(uid).orElseThrow(() -> new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "food not exists"));
			food = this.foodFactory.createFood(po);
			String value = JSONObject.toJSONString(food);
			this.redisTemplate.opsForValue().set(key, value, RedisKey.KEY_FOOD.getTimeout(), Unit.of(RedisKey.KEY_FOOD.getUnit()));
		} else {
			if(log.isInfoEnabled()) {
				log.info("food.findById from cache!!!");
			}
			food = JSONObject.parseObject(json, Food.class);
		}
    	return food;
    }

	public IPage<Food> queryFoodList(Integer current, Integer size, Integer firstCategoryId, Integer secondCategoryId, String foodName) {
		Page<Food> page = new Page<Food>(current, size);
		List<FoodPO> records = this.foodRepository.queryFoodList(page, firstCategoryId, secondCategoryId, foodName);
		page.setRecords(this.foodFactory.createFoodList(records));
		return page;
	}
	
	public List<Food> findFoodListRandom(Integer foodCount) {
		List<FoodPO> poList = this.foodRepository.findFoodListRandom(foodCount);
		List<Food> foodList = new ArrayList<>();
		for(FoodPO po : poList) {
			foodList.add(this.foodFactory.createFood(po));
		}
		return foodList;
	}

	public List<Food> findFoodList() {
		List<FoodPO> poList = this.foodRepository.findFoodList();
		List<Food> foodList = new ArrayList<>();
		for(FoodPO po : poList) {
			foodList.add(this.foodFactory.createFood(po));
		}
		return foodList;
	}
	
	public List<FoodStock> findFoodStockList() {
		List<FoodStockPO> poList = this.foodStockRepository.findFoodStockList();
		List<FoodStock> foodStockList = new ArrayList<>();
		for(FoodStockPO po : poList) {
			foodStockList.add(this.foodFactory.createFoodStock(po));
		}
		return foodStockList;
	}
	
	public List<Promotion> findPromotionListByFoodId(Long foodId) {
		String key = RedisKey.KEY_PROMOTION.getKeyPrefix() + foodId;
		Long timeout = RedisKey.KEY_PROMOTION.getTimeout();
		TimeUnit unit = Unit.of(RedisKey.KEY_PROMOTION.getUnit());
		Set<String> sets = this.redisTemplate.opsForZSet().reverseRange(key, 0, -1);
		List<Promotion> promotionList = new ArrayList<>();
		if(CollectionUtils.isEmpty(sets)) {
			List<PromotionPO> poList = this.promotionRepository.findPromotionListByFoodId(foodId);
			for(PromotionPO po : poList) {
				Promotion promotion = this.foodFactory.createPromotion(po);
				promotionList.add(promotion);
			    this.redisTemplate.opsForZSet().add(key, JSONObject.toJSONString(promotion), promotion.getTotalAmount().doubleValue());
				this.redisTemplate.expire(key, timeout, unit);
			}
		} else {
			if(log.isInfoEnabled()) {
				log.info("findPromotionListByFoodId from cache!!!");
			}
			for(String p : sets) {
				Promotion promotion = JSONObject.parseObject(p, Promotion.class);
				promotionList.add(promotion);
			}
		}
		return promotionList;
	}
	
	public Promotion findBestPromotion(Long foodId, BigDecimal orderItemAmount) {
		List<Promotion> promotionList = this.findPromotionListByFoodId(foodId);
		if(CollectionUtils.isEmpty(promotionList)) {
			return null;
		}
		for(Promotion promotion : promotionList) {
			if(orderItemAmount.compareTo(promotion.getTotalAmount()) > -1) {
				return promotion;
			}
		}
		return null;
	}
	
}