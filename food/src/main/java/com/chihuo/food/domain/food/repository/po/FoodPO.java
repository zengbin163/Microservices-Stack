package com.chihuo.food.domain.food.repository.po;

import java.math.BigDecimal;
import java.util.Date;

import com.chihuo.food.domain.category.repository.po.CategoryPO;
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
@TableName(value = "food")
public class FoodPO {
	@TableId(value = "uid")
    private Long uid;
    private CategoryPO parentCategory;
    private CategoryPO category;
    private String foodName;
    private String foodPic;
    private String foodInfo;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
