package com.chihuo.api.component;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderComponent {
	
	@RequestMapping("/order/save")
	public Long save(@RequestParam("json") String json);

	@RequestMapping("/order/findOrderList")
	public String findOrderList(@RequestParam("current") Integer current, @RequestParam("size") Integer size);

	@RequestMapping("/order/findOrderCount")
	public Integer findOrderCount();
	
	@RequestMapping("/order/findUserOrderList")
	public String findUserOrderList(@RequestParam("current") Integer current, @RequestParam("size") Integer size, @RequestParam("userId") Long userId);

	@RequestMapping("/order/findSellerOrderList")
	public String findSellerOrderList(@RequestParam("current") Integer current, @RequestParam("size") Integer size, @RequestParam("sellerId") Long sellerId);
	
	@RequestMapping("/order/findOrderById")
	public String findOrderById(@RequestParam("uid") Long uid);
	
	@RequestMapping("/order/refreshSellerIdOrderList")
	public String refreshSellerIdOrderList(@RequestParam("userId") Long userId);

	@RequestMapping("/order/countRefreshSellerIdOrderList")
	public Integer countRefreshSellerIdOrderList(@RequestParam("userId") Long userId);

	@RequestMapping("/order/updateOrderBatch")
	public Integer updateOrderBatch(@RequestParam("json") String json);
	
}
