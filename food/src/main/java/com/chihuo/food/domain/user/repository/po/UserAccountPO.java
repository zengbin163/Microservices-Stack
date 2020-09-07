package com.chihuo.food.domain.user.repository.po;

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
public class UserAccountPO {
	
    private Long uid;
    private Long userId;
    private BigDecimal hongbaoTotal;
    private Integer version;
    private Date createTime;
    
}
