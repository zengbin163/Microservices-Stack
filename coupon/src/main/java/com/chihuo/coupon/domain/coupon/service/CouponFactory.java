package com.chihuo.coupon.domain.coupon.service;

import org.springframework.stereotype.Service;

import com.chihuo.coupon.domain.coupon.entity.UserCoupon;
import com.chihuo.coupon.domain.coupon.repository.po.UserCouponPO;

import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;

@Service
public class CouponFactory {

	public UserCoupon createUserCoupon(UserCouponPO po) {
		if (null == po) {
			return null;
		}
		BeanCopier<UserCoupon> beanCopier = BeanCopier.create(po, new UserCoupon(), CopyOptions.create());
		return beanCopier.copy();
	}
	
	public UserCouponPO createUserCouponPO(UserCoupon userCoupon) {
		if (null == userCoupon) {
			return null;
		}
		BeanCopier<UserCouponPO> beanCopier = BeanCopier.create(userCoupon, new UserCouponPO(), CopyOptions.create());
		return beanCopier.copy();
	}

}
