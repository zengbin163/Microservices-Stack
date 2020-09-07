package com.chihuo.coupon.domain.coupon.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chihuo.coupon.domain.coupon.entity.UserCoupon;
import com.chihuo.coupon.domain.coupon.repository.facade.CouponRepository;
import com.chihuo.coupon.domain.coupon.repository.po.UserCouponPO;
import com.chihuo.coupon.infrastructure.consumer.UidClientComponent;

@Service
public class CouponDomainService {

    @Resource
    private UidClientComponent uidClientComponent;
	@Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponFactory factory;

    public Long saveUserCoupon(UserCoupon userCoupon) {
    	Long userCouponId = this.uidClientComponent.getUID();
    	userCoupon.setUid(userCouponId);
    	return this.couponRepository.save(this.factory.createUserCouponPO(userCoupon));
    }
    
    public boolean updateUserCoupon(UserCoupon userCoupon) {
    	return this.couponRepository.update(this.factory.createUserCouponPO(userCoupon));
    }
    
    public boolean removeUserCoupon(Long uid) {
    	return this.couponRepository.remove(uid);
    }
    
    public UserCoupon findUserCouponByUid(Long uid) {
    	UserCouponPO po = this.couponRepository.findUserCouponByUid(uid);
    	return this.factory.createUserCoupon(po);
    }
    
    public boolean use(Long uid) {
    	return this.couponRepository.use(uid);
    }

	public List<UserCoupon> findCouponListByCategoryIds(List<Long> ids) {
		List<UserCouponPO> poList = this.couponRepository.findCouponListByCategoryIds(ids);
		List<UserCoupon> couponList = new ArrayList<>();
		for(UserCouponPO po : poList) {
			couponList.add(this.factory.createUserCoupon(po));
		}
		return couponList;
	}

}
