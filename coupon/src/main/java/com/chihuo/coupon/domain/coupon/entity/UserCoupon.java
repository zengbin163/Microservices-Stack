package com.chihuo.coupon.domain.coupon.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon implements Comparable<UserCoupon>{
	
    private Long uid;
    private Long userId;
	private Long categoryId;
    private BigDecimal totalAmount;
	private BigDecimal deductAmount;
    private Integer isUsed;
	private Date createTime;
	private Date useTime;
	
	@Override
	public int compareTo(UserCoupon userCoupon) {
		return totalAmount.compareTo(userCoupon.getTotalAmount());
	}
	
}
