package com.chihuo.coupon.domain.coupon.repository.facade;

import java.util.List;

import com.chihuo.coupon.domain.coupon.repository.po.UserCouponPO;

public interface CouponRepository {
	
	Long save(UserCouponPO userCouponPO);
	
    boolean update(UserCouponPO userCouponPO);
    
    boolean remove(Long uid);

    UserCouponPO findUserCouponByUid(Long uid);
    
	boolean use(Long uid);

	List<UserCouponPO> findCouponListByCategoryIds(List<Long> ids);

}
