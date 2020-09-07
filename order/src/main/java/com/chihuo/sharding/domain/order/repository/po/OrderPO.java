package com.chihuo.sharding.domain.order.repository.po;

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
public class OrderPO {
	
    private Long uid;
    private Long userId;
    private Long sellerId;
    private BigDecimal orderAmount;
    private BigDecimal hongbaoAmount;
    private Date createTime;
    
    List<OrderItemPO> itemList;
    
}
