package com.chihuo.food.domain.category.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private Integer categoryTypeId;
    private Integer parentId;
    private String categoryName;
    private Date createTime;
    private Date updateTime;
}
