package com.chihuo.sharding.domain.order.repository.po;

import java.math.BigDecimal;
import java.util.Date;

import com.codingapi.txlcn.tc.annotation.TableId;
import com.codingapi.txlcn.tc.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "f_order_item")
public class OrderItemPO {
	@TableId(value = "uid")
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
