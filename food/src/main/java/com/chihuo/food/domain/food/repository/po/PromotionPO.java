package com.chihuo.food.domain.food.repository.po;

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
@TableName(value = "f_promotion")
public class PromotionPO {
	@TableId(value = "uid")
    private Long uid;
    private Long categoryId;
    private Integer promotionRule;
    private BigDecimal totalAmount;
    private BigDecimal deductAmount;
    private Date createTime;
    
    private Long foodId;
}
