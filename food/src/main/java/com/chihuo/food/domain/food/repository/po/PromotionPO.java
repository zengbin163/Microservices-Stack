package com.chihuo.food.domain.food.repository.po;

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
public class PromotionPO {
	
    private Long uid;
    private Long categoryId;
    private Integer promotionRule;
    private BigDecimal totalAmount;
    private BigDecimal deductAmount;
    private Date createTime;
    
    private Long foodId;
}
