package com.chihuo.food.domain.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
    private Long uid;
    private Long userId;
    private Long sellerId;
    private BigDecimal orderAmount;
    private BigDecimal hongbaoAmount;
    private Date createTime;
    
    List<OrderItem> itemList;

}
