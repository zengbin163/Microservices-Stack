package com.chihuo.api.component;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderItemComponent {
	
	@RequestMapping("/orderItem/save")
	public Long save(@RequestParam("json") String json);

	@RequestMapping("/orderItem/findOrderItemList")
	public String findOrderItemList(@RequestParam("current") Integer current, @RequestParam("size") Integer size);

	@RequestMapping("/orderItem/findOrderItemCount")
	public Integer findOrderItemCount();
	
	@RequestMapping("/order/updateOrderItemBatch")
	public Integer updateOrderItemBatch(@RequestParam("json") String json);

}
