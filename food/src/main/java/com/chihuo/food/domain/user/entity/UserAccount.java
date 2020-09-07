package com.chihuo.food.domain.user.entity;

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
public class UserAccount {
	
	public static final BigDecimal DEFAULT_DEDUCT_AMOUNT = new BigDecimal(100);
	
    private Long uid;
    private Long userId;
    private BigDecimal hongbaoTotal;
    private Integer version;
    private Date createTime;
    
}
