package com.chihuo.coupon.domain.coupon.repository.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_coupon")
public class UserCouponPO implements Serializable {
	
	private static final long serialVersionUID = 188064912533869414L;

	@Id
	@Field("_id")
	@JsonIgnore
    private String id;
    
	@Field("uid")
    private Long uid;
	@Field("user_id")
    private Long userId;
	@Field("category_id")
	private Long categoryId;
	@Field("total_amount")
    private BigDecimal totalAmount;
	@Field("deduct_amount")
	private BigDecimal deductAmount;
	@Field("is_used")
    private Integer isUsed;
	@Field("create_time")
	private Date createTime;
	@Field("use_time")
	private Date useTime;
    
}
