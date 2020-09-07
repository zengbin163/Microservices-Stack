package com.chihuo.coupon.interfaces.facade.provider;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chihuo.api.component.CouponComponent;
import com.chihuo.coupon.application.CouponApplicationService;
import com.chihuo.coupon.domain.coupon.entity.UserCoupon;

import cn.hutool.core.net.URLDecoder;

@RestController
public class CouponComponentProvider implements CouponComponent {

	@Autowired
	private CouponApplicationService service;

	@Override
	public Long save(String json) {
		UserCoupon userCoupon = JSONObject.parseObject(URLDecoder.decode(json, Charset.defaultCharset()), UserCoupon.class);
		return this.service.saveUserCoupon(userCoupon);
	}

	@Override
	public boolean update(String json) {
		UserCoupon userCoupon = JSONObject.parseObject(URLDecoder.decode(json, Charset.defaultCharset()), UserCoupon.class);
		return this.service.updateUserCoupon(userCoupon);
	}

	@Override
	public boolean remove(Long uid) {
		return this.service.removeUserCoupon(uid);
	}

	@Override
	public String findUserCouponByUid(Long uid) {
		UserCoupon userCoupon = this.service.findUserCouponByUid(uid);
		return (null == userCoupon) ? null : JSONObject.toJSONString(userCoupon);
	}

	@Override
	public boolean use(Long uid) {
		return this.service.use(uid);
	}

	@Override
	public String findCouponListByCategoryIds(List<Long> ids) {
		List<UserCoupon> couponList = this.service.findCouponListByCategoryIds(ids);
		return (CollectionUtils.isEmpty(couponList) ? null : JSONObject.toJSONString(couponList));
	}

}
