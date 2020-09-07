package com.chihuo.food.application.operation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.food.domain.category.repository.po.CategoryPO;
import com.chihuo.food.domain.food.repository.facade.FoodStockRepository;
import com.chihuo.food.domain.food.repository.facade.PromotionRepository;
import com.chihuo.food.domain.food.repository.po.FoodPO;
import com.chihuo.food.domain.food.repository.po.FoodStockPO;
import com.chihuo.food.domain.food.repository.po.PromotionPO;
import com.chihuo.food.domain.food.service.FoodDomainService;
import com.chihuo.food.domain.food.service.FoodFactory;
import com.chihuo.food.domain.user.entity.ShoppingCat;
import com.chihuo.food.domain.user.entity.User;
import com.chihuo.food.domain.user.repository.facade.UserAccountRepository;
import com.chihuo.food.domain.user.repository.facade.UserRepository;
import com.chihuo.food.domain.user.repository.po.UserAccountPO;
import com.chihuo.food.domain.user.repository.po.UserPO;
import com.chihuo.food.domain.user.service.UserDomainService;
import com.chihuo.food.infrastructure.consumer.CouponClientComponent;
import com.chihuo.food.infrastructure.consumer.UidClientComponent;

@Service
public class DataApplicationOperation {
	
	private static final Integer DEFAULT_COUNT = 1000;
	
	@Resource
	private UidClientComponent uidClientComponent;
	@Resource
	private CouponClientComponent couponClientComponent;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private FoodStockRepository foodStockRepository;
	@Autowired
	private UserDomainService userDomainService;
	@Autowired
	private FoodDomainService foodDomainService;
	@Autowired
	private FoodFactory foodFactory;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	private TransactionDefinition transactionDefinition;
	
	private static final Logger log = LoggerFactory.getLogger(DataApplicationOperation.class);
	
	public Integer createBatchOrder(Integer userCount, Integer foodCount) {
    	Integer pages = 1;
    	if(userCount > DEFAULT_COUNT) {
    		pages = (userCount % DEFAULT_COUNT) == 0 ? (userCount / DEFAULT_COUNT) : (userCount / DEFAULT_COUNT) + 1;
    	}
		for (int page = 0; page < pages; page++) {
			TransactionStatus transactionStatus = null;
			try {
				Integer count = DEFAULT_COUNT;
				if(page == pages-1) {
					count = userCount - (DEFAULT_COUNT * (pages-1));
				}
				transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
				log.error("------transaction begin isCompleted={},isNewTransaction={},isRollbackOnly={}", transactionStatus.isCompleted(), transactionStatus.isNewTransaction(), transactionStatus.isRollbackOnly());
		    	long beginTime = System.currentTimeMillis();
				for (int i = 0; i < count; i++) {
		        	ShoppingCat shoppingCat = new ShoppingCat();
		        	Long userId = ShoppingCat.userId();
					Map<Long, Integer> shoppingFoodList = new HashMap<>();
		        	List<Long> sellerFoodIds = ShoppingCat.randomSellerFoodIds();
		    		for(Long sellerFoodId : sellerFoodIds) {
		    			Integer sum = (int) (Math.random() * 20 + 1);
		    	    	shoppingFoodList.put(sellerFoodId, sum);
		    		}
		        	shoppingCat.setShoppingFoodList(shoppingFoodList);
		        	long createOrderBeginTime = System.currentTimeMillis();
		        	this.userDomainService.createOrder(userId, shoppingCat);
		        	long createOrderEndTime = System.currentTimeMillis();
		        	log.error("用户{}创建订单耗时{}ms", userId, (createOrderEndTime-createOrderBeginTime));
		    	}
				platformTransactionManager.commit(transactionStatus);
				log.error("------transaction commit isCompleted={},isNewTransaction={},isRollbackOnly={}", transactionStatus.isCompleted(), transactionStatus.isNewTransaction(), transactionStatus.isRollbackOnly());
		    	long endTime = System.currentTimeMillis();
				log.error("--------第{}页，{}条提交成功，耗时：{} (ms)", (page + 1), count, (endTime-beginTime));
			} catch (Exception e) {
				platformTransactionManager.rollback(transactionStatus);
				log.error("------transaction rollback isCompleted={},isNewTransaction={},isRollbackOnly={}", transactionStatus.isCompleted(), transactionStatus.isNewTransaction(), transactionStatus.isRollbackOnly());
				e.printStackTrace();
			}
		}
		return userCount;
	}
	
	public void createUserData() {
		for (int q = 0; q < 50; q++) {
			TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
			try {
				for (int i = 1; i < 101; i++) {
					Long userId = uidClientComponent.getUID();
					UserPO userPO = new UserPO();
					userPO.setUid(userId);
					String s = "";
					if (String.valueOf(i).length() == 1) {
						s = "00" + i;
					} else if (String.valueOf(i).length() == 2) {
						s = "0" + i;
					} else {
						s = "" + i;
					}
					userPO.setMobile("18888888" + s);
					userPO.setPassword("11111" + s);
					userPO.setUserName("zhangsan" + s);
					this.userRepository.save(userPO);
					
					Long userAccountId = this.uidClientComponent.getUID();
					UserAccountPO userAccountPO = new UserAccountPO();
					userAccountPO.setUid(userAccountId);
					userAccountPO.setUserId(userId);
					userAccountPO.setHongbaoTotal(new BigDecimal(10000000));
					this.userAccountRepository.save(userAccountPO);
					
					for (int j = 0; j < 10; j++) {
						JSONObject JSON = new JSONObject();
						JSON.put("userId", userId);
						Long categoryId = (long) (Math.random() * 5 + 1);
						JSON.put("categoryId", categoryId);
						JSON.put("isUsed", 0);
						JSON.put("totalAmount", new BigDecimal(500 + j * 100));
						JSON.put("deductAmount", new BigDecimal(100 + j * 100));
						this.couponClientComponent.save(JSON.toJSONString());
					}
				}
			
				for (int j = 0; j < 10; j++) {
					Long promotionId = this.uidClientComponent.getUID();
					PromotionPO promotionPO = new PromotionPO();
					promotionPO.setUid(promotionId);
					promotionPO.setPromotionRule(2);
					promotionPO.setTotalAmount(new BigDecimal(800 + j));
					promotionPO.setDeductAmount(new BigDecimal(300));
					Long categoryId = (long) (Math.random() * 5 + 1);
					promotionPO.setCategoryId(categoryId);
					this.promotionRepository.save(promotionPO);
				}
			
				for (int i = 1; i < 101; i++) {
					String s = "";
					if (String.valueOf(i).length() == 1) {
						s = "00" + i;
					} else if (String.valueOf(i).length() == 2) {
						s = "0" + i;
					} else {
						s = "" + i;
					}
		
					Long foodId = this.uidClientComponent.getUID();
					FoodPO foodPO = new FoodPO();
					foodPO.setUid(foodId);
					CategoryPO categoryPO = new CategoryPO();
					categoryPO.setId((int) (Math.random() * 5 + 1));
					foodPO.setCategory(categoryPO);
					foodPO.setFoodName("fanque" + s);
					foodPO.setFoodPic("www.pic.com/" + s);
					foodPO.setFoodInfo("foodInfo" + s);
					foodPO.setPrice(new BigDecimal(3500));
					this.foodDomainService.create(this.foodFactory.createFood(foodPO));
					
					Long foodStockId = this.uidClientComponent.getUID();
					FoodStockPO foodStockPO = new FoodStockPO();
					foodStockPO.setUid(foodStockId);
					foodStockPO.setFoodId(foodId);
					foodStockPO.setStockNum(1000000);
					this.foodStockRepository.save(foodStockPO);
				}
				platformTransactionManager.commit(transactionStatus);
				log.error("------transaction 第{}批 commit isCompleted={},isNewTransaction={},isRollbackOnly={}", q, transactionStatus.isCompleted(), transactionStatus.isNewTransaction(), transactionStatus.isRollbackOnly());
			} catch (Exception e) {
				e.printStackTrace();
				platformTransactionManager.rollback(transactionStatus);
			}
		}	
	}

	public void sendUserCoupon() {
		List<User> userList = this.userDomainService.findUserList();
		for(User user : userList) {
			for (int i = 0; i < 10; i++) {
				for (int j = 1; j < 6; j++) {
					JSONObject json = new JSONObject();
					json.put("userId", user.getUid());
					json.put("categoryId", j);
					json.put("totalAmount", 500 * j);
					json.put("deductAmount", 100 * j);
					json.put("isUsed", 0);
					json.put("createTime", new Date());
					this.couponClientComponent.save(json.toJSONString());
				}
			}
		}
	}
	
}
