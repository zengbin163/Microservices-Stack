package com.chihuo.food.interfaces.facade.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.application.service.FoodApplicationService;
import com.chihuo.food.application.service.OrderApplicationService;
import com.chihuo.food.application.service.SellerApplicationService;
import com.chihuo.food.application.service.UserApplicationService;
import com.chihuo.food.domain.food.entity.FoodStock;
import com.chihuo.food.domain.seller.entity.SellerFood;
import com.chihuo.food.domain.user.entity.ShoppingCat;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.session.value.NoLogin;
import com.chihuo.food.infrastructure.config.ThreadPoolConfiguration;

@RestController
@RequestMapping("/capacity")
public class CapacityApi {
	
	@Autowired
	private FoodApplicationService foodApplicationService;
	@Autowired
	private OrderApplicationService orderApplicationService;
	@Autowired
	private UserApplicationService userApplicationService;
	@Autowired
	private SellerApplicationService sellerApplicationService;
	@Autowired
	private ThreadPoolConfiguration threadPoolConfiguration;
	
	private static final Logger log = LoggerFactory.getLogger(CapacityApi.class);

	/***
	 * capacity calculate
	 * @throws Exception 
	 * 
	 * @apiNote 100  sellers 同时查询订单列表
	 * @apiNote 1000 buyers  同时查询订单详情
	 * @apiNote 200  buyers  同时下单
	 * @apiNote 800  buyers  同时查询商品详情
	 * @apiNote 200  seller  同时维护商品库存
	 * 
	 */
    @RequestMapping(value = "/go", method = RequestMethod.POST)
    @ResponseBody
    @NoLogin
	public Response go(@RequestParam(value = "count") Integer count) throws Exception {
		ThreadPoolTaskExecutor executor = this.threadPoolConfiguration.defaultThreadPool();
		
		for (int i = 0; i < count; i++) {
			SellerQueryOrder sellerQueryOrder = new SellerQueryOrder(orderApplicationService);
			Future<Long> f1 = executor.submit(sellerQueryOrder);
			UserQueryOrder userQueryOrder = new UserQueryOrder(orderApplicationService);
			Future<Long> f2 = executor.submit(userQueryOrder);
			UserCreateOrder userCreateOrder = new UserCreateOrder(userApplicationService);
			Future<Long> f3 = executor.submit(userCreateOrder);
			UserQueryFood userQueryFood = new UserQueryFood(foodApplicationService);
			Future<Long> f4 = executor.submit(userQueryFood);
			SellerUpdateStock sellerUpdateStock = new SellerUpdateStock(foodApplicationService, sellerApplicationService);
			Future<Long> f5 = executor.submit(sellerUpdateStock);
			log.error("订单列表耗时{}毫秒   |   订单详情耗时{}毫秒   |   创建订单耗时{}毫秒   |   商品详情耗时{}毫秒   |   维护库存耗时{}毫秒", f1.get(), f2.get(), f3.get(), f4.get(), f5.get());
		}
		
    	return Response.ok();
	}

}

class SellerQueryOrder implements Callable<Long> {
	
	private OrderApplicationService orderApplicationService;

	public SellerQueryOrder(OrderApplicationService orderApplicationService) {
		this.orderApplicationService = orderApplicationService;
	}

	@Override
	public Long call() throws Exception {
		Long sellerId = ShoppingCat.randomSellerIds(1).get(0);
		long beginTime = System.currentTimeMillis();
		this.orderApplicationService.findSellerOrderList(0, 100, sellerId);
		long endTime = System.currentTimeMillis();
		return (endTime-beginTime);
	}
}

class UserQueryOrder implements Callable<Long> {
	
	private OrderApplicationService orderApplicationService;

	public UserQueryOrder(OrderApplicationService orderApplicationService) {
		this.orderApplicationService = orderApplicationService;
	}
	
	@Override
	public Long call() throws Exception {
		Long orderId = ShoppingCat.randomOrderIds(1).get(0);
		long beginTime = System.currentTimeMillis();
		this.orderApplicationService.findOrderById(orderId);
		long endTime = System.currentTimeMillis();
		return (endTime-beginTime);
	}
}

class UserCreateOrder implements Callable<Long> {
	
	private UserApplicationService userApplicationService;

	public UserCreateOrder(UserApplicationService userApplicationService) {
		this.userApplicationService = userApplicationService;
	}
	
	@Override
	public Long call() throws Exception {
		Long userId = ShoppingCat.userId();
		Long couponId = ShoppingCat.couponId();
		List<Long> sellerFoodIds = ShoppingCat.randomSellerFoodIds(5);
		Map<Long, Integer> shoppingFoodList = new HashMap<>();
		for(Long sellerFoodId : sellerFoodIds) {
			shoppingFoodList.put(sellerFoodId, 20);
		}
		long beginTime = System.currentTimeMillis();
		this.userApplicationService.createSingleOrder(userId, couponId, shoppingFoodList);
		long endTime = System.currentTimeMillis();
		return (endTime-beginTime);
	}
}

class UserQueryFood implements Callable<Long> {
	
	private FoodApplicationService foodApplicationService;
	
	public UserQueryFood(FoodApplicationService foodApplicationService) {
		this.foodApplicationService = foodApplicationService;
	}
	
	@Override
	public Long call() throws Exception {
		Long foodId = ShoppingCat.randomFoodIds(1).get(0);
		long beginTime = System.currentTimeMillis();
		this.foodApplicationService.findById(foodId);
		long endTime = System.currentTimeMillis();
		return (endTime-beginTime);
	}
}

class SellerUpdateStock implements Callable<Long> {
	
	private FoodApplicationService foodApplicationService;
	private SellerApplicationService sellerApplicationService;
	
	public SellerUpdateStock(FoodApplicationService foodApplicationService, SellerApplicationService sellerApplicationService) {
		this.foodApplicationService = foodApplicationService;
		this.sellerApplicationService = sellerApplicationService;
	}
	
	@Override
	public Long call() throws Exception {
		Long sellerFoodId = ShoppingCat.randomSellerFoodIds(1).get(0);
		SellerFood sellerFood = this.sellerApplicationService.findSellerFoodById(sellerFoodId);
		FoodStock foodStock = this.foodApplicationService.findFoodStockByFoodSellerId(sellerFood.getFoodId(), sellerFood.getSellerId());
		long beginTime = System.currentTimeMillis();
		this.foodApplicationService.updateFoodStock(sellerFood.getFoodId(), sellerFood.getSellerId(), foodStock.getStockNum() + 10000);
		long endTime = System.currentTimeMillis();
		return (endTime-beginTime);
	}
}
