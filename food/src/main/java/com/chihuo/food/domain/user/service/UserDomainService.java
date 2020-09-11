package com.chihuo.food.domain.user.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.api.component.event.value.CouponEventType;
import com.chihuo.api.component.topic.Topic;
import com.chihuo.food.domain.food.entity.Food;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.food.entity.Promotion;
import com.chihuo.food.domain.food.service.FoodDomainService;
import com.chihuo.food.domain.order.entity.Order;
import com.chihuo.food.domain.order.entity.OrderItem;
import com.chihuo.food.domain.order.service.OrderDomainService;
import com.chihuo.food.domain.seller.entity.SellerFood;
import com.chihuo.food.domain.seller.service.SellerDomainService;
import com.chihuo.food.domain.user.entity.ShoppingCat;
import com.chihuo.food.domain.user.entity.User;
import com.chihuo.food.domain.user.entity.UserAccount;
import com.chihuo.food.domain.user.event.CouponEvent;
import com.chihuo.food.domain.user.repository.facade.UserAccountRepository;
import com.chihuo.food.domain.user.repository.facade.UserRepository;
import com.chihuo.food.domain.user.repository.po.UserAccountPO;
import com.chihuo.food.domain.user.repository.po.UserPO;
import com.chihuo.food.infrastructure.common.api.RedisKey;
import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.api.value.Locks;
import com.chihuo.food.infrastructure.common.cache.DistributedCacheComponent;
import com.chihuo.food.infrastructure.common.cache.DistributedLocksComponent;
import com.chihuo.food.infrastructure.common.event.EventPublisher;
import com.chihuo.food.infrastructure.common.exception.BusinessException;
import com.chihuo.food.infrastructure.consumer.UidClientComponent;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;

@Service
public class UserDomainService {
	
	@Resource
	private UidClientComponent uidClientComponent;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private FoodDomainService foodDomainService;
	@Autowired
	private SellerDomainService sellerDomainService;
	@Autowired
	private OrderDomainService orderDomainService;
	@Autowired
	private DistributedLocksComponent locksComponent;
	@Autowired
	private DistributedCacheComponent cacheComponent;
	@Autowired
	private UserFactory userFactory;
	@Autowired
	private EventPublisher publisher;

	private static final Logger log = LoggerFactory.getLogger(UserDomainService.class);
	
	public BigDecimal createSingleOrder(Long userId, ShoppingCat shoppingCat) {
		long beginTime = System.currentTimeMillis();
		BigDecimal payAmount = this.createOrder(userId, shoppingCat);
		long endTime = System.currentTimeMillis();
		if(log.isInfoEnabled()) {
			log.info("创建订单耗时{}ms", (endTime-beginTime));
		}
		return payAmount;
	}
	
	@LcnTransaction
	@Transactional(rollbackFor = Exception.class)
	public BigDecimal createOrder(Long userId, ShoppingCat shoppingCat) {
		Long orderId = this.uidClientComponent.getUID();
		BigDecimal totalAmount = BigDecimal.ZERO;
		Map<Long, Integer> foodMap = shoppingCat.getShoppingFoodList();
		if(null == shoppingCat || null == foodMap) {
			throw new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "shopping cat is null");
		}
		List<OrderItem> logOrderItemList = new ArrayList<OrderItem>();
		for(Map.Entry<Long, Integer> entry : foodMap.entrySet()) {
			Long sellerFoodId = entry.getKey();
			Integer orderItemSum = entry.getValue();
			SellerFood sellerFood = this.sellerDomainService.findSellerFoodById(sellerFoodId);
			Long sellerId = sellerFood.getSellerId();
			Food food = this.foodDomainService.findById(sellerFood.getFoodId());
			if(null == food.getPrice()) {
				log.error("food id = {} price is null", food.getUid());
				continue;
			}
			BigDecimal orderItemAmount = food.getPrice().multiply(new BigDecimal(orderItemSum));
			Promotion promotion = this.foodDomainService.findBestPromotion(food.getUid(), orderItemAmount);
			OrderItem orderItem = new OrderItem();
			if (null != promotion) {
				if(orderItemAmount.compareTo(promotion.getDeductAmount()) <1) {
					orderItemAmount = BigDecimal.ZERO;
				} else {
					orderItemAmount = orderItemAmount.subtract(promotion.getDeductAmount());
				}	
				orderItem.setPromotionId(promotion.getUid());
				orderItem.setPromotionAmount(promotion.getDeductAmount());
			}
			JSONObject coupon = shoppingCat.getCoupon();
			if(null != coupon) {
				BigDecimal couponDeductAmount = coupon.getBigDecimal("deductAmount");
				if(orderItemAmount.compareTo(couponDeductAmount) <1) {
					orderItemAmount = BigDecimal.ZERO;
				} else {
					orderItemAmount = orderItemAmount.subtract(couponDeductAmount);
				}
				Long couponId = coupon.getLong("uid");
				orderItem.setCouponId(couponId);
				orderItem.setCouponAmount(couponDeductAmount);
				
				this.publisher.publish(CouponEvent.create(Topic.TOPIC_COUPON_SERVICE, CouponEventType.COUPON_USE, couponId.toString()));
			}
			
			FoodStock foodStock = this.foodDomainService.findFoodStockByFoodSellerId(sellerFood.getFoodId(), sellerFood.getSellerId());
			if(null == foodStock || foodStock.getStockNum() < orderItemSum) {
				log.error("foodId:{},sellerId:{} stock not enough!!!", sellerFood.getFoodId(), sellerFood.getSellerId());
				continue;
			}
			boolean flag = this.foodDomainService.updateFoodStock(sellerFood.getFoodId(), sellerFood.getSellerId(), (foodStock.getStockNum()-orderItemSum));
			
			if(flag) {
				Long orderItemId = this.uidClientComponent.getUID();
				orderItem.setUid(orderItemId);
				orderItem.setUserId(userId);
				orderItem.setSellerId(sellerId);
				orderItem.setFoodId(food.getUid());
				orderItem.setOrderId(orderId);
				orderItem.setFoodName(food.getFoodName());
				orderItem.setOrderItemAmount(orderItemAmount);
				orderItem.setOrderItemSum(orderItemSum);
				this.orderDomainService.saveOrderItem(orderItem);
			}
			
			totalAmount = totalAmount.add(orderItemAmount);
			logOrderItemList.add(orderItem);
		}
		Order order = new Order();
		order.setUid(orderId);
		order.setUserId(userId);
		order.setSellerId(null); // sellerId迁移至order_item中
		order.setOrderAmount(totalAmount);
		UserAccount userAccount = this.findUserAccountByUserId(userId);
		BigDecimal hongbaoAmount = userAccount.getHongbaoTotal();
		BigDecimal hongbaoDeductAmount = BigDecimal.ZERO;
		if(null == hongbaoAmount || BigDecimal.ZERO.compareTo(hongbaoAmount) > -1) {
			order.setHongbaoAmount(hongbaoDeductAmount);
		} else if(hongbaoAmount.compareTo(UserAccount.DEFAULT_DEDUCT_AMOUNT) < 1){
			if(totalAmount.compareTo(hongbaoAmount) < 1) {
				hongbaoDeductAmount = totalAmount;
			} else {
				hongbaoDeductAmount = userAccount.getHongbaoTotal();
			}
			order.setHongbaoAmount(hongbaoDeductAmount);
			totalAmount = totalAmount.subtract(hongbaoDeductAmount);
			this.updateHongbao(userId, hongbaoAmount.subtract(hongbaoDeductAmount));
		} else {
			if(totalAmount.compareTo(hongbaoAmount) < 1) {
				hongbaoDeductAmount = totalAmount;
			} else {
				hongbaoDeductAmount = UserAccount.DEFAULT_DEDUCT_AMOUNT;
			}
			order.setHongbaoAmount(hongbaoDeductAmount);
			totalAmount = totalAmount.subtract(hongbaoDeductAmount);
			this.updateHongbao(userId, hongbaoAmount.subtract(hongbaoDeductAmount));
		}
		this.orderDomainService.saveOrder(order);
		
		log.info("-----order : \n{}", JSONObject.toJSONString(order));
		log.info("-----orderItem : \n{}", JSONObject.toJSONString(logOrderItemList));
		
		BigDecimal payAmount = totalAmount;
		return payAmount;
	}
	
	
	public List<User> findUserList() {
		List<UserPO> poList = this.userRepository.findUserList();
		List<User> userList = new ArrayList<>();
		for(UserPO po : poList) {
			userList.add(this.userFactory.createUser(po));
		}
		return userList;
	}
	
	public List<User> findUserListRandom(Integer userCount) {
		List<UserPO> poList = this.userRepository.findUserListRandom(userCount);
		List<User> userList = new ArrayList<>();
		for(UserPO po : poList) {
			userList.add(this.userFactory.createUser(po));
		}
		return userList;
	}

	public List<UserAccount> findUserAccountList() {
		List<UserAccountPO> poList = this.userAccountRepository.findUserAccountList();
		List<UserAccount> userAccountList = new ArrayList<>();
		for(UserAccountPO po : poList) {
			userAccountList.add(this.userFactory.createUserAccount(po));
		}
		return userAccountList;
	}
	
	public UserAccount findUserAccountByUserId(Long userId) {
		String value = this.cacheComponent.get(RedisKey.KEY_USER_ACCOUNT, userId);
		UserAccount userAccount = null;
		if(StringUtils.isEmpty(value)) {
			UserAccountPO userAccountPO = this.userAccountRepository.findUserAccountByUserId(userId).orElseThrow(() -> new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "user account not exists"));
			userAccount = this.userFactory.createUserAccount(userAccountPO);
			this.cacheComponent.set(RedisKey.KEY_USER_ACCOUNT, userId, JSONObject.toJSONString(userAccount));
		} else {
			userAccount = JSONObject.parseObject(value, UserAccount.class);
		}
		return userAccount;
	}

	public boolean updateHongbao(Long userId, BigDecimal hongbaoTotal) {
		String lockKey = Locks.hongBaokey(userId);
		if(this.locksComponent.lock(lockKey)) {
			String value = this.cacheComponent.get(RedisKey.KEY_USER_ACCOUNT, userId);
			UserAccount userAccount = null;
			if(null == value) {
				UserAccountPO userAccountPO = this.userAccountRepository.findUserAccountByUserId(userId).orElseThrow(() -> new BusinessException(ResponseStatus.DATA_NOT_EXISTS, "data not exists!!!"));
				userAccount = this.userFactory.createUserAccount(userAccountPO);
			} else {
				if(log.isInfoEnabled()) {
					log.info("findUserAccountByUserId from cache!!!");
				}
				userAccount = JSONObject.parseObject(value, UserAccount.class);
			}
			userAccount.setHongbaoTotal(hongbaoTotal);
			this.cacheComponent.set(RedisKey.KEY_USER_ACCOUNT, userId, JSONObject.toJSONString(userAccount));
			this.userAccountRepository.updateHongbao(userId, hongbaoTotal);
			return this.locksComponent.release(lockKey);
		} else {
			log.error("can not get distributed locks, lockKey={}", lockKey);
			return false;
		}
	}
	
}
