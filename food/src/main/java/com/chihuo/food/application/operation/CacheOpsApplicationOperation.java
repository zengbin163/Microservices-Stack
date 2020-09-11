package com.chihuo.food.application.operation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.food.entity.Promotion;
import com.chihuo.food.domain.food.service.FoodDomainService;
import com.chihuo.food.domain.seller.entity.Seller;
import com.chihuo.food.domain.seller.entity.SellerFood;
import com.chihuo.food.domain.seller.service.SellerDomainService;
import com.chihuo.food.domain.user.entity.User;
import com.chihuo.food.domain.user.entity.UserAccount;
import com.chihuo.food.domain.user.service.UserDomainService;
import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.cache.DistributedCacheComponent;

@Service
public class CacheOpsApplicationOperation {

	private static final Logger log = LoggerFactory.getLogger(CacheOpsApplicationOperation.class);
	@Autowired
	private DistributedCacheComponent distributedCacheComponent;
	@Autowired
	private FoodDomainService foodDomainService;
	@Autowired
	private UserDomainService userDomainService;
	@Autowired
	private SellerDomainService sellerDomainService;
	
	public void refreshFood() {
		log.error("CacheRefreshApplicationService.refreshFood begin...");
		List<Food> foodList = this.foodDomainService.findFoodList();
		for(Food food : foodList) {
			String value = JSONObject.toJSONString(food);
			this.distributedCacheComponent.set(RedisKey.KEY_FOOD, food.getUid(), value);
		}
		log.error("CacheRefreshApplicationService.refreshFood end...{} food was cached", foodList.size());
	}

	public void refreshFoodStock() {
		log.error("CacheRefreshApplicationService.refreshFoodStock begin...");
		List<FoodStock> foodStockList = this.foodDomainService.findFoodStockList();
		for(FoodStock foodStock : foodStockList) {
			String value = JSONObject.toJSONString(foodStock);
			String key = foodStock.getFoodId() + "_" + foodStock.getSellerId();
			this.distributedCacheComponent.set(RedisKey.KEY_FOOD_STOCK, key, value);
		}
		log.error("CacheRefreshApplicationService.refreshFoodStock end...{} foodStock was cached", foodStockList.size());
	}

	public void refreshUser() {
		log.error("CacheRefreshApplicationService.refreshUser begin...");
		List<User> userList = this.userDomainService.findUserList();
		for(User user : userList) {
			String value = JSONObject.toJSONString(user);
			this.distributedCacheComponent.set(RedisKey.KEY_USER, user.getUid(), value);
		}
		log.error("CacheRefreshApplicationService.refreshUser end...{} user was cached", userList.size());
	}

	public void refreshUserAccount() {
		log.error("CacheRefreshApplicationService.refreshUserAccount begin...");
		List<UserAccount> userAccountList = this.userDomainService.findUserAccountList();
		for(UserAccount userAccount : userAccountList) {
			String value = JSONObject.toJSONString(userAccount);
			this.distributedCacheComponent.set(RedisKey.KEY_USER_ACCOUNT, userAccount.getUserId(), value);
		}
		log.error("CacheRefreshApplicationService.refreshUserAccount end...{} userAccount was cached", userAccountList.size());
	}
	
	public void refreshSeller() {
		log.error("CacheRefreshApplicationService.refreshSeller begin...");
		List<Seller> sellerList = this.sellerDomainService.findSellerList();
		for(Seller seller : sellerList) {
			String value = JSONObject.toJSONString(seller);
			this.distributedCacheComponent.set(RedisKey.KEY_SELLER, seller.getUid(), value);
		}
		log.error("CacheRefreshApplicationService.refreshSeller end...{} seller was cached", sellerList.size());
	}
	
	public void refreshSellerFood() {
		log.error("CacheRefreshApplicationService.refreshSellerFood begin...");
		List<SellerFood> sellerFoodList = this.sellerDomainService.findSellerFoodList();
		for(SellerFood sellerFood : sellerFoodList) {
			String value = JSONObject.toJSONString(sellerFood);
			this.distributedCacheComponent.rightPush(RedisKey.KEY_SELLER_FOOD, sellerFood.getUid(), value);
		}
		log.error("CacheRefreshApplicationService.refreshSellerFood end...{} sellerFood was cached", sellerFoodList.size());
	}
	
	public void refreshPromotion() {
		log.error("CacheRefreshApplicationService.refreshPromotion begin...");
		Integer totalSum = 0;
		List<Food> foodList = this.foodDomainService.findFoodList();
		for (Food food : foodList) {
			List<Promotion> promotionList = this.foodDomainService.findPromotionListByFoodId(food.getUid());
			for(Promotion promotion : promotionList) {
				String value = JSONObject.toJSONString(promotion);
				this.distributedCacheComponent.add(RedisKey.KEY_PROMOTION, food.getUid(), value, promotion.getTotalAmount().doubleValue());
			}
			totalSum = totalSum + promotionList.size();
		}
		log.error("CacheRefreshApplicationService.refreshPromotion end...{} promotion was cached", totalSum);
	}
	
	public void clear(String keysPattern) {
		this.distributedCacheComponent.clear(keysPattern);
	}
	
}
