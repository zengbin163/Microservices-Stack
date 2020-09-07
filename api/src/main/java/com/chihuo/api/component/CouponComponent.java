package com.chihuo.api.component;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CouponComponent {
	
	@RequestMapping("/coupon/save")
	public Long save(@RequestParam("json") String json);
	
	@RequestMapping("/coupon/update")
	public boolean update(@RequestParam("json") String json);

	@RequestMapping("/coupon/use")
	public boolean use(@RequestParam("uid") Long uid);
	
	@RequestMapping("/coupon/remove")
	public boolean remove(@RequestParam("uid") Long uid);
	
	@RequestMapping("/coupon/findUserCouponByUid")
	public String findUserCouponByUid(@RequestParam("uid") Long uid);

	@RequestMapping("/coupon/findCouponListByCategoryIds")
	public String findCouponListByCategoryIds(@RequestParam("ids") List<Long> ids);
	
}
