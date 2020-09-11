package com.chihuo.food.domain.user.repository.po;

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
@TableName(value = "f_user_account")
public class UserAccountPO {
	@TableId(value = "uid")
    private Long uid;
    private Long userId;
    private BigDecimal hongbaoTotal;
    private Integer version;
    private Date createTime;
    
}
