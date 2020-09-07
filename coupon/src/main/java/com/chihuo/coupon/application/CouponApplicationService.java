package com.chihuo.coupon.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.coupon.domain.coupon.entity.UserCoupon;
import com.chihuo.coupon.domain.coupon.service.CouponDomainService;

@Service
public class CouponApplicationService {
	
	@Autowired
	private CouponDomainService couponDomainService;
	
	public Long saveUserCoupon(UserCoupon userCoupon) {
		return this.couponDomainService.saveUserCoupon(userCoupon);
	}

    public boolean updateUserCoupon(UserCoupon userCoupon) {
    	return this.couponDomainService.updateUserCoupon(userCoupon);
    }
    
    public boolean removeUserCoupon(Long uid) {
    	return this.couponDomainService.removeUserCoupon(uid);
    }
    
    public UserCoupon findUserCouponByUid(Long uid) {
    	return this.couponDomainService.findUserCouponByUid(uid);
    }
    
    public boolean use(Long uid) {
    	return this.couponDomainService.use(uid);
    }

	public List<UserCoupon> findCouponListByCategoryIds(List<Long> ids) {
		return this.couponDomainService.findCouponListByCategoryIds(ids);
	}    
}
