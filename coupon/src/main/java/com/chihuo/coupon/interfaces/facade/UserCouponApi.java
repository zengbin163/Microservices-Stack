package com.chihuo.coupon.interfaces.facade;


import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chihuo.coupon.application.CouponApplicationService;
import com.chihuo.coupon.domain.coupon.entity.UserCoupon;
import com.chihuo.coupon.domain.coupon.entity.valueobject.Use;


@Controller
@RequestMapping("/userCoupon")
public class UserCouponApi {
	
    @Resource
    private CouponApplicationService service;

    @RequestMapping("/add")
    @ResponseBody
    public Long add(){
    	UserCoupon coupon = new UserCoupon();
    	coupon.setUserId(1L);
    	coupon.setCategoryId(2L);
    	coupon.setTotalAmount(new BigDecimal(1000));
    	coupon.setDeductAmount(new BigDecimal(100));
    	coupon.setIsUsed(Use.NOT_USE.getIsUse());
    	coupon.setCreateTime(new Date());
    	return this.service.saveUserCoupon(coupon);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public boolean edit(@RequestParam(value = "uid") Long uid){
    	UserCoupon coupon = new UserCoupon();
    	coupon.setUid(uid);
    	coupon.setUserId(3L);
    	coupon.setCategoryId(5L);
    	coupon.setTotalAmount(new BigDecimal(2000));
    	coupon.setDeductAmount(new BigDecimal(200));
    	coupon.setIsUsed(1);
    	coupon.setUseTime(new Date());
    	return this.service.updateUserCoupon(coupon);
    }
    
    @RequestMapping("/findUserCouponByUid")
    @ResponseBody
    public UserCoupon findUserCouponByUid(@RequestParam(value = "uid") Long uid){
    	 return this.service.findUserCouponByUid(uid);
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestParam(value = "uid") Long uid){
    	return this.service.removeUserCoupon(uid);
    }
    
}
