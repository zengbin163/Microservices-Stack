package com.chihuo.food.domain.order.entity;

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
public class OrderItem {
	
    private Long uid;
    private Long userId;
    private Long sellerId;
    private Long orderId;
    private Long foodId;
    private String foodName;
    private BigDecimal orderItemAmount;
    private Integer orderItemSum;
    private Long promotionId;
    private BigDecimal promotionAmount;
    private Long couponId;
    private BigDecimal couponAmount;
    private Date createTime;
    
}
