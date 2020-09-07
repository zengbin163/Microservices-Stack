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
public class CategoryType {
    private Integer id;
    private String typeName;
    private Date createTime;
    private Date updateTime;
}
